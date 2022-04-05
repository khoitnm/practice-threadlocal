package org.tnmk.practice.pro01simplethreadlocal.localthread.simple;

import org.tnmk.practice.pro01simplethreadlocal.localthread.FailThreadObjectHolder;

public class FailThreadObjectHolderRunnable implements Runnable {
    private final Object object;

    public FailThreadObjectHolderRunnable(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        System.out.println("Start thread for FailThreadObjectHolder: " + object);
        FailThreadObjectHolder.INSTANCE.setObject(object);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End thread for FailThreadObjectHolder: "+object+". The result is: "+FailThreadObjectHolder.INSTANCE.getObject());
    }
}
