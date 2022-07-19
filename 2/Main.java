package com.company;


class MyRunnable  implements Runnable {
    public void run() {
        for (int i=0; i<=100;i++) {
            if (i % 10==0)
                System.out.println("Поток " + Thread.currentThread().getName()+ ": " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

public class Main {
        public static void main(String[] args) {
        //второе задание
            MyRunnable myRunnable = new MyRunnable();
            Thread thread1 = new Thread(myRunnable);
            thread1.setName("Первый");
            Thread thread2 = new Thread(myRunnable);
            thread2.setName("Второй");
            Thread thread3 = new Thread(myRunnable);
            thread3.setName("Третий");

            thread1.start();
            thread2.start();
            thread3.start();

    }
}


