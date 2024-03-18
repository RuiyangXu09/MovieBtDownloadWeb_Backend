package com.example.moviedownloadbtweb.domain;

import java.time.LocalDate;
/**
 * 电影信息实体类
 */

public class MovieBt {
    private Integer id;
    private String movieName;
    private Integer category; //1：动作，2：科幻，3：冒险，4：动画
    private LocalDate createTime;
    private LocalDate updateTime;
    private String btDownloadUrl;
    private String subtitleDownloadUrl;
    private Integer countDownload;

    public MovieBt() {
    }

    public MovieBt(Integer id, String movieName, Integer category, LocalDate createTime, LocalDate updateTime, String btDownloadUrl, String subtitleDownloadUrl, Integer countDownload) {
        this.id = id;
        this.movieName = movieName;
        this.category = category;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.btDownloadUrl = btDownloadUrl;
        this.subtitleDownloadUrl = subtitleDownloadUrl;
        this.countDownload = countDownload;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getBtDownloadUrl() {
        return btDownloadUrl;
    }

    public void setBtDownloadUrl(String btDownloadUrl) {
        this.btDownloadUrl = btDownloadUrl;
    }

    public String getSubtitleDownloadUrl() {
        return subtitleDownloadUrl;
    }

    public void setSubtitleDownloadUrl(String subtitleDownloadUrl) {
        this.subtitleDownloadUrl = subtitleDownloadUrl;
    }

    public Integer getCountDownload() {
        return countDownload;
    }

    public void setCountDownload(Integer countDownload) {
        this.countDownload = countDownload;
    }

    @Override
    public String toString() {
        return "MovieBt{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", category=" + category +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", btDownloadUrl='" + btDownloadUrl + '\'' +
                ", subtitleDownloadUrl='" + subtitleDownloadUrl + '\'' +
                ", countDownload=" + countDownload +
                '}';
    }
}
