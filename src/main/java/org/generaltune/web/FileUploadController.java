package org.generaltune.web;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by zhumin on 2016/12/18.
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response ) {
        try {
            String ContextPath = request.getServletContext().getRealPath("/");
            logger.info("项目路径是：" + ContextPath);
            FileUtils.writeByteArrayToFile(new File(ContextPath +file.getOriginalFilename()),file.getBytes());
//            System.
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }


//    @PostMapping("/form")
//    public String handleFormUpload(@RequestParam("name") String name,
//                                   @RequestParam("file") MultipartFile file) {
//        if (!file.isEmpty()) {
//            byte[] bytes = file.getBytes();
//// store the bytes somewhere
//            return "redirect:uploadSuccess";
//        }
//        return "redirect:uploadFailure";
//    }
}
