package com.hpugs.learning.basic.throwables;

import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 简单异常测试
 *
 * @author gaoshang
 * date: 2020/11/10 下午2:13
 */
public class SimpleExceptionTest {

    public static void main(String[] args) {

        // 抛出异常测试
//        throwExceptionTest();

        // 系统检查异常测试
//        checkSystemExceptionTest();

        // 异常捕获测试
//        captureExceptionTest();

        // finally关键字
//        finallyTest();

        // AutoCloseable接口测试
//        autoCloseableTest();

        // 打印异常堆栈信息
        printStackTraceMsgTest();

    }

    /**
     * 抛出异常测试
     */
    private static void throwExceptionTest() {
        try {
            // 无参系统异常
            throw new Exception();
        } catch (Exception e) {
            sout("异常类：Exception(无参)");
        }

        try {
            // 有参系统异常
            throw new Exception("异常类：Exception(有参)");
        } catch (Exception e) {
            sout(e);
        }

        try {
            // 有参系统异常
            throw new Throwable("异常基类：Throwable");
        } catch (Throwable e) {
            sout(e);
        }

        try {
            // 有参系统异常
            throw new RuntimeException("运行时异常：RuntimeException");
        } catch (Throwable e) {
            sout(e);
        }

        try {
            // 有参系统异常
            throw new NullPointerException("空指针异常：NullPointerException");
        } catch (Throwable e) {
            sout(e);
        }

        try {
            // 无参系统异常
            throw new IndexOutOfBoundsException("数组下标越界异常：IndexOutOfBoundsException");
        } catch (Exception e) {
            sout(e);
        }

        try {
            // 有参系统异常
            throw new SimpleException();
        } catch (Exception e) {
            sout("自定义异常：SimpleException(无参)");
        }

        try {
            // 有参系统异常
            throw new MyException("自定义异常：MyException(有参)");
        } catch (Exception e) {
            sout(e);
        }

        try {
            // 有参系统异常
            throw new SimpleRuntimeException("自定义异常：SimpleRuntimeException");
        } catch (Exception e) {
            sout(e);
        }
    }

    /**
     * 检查系统异常测试
     * <p>
     * RuntimeException 代表的是编程错误
     * RuntimeException及其子类是不受系统检查的特殊异常类
     * 代码中只有 RuntimeException（及其子类）类型的异常可以被忽略，因为编译器强制要求处理所有受检查类型的异常
     */
    private static void checkSystemExceptionTest() {

        try {
            throw new Exception("异常类：Exception");
        } catch (Exception e) {
            sout(e.getMessage());
        }

        throw new RuntimeException("异常类：RuntimeException");

    }

    /**
     * 捕获异常测试
     */
    private static void captureExceptionTest() {

        try {
            throw new SimpleException();
        } catch (SimpleException e) {
            sout("捕获到异常：SimpleException");
        } catch (Exception e) {
            sout("捕获到异常：Exception");
        }

        try {
            throw new SimpleException();
        } catch (Exception e) {
            sout("捕获到异常：Exception");
        }

    }

    /**
     * finally关键字测试
     */
    private static void finallyTest() {

        try {
            throw new SimpleException();
        } catch (SimpleException e) {
            sout("捕获到异常：SimpleException");
        } catch (Exception e) {
            sout("捕获到异常：Exception");
        } finally {
            sout("异常：SimpleException  执行finally");
        }

        try {
            throw new SimpleException();
        } catch (Exception e) {
            sout("捕获到异常：Exception");
        } finally {
            sout("异常：SimpleException  执行finally");
        }

        try {
            try {
                throw new SimpleException();
            } finally {
                sout("异常：SimpleException  执行finally");
            }
        } catch (SimpleException e) {
            sout("捕获到异常：SimpleException");
        }

        // 异常替换
        try {
            try {
                throw new SimpleException();
            } finally {
                throw new MyException();
            }
        } catch (Exception e) {
            sout(e.toString());
        }

        // 异常丢失
        try {
            throw new RuntimeException();
        } finally {
            sout("异常：RuntimeException  执行finally");
        }

    }

    /**
     * try-with-resources 测试
     */
    private static void autoCloseableTest() {

        try {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(new File("SimpleExceptionTest"));
            } catch (FileNotFoundException e) {
                sout("捕获到异常：FileNotFoundException");
            } finally {
                if (fileReader != null) {
                    fileReader.close();
                }
            }
        } catch (IOException e) {
            sout("捕获到异常：IOException");
        }

        try (
                FileReader fileReader = new FileReader(new File("SimpleExceptionTest"));
        ) {
            System.out.println("读取文件流...");
        } catch (IOException e) {
            sout("捕获到异常：IOException");
        }

        try (
                SimpleAutoCloseable simpleAutoCloseable = new SimpleAutoCloseable();
        ) {
            simpleAutoCloseable.execute();
        } catch (Throwable e) {
            sout("捕获到异常：Throwable");
        }
    }

    /**
     * 打印异常堆栈信息
     *
     * 如果只是把当前异常对象重新抛出，那么 printStackTrace() 方法显示的将是原来异常抛出点的调用栈信息，而并非重新抛出点的信息。
     * 要想更新这个信息，可以调用 fillInStackTrace() 方法，这将返回一个 Throwable 对象，它是通过把当前调用栈信息填入原来那个异常对象而建立的
     */
    public static void printStackTraceMsgTest() {
        try {
            throw new Exception("抛出异常");
        } catch (Exception e) {
            System.out.println("Caught Exception");
            System.out.println("getMessage():" + e.getMessage());
            System.out.println("getLocalizedMessage():" + e.getLocalizedMessage());
            System.out.println("toString():" + e);
            System.out.println("printStackTrace():");
            e.printStackTrace(System.out);
        }

        sout("------------------------- 分割线 --------------------------");
        try {
            try {
                throwException();
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            System.out.println("printStackTrace():");
            e.printStackTrace(System.out);
        }

        sout("------------------------- 分割线 --------------------------");
        try {
            try {
                throwException();
            } catch (Exception e) {
                e.fillInStackTrace();
                throw e;
            }
        } catch (Exception e) {
            System.out.println("printStackTrace():");
            e.printStackTrace(System.out);
        }

    }

    private static void throwException() throws Exception {
        throw new Exception("throwException 抛出异常");
    }

    // ---------------- 自定义异常 --------------------------

    static class SimpleException extends Exception {

    }

    static class MyException extends Exception {

        public MyException() {
        }

        public MyException(String message) {
            super(message);
        }

    }

    @NoArgsConstructor
    static class SimpleRuntimeException extends RuntimeException {

        public SimpleRuntimeException(String message) {
            super(message);
        }

    }

    /**
     * try-with-resources 定义子句中创建的对象（在括号内）必须实现 java.lang.AutoCloseable 接口，这个接口只有一个方法：close()
     */
    static class SimpleAutoCloseable implements AutoCloseable {

        public void execute() {
            sout("SimpleAutoCloseable 开始业务处理");
            throw new RuntimeException();
        }

        @Override
        public void close() throws Exception {
            sout("SimpleAutoCloseable 对象已关闭");
        }

    }

    // ---------------- 通用输出服务 --------------------------

    private static void sout(Throwable e) {
        sout(e.getMessage());
    }

    private static void sout(String s) {
        System.out.println(s);
    }

}
