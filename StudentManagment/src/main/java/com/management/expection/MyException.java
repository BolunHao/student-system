package com.management.exception;

public class MyException extends RuntimeException{

    /**
     * Return results
     */
    private Object result;

    public MyException(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
