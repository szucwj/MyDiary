package com.byd.mydiary;

public class Diary {
    private int _id;
    private String title;
    private String content;
    private String date;

    public Diary() {
    }

    public Diary(int _id, String title, String content, String date) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int get_id() {

        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
