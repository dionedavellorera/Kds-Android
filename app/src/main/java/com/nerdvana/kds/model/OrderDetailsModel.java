package com.nerdvana.kds.model;

public class OrderDetailsModel {
    private String name;
    private String qty;
    private String imageUrl;
    private String recipe;
    private boolean isChecked;
    private String videoUrl;

    public OrderDetailsModel(String name, String qty, boolean isChecked,
                             String imageUrl, String recipe,
                             String videoUrl) {
        this.name = name;
        this.qty = qty;
        this.isChecked = isChecked;
        this.imageUrl = imageUrl;
        this.recipe = recipe;
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRecipe() {
        return recipe;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getName() {
        return name;
    }

    public String getQty() {
        return qty;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
