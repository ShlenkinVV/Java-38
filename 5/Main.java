package com.company;

class Fork {
    boolean isFree=true;

    public synchronized void take(){
        if (!isFree)
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isFree=false;
    }

    public synchronized void drop(){
        isFree=true;
        notify();

    }
}

class Philosopher implements Runnable{
        Fork f1, f2;

        public Philosopher(Fork f1, Fork f2){
            this.f1=f1;
            this.f2=f2;
        }

        public void eat(){
            f1.take();
            f2.take();
             try {
                    System.out.println(Thread.currentThread().getName() + " ест");
                    Thread.sleep(2000);
             } catch (InterruptedException e) {
          e.printStackTrace();
         }
}

    public void think(){
            f1.drop();
            f2.drop();
        try {
            System.out.println(Thread.currentThread().getName() + " думает");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        while (true){
            eat();
            think();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Fork[] forks = {new Fork(), new Fork(), new Fork(), new Fork(), new Fork()};

        Philosopher p1 = new Philosopher(forks[0], forks[1]);
        Philosopher p2 = new Philosopher(forks[1], forks[2]);
        Philosopher p3 = new Philosopher(forks[2], forks[3]);
        Philosopher p4 = new Philosopher(forks[3], forks[4]);
        Philosopher p5 = new Philosopher(forks[4], forks[0]);

        Thread thread1= new Thread(p1);
        thread1.setName("Philosopher №1");
        Thread thread2= new Thread(p2);
        thread2.setName("Philosopher №2");
        Thread thread3= new Thread(p3);
        thread3.setName("Philosopher №3");
        Thread thread4= new Thread(p4);
        thread4.setName("Philosopher №4");
        Thread thread5= new Thread(p5);
        thread5.setName("Philosopher №5");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

    }
}