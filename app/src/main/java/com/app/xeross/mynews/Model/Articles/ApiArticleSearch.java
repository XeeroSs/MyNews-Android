package com.app.xeross.mynews.Model.Articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiArticleSearch {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("response")
    @Expose
    private Response response;

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

    public void setResponse(Response response) {
        this.response = response;
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

    public class Doc {

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
        @SerializedName("headline")
        @Expose
        private Headline headline;
        @SerializedName("keywords")
        @Expose
        private List<Keyword> keywords = null;
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
        @SerializedName("byline")
        @Expose
        private Byline byline;
        @SerializedName("news_desk")
        @Expose
        private String newsDesk;

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

        public Headline getHeadline() {
            return headline;
        }

        public void setHeadline(Headline headline) {
            this.headline = headline;
        }

        public List<Keyword> getKeywords() {
            return keywords;
        }

        public void setKeywords(List<Keyword> keywords) {
            this.keywords = keywords;
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

        public Byline getByline() {
            return byline;
        }

        public void setByline(Byline byline) {
            this.byline = byline;
        }

        public String getNewsDesk() {
            return newsDesk;
        }

        public void setNewsDesk(String newsDesk) {
            this.newsDesk = newsDesk;
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


}
