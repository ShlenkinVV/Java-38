package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Producer <T> implements Runnable {
    private MyQueue<T> myQueue;

    public Producer(MyQueue<T> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        int i =0;
        while (true){
            myQueue.put((T) new Obj(i++));
        }
    }
}

class Consumer<T> implements Runnable {
    private MyQueue<T> myQueue;

    public Consumer(MyQueue<T> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        while(true) {
            T t = myQueue.get();

        }
    }
}

class MyQueue <T>{
    Queue<T> queue;

    public MyQueue(Queue<T> queue){
        this.queue=queue;
    }

    public synchronized T get() {
        while (queue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " ожидает... ");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T n = queue.poll();
        System.out.println(Thread.currentThread().getName() + " пoлyчил: " + n);

        notify();
        return n;
    }

    public synchronized void put(T n) {
        while (queue.size() >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queue.offer(n);
        System.out.println(Thread.currentThread().getName() + " отпpaвил: " + n);
        notify();
    }
}

class Obj{
    private List<Double> list;
    int num;

    public Obj(int num){
        this.num=num;
        list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            list.add(Math.random());
        }
    }

    @Override
    public String toString() {
        return ""+num;
    }

}


public class Main {
    public static void main(String[] args) {
        Queue<Obj> queue= new LinkedList<>();
        MyQueue<Obj> myQueue = new MyQueue<>(queue);

        Consumer<Obj>consumer = new Consumer(myQueue);
        Consumer<Obj> consumer1 = new Consumer(myQueue);
        Producer<Obj> producer = new Producer(myQueue);

        Thread t1 = new Thread(producer);
        t1.setName("Producer");
        Thread t2 = new Thread(consumer);
        t2.setName("Consumer №1");
        Thread t3 = new Thread(consumer1);
        t3.setName("Consumer №2");

        t1.start();
        t2.start();
        t3.start();
    }
}