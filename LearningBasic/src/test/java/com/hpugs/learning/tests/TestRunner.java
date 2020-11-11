package com.hpugs.learning.tests;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/10 下午5:35
 */
public class TestRunner {

    public static void main(String[] a) {
        // add the test's in the suite
        TestSuite suite = new TestSuite(SimpleTest.class, TestCaseTest.class);
        TestResult result = new TestResult();
        suite.run(result);
        System.out.println("Number of test cases = " + result.runCount());

        // 运行测试类是否存在异常
        Result result1 = JUnitCore.runClasses(AssertTest.class);
        System.out.println(result1.wasSuccessful());;
    }

}
