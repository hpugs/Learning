package com.hpugs.learning.tests;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 断言工具测试
 *
 * @author gaoshang
 * date: 2020/11/10 下午4:59
 */
public class AssertTest {

    @Test
    public void test(){
        // Expected：abc    Actual：abc
        assertEquals("abc", "abc");

        // 检查条件是假的
        assertFalse(false);

        // 检查条件是真的
        assertTrue(true);

        // 检查对象不为空
        assertNotNull(new Object());

        // 检查对象为空
        assertNull(null);

    }

    @Ignore
    @Test
    public void assertEqualsTest(){
        // Expected：abc    Actual：abc
        assertEquals("abc", "abc");

        // Expected：abc    Actual：cba
        assertEquals("abc", "cba");
    }

    @Ignore
    @Test
    public void assertBooleanTest(){
        // 检查条件是假的
        assertFalse(false);

        // 检查条件是真的
        assertTrue(false);
    }

    @Ignore
    @Test
    public void assertObjectTest(){
        // 检查对象不为空
        assertNotNull(new Object());

        // 检查对象为空
        assertNull(new Object());
    }

    @Ignore
    @Test
    public void failTest(){
        // 在没有报告的情况下使测试不通过
        fail();
    }

}
