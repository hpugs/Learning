package com.hpugs.learning.tests;

import org.junit.*;

/**
 *
 *
 * @author gaoshang
 * date: 2020/11/10 下午4:38
 */
public class SimpleTest {

    @BeforeClass
    public static void beforeClassTest(){
        System.out.println("beforeClassTest");
    }

    @Before
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @Test
    public void test(){
        System.out.println("test");
    }

    @After
    public void afterTest(){
        System.out.println("afterTest");
    }

    @AfterClass
    public static void afterCLassTest(){
        System.out.println("afterCLassTest");
    }

}
