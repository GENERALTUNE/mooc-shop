package org.generaltune.Uitl;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.*;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhumin on 2017/8/25.
 */
public class TestGuava {


    @Test
    public void cache() {
        List<String> names = Lists.newArrayList();
        names.add("iamzhongyong");
        names.add("bixiao.zy");
        names.add("李易峰");
        StringBuilder sb = new StringBuilder();
        String rs = Joiner.on("--").appendTo(sb, names).toString();
        System.out.println(rs);

        String s = "dd  sfsfs  , dsfsf,ssfdfsdffsdfsf.sdfsfs,msfds";
        for(String name : Splitter.on(",").trimResults().split(s)){
            System.out.println(name);
        }


        try {
                LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                        //设置大小，条目数
                        .maximumSize(20)
                        //设置失效时间，创建时间
                        .expireAfterWrite(20, TimeUnit.SECONDS)
                        //设置时效时间，最后一次被访问
                        .expireAfterAccess(20, TimeUnit.HOURS)
                        //移除缓存的监听器
                        .removalListener(new RemovalListener<String, String>() {
                            public void onRemoval(RemovalNotification<String, String> notification) {
                                System.out.println("有缓存数据被移除了");
                            }})
                        //缓存构建的回调
                        .build(new CacheLoader<String, String>(){//加载缓存
                            @Override
                            public String load(String key) throws Exception {
                                return key + "-" + "iamzhongyong";
                            }
                        });

                System.out.println(cache.get("王厂长") + "获取name缓存！");
                cache.invalidateAll();
                System.out.println(" after invalidateAll method executed 获取name缓存！");
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
