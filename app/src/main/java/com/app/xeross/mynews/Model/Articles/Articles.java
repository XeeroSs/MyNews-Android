package com.app.xeross.mynews.Model.Articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Articles {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("docs")
    @Expose
    private List<Result> docs = null;
    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public List<Result> getResults() {
        return results;
    }

    public class Response {

        @SerializedName("docs")
        @Expose
        private List<Result> docs = null;

        public List<Result> getDocs() {
            return docs;
        }

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
        private String section = null;
        @SerializedName("section_name")
        @Expose
        private String search_section = null;
        @SerializedName("url")
        @Expose
        private String url = null;
        @SerializedName("web_url")
        @Expose
        private String search_url = null;
        @SerializedName("published_date")
        @Expose
        private String publishedDate = null;
        @SerializedName("pub_date")
        @Expose
        private String search_publishedDate = null;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("title_name")
        @Expose
        private String search_title;
        @SerializedName("multimedia")
        @Expose
        private List<Multimedium> media = null;
        @SerializedName("media")
        @Expose
        private List<Medium> multimedia = null;
        private String color;

        public String getSection() {
            if (section != null) {
                return section;
            } else if (search_section != null) {
                return search_section;
            } else {
                return "";
            }
        }

        public String getUrl() {
            if (url != null) {
                return url;
            } else if (search_url != null) {
                return search_url;
            } else {
                return "";
            }
        }

        public String getPublishedDate() {
            if (publishedDate != null) {
                return publishedDate;
            } else if (search_publishedDate != null) {
                return search_publishedDate;
            } else {
                return "";
            }
        }

        public String getTitle() {
            if (title != null) {
                return title;
            } else if (search_title != null) {
                return search_title;
            } else {
                return "";
            }
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