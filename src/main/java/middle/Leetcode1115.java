package middle;

public class Leetcode1115 {
    public static void main(String[] args) throws InterruptedException {
        int n = 7;
        FooBar f = new FooBar(n);
        Runnable printFoo = new Runnable() {
            @Override
            public void run() {
                System.out.print("foo");
            }
        };
        Runnable printBar = new Runnable() {
            @Override
            public void run() {
                System.out.print("bar");
            }
        };

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    f.foo(printFoo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    f.bar(printBar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }


}

class FooBar { // 使用volatile 防止 b参数
    private int n;
    private boolean b = true;
//    private volatile boolean b = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
//            long l1 = System.nanoTime();
//            long l2 = System.nanoTime();
//            long cnt = 0;
            while (!b) {
//                cnt++;
//                l2 = System.nanoTime();
            }
//            System.out.println("\n foo等待时间：" + (l2 - l1)+ " 进入次数" + cnt);
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            b = !b;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
//        long tt = System.nanoTime();
        for (int i = 0; i < n; i++) {
//            long l1 = System.nanoTime();
//            long l2 = System.nanoTime();
//            long cnt = 0;
            while (b) {
//                cnt++;
//                l2 = System.nanoTime();
            }
//            System.out.println("\n bar等待时间：" + (l2 - l1)+ " 进入次数" + cnt);
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            b = !b;
        }
//        System.out.println("\n 总时间：" + (System.nanoTime() - tt));
    }
}