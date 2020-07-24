package com.insigma.ordercenter.logistics.best.sdk.converter.util.jsonReader;

public class ExceptionErrorListener extends BufferErrorListener {
    
    @Override
    public void error(String type, int col) {
        super.error(type, col);
        throw new IllegalArgumentException(buffer.toString());
    }
}
