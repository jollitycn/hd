package com.insigma.ordercenter.logistics.best.sdk.converter.util.jsonReader;

public class StdoutStreamErrorListener extends BufferErrorListener {
    
    @Override
    public void end() {
        System.out.print(buffer.toString());
    }
}
