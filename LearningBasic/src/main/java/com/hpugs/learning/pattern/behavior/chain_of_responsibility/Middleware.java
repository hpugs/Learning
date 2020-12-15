package com.hpugs.learning.pattern.behavior.chain_of_responsibility;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/15 下午3:44
 */
public abstract class Middleware {

    private Middleware next;

    public abstract boolean check(String email, String password);

    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }

        return next.check(email, password);
    }

    public void setNext(Middleware next) {
        this.next = next;
    }

}
