package com.krupp.threadpool;

/**
 * @author lgz
 * 通过继承Thread类，重写Thread的run()方法实现多线程
 */
public class TestThread1 extends Thread {


    /**
     * main方法线程体
     */
    public static void main(String[] args) {

        //创建线程对象
        TestThread1 testThread1 = new TestThread1();
        //start（）方法让一个线程进入就绪队列等待分配cpu，分到cpu后才调用实现的run()方法。
        testThread1.start();
        //在main方法中执行的run()方法不会创建新的线程
        //testThread1.run();

        for (int i = 0; i < 500; i++) {
            System.out.println("我在学习多线程" + i);
        }
    }

    /**
     * run方法线程体
     */
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            System.out.println("我在看代码" + i);
        }
    }

}
