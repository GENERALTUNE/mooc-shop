package org.generaltune.util;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.http.HttpClient;

import javax.servlet.http.Cookie;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by zhumin on 2017/7/23.
 */
public class NetUtils {
    protected static Logger log = LoggerFactory.getLogger(NetUtils.class);
    // 定义默认编码
    private final static String DEFAULT_ENCODING = "utf-8";
    private static String SERVER_DOMAIN = ".iqiyi.com";

    /**
     * getTitle:
     * 获得网页的title
     *
     * @param page 网页
     * @author lichunping
     * @since 1.0.0
     */
    public static String getTitle(String page) {
        try {
            //  Pattern p = Pattern.compile("<title>([^<]*)</title>");
            Pattern p = Pattern.compile("<title>(.*)</title>", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(page);
            while (m.find()) {
                return m.group(1).trim();
            }
        } catch (Exception e) {
            LoggerUtils.error(log, "getTitle " + page + " error", e);
            return "error:title Pattern.compile error";
        }
        return "";
    }

    /**
     * getContentType:
     * 获得网页内容上的ContentType
     *
     * @param page 网页
     * @author lichunping
     * @since 1.0.0
     */
    public static String getContentType(String page) {
        String charset = "";
        try {
            Pattern p = Pattern.compile("<meta\\s[^>]*?charset=[\"]{0,1}([a-z|A-Z|0-9]*[\\-]*[0-9]*)[.]*", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(page);
            while (m.find()) {
                charset = m.group(1);
            }
        } catch (Exception e) {
            LoggerUtils.error(log, "getContentType " + page + " error", e);
            return "error:contentType Pattern.compile error";
        }
        return charset;
    }

    /**
     * removeSuffix:
     * 截取keyword与description引号后的内容
     *
     * @param source 内容
     * @author lichunping
     * @since 1.0.0
     */
    protected static String removeSuffix(String source) {
        String symbol = "\"";
        int index = source.lastIndexOf(symbol);
        if (index != -1) {
            source = source.substring(0, index);
        }
        return source;
    }


    /**
     * getKeyword:
     * 获得网页的keyword
     *
     * @param page 网页
     * @author lichunping
     * @since 1.0.0
     */
    public static String getKeywords(String page) {
        String keyword = "";
        try {
            String kwPattern = "<meta\\s[^>]*name=[\"]{0,1}keywords[\"]{0,1}\\s[^<>]*content=\"([^<>]*)\\s*?>";
            Pattern p = Pattern.compile(kwPattern, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(page);
            while (m.find()) {
                keyword = m.group(1).trim();
            }
            keyword = removeSuffix(keyword);
        } catch (Exception e) {
            LoggerUtils.error(log, "getKeywords " + page + " error", e);
            return "error:keywords Pattern.compile error";
        }
        return keyword;
    }


    /**
     * getDescription:
     * 获得网页的description
     *
     * @param page 网页
     * @author lichunping
     * @since 1.0.0
     */
    public static String getDescription(String page) {
        String desc = "";
        try {
            String descPattern = "<meta\\s[^>]*name=[\"]{0,1}description[\"]{0,1}\\s[^<>]*content=\"([^<>]*)\\s*?>";
            Pattern p = Pattern.compile(descPattern, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(page);
            while (m.find()) {
                desc = m.group(1);
            }
            desc = removeSuffix(desc);
        } catch (Exception e) {
            LoggerUtils.error(log, "getDescription " + page + " error", e);
            return "error:description Pattern.compile error";
        }
        return desc;
    }


    /**
     * getContentByUrl:
     * 根据url返回查询内容，使用httpClient包，见: http://hc.apache.org/
     * 不区分编码，按照用户客户端编码请求
     *
     * @param url 网址
     * @return 网页内容
     * @author lichunping
     * @since 1.0.0
     */
    public static String getPageByUrl(String url) throws Exception {
        String content;
        if (!StringUtils.isEmpty(url)) {
//            HttpClient httpclient = new DefaultHttpClient();
            try {
//              get content only
//              HttpGet httpget = new HttpGet(url);
//              ResponseHandler<String> responseHandler = new BasicResponseHandler();
//              content = httpclient.execute(httpget, responseHandler);

//                HttpGet httpget = new HttpGet(url);
//                ResponseHandler<String> responseHandler = new BasicResponseHandler();
//                content = httpclient.execute(httpget, responseHandler);

//                HttpResponse response = httpclient.execute(httpget);
//                int statusCode = response.getStatusLine().getStatusCode();
//                String str = String.valueOf(statusCode);
//              redirecting or error(as 404 505)
//                if (str.startsWith("4") || str.startsWith("5")) {
//                    content = null;
//                }
//                return content;
            } catch (Exception e) {
//                LoggerUtils.error(log, "getPageByUrl: " + url + " error", e);
                throw new IOException(e);
            } finally {
//                httpclient.getConnectionManager().shutdown();
            }

        }
        return url;
    }

    /**
     * getContentByUrl:
     * 根据url返回查询内容，默认gbk编码
     *
     * @param url 网址
     * @return 网页内容
     * @author lichunping
     * @since 1.0.0
     */
    public static String getContentByUrl(String url) throws Exception {
        return getContentByUrl(url, null);
    }

    /**
     * getContentByUrl:
     * 根据url返回查询内容，使用httpClient包，见: http://hc.apache.org/
     * 根据contentType设置以及用户设置的编码抓取，适合需要区分编码的情况
     *
     * @param url    网址
     * @param encode 编码 [gbk | utf-8 | etc.]
     * @return 网页内容
     * @author lichunping
     * @since 1.0.0
     */
    public static String getContentByUrl(String url, String encode) throws Exception {
        String content;
        String encoding;

        if (!StringUtils.isBlank(url)) {
            try {
//                HttpClient httpclient = new DefaultHttpClient();
//
//                HttpGet httpget = new HttpGet(url);
//                ResponseHandler<String> responseHandler = new BasicResponseHandler();
//
//                content = httpclient.execute(httpget, responseHandler);
//
//                HttpResponse rsp = httpclient.execute(httpget);
//                HttpEntity entity = rsp.getEntity();
//                String docType = entity.getContentType().toString();

//                指定了编码则按照指定编码对内容进行转码
//                if (!StringUtils.isBlank(encode)) {
//                    encoding = encode;
//                    content = EncodeUtils.encodeCovert(content, "iso-8859-1", encoding);
//                } else {
//                    int index = docType.lastIndexOf("charset=");
//                    if (index == -1) {
//                      如未指定编码且HTTP response headers中也没有contentType
//                      则按照页面代码中mate的Content-Type设置进行转码，否则按默认转码
//                        String charsetInpage = getContentType(content);
//                        if (!charsetInpage.equals("")) {
//                            encoding = charsetInpage;
//                        } else {
//                            encoding = DEFAULT_ENCODING;
//                        }
//                        content = EncodeUtils.encodeCovert(content, "iso-8859-1", encoding);
//                    }
//                }
//
//                httpclient.getConnectionManager().shutdown();
            } catch (Exception e) {
                LoggerUtils.error(log, "getContentByUrl: " + url + " error", e);
                return "error:url can't visited" + e.getMessage();
            }
//            return content;
        }
        return url;
    }

    /**
     * getHtml:
     * 根据url通过HttpURLConnection返回查询的内容，默认gbk编码
     *
     * @param url 地址
     * @return 网页内容
     * @throws Exception
     * @author lichunping
     * @since 1.0.0
     */
    public static String getHtmlByUrl(String url) throws Exception {
        return getHtmlByUrl(url, null);
    }

    /**
     * getContentByStream:
     * 返回读取数据流之后的内容
     *
     * @param inputStream 数据流
     * @return 网页内容
     * @throws IOException
     * @author lichunping
     * @since 1.0.0
     */
    public static String getContentByStream(InputStream inputStream, String encode) throws Exception {
//        HttpURLConnection conn = (HttpURLConnection) new URL("url").openConnection();
//        InputStream inputStream = conn.getInputStream();
        if (inputStream == null) {
            return null;
        }
        StringBuffer html = new StringBuffer();
        String content;
        String encoding = (!StringUtils.isBlank(encode)) ? encode : DEFAULT_ENCODING;
        InputStreamReader isr = new InputStreamReader(inputStream, encoding);
        BufferedReader br = new BufferedReader(isr);
        String inLine;

        while ((inLine = br.readLine()) != null) {
            html.append(inLine).append("\n");
        }

        br.close();
        isr.close();
        content = html.toString();

        return content;

    }


    /**
     * getHtml:
     * 根据url通过HttpURLConnection返回查询的内容
     *
     * @param url    地址
     * @param encode 编码 [gbk | utf-8 | etc.]
     * @return 网页内容
     * @throws IOException
     * @author lichunping
     * @since 1.0.0
     */
    public static String getHtmlByUrl(String url, String encode) throws Exception {
        try {

            String content;
            String encoding;
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            String docType = conn.getContentType();
            String charset = "charset=";

//          如果指定了编码则按照编码读取数据流
//          否则如果有HTTP header有contentType，则按照contentType获取编码，否则按默认编码读取
            boolean hasCharset = false;
            if (!StringUtils.isBlank(encode)) {
                encoding = encode;
            } else {
                int index = docType.lastIndexOf(charset);
                if (index != -1) {
                    hasCharset = true;
                    encoding = docType.substring(index + charset.length(), docType.length());
                } else {
                    encoding = DEFAULT_ENCODING;
                }
            }

            content = getContentByStream(conn.getInputStream(), encoding);

//          如果既没有指定编码，HTTP header也没有设定contentType
//          再通过已经获得的内容得到meta中charset，如果有编码，则按照新的编码再次连接
            if (StringUtils.isBlank(encode) && !hasCharset) {
                String charsetInpage = getContentType(content);
                if (!charsetInpage.equals("")) {
                    encoding = charsetInpage;
                }
                conn = (HttpURLConnection) new URL(url).openConnection();
                content = getContentByStream(conn.getInputStream(), encoding);
            }

            return content;

        } catch (Exception e) {
            LoggerUtils.error(log, "getHtmlByUrl: " + url + " error", e);
            throw new Exception("error:url can't visited. " + e.getMessage());
        }
    }

    /**
     * getImageByUrl:
     * 根据URL下载 图像
     *
     * @param urlString
     * @param toPath
     * @param toPath
     * @return
     * @throws Exception
     */
    public static Map<String, String> getImageByUrl(String urlString, String toPath) throws Exception {
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        con.setConnectTimeout(5 * 1000);
        InputStream is = con.getInputStream();
        Map<String, String> img = new HashMap<String, String>();
        String fileName = StringUtils.md5(urlString);
        byte[] bs = new byte[1024];
        int len;
        File file = new File(toPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        OutputStream os = new FileOutputStream(file.getPath() + "/" + fileName);
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
        is.close();
        String type = ImageUtils.getImageType(file.getPath() + "/" + fileName);
        if (!ImageUtils.isValidType(type)) {
            log.error("is not FileInputStream:" + urlString);
            return null;
        }
        img.put("path", file.getPath());
        img.put("name", fileName);
        return img;
    }



//    public static org.apache.commons.httpclient.Cookie getHttpCookie(Cookie cookie) {
//        if(cookie == null) {
//            return null;
//        }
//
//        org.apache.commons.httpclient.Cookie httpCookie = null;
//
//        String domain = cookie.getDomain();
//        if (org.apache.commons.lang.StringUtils.isEmpty(domain)) {
//            domain = SERVER_DOMAIN;
//        }
//        String name = cookie.getName();
//        String value = cookie.getValue();
//        String path = cookie.getPath();
//        if (path == null) {
//            path = "/";
//        }
//        int maxAge = cookie.getMaxAge();
//        boolean secure = cookie.getSecure();
//
//        httpCookie = new org.apache.commons.httpclient.Cookie(domain, name, value, path, maxAge, secure);
//
//        httpCookie.setComment(cookie.getComment());
//        httpCookie.setVersion(cookie.getVersion());
//
//        return httpCookie;
//    }
    public static String getContent(String url) {
        return getHTTPContent(url, null);
    }

    public static String getHTTPContent(String url, String charset, Cookie[] cookies, Map headerMap) {
        return getHTTPContent(url, charset, cookies, headerMap, true);
    }

    public static String getHTTPContent(String url) {
        return getHTTPContent(url, null);
    }

    public static String getHTTPContent(String url, Cookie[] cookies) {
        return getHTTPContent(url, "UTF-8", cookies, null);
    }

    public static String getHTTPContent(String url, String charset, Cookie[] cookies, Map headerMap, boolean noProxy) {
        if (url == null || url.equals("")) {
            return null;
        }
//        try {
//            HttpClientUtils http;
//            if (noProxy) {
//                http = HttpClientUtils.getNoProxyInstance();
//            } else {
//                http = HttpClientUtils.getInstance();
//            }
//            log.info("[VelocityUtil>getHTTPContent]:[url][" + url + "]" + ":[cookies]" + JSON.toJSON(cookies));
//            org.apache.commons.httpclient.Cookie[] cookieList = null;
//            if (cookies != null) {
//                cookieList = new org.apache.commons.httpclient.Cookie[cookies.length];
//            }
//            if (cookies != null && cookieList != null) {
//                for (int i = 0; i < cookies.length; i++) {
//                    cookieList[i] = getHttpCookie(cookies[i]);
//                }
//            }
//            return http.exeGetMethod(url, charset, cookieList, headerMap);
//        } catch (Exception e) {
//            log.error("[http2map get error:]", e);
//        }
        return null;
    }

//    public static Map<?, ?> http2Map(String url) {
//        return ObjectUtils.parseMap(getHTTPContent(url));
//    }
//
//    public static List<?> http2Array(String url) {
//        return ObjectUtils.parseArray(getHTTPContent(url));
//    }
//
//    public static List<?> http2Array(String url, Cookie[] cookies) {
//        return ObjectUtils.parseArray(getHTTPContent(url, cookies));
//    }
//
//    public static Map<?, ?> http2Map(String url, Cookie[] cookies) {
//        return ObjectUtils.parseMap(getHTTPContent(url, cookies));
//    }
//
//    public static Map<?, ?> http2Map(String url, Cookie[] cookies, Map headerMap, boolean noProxy) {
//        return ObjectUtils.parseMap(getHTTPContent(url, "utf-8", cookies, headerMap, noProxy));
//    }


    /**
     * download:
     * 根据URL下载
     *
     * @param urlString
     * @param toPath
     * @return
     * @throws Exception
     */
    public static void download(String urlString, String toPath) throws Exception {
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        con.setConnectTimeout(5 * 1000);
        InputStream is = con.getInputStream();
        String fileName = ImageUtils.getMD5FileName(urlString);

        byte[] bs = new byte[1024];
        int len;
        File file = new File(toPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        OutputStream os = new FileOutputStream(file.getPath() + "/" + fileName);
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
        is.close();
    }

}
