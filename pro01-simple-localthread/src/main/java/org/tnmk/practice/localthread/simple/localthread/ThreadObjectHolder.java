package org.tnmk.practice.localthread.simple.localthread;

public class ThreadObjectHolder {

    private static final ThreadLocal<Object> objectHolder = new ThreadLocal();

    public static Object getObject() {
        return objectHolder.get();
    }

    public static void setObject(Object context) {
        objectHolder.set(context);
    }
}
