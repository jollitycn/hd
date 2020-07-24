package com.insigma.ordercenter.logistics.best.sdk.converter;

import org.w3c.dom.Node;

import java.lang.reflect.Field;

public interface Converter {

    Object convertXml(Node node, Field field);

    String xmlReverse(Object propValue, Field prop);

    Object convertJson(Object value, Field field);

}
