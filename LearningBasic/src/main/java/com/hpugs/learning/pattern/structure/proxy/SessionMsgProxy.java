package com.hpugs.learning.pattern.structure.proxy;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/15 下午3:27
 */
public class SessionMsgProxy implements SessionMsg {

    private SessionMsg sessionMsg;

    public SessionMsgProxy(SessionMsg sessionMsg) {
        this.sessionMsg = sessionMsg;
    }

    @Override
    public void sendMsg(String msg) {
        Long start = System.currentTimeMillis();
        sessionMsg.sendMsg(msg);
        System.out.println("消息发送耗时：" + (System.currentTimeMillis() - start));
    }
}
