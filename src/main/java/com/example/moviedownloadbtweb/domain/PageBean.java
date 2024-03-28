package com.example.moviedownloadbtweb.domain;

import java.util.List;

/**
 * 分页查询结果的封装类
 */
public class PageBean<T> {
    /**
     * 总记录数
     */
    private Integer total;
    /**
     * 接收返回的当前页面的数据列表，有多条数据，用list接收
     */
    private List<T> rows;

    public PageBean() {
    }

    public PageBean(Integer total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
