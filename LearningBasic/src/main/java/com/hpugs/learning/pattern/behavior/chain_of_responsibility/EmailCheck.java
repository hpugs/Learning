package com.hpugs.learning.pattern.behavior.chain_of_responsibility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/15 下午3:48
 */
public class EmailCheck extends Middleware {

    private List<String> emailList = new ArrayList<>();

    public EmailCheck(String ... emails) {
        this.emailList.addAll(Arrays.asList(emails));
    }

    @Override
    public boolean check(String email, String password) {
        if(emailList.contains(email)){
            System.out.println("邮箱检查通过");
            return true;
        }
        return checkNext(email, password);
    }
}
