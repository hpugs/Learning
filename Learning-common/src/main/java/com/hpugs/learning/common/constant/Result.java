package com.hpugs.learning.common.constant;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/1 下午4:54
 */
public class Result<T> implements Serializable {

    /**
     * 状态
     */
    private boolean status;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 返回值
     */
    private int responseCode;

    /**
     * 结果对象
     */
    private T entry;

    /**
     * 数据总数
     */
    private int count;

    /**
     * 是否存在下一页
     */
    private int hasNext;

    /**
     * 新增 fail 方法，消除 IDE 的 warning
     *
     * @param responseCode int
     * @param message      String
     * @param <T>          T
     * @return Result<T>
     */
    public static <T> Result<T> buildFailResult(int responseCode, String message) {

        Result<T> result = new Result<>();

        result.setStatus(false);
        result.setResponseCode(responseCode);
        result.setMessage(message);

        return result;
    }

    public int getHasNext() {
        return hasNext;
    }

    public void setHasNext(int hasNext) {
        this.hasNext = hasNext;
    }

    /**
     * 新增 fail 方法，消除 IDE 的 warning
     *
     * @param message String
     * @param <T>     T
     * @return Result<T>
     */
    public static <T> Result<T> buildFailResult(String message) {

        Result<T> result = new Result<>();

        result.setStatus(false);
        result.setMessage(message);

        return result;
    }


    /**
     * 新增 fail 方法，消除 IDE 的 warning
     *
     * @param <T> T
     * @return Result<T>
     */
    public static <T> Result<T> buildFailResult() {

        Result<T> result = new Result<>();

        result.setStatus(false);

        return result;
    }

    /**
     * 新增 fail 方法，消除 IDE 的 warning
     *
     * @param errorEnum ErrorEnum
     * @param <T>       T
     * @return Result<T>
     */
    public static <T> Result<T> buildFailResult(ErrorEnum errorEnum) {

        return buildFailResult(errorEnum.getValue(), errorEnum.getName());
    }

    /**
     * 新增 success 方法，消除 IDE 的 warning
     *
     * @param entry T
     * @param count int
     * @param <T>   T
     * @return Result<T>
     */
    public static <T> Result<T> buildSuccessResult(T entry, int count) {

        Result<T> result = new Result<>();

        result.setStatus(true);
        result.setEntry(entry);
        result.setCount(count);

        return result;
    }

    /**
     * 新增 success 方法，消除 IDE 的 warning
     *
     * @param <T> T
     * @return Result<T>
     */
    public static <T> Result<T> buildSuccessResult() {

        Result<T> result = new Result<>();

        result.setStatus(true);

        return result;
    }


    /**
     * 推荐使用 消除类型校验出现idea 提示
     *
     * @param entry 实体
     * @param count 查询时总数量
     * @param <T>
     * @return t
     */
    public static <T> Result<T> buildSuccess(T entry, int count) {
        Result<T> result = new Result<>();
        result.setStatus(true);
        result.setEntry(entry);
        result.setCount(count);
        return result;
    }

    /**
     * 推荐使用 消除类型校验出现idea 提示
     *
     * @param entry 实体
     * @param <T>   类型
     * @return Result
     */
    public static <T> Result<T> buildSuccess(T entry) {
        return buildSuccess(entry, 0);
    }


    /**
     * 推荐使用 消除类型校验出现idea 提示
     *
     * @param <T> 实体类型
     * @return
     */
    public static <T> Result<T> buildSuccess() {
        return buildSuccess(null, 0);
    }

    /**
     * 新增 success 方法，消除 IDE 的 warning
     *
     * @param entry T
     * @param <T>   T
     * @return Result<T>
     */
    public static <T> Result<T> buildSuccessResult(T entry) {

        return buildSuccessResult(entry, 0);
    }


    public static Result success() {
        Result result = new Result();
        result.setStatus(true);
        return result;
    }


    public static Result success(Object entry, int count) {
        Result result = new Result();
        result.setCount(count);
        result.setEntry(entry);
        result.setStatus(true);
        return result;
    }


    public static Result success(Object entry) {
        Result result = new Result();
        result.setStatus(true);
        result.setEntry(entry);
        return result;
    }

    public static Result success(Object entry, String message) {
        Result result = new Result();
        result.setStatus(true);
        result.setEntry(entry);
        result.setMessage(message);
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setStatus(false);
        return result;
    }

    public static Result fail(ErrorEnum errorEnum) {
        Result result = new Result();
        result.setStatus(false);
        result.setMessage(errorEnum.getName());
        result.setResponseCode(errorEnum.getValue());

        return result;
    }

    public static Result fail(int code, String msg) {
        Result result = new Result();
        result.setStatus(false);
        result.setMessage(msg);
        result.setResponseCode(code);

        return result;
    }

    public static Result fail(int code, String msg, int count) {
        Result result = new Result();
        result.setStatus(false);
        result.setMessage(msg);
        result.setResponseCode(code);
        result.setCount(count);

        return result;
    }

    public static Result fail(Object object) {
        Result result = new Result();
        result.setEntry(object);
        result.setStatus(false);
        return result;
    }


    public static <T> Result<T> buildFail(String msg) {
        Result<T> result = new Result<>();
        result.setStatus(false);
        result.setMessage(msg);
        result.setResponseCode(ErrorEnum.DEFAULT_ERROR.getValue());
        return result;
    }

    /**
     * 推荐使用
     *
     * @param errorEnum
     * @param <T>
     * @return
     */
    public static <T> Result<T> buildFail(ErrorEnum errorEnum) {
        Result<T> result = new Result<>();
        result.setStatus(false);
        result.setMessage(errorEnum.getName());
        result.setResponseCode(errorEnum.getValue());
        return result;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public T getEntry() {
        return entry;
    }

    public void setEntry(T entry) {
        this.entry = entry;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        String str;
        try {
            str = JSON.toJSONString(this);
        } catch (RuntimeException e) {
            str = "";
        }
        return str;
    }

}
