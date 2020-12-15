package com.hpugs.learning.pattern.structure.proxy;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/15 下午3:29
 */
public class Demo {

    public static void main(String[] args) {
        SessionMsgProxy sessionMsgProxy = new SessionMsgProxy(new SessionTextMsg());
        sessionMsgProxy.sendMsg("发送一条文本消息");

        sessionMsgProxy = new SessionMsgProxy(new SessionImageMsg());
        sessionMsgProxy.sendMsg("发送一条图片消息");
    }

}
