package com.hpugs.learning.pattern.structure.proxy;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/15 下午3:25
 */
public class SessionTextMsg implements SessionMsg {

    @Override
    public void sendMsg(String msg) {
        System.out.println("------------文本消息 start -------------");
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(msg);
        System.out.println("------------文本消息 end -------------");
    }

}
