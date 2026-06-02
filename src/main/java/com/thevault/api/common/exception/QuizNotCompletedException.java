package com.thevault.api.common.exception;

public class QuizNotCompletedException extends RuntimeException {

    public QuizNotCompletedException() {
        super("Please complete the Main Quiz before accessing this feature.");
    }
}
