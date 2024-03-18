package com.example.moviedownloadbtweb.domain;

import java.util.List;

/**
 * 分页查询结果的封装类
 */
public class PageBean {
    //总记录数
    private Integer total;
    //接收返回的数据列表
    private List rows;

    public PageBean() {
    }

    public PageBean(Integer total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
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
