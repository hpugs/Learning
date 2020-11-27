package com.hpugs.learning.pattern.builds.singleton;

/**
 * 单例模式
 *
 * @author gaoshang
 * date: 2020/11/27 下午3:39
 */
public class Database {

    private static Database instance;

    private Database() {
    }

    public Database getInstance() {
        if (instance != null) {
            return instance;
        }
        return new Database();
    }
}
