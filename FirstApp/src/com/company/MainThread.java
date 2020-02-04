package com.company;

public class MainThread {

    public static void main(String[] args) {
        // write your code here
//        MyThread mt = new MyThread ();
//        mt.start ();
//        for (int i = 1; i < 10; i++) {
//            System.out.println("i = " + i + ", i * i = " + i * i);
//        }

//        MyThread mt = new MyThread();
//        mt.start();
//
//        try {
//            mt.join(); // Sleep for 10 milliseconds
//        } catch (InterruptedException e) {
//        }
//        System.out.println("pi = " + mt.pi);

//        Thread [] threads = new Thread [Thread.activeCount ()];
//        int n = Thread.enumerate (threads);
//        for (int i = 0; i < n; i++)
//            System.out.println (threads [i].toString ())

        String[] args1=new String[1];
        args1[0]="x";
        MyThread mt=new MyThread ();;
        if (args1.length == 0)
            new MyThread ().start();
        else
        {
            mt.setDaemon (true);
            mt.start ();
        }
        System.out.println(mt.isAlive());
        try
        {
            Thread.sleep (4000);

            System.out.println(mt.isAlive());
        }
        catch (InterruptedException e)
        {
        }

        System.out.println(mt.isAlive());

    }
}
