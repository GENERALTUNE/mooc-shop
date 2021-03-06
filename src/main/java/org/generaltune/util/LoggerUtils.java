package org.generaltune.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhumin on 2017/7/23.
 */
public class LoggerUtils {
    //    protected static org.apache.log4j.Logger log = Logger.getLogger(LoggerUtils.class);
    protected static Logger log = LoggerFactory.getLogger(LoggerUtils.class);

    /**
     * info级别日志，记录一些关键信息
     *
     * @param logger
     * @param name
     * @param doWhat
     *
     * */
    public static void info(Logger logger, String name, String ip, String onWhich, String doWhat, Exception e) {
        String info = "";
        if (name != null && ip != null) {
            info = "Visitor(" + name + ") doWhat, Object(" + onWhich + ")," + "from IP(" + ip + "),failed,because ";
        } else {
            info = doWhat + ",Object(" + onWhich + "),failed,because ";
        }
        logger.info(info);
    }

    /**
     * info级别日志，记录一些关键信息
     *
     * @param logger
     * @param doWhat
     * @param doWhat
     *
     * */
    public static void info(Logger logger, String doWhat, Exception e) {
        String info = doWhat + ",failed,because ";
        if (e != null) {
            info += getStackTraceInfo(e);
        }
        logger.warn(info);
    }

    /**
     * warn级别日志，记录出错信息，但未对系统造成影响
     *
     * @param logger
     * @param name
     * @param onWhich
     * @param doWhat
     * @param e
     * */
    public static void warn(Logger logger, String name, String ip, String onWhich, String doWhat, Exception e) {
        String info = "";
        if (name != null && ip != null) {
            info = "Visitor(" + name + ") doWhat, Object(" + onWhich + ")," + "from IP(" + ip + "),failed,because ";
        } else {
            info = doWhat + ",Object(" + onWhich + "),failed,because ";
        }

        if (e != null) {
            info += getStackTraceInfo(e);
        }
        logger.warn(info);
    }
    /**
     * warn级别日志，记录出错信息，但未对系统造成影响
     *
     * @param logger
     * @param doWhat
     * @param e
     * */
    public static void warn(Logger logger, String doWhat, Exception e) {
        String info = doWhat + ",failed,because ";
        if (e != null) {
            info += getStackTraceInfo(e);
        }
        logger.warn(info);
    }
    /**
     * error级别日志，记录出错信息，对系统造成影响
     *
     * @param logger
     * @param name
     * @param onWhich
     * @param doWhat
     * @param e
     * */
    public static void error(Logger logger, String name, String ip, String onWhich, String doWhat, Exception e) {
        String info = "";
        if (name != null && ip != null) {
            info = "Visitor(" + name + ") doWhat, Object(" + onWhich + ")," + "from IP(" + ip + "),failed,because ";
        } else {
            info = doWhat + ",Object(" + onWhich + "),failed,because ";
        }

        String logInfo = "";

        if (e != null) {
            logInfo = info + getStackTraceInfo(e);
        }

        logger.error(logInfo);
    }
    /**
     * error级别日志，记录出错信息，对系统造成影响
     *
     * @param logger
     * @param reason
     * @param e
     * */
    public static void error(Logger logger, String reason, Exception e) {
        try {
            String logInfo = reason;
            if (e != null) {
                logInfo = logInfo + "\n" + getStackTraceInfo(e).toString();
            }
            logger.error(logInfo);
        } catch (Exception ex) {
            System.out.print(ex.toString());
        }
    }

    /**
     * 获得方法被调用的堆栈信息,记录日志用
     *
     * @param e
     * @return
     */
    public static String getStackTraceInfo(Exception e) {
        StringBuffer stackTraceInfo = new StringBuffer();
        String token = "\n";
        String tokenDi = "\t";

        stackTraceInfo.append(getSystemDate());
        stackTraceInfo.append(tokenDi);
        stackTraceInfo.append(e.getMessage());
        stackTraceInfo.append(token);

        StackTraceElement[] stackTraceElements = e.getStackTrace();
        for (int i = 0; i < stackTraceElements.length; i++) {
            stackTraceInfo.append(getSystemDate());
            stackTraceInfo.append(tokenDi);
            stackTraceInfo.append(stackTraceElements[i]);
            stackTraceInfo.append(token);
        }
        return stackTraceInfo.toString();
    }

    /**
     * 获得方法被调用的堆栈信息,邮件发送用
     *
     * @param e
     * @return
     */
    public static String getStackTraceInfoForMail(Exception e) {
        StringBuffer stackTraceInfo = new StringBuffer();
        String token = "<br>";
        String tokenDi = "\t";

        stackTraceInfo.append(getSystemDate());
        stackTraceInfo.append(tokenDi);
        stackTraceInfo.append(e.getMessage());
        stackTraceInfo.append(token);

        StackTraceElement[] stackTraceElements = e.getStackTrace();
        for (int i = 0; i < stackTraceElements.length; i++) {
            stackTraceInfo.append(getSystemDate());
            stackTraceInfo.append(tokenDi);
            stackTraceInfo.append(stackTraceElements[i]);
            stackTraceInfo.append(token);
        }
        return stackTraceInfo.toString();
    }

    /**
     * 得到系统当前的运行时间，并格式化
     *
     * @return
     */
    private static String getSystemDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        return sdf.format(date);
    }
}
