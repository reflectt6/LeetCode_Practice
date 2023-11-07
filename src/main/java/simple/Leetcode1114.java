package simple;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Leetcode1114 {
//    static AtomicInteger a = new AtomicInteger(0);

    // 单核运行这三个线程是没有问题的
    static int a = 0;
    // 多核运行多线程会有a自增后的可见性问题 需要加上volatile关键字


//    static int a = 0;
    public static void main(String[] args) throws InterruptedException {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("t1");
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("t2");
            }
        };
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("t3");
            }
        };
        HashMap<Integer, Thread> map = new HashMap<>();
        int[] s = new int[]{1,3,2};
        Leetcode1114 leetcode1114 = new Leetcode1114();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    leetcode1114.first(r1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    leetcode1114.second(r2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    leetcode1114.third(r3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        map.put(1, t1);
        map.put(2, t2);
        map.put(3, t3);
//        while (true) {}
        map.get(s[0]).start();
        map.get(s[1]).start();
        map.get(s[2]).start();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        a++;
        while (true) {}
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (a != 1) {}
        printSecond.run();
        a++;
        while (true) {}
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (a != 2) {}
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        while (true) {}
    }
}

class Foo {

    private int a = 0;

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        a++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (a == 0) {}
        printSecond.run();
        a++;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (a != 2) {}
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
