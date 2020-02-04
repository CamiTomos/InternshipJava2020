package com.company;

public class MyThread extends Thread {
    //    public void run ()
//    {
//        for (int count = 1, row = 1; row < 200; row++, count++)
//        {
//            for (int i = 0; i < count; i++)
//                System.out.print ('*');
//            System.out.print ('\n');
//        }
//    }
//    boolean negative = true;
//    double pi; // Initializes to 0.0, by default

    //    public void run() {
//        for (int i = 3; i < 100000; i += 2) {
//            if (negative)
//                pi -= (1.0 / i);
//            else
//                pi += (1.0 / i);
//            negative = !negative;
//        }
//        pi += 1.0;
//        pi *= 4.0;
//        System.out.println("Finished calculating PI");
//    }
    public void run() {
        System.out.println("Daemon is " + isDaemon());
        while (true) ;
    }
}
