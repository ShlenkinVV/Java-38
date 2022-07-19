package com.company;


class NewThread extends Thread {
    public void run() {
        for (int i=0; i<100;i++)
            System.out.print("a ");
    }
}

public class Main {
        public static void main(String[] args) {
        //первое задание
        NewThread newThread= new NewThread();
        newThread.start();

    }
}


