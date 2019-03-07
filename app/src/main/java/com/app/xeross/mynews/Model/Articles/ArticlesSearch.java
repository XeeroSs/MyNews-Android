package com.app.xeross.mynews.Model.Articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesSearch {

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
        @SerializedName("blog")
        @Expose
        private Blog blog;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("multimedia")
        @Expose
        private List<Multimedium> multimedia = null;
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
        @SerializedName("news_desk")
        @Expose
        private String newsDesk;
        @SerializedName("section_name")
        @Expose
        private String sectionName;
        @SerializedName("byline")
        @Expose
        private Byline byline;
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
        private double score;
        @SerializedName("uri")
        @Expose
        private String uri;

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

        public Blog getBlog() {
            return blog;
        }

        public void setBlog(Blog blog) {
            this.blog = blog;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public List<Multimedium> getMultimedia() {
            return multimedia;
        }

        public void setMultimedia(List<Multimedium> multimedia) {
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

        public String getNewsDesk() {
            return newsDesk;
        }

        public void setNewsDesk(String newsDesk) {
            this.newsDesk = newsDesk;
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

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

    }

    public class Facets {

        @SerializedName("section_name")
        @Expose
        private SectionName sectionName;

        public SectionName getSectionName() {
            return sectionName;
        }

        public void setSectionName(SectionName sectionName) {
            this.sectionName = sectionName;
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
        private String printHeadline;
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

        public String getPrintHeadline() {
            return printHeadline;
        }

        public void setPrintHeadline(String printHeadline) {
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
        private String major;

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

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

    }

    public class Legacy {

        @SerializedName("xlarge")
        @Expose
        private String xlarge;
        @SerializedName("xlargewidth")
        @Expose
        private Integer xlargewidth;
        @SerializedName("xlargeheight")
        @Expose
        private Integer xlargeheight;
        @SerializedName("wide")
        @Expose
        private String wide;
        @SerializedName("widewidth")
        @Expose
        private Integer widewidth;
        @SerializedName("wideheight")
        @Expose
        private Integer wideheight;
        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;
        @SerializedName("thumbnailwidth")
        @Expose
        private Integer thumbnailwidth;
        @SerializedName("thumbnailheight")
        @Expose
        private Integer thumbnailheight;

        public String getXlarge() {
            return xlarge;
        }

        public void setXlarge(String xlarge) {
            this.xlarge = xlarge;
        }

        public Integer getXlargewidth() {
            return xlargewidth;
        }

        public void setXlargewidth(Integer xlargewidth) {
            this.xlargewidth = xlargewidth;
        }

        public Integer getXlargeheight() {
            return xlargeheight;
        }

        public void setXlargeheight(Integer xlargeheight) {
            this.xlargeheight = xlargeheight;
        }

        public String getWide() {
            return wide;
        }

        public void setWide(String wide) {
            this.wide = wide;
        }

        public Integer getWidewidth() {
            return widewidth;
        }

        public void setWidewidth(Integer widewidth) {
            this.widewidth = widewidth;
        }

        public Integer getWideheight() {
            return wideheight;
        }

        public void setWideheight(Integer wideheight) {
            this.wideheight = wideheight;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public Integer getThumbnailwidth() {
            return thumbnailwidth;
        }

        public void setThumbnailwidth(Integer thumbnailwidth) {
            this.thumbnailwidth = thumbnailwidth;
        }

        public Integer getThumbnailheight() {
            return thumbnailheight;
        }

        public void setThumbnailheight(Integer thumbnailheight) {
            this.thumbnailheight = thumbnailheight;
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

    public class Multimedium {

        @SerializedName("rank")
        @Expose
        private Integer rank;
        @SerializedName("subtype")
        @Expose
        private String subtype;
        @SerializedName("caption")
        @Expose
        private Object caption;
        @SerializedName("credit")
        @Expose
        private Object credit;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("legacy")
        @Expose
        private Legacy legacy;
        @SerializedName("subType")
        @Expose
        private String subType;
        @SerializedName("crop_name")
        @Expose
        private String cropName;

        public Integer getRank() {
            return rank;
        }

        public void setRank(Integer rank) {
            this.rank = rank;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public Object getCaption() {
            return caption;
        }

        public void setCaption(Object caption) {
            this.caption = caption;
        }

        public Object getCredit() {
            return credit;
        }

        public void setCredit(Object credit) {
            this.credit = credit;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Legacy getLegacy() {
            return legacy;
        }

        public void setLegacy(Legacy legacy) {
            this.legacy = legacy;
        }

        public String getSubType() {
            return subType;
        }

        public void setSubType(String subType) {
            this.subType = subType;
        }

        public String getCropName() {
            return cropName;
        }

        public void setCropName(String cropName) {
            this.cropName = cropName;
        }

    }

    public class Person {

        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("middlename")
        @Expose
        private Object middlename;
        @SerializedName("lastname")
        @Expose
        private String lastname;
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

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public Object getMiddlename() {
            return middlename;
        }

        public void setMiddlename(Object middlename) {
            this.middlename = middlename;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
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
        @SerializedName("facets")
        @Expose
        private Facets facets;

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

        public Facets getFacets() {
            return facets;
        }

        public void setFacets(Facets facets) {
            this.facets = facets;
        }

    }

    public class SectionName {

        @SerializedName("terms")
        @Expose
        private List<Term> terms = null;

        public List<Term> getTerms() {
            return terms;
        }

        public void setTerms(List<Term> terms) {
            this.terms = terms;
        }

    }

    public class Term {

        @SerializedName("term")
        @Expose
        private String term;
        @SerializedName("count")
        @Expose
        private Integer count;

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

    }

}
