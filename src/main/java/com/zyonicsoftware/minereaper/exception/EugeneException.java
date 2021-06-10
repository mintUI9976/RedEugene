/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.exception;

public class EugeneException extends RuntimeException {
    private static final long serialVersionUID = -380577725444088189L;

    public EugeneException() {
    }

    public EugeneException(final String message) {
        super(message);
    }

    public EugeneException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EugeneException(final Throwable cause) {
        super(cause);
    }

    public EugeneException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
