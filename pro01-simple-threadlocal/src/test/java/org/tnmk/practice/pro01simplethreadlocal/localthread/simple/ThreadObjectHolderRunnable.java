package org.tnmk.practice.pro01simplethreadlocal.localthread.simple;

import org.tnmk.practice.pro01simplethreadlocal.localthread.CorrectThreadObjectHolder;

public class ThreadObjectHolderRunnable implements Runnable {
    private final Object object;

    public ThreadObjectHolderRunnable(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        System.out.println("Start thread for CorrectThreadObjectHolder: " + object);
        CorrectThreadObjectHolder.setObject(object);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End thread for CorrectThreadObjectHolder: "+object+". The result is: "+ CorrectThreadObjectHolder.getObject());
    }
}
