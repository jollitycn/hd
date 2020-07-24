package com.insigma.ordercenter.logistics.best.sdk.converter;

import org.w3c.dom.Node;

import java.lang.reflect.Field;

public interface XmlConverter {

    Object convert(Node node, Field field);

    String reverse(Object propValue, Field prop);
}
