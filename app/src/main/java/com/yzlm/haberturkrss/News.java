package com.yzlm.haberturkrss;

/**
 * Created by yzlm on 12.06.2017.
 */

public class News {
    String id;
    String category;
    String title;
    String desc;
    String image;
    String pubdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    String link;

    public News(String id, String category, String title, String desc, String image, String pubdate, String link) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.pubdate = pubdate;
        this.link = link;
    }
}
