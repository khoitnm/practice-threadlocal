package org.tnmk.practice.localthread.simple;

import org.tnmk.practice.localthread.simple.localthread.ThreadObjectHolder;

public class ThreadObjectHolderRunnable implements Runnable {
    private final Object object;

    public ThreadObjectHolderRunnable(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        System.out.println("Start thread for " + object);
        ThreadObjectHolder.setObject(object);
        System.out.println("End thread for "+object+". The result is: "+ThreadObjectHolder.getObject());
    }
}
