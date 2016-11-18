package org.generaltune.exception;

/**秒杀相关异常
 * Created by zhumin on 2016/11/15.
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
