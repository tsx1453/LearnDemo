package com.example.learndemo.recyclerview.zhihuAd;

public class ItemBean {

    private int index;
    private String title;
    private boolean isAd;

    public ItemBean(int index) {
        this.index = index;
        this.title = String.valueOf(index);
        this.isAd = false;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAd() {
        return isAd;
    }

    public void setAd(boolean ad) {
        isAd = ad;
    }
}
