package com.aditya.BlogPost.model;

import java.util.List;

public class PostModel {
    private String title;
    private String tags;
    private String blogContent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", blogContent='" + blogContent + '\'' +
                '}';
    }
}
