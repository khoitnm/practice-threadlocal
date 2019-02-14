package org.tnmk.practice.localthread.simple.localthread;

public class FailThreadObjectHolder extends ThreadLocal {

    public static FailThreadObjectHolder INSTANCE = new FailThreadObjectHolder();

    private Object object;

    public static FailThreadObjectHolder getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(FailThreadObjectHolder INSTANCE) {
        FailThreadObjectHolder.INSTANCE = INSTANCE;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
