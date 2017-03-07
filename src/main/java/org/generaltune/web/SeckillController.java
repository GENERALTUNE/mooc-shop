package org.generaltune.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.generaltune.dto.Exposer;
import org.generaltune.dto.SeckillExecution;
import org.generaltune.dto.SeckillResult;
import org.generaltune.entity.Seckill;
import org.generaltune.enums.SeckillStatEnum;
import org.generaltune.exception.RepeatKillException;
import org.generaltune.exception.SeckillCloseException;
import org.generaltune.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhumin on 2016/11/16.
 */
@Controller //@Service @Component
@RequestMapping("/seckill")  //url:/模块/资源/{id}/细分   /seckill/list
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    /**
     * 秒杀列表页
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public  String list(Model model) {
        //获取列表页
        List<Seckill> list = seckillService.getSeckillList(0, 10);
        model.addAttribute("list", list);
        //list.jsp + model = ModelAndView
        return "list";
    }

    /**
     * 秒杀详情页
     * @param seckillId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if(seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if(seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";

    }

    /**
     * 删除秒杀
     * @param seckillId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{seckillId}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("seckillId") Long seckillId, Model model) {
        try {
            if (seckillId == null) {
                return "redirect:/seckill/list";
            }
            int count  = seckillService.deleteById(seckillId);
            logger.info("删除的秒杀ID：" + seckillId + "操作状态：" + count);
            if (count == 1) {
                return "redirect:/seckill/list";
            } else {
                return "redirect:404";
            }
        } catch (Exception e) {
            logger.error("删除失败", e.getMessage(),e);
            return "redirect:/seckill/list";
        }
    }


    /**
     * 增加一条秒杀
     * @param name
     * @param number
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public String insert(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "number", required = true) int number,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
            //yyyy-MM-dd HH:mm:ss
//            @RequestParam(value = "startTime", required = true ) Date startTime,
//            @RequestParam(value = "endTime", required = true ) Date endTime,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime
    ) {
        try {
            if (name != null && number != 0 && startTime != null && endTime != null) {
                Seckill seckill = new Seckill();
                seckill.setName(name);
                seckill.setNumber(number);
                seckill.setStartTime(startTime);
                seckill.setEndTime(endTime);
                seckill.setCreateTime(new Date());
                int count = seckillService.insertSeckill(seckill);
                logger.info("hello:" + count);
                if (count == 1) {
                    return "redirect:/seckill/list";
                }else {
                    return "add";
                }
            }else {
                return "add";
            }
        }catch (Exception e) {
            logger.info(e.getMessage(),e +">>>>>>");
            return "add";
        }
    }


    /**
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "add";
    }

    /**
     * velocity 实验
     */
    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public String template() { return "template";}

    /**
     * 暴露秒杀地址
     * @param seckillId
     * @return
     */
    //ajax json
    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST,
        produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer>  exporter(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }


    /**
     * 执行秒杀
     * @param seckillId
     * @param md5
     * @param phone
     * @return
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
        method = RequestMethod.POST,
        produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId, @PathVariable("md5") String md5, @CookieValue(value = "killPhone") Long phone) {
        //spirngmvc valid
        if (phone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        SeckillResult<SeckillExecution> result;

        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExecution>(true, execution);
        }catch (RepeatKillException e) {
            SeckillExecution execution = new SeckillExecution( SeckillStatEnum.REPEATE_KILL, seckillId);
            return new SeckillResult<SeckillExecution>(true, execution);
        }catch (SeckillCloseException e) {
            SeckillExecution execution = new SeckillExecution( SeckillStatEnum.END, seckillId);
            return new SeckillResult<SeckillExecution>(true, execution);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            //TODO
            SeckillExecution execution = new SeckillExecution( SeckillStatEnum.INNER_ERROR, seckillId);
            return new SeckillResult<SeckillExecution>(false, execution);
        }
    }



    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time () {

        Date now = new Date();
        return new SeckillResult(true, now.getTime());
    }
}
