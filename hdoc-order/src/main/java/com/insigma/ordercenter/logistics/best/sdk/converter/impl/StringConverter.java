package com.insigma.ordercenter.logistics.best.sdk.converter.impl;

import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.converter.Converter;
import org.w3c.dom.Node;

import java.lang.reflect.Field;

public class StringConverter implements Converter {
    @Override
    public Object convertXml(Node node, Field field) {
        Node firstChild = node.getFirstChild();
        return firstChild.getNodeValue();
    }

    @Override
    public String xmlReverse(Object propValue, Field prop) {
        String propName = prop.getName();
        return Parser.appendXmlNode(propName, propValue.toString());
    }

    @Override
    public Object convertJson(Object value, Field field) {
        return value.toString();
    }
}
