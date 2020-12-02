package com.hpugs.learning.basic.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/2 上午10:19
 */
public class LockSupportUtil {

    public static void main(String[] args) {
        // LockSupport park与unPark测试
        parkAndUnPark();

        // 通过相对时间设置线程阻塞
//        parkNanos();

        // 通过绝对时间设置线程阻塞
//        parkUntil();

//        parkNanosAndUnPark();
    }

    /**
     * 1、通过park设置当前线程阻塞
     * 2、通过park更新线程的Blocker数据
     * 3、通过 LockSupport.getBlocker 获取线程的Blocker数据
     * 4、通过 LockSupport.unpark 唤醒阻塞线程
     * 5、unpark后，线程a的Blocker数据被置空，原理：LockSupport.unPark(Object blocker)
     */
    public static void parkAndUnPark() {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park("线程a的Blocker数据");
                System.out.println("被其他线程唤醒后的a线程的操作");
            }
        });
        a.start();

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                String before = (String) LockSupport.getBlocker(a);
                System.out.println("阻塞时从线程a中获取的blocker数据------>" + before);

                LockSupport.unpark(a);

                try {
                    // 保证线程a被正常唤醒
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String after = (String) LockSupport.getBlocker(a);
                System.out.println("唤醒时从线程a中获取的blocker数据------>" + after);
            }
        });
        b.start();
    }

    /**
     * parkNanos测试
     */
    public static void parkNanos() {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                Long start = System.currentTimeMillis();
                // nanos相对时间，单位纳秒
                Long nanos = 1 * 1000 * 1000000L;
                LockSupport.parkNanos("线程a的Blocker数据", nanos);
                System.out.println("等待超时唤醒后的a线程的操作，等待时长=" + (System.currentTimeMillis() - start));
            }
        });
        a.start();

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                String blocker = (String) LockSupport.getBlocker(a);
                System.out.println("阻塞时从线程a中获取的blocker数据------>" + blocker);

                while (true) {
                    blocker = (String) LockSupport.getBlocker(a);
                    if (blocker == null) {
                        break;
                    }

                    System.out.println("线程a阻塞中...");

                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        b.start();
    }

    /**
     * parkUntil测试
     */
    public static void parkUntil() {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                Long start = System.currentTimeMillis();
                // deadline绝对时间，单位毫秒
                Long deadline = System.currentTimeMillis() + 1* 1000;
                LockSupport.parkUntil("线程a的Blocker数据", deadline);
                System.out.println("等待超时唤醒后的a线程的操作，等待时长=" + (System.currentTimeMillis() - start));
            }
        });
        a.start();

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                String blocker = (String) LockSupport.getBlocker(a);
                System.out.println("阻塞时从线程a中获取的blocker数据------>" + blocker);

                while (true) {
                    blocker = (String) LockSupport.getBlocker(a);
                    if (blocker == null) {
                        break;
                    }

                    System.out.println("线程a阻塞中...");

                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        b.start();
    }

    /**
     * parkUntil与unpark测试测试
     */
    public static void parkNanosAndUnPark() {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                Long start = System.currentTimeMillis();
                // nanos相对时间，单位纳秒
                Long nanos = 5 * 1000 * 1000000L;
                LockSupport.parkNanos("线程a的Blocker数据", nanos);
                System.out.println("等待超时唤醒后的a线程的操作，等待时长=" + (System.currentTimeMillis() - start));
            }
        });
        a.start();

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                String blocker = (String) LockSupport.getBlocker(a);
                System.out.println("阻塞时从线程a中获取的blocker数据------>" + blocker);

                while (true) {
                    blocker = (String) LockSupport.getBlocker(a);
                    if (blocker == null) {
                        break;
                    }

                    System.out.println("线程a阻塞中...");

                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        b.start();

        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                LockSupport.unpark(a);
            }
        });
        c.start();
    }

}
