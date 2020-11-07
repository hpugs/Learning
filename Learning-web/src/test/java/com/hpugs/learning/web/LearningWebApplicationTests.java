package com.hpugs.learning.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearningWebApplication.class)
@Rollback
public class LearningWebApplicationTests {

    /**
     * 测试执行开始时间
     */
    private long executeStart = 0;

    @Before
    public void before() {
        System.out.println("测试执行开始");
        executeStart = System.currentTimeMillis();
    }

    @Test
    public void contextLoads() {
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        System.out.println("测试执行完成，执行时间：" + (System.currentTimeMillis() - executeStart) / 1000.0 + "s");
    }

}
