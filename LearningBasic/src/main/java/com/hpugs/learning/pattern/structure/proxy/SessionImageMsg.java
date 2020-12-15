package com.hpugs.learning.pattern.structure.proxy;

import org.apache.commons.lang3.RandomUtils;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/15 下午3:30
 */
public class SessionImageMsg implements SessionMsg {

    @Override
    public void sendMsg(String msg) {
        System.out.println("------------图片消息 start -------------");
        try {
            Thread.sleep(RandomUtils.nextInt(0, 10) * 200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(msg);
        System.out.println("------------图片消息 end -------------");
    }
}
