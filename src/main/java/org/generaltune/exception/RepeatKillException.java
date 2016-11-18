package org.generaltune.exception;

/**
 * 重复秒杀异常（运行期异常）
 * Created by zhumin on 2016/11/15.
 */
public class RepeatKillException extends SeckillException{
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
