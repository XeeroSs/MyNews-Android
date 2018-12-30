package com.app.xeross.mynews.Model.Articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Articles {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public List<Result> getResults() {
        return results;
    }

    public class Multimedium {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("type")
        @Expose
        private String type;

        public String getUrl() {
            return url;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

    public class Result {

        @SerializedName("section")
        @Expose
        private String section;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("published_date")
        @Expose
        private String publishedDate;
        @SerializedName("multimedia")
        @Expose
        private List<Multimedium> media = null;
        @SerializedName("media")
        @Expose
        private List<Medium> multimedia = null;
        private String color;

        public String getSection() {
            return section;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public List<Medium> getMultimedia() {
            return multimedia;
        }

        public List<Multimedium> getMultimedias() {
            return media;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public class Medium {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("media-metadata")
        @Expose
        private List<Multimedium> mediaMetadata = null;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Multimedium> getMediaMetadata() {
            return mediaMetadata;
        }

    }
}