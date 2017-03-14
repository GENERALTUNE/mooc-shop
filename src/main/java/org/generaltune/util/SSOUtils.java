package org.generaltune.util;

import org.apache.commons.codec.binary.Base64;
import org.generaltune.constants.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhumin on 2017/3/12.
 */
public class SSOUtils {


    private final static String SHOP_USERID_COOKIE_KEY = "L00001";   //cookie name
    private final static String SHOP_USERVALIDATE_COOKIE_KEY = "V00001";   //cookie name
    private final static String COOKIE_ENCODE_KEY = "@#$fde!23^&%56&&%%$$";  //MD5 key
    private final static long DEFAULT_VALID_TIME = 3 * 24 * 60 * 60 * 1000; //3天,默认有效期

    /**
     * 判断用户是否已登录调用此方法
     * 根据request获取sso的用户邮箱
     * 若返回null则说明用户未登录,须让用户先登录
     *
     * @param request
     * @return 用户邮箱
     */
    public static String getSsoUsername(HttpServletRequest request) {
        //1.获取用户cookie
        String ticket = getTicket(request);
        //2.校验
        if (ticket != null) {
            return authenticateTicket(ticket);
        }
        return null;
    }

    public static boolean validateCookie(HttpServletRequest request) {
        String ticket = getValidateCookie(request);
        if (ticket == null)
            return false;
        return validateCookie(request, ticket);
    }

    private static boolean validateCookie(HttpServletRequest request,
                                          String ticket) {
        // TODO Auto-generated method stub
        if (ticket.equals(makeCookie(request)))
            return true;
        return false;
    }

    public static void setValidateCookie(HttpServletRequest request, HttpServletResponse response, Long validTime) {
        if (validTime == null) {
            validTime = DEFAULT_VALID_TIME;
        }
        String cookie = makeCookie(request);
        setCookie(request, response, SHOP_USERVALIDATE_COOKIE_KEY, cookie, validTime.intValue() / 1000, true);
    }

    private static String makeCookie(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        String ip = request.getRemoteAddr();
        String oriMd5 = ip + ua + COOKIE_ENCODE_KEY;  //MD5原值
        String calMd5 = MD5(oriMd5, "utf-8");  //MD5加密
        return calMd5;
    }


    /**
     * 登录成功调用此方法
     * 设置sso cookie
     *
     * @param request
     * @param response
     * @param username  用户邮箱
     * @param validTime 登录有效期，可以为空
     */
    public static void setSsoTicket(HttpServletRequest request, HttpServletResponse response, String username, Long validTime) {
        //1.若无有效期则使用默认，3天
        if (validTime == null) {
            validTime = DEFAULT_VALID_TIME;
        }
        //2.计算ticket
        String cookie = genTicket(username, validTime);
        //3.重新设置cookie
//        deleteCookie(request,response,LEGO_USERID_COOKIE_KEY,true);
//        setCookie(request,response,LEGO_USERID_COOKIE_KEY,cookie,validTime / 1000,true);
        addHttpOnly(request, response, SHOP_USERID_COOKIE_KEY, cookie, validTime / 1000, true);
    }

    /**
     * 退出登录时调用此方法
     * 删除sso cookie
     *
     * @param request
     * @param response
     */
    public static void deleteSsoTicket(HttpServletRequest request, HttpServletResponse response) {
        deleteCookie(request, response, SHOP_USERID_COOKIE_KEY, true);
        // deleteCookie(request, response, Constants.AUTH_COOKIE_KEY, true);
    }

    /**
     * 每次操作完调用此方法
     * 设置sso cookie
     *
     * @param request
     * @param response
     * @param username  用户邮箱
     * @param validTime 登录有效期，可以为空
     */
    public static void refreshSsoTicket(HttpServletRequest request, HttpServletResponse response, String username, Long validTime) {
        //1.若无有效期则使用默认，3天
        if (validTime == null) {
            validTime = DEFAULT_VALID_TIME;
        }
        //2.计算ticket
        String cookie = genTicket(username, validTime);
        //3.重新设置cookie
        addHttpOnly(request, response, SHOP_USERID_COOKIE_KEY, cookie, validTime / 1000, true);
    }

    /**
     * 验证cookie值是否有效
     * 有效则返回用户邮箱，无效则返回null
     *
     * @param ticket
     * @return
     */
    private static String authenticateTicket(String ticket) {
        String username = null;
        try {
            //1.Base64解密 cookie值
            byte[] ticketByte = base64Decode(ticket);
            String decodeTicket = new String(ticketByte);

            //2.解析解压完的值,并校验
            String[] values = decodeTicket.split(":", 3);
            if (values.length != 3) {   //判断数组大小
                return null;
            }
            //判断有效期
            Long validTime = Long.parseLong(values[1]);
            if (validTime < System.currentTimeMillis()) {
                return null;
            }
            username = values[0];
            //判断MD5是否一致
            String encodeMd5 = values[2];
            //获取cookie中的MD5加密值
            String oriMd5 = username + validTime + COOKIE_ENCODE_KEY;
            //MD5原值
            String calMd5 = MD5(oriMd5, "utf-8");  //计算MD5加密
            if (!encodeMd5.equals(calMd5)) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return username;
    }

    /**
     * 生成cookie值
     *
     * @param username  用户名
     * @param validTime cookie有效期
     * @return
     */
    private static String genTicket(String username, Long validTime) {
        //1.从当前时间算有效期
        validTime = System.currentTimeMillis() + validTime;

        //2.计算cookie值
        String oriMd5 = username + validTime + COOKIE_ENCODE_KEY;  //MD5原值
        String calMd5 = MD5(oriMd5, "utf-8");  //MD5加密
        String value = username + ":" + validTime + ":" + calMd5;  //构建Base64原串
        String ticket = base64Encode(value.getBytes());     //Base64加密

        return ticket;
    }


    /**
     * 获取用户cookie
     *
     * @param request
     * @return
     */
    private static String getTicket(HttpServletRequest request) {
        // 支持从参数中取L00001，如果没有则从Cookie中取
        String cookieKey = request.getParameter(SHOP_USERID_COOKIE_KEY);
        if (cookieKey == null || cookieKey.trim().length() == 0) {
            cookieKey = getCookieValue(request, SHOP_USERVALIDATE_COOKIE_KEY);
        }
        return cookieKey;
        //return getCookieValue(request, LEGO_USERID_COOKIE_KEY);
    }

    /**
     * 防劫持cookie
     *
     * @param request
     * @return
     */
    private static String getValidateCookie(HttpServletRequest request) {
        String P00001 = request.getParameter(SHOP_USERVALIDATE_COOKIE_KEY);
        if (P00001 == null || P00001.trim().length() == 0) {
            P00001 = getCookieValue(request, SHOP_USERVALIDATE_COOKIE_KEY);
        }
        return P00001;
    }

    /////------------------------------加密操作 start--------------------------------------------------/////

    /**
     * Base64编码.
     *
     * @param input 需要编码的字节数组
     * @return 编码后的字符串
     */
    private static String base64Encode(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }

    /**
     * Base64解码.
     *
     * @param input 需要解码的字符串
     * @return 转码后的字节数组
     */
    private static byte[] base64Decode(String input) {
        return Base64.decodeBase64(input);
    }

    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * MD5加密, 默认UTF-8
     *
     * @param text    需要加密的文本
     * @param charset 加密的编码格式
     * @return 加密串
     */
    private static String MD5(String text, String charset) {
        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "System doesn't support MD5 algorithm.");
        }
        try {
            msgDigest.update(text.getBytes(charset));

        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(
                    "System doesn't support your  EncodingException.");
        }
        byte[] bytes = msgDigest.digest();
        String md5Str = new String(encodeHex(bytes));
        return md5Str;
    }

    private static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }

    /**
     * 设置COOKIE
     *
     * @param request        HttpServletRequest
     * @param response       HttpServletResponse
     * @param name           Cookie的名称
     * @param value          Cookie的值
     * @param maxAge         有效时长
     * @param all_sub_domain 域名
     */
    private static void setCookie(HttpServletRequest request, HttpServletResponse response, String name,
                                  String value, int maxAge, boolean all_sub_domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        if (all_sub_domain) {
            String serverName = request.getServerName();
            String domain = getDomainOfServerName(serverName);
            if (domain != null && domain.indexOf('.') != -1) {
                cookie.setDomain('.' + domain);
            }
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 添加http-only cookie without jee6 目前只支持一个cookie 调用此方法
     */
    public static void addHttpOnly(HttpServletRequest request, HttpServletResponse response, String name, String value, long maxAge, boolean all_sub_domain) {
        // be careful overwriting: JSESSIONID may have been set with other flags
        StringBuilder sb = new StringBuilder(name).append("=").append(value).append(";");
        if (all_sub_domain) {
            String serverName = request.getServerName();
            String domain = getDomainOfServerName(serverName);
            if (domain != null && domain.indexOf('.') != -1) {
                sb.append("Domain=").append('.' + domain).append(";");
            }
        }
        sb.append("Path=/;").append("Max-Age=").append(maxAge).append(";HTTPOnly");
        response.setHeader("SET-COOKIE", sb.toString());
    }


    /**
     * 删除cookie
     *
     * @param request        HttpServletRequest
     * @param response       HttpServletResponse
     * @param name           Cookie的名称
     * @param all_sub_domain 域名
     */
    private static void deleteCookie(HttpServletRequest request,
                                     HttpServletResponse response, String name, boolean all_sub_domain) {
        addHttpOnly(request, response, name, "", 0, all_sub_domain);
    }

    /**
     * 获取用户访问URL中的根域名
     * 例如: www.dlog.cn -> dlog.cn
     *
     * @param host 域名
     * @return 根域名
     */
    private static String getDomainOfServerName(String host) {
        if (isIPAddr(host))
            return null;
        String[] names = org.apache.commons.lang.StringUtils.split(host, '.');
        int len = names.length;
        if (len == 1) return null;
        if (len == 3) {
            return makeup(names[len - 2], names[len - 1]);
        }
        if (len > 3) {
            String dp = names[len - 2];
            if (dp.equalsIgnoreCase("com") || dp.equalsIgnoreCase("gov") || dp.equalsIgnoreCase("net") || dp.equalsIgnoreCase("edu") || dp.equalsIgnoreCase("org"))
                return makeup(names[len - 3], names[len - 2], names[len - 1]);
            else
                return makeup(names[len - 2], names[len - 1]);
        }
        return host;
    }

    /**
     * 判断字符串是否是一个IP地址
     *
     * @param addr 字符串
     * @return true:IP地址，false：非IP地址
     */
    private static boolean isIPAddr(String addr) {
        if (org.apache.commons.lang.StringUtils.isEmpty(addr))
            return false;
        String[] ips = org.apache.commons.lang.StringUtils.split(addr, '.');
        if (ips.length != 4)
            return false;
        try {
            int ipa = Integer.parseInt(ips[0]);
            int ipb = Integer.parseInt(ips[1]);
            int ipc = Integer.parseInt(ips[2]);
            int ipd = Integer.parseInt(ips[3]);
            return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0
                    && ipc <= 255 && ipd >= 0 && ipd <= 255;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 把字符串数组串起来，中间加“.”，用于域名的串接
     *
     * @param ps 字符串数组
     * @return 字符串
     */
    private static String makeup(String... ps) {
        StringBuilder s = new StringBuilder();
        for (int idx = 0; idx < ps.length; idx++) {
            if (idx > 0)
                s.append('.');
            s.append(ps[idx]);
        }
        return s.toString();
    }

    /**
     * 获取COOKIE的值
     *
     * @param request HttpServletRequest
     * @param name    Cookie的名称
     * @return CookieValue or null
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie ck : cookies) {
            if (org.apache.commons.lang.StringUtils.equalsIgnoreCase(name, ck.getName()))
                return ck.getValue();
        }
        return null;
    }
}
