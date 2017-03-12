package org.generaltune.util;

import org.springframework.util.DigestUtils;

/**
 * Created by zhumin on 2017/3/9.
 */
public class StringUtil {

    //md5盐值字符串,用于混淆MD5
    static final String slat = "fsalfjsdljfldskjflkfdsjgioreofewe243242o3iosjdfo324214pj23423";


    public static  String getMD5(String  source) {
        String base = source + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

}
