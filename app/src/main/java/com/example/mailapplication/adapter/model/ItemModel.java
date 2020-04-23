package com.example.mailapplication.adapter.model;

public class ItemModel {

    private String tag;
    private String name;
    private String header;
    private String content;
    private boolean favorite;
    private boolean chosen;

    public ItemModel(String tag, String name, String header, String content) {
        this.tag = tag;
        this.name = name;
        this.header = header;
        this.content = content;
        this.chosen = false;
        this.favorite = false;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }
}
