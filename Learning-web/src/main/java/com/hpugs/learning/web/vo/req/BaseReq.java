package com.hpugs.learning.web.vo.req;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/1 下午4:41
 */
public class BaseReq {

    /**
     * 页码
     */
    private Integer pageNo = 1;

    /**
     * 每页个数
     */
    private Integer pageSize = 10;

    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 排序类型
     */
    private Integer orderByType;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        computeOffset();
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        computeOffset();
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        computeOffset();
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(Integer orderByType) {
        this.orderByType = orderByType;
    }

    /**
     * 计算分页偏移量
     */
    public Integer computeOffset() {
        if (null == pageNo || null == pageSize) {
            return null;
        }
        offset = (pageNo - 1) * pageSize;
        return offset;
    }
}
