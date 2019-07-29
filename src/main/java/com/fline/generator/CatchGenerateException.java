package com.fline.generator;

/**
 * 用于捕获不再继续抛出的异常
 * 
 * @since 2019年7月27日 下午7:42:21
 */
public class CatchGenerateException extends GenerateException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public CatchGenerateException(String message) {
        super(message);
    }

    /**
     * 
     */
    public CatchGenerateException(String message, Throwable e) {
        super(message, e);
    }
}
