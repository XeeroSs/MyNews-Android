package com.app.xeross.mynews.Model.Articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Articles {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Response getResponse() {
        return response;
    }

    public List<Result> getResult() {
        return results;
    }

    public class Blog {

    }

    public class Byline {

        @SerializedName("original")
        @Expose
        private String original;
        @SerializedName("person")
        @Expose
        private List<Person> person = null;
        @SerializedName("organization")
        @Expose
        private String organization;

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public List<Person> getPerson() {
            return person;
        }

        public void setPerson(List<Person> person) {
            this.person = person;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

    }

    public class Headline {

        @SerializedName("main")
        @Expose
        private String main;
        @SerializedName("kicker")
        @Expose
        private Object kicker;
        @SerializedName("content_kicker")
        @Expose
        private Object contentKicker;
        @SerializedName("print_headline")
        @Expose
        private Object printHeadline;
        @SerializedName("name")
        @Expose
        private Object name;
        @SerializedName("seo")
        @Expose
        private Object seo;
        @SerializedName("sub")
        @Expose
        private Object sub;

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public Object getKicker() {
            return kicker;
        }

        public void setKicker(Object kicker) {
            this.kicker = kicker;
        }

        public Object getContentKicker() {
            return contentKicker;
        }

        public void setContentKicker(Object contentKicker) {
            this.contentKicker = contentKicker;
        }

        public Object getPrintHeadline() {
            return printHeadline;
        }

        public void setPrintHeadline(Object printHeadline) {
            this.printHeadline = printHeadline;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getSeo() {
            return seo;
        }

        public void setSeo(Object seo) {
            this.seo = seo;
        }

        public Object getSub() {
            return sub;
        }

        public void setSub(Object sub) {
            this.sub = sub;
        }

    }

    public class Meta {

        @SerializedName("hits")
        @Expose
        private Integer hits;
        @SerializedName("offset")
        @Expose
        private Integer offset;
        @SerializedName("time")
        @Expose
        private Integer time;

        public Integer getHits() {
            return hits;
        }

        public void setHits(Integer hits) {
            this.hits = hits;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Integer getTime() {
            return time;
        }

        public void setTime(Integer time) {
            this.time = time;
        }

    }

    public class Keyword {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("value")
        @Expose
        private String value;
        @SerializedName("rank")
        @Expose
        private Integer rank;
        @SerializedName("major")
        @Expose
        private Object major;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getRank() {
            return rank;
        }

        public void setRank(Integer rank) {
            this.rank = rank;
        }

        public Object getMajor() {
            return major;
        }

        public void setMajor(Object major) {
            this.major = major;
        }

    }

    public class Person {

        @SerializedName("firstname")
        @Expose
        private Object firstname;
        @SerializedName("middlename")
        @Expose
        private Object middlename;
        @SerializedName("lastname")
        @Expose
        private Object lastname;
        @SerializedName("qualifier")
        @Expose
        private Object qualifier;
        @SerializedName("title")
        @Expose
        private Object title;
        @SerializedName("role")
        @Expose
        private String role;
        @SerializedName("organization")
        @Expose
        private String organization;
        @SerializedName("rank")
        @Expose
        private Integer rank;

        public Object getFirstname() {
            return firstname;
        }

        public void setFirstname(Object firstname) {
            this.firstname = firstname;
        }

        public Object getMiddlename() {
            return middlename;
        }

        public void setMiddlename(Object middlename) {
            this.middlename = middlename;
        }

        public Object getLastname() {
            return lastname;
        }

        public void setLastname(Object lastname) {
            this.lastname = lastname;
        }

        public Object getQualifier() {
            return qualifier;
        }

        public void setQualifier(Object qualifier) {
            this.qualifier = qualifier;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public Integer getRank() {
            return rank;
        }

        public void setRank(Integer rank) {
            this.rank = rank;
        }

    }

    public class Response {

        @SerializedName("docs")
        @Expose
        private List<Doc> docs = null;
        @SerializedName("meta")
        @Expose
        private Meta meta;

        public List<Doc> getDocs() {
            return docs;
        }

        public void setDocs(List<Doc> docs) {
            this.docs = docs;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

    }

    public class Doc {

        @SerializedName("section_name")
        @Expose
        private String search_section = null;
        @SerializedName("web_url")
        @Expose
        private String search_url = null;
        @SerializedName("pub_date")
        @Expose
        private String search_publishedDate = null;
        @SerializedName("title_name")
        @Expose
        private String search_title;
        @SerializedName("web_url")
        @Expose
        private String webUrl;
        @SerializedName("snippet")
        @Expose
        private String snippet;
        @SerializedName("abstract")
        @Expose
        private String _abstract;
        @SerializedName("print_page")
        @Expose
        private String printPage;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("multimedia")
        @Expose
        private List<Object> multimedia = null;
        @SerializedName("pub_date")
        @Expose
        private String pubDate;
        @SerializedName("document_type")
        @Expose
        private String documentType;
        @SerializedName("type_of_material")
        @Expose
        private String typeOfMaterial;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("word_count")
        @Expose
        private Integer wordCount;
        @SerializedName("score")
        @Expose
        private Integer score;
        @SerializedName("section_name")
        @Expose
        private String sectionName;
        @SerializedName("news_desk")
        @Expose
        private String newsDesk;
        @SerializedName("results")
        @Expose
        private List<Result> results = null;

        public List<Result> getResult() {
            return results;
        }

        public String getSection() {
            return search_section;
        }

        public String getUrl() {
            return search_url;
        }

        public String getPublishedDate() {
            return search_publishedDate;
        }

        public String getTitle() {
            return search_title;
        }


        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public String getSnippet() {
            return snippet;
        }

        public void setSnippet(String snippet) {
            this.snippet = snippet;
        }

        public String getAbstract() {
            return _abstract;
        }

        public void setAbstract(String _abstract) {
            this._abstract = _abstract;
        }

        public String getPrintPage() {
            return printPage;
        }

        public void setPrintPage(String printPage) {
            this.printPage = printPage;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public List<Object> getMultimedia() {
            return multimedia;
        }

        public void setMultimedia(List<Object> multimedia) {
            this.multimedia = multimedia;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getTypeOfMaterial() {
            return typeOfMaterial;
        }

        public void setTypeOfMaterial(String typeOfMaterial) {
            this.typeOfMaterial = typeOfMaterial;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getWordCount() {
            return wordCount;
        }

        public void setWordCount(Integer wordCount) {
            this.wordCount = wordCount;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public String getSectionName() {
            return sectionName;
        }

        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }

        public String getNewsDesk() {
            return newsDesk;
        }

        public void setNewsDesk(String newsDesk) {
            this.newsDesk = newsDesk;
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
        @SerializedName("url")
        @Expose
        private String url = null;
        @SerializedName("published_date")
        @Expose
        private String publishedDate = null;
        @SerializedName("title")
        @Expose
        private String title;
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

        public String getUrl() {
                return url;
        }

        public String getPublishedDate() {
                return publishedDate;
        }

        public String getTitle() {
                return title;
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