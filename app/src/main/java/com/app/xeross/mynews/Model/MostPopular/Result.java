
package com.app.xeross.mynews.Model.MostPopular;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private String section;
    private String subsection;
    private String title;
    private String _abstract;
    private String url;
    private String byline;
    private String itemType;
    private String updatedDate;
    private String createdDate;
    private String publishedDate;
    private String materialTypeFacet;
    private String kicker;
    private List<String> desFacet = null;
    private List<String> orgFacet = null;
    private List<String> perFacet = null;
    private List<String> geoFacet = null;
    private List<Multimedium> multimedia = null;
    private String shortUrl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    public void setMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public List<String> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    public List<String> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(List<String> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public List<String> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(List<String> perFacet) {
        this.perFacet = perFacet;
    }

    public List<String> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(List<String> geoFacet) {
        this.geoFacet = geoFacet;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
