package com.food.recipes;

public class modal {
    String label , thumbnail_image , regular_image , url , cuisine_type ;

    public modal(String label, String thumbnail_image) {
        this.label = label;
        this.thumbnail_image = thumbnail_image;
    }



    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getThumbnail_image() {
        return thumbnail_image;
    }

    public void setThumbnail_image(String thumbnail_image) {
        this.thumbnail_image = thumbnail_image;
    }

    public String getRegular_image() {
        return regular_image;
    }

    public void setRegular_image(String regular_image) {
        this.regular_image = regular_image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCuisine_type() {
        return cuisine_type;
    }

    public void setCuisine_type(String cuisine_type) {
        this.cuisine_type = cuisine_type;
    }
}
