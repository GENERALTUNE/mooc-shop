package org.generaltune.exception;

/**
 * 秒杀关闭异常
 * Created by zhumin on 2016/11/15.
 */
public class SeckillCloseException  extends SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
