package com.mitesofor.smartsite.car.biz.common;

import org.springframework.web.context.request.RequestAttributes;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/28 10:20
 */
public class NonRequestAttributes implements RequestAttributes {
    @Override
    public Object getAttribute(String s, int i) {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o, int i) {

    }

    @Override
    public void removeAttribute(String s, int i) {

    }

    @Override
    public String[] getAttributeNames(int i) {
        return new String[0];
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable, int i) {

    }

    @Override
    public Object resolveReference(String s) {
        return null;
    }

    @Override
    public String getSessionId() {
        return null;
    }

    @Override
    public Object getSessionMutex() {
        return null;
    }
}
