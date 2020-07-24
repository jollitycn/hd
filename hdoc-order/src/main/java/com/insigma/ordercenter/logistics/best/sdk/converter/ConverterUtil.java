package com.insigma.ordercenter.logistics.best.sdk.converter;

public class ConverterUtil {
    public static Object convert(Class<?> type, String value){
        if(type.isAssignableFrom(int.class)){
            return Integer.parseInt(value);
        }else if(type.isAssignableFrom(double.class)){
            return Double.valueOf(value);
        }
        return value;
    }
}
