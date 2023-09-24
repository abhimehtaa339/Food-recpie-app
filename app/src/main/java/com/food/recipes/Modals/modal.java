package com.food.recipes.Modals;

public class modal {
    String title, thumbnail_image , url;
    int readyin , id;

    public modal(String title, String thumbnail_image, int readyin, String url) {
        this.title = title;
        this.thumbnail_image = thumbnail_image;
        this.readyin = readyin;
        this.url = url;
    }

    public String getLabel() {
        return title;
    }

    public void setLabel(String label) {
        this.title = label;
    }

    public String getThumbnail_image() {
        return thumbnail_image;
    }

    public void setThumbnail_image(String thumbnail_image) {
        this.thumbnail_image = thumbnail_image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
