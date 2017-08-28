package org.generaltune.Uitl;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.*;

/**
 * Created by zhumin on 2017/8/18
 * java类加载器的用法.
 */
public class TestClassLoader {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 1.BootStrap ClassLoader 启动类加载器，是Java类加载层次中最顶层的类加载器，负责加载JDK中的核心类库，
     * 如：rt.jar、resources.jar、charsets.jar等。
     * 2.Extension ClassLoader 扩展类加载器，负责加载Java的扩展类库，默认加载JAVA_HOME/jre/lib/ext/目下的所有jar
     * 3.App ClassLoader 系统类加载器，负责加载应用程序classpath目录下的所有jar和class文件
     */
    @Test
    public void classloader() {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();

        for (int i = 0; i < urls.length; i++) {
//            System.out.println(urls[i].toExternalForm());
            logger.info("url:{}",urls[i].toExternalForm());
        }
        // 也可以通过sun.boot.class.path获取
        logger.info("path:{}", System.getProperty("sun.boot.class.path"));
    }


    /**
     * JAVA多线程实现方式主要有三种：继承Thread类、实现Runnable接口、使用ExecutorService、Callable、Future实现有返回结果的多线程。
     * 其中前两种方式线程执行完后都没有返回值，只有最后一种是带返回值的。
     */
    @Test
    public void multiThread() {


    }

class MyRunnable implements Runnable {

    public void run() {
        for (int x = 0; x < 100; x++) {
             System.out.println(Thread.currentThread().getName() + ":" + x);
        }
    }
}

public class MyThread extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + "正在执行… …");
    }
}
    /**
     * 线程池 固定线程池submit方法
     */
    @Test
    public void ThreadPool() throws Exception{
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());

        pool.shutdown();
    }

    /**
     * 固定线程池 execute 方法
     */
    @Test
    public void  ThreadExe() throws Exception{
        // 创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();

        // 将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        // 关闭线程池
        pool.shutdown();

        System.out.println("关闭连接池！");

    }

    class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }


        public void run() {
            System.out.println("正在执行task "+taskNum);
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                System.out.println("task "+taskNum+"执行完毕");
            }
        }
    /**
     * 线程池 执行任务
     */
    @Test
    public void  executeTask() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
        new ArrayBlockingQueue<Runnable>(5));
        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
            executor.getQueue().size()+"，已执行完别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
