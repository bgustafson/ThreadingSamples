package com.intercall.ThreadingSamples.Examples;


import java.util.LinkedList;


public class ConsumerProducer {

    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException {

        int value = 0;

        while (true) {

            synchronized (lock) {

                while (list.size() == LIMIT) {
                    lock.wait();
                }

                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {

        while (true) {

            synchronized (lock) {

                while (list.size() == 0) {

                    lock.wait();
                }

                int value = list.removeFirst();
                lock.notify();
            }
        }
    }

}
