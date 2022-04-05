package org.tnmk.practice.pro01simplethreadlocal.localthread;

public class CorrectThreadObjectHolder {

    private static final ThreadLocal<Object> objectHolder = new ThreadLocal();

    public static Object getObject() {
        return objectHolder.get();
    }

    public static void setObject(Object context) {
        objectHolder.set(context);
    }
}
