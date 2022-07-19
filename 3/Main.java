package com.company;

class MyThread extends Thread {
    private StringBuilder stringBuilder;

    public MyThread(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public void run() {
        synchronized (stringBuilder){
            for (int i = 0; i < 100; i++) {
                System.out.print(stringBuilder);
            }
            System.out.println();

            char ch = stringBuilder.charAt(0);
            ch++;
            stringBuilder.setCharAt(0, ch);
        }
    }
}

    public class Main {
        public static void main(String[] args) {
            //третье задание

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('a');

            MyThread thread1 = new MyThread(stringBuilder);
            MyThread thread2 = new MyThread(stringBuilder);
            MyThread thread3 = new MyThread(stringBuilder);

            thread1.start();
            thread2.start();
            thread3.start();

        }
    }



