package com.hpugs.learning.tests;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/10 下午5:14
 */
public class TestCaseTest extends TestCase {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCaseTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

    protected double fValue1;
    protected double fValue2;

    @Before
    public void setUp() {
        System.out.println("setUp");
        fValue1= 2.0;
        fValue2= 3.0;
    }

    @Test
    public void testAdd() {
        //count the number of test cases
        System.out.println("No of Test Case = "+ this.countTestCases());

        //test getName
        String name= this.getName();
        System.out.println("Test Case Name = "+ name);

        //test setName
        this.setName("testNewAdd");
        String newName= this.getName();
        System.out.println("Updated Test Case Name = "+ newName);
    }

    @Test
    public void testRemove() {
        //count the number of test cases
        System.out.println("No of Test Case = "+ this.countTestCases());

        //test getName
        String name= this.getName();
        System.out.println("Test Case Name = "+ name);

        //test setName
        this.setName("testNewRemove");
        String newName= this.getName();
        System.out.println("Updated Test Case Name = "+ newName);
    }

    //tearDown used to close the connection or clean up activities
    public void tearDown(  ) {
        System.out.println("tearDown");
    }

}
