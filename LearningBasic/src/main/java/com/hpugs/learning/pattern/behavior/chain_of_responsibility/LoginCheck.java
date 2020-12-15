package com.hpugs.learning.pattern.behavior.chain_of_responsibility;

import java.util.HashMap;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/15 下午3:52
 */
public class LoginCheck extends Middleware {

    private HashMap emailMap;

    public LoginCheck(HashMap emailMap) {
        this.emailMap = emailMap;
    }

    @Override
    public boolean check(String email, String password) {
        if (emailMap.keySet().contains(email)) {
            if (emailMap.get(email).equals(password)) {
                System.out.println("账号检查通过");
                return true;
            } else {
                System.out.println("账号检查拒绝");
                return false;
            }
        }
        return checkNext(email, password);
    }

}
