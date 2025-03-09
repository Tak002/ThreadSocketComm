package org.example.blockAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadExtended extends Thread{
    public void run(){
        System.out.println(Thread.currentThread().getName()+": running");
        try{
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+": ended");
        }catch (Exception e){}
    }
}

class ThreadImpl  implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+": running");
        try{
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+": ended");
        }catch (Exception e){}
    }
}




public class ThreadExample {
    public static void main(String[] args){
        //Thread class 상속
        ThreadExtended thread0 = new ThreadExtended();
        ThreadExtended thread1 = new ThreadExtended();

        thread0.start();
        thread1.start();

        System.out.println("thread0, thread1 is running");

        //Runnable interface 구현
        Thread thread2 = new Thread(new ThreadImpl());
        Thread thread3 = new Thread(new ThreadImpl());

        thread2.start();
        thread3.start();

        System.out.println("thread2, thread3 is running");

        //ExecutorService - ThreadPool
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        for(int i=0; i<10; i++){
            executorService.execute(new ThreadImpl());
            System.out.println("Thread pool started: " + i);
        }
        System.out.println("Every executor works");
        executorService.shutdown(); // 작업 완료 후 종료
    }
}
