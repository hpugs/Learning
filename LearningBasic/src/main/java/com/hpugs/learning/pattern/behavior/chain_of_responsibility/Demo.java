package com.hpugs.learning.pattern.behavior.chain_of_responsibility;

import java.util.HashMap;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/15 下午3:57
 */
public class Demo {

    public static void main(String[] args) {
        EmailCheck emailCheck = new EmailCheck("abc", "bcd", "efj");
        HashMap hashMap = new HashMap(4);
        hashMap.put("abc", "123");
        hashMap.put("123", "123");
        LoginCheck loginCheck = new LoginCheck(hashMap);
        WhiteUserCheck whiteUserCheck = new WhiteUserCheck("abc123");

        emailCheck.setNext(loginCheck);
        loginCheck.setNext(whiteUserCheck);

        System.out.println("检查邮箱：123 check=" + emailCheck.check("123", "123"));
        System.out.println("检查邮箱：123 check=" + emailCheck.check("123", "abc"));
        System.out.println("检查邮箱：abd123 check=" + emailCheck.check("abc123", "abc123"));
    }
    
}
