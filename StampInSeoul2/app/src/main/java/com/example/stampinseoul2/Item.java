package com.example.stampinseoul2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("addr1")
    @Expose
    private String addr1;
    @SerializedName("areacode")
    @Expose
    private Integer areacode;
    @SerializedName("cat1")
    @Expose
    private String cat1;
    @SerializedName("cat2")
    @Expose
    private String cat2;
    @SerializedName("cat3")
    @Expose
    private String cat3;
    @SerializedName("contentid")
    @Expose
    private Integer contentid;
    @SerializedName("contenttypeid")
    @Expose
    private Integer contenttypeid;
    @SerializedName("createdtime")
    @Expose
    private Integer createdtime;
    @SerializedName("eventenddate")
    @Expose
    private Integer eventenddate;
    @SerializedName("eventstartdate")
    @Expose
    private Integer eventstartdate;
    @SerializedName("firstimage")
    @Expose
    private String firstimage;
    @SerializedName("firstimage2")
    @Expose
    private String firstimage2;
    @SerializedName("mapx")
    @Expose
    private Double mapx;
    @SerializedName("mapy")
    @Expose
    private Double mapy;
    @SerializedName("mlevel")
    @Expose
    private Integer mlevel;
    @SerializedName("modifiedtime")
    @Expose
    private Integer modifiedtime;
    @SerializedName("readcount")
    @Expose
    private Integer readcount;
    @SerializedName("sigungucode")
    @Expose
    private Integer sigungucode;
    @SerializedName("tel")
    @Expose
    private String tel;
    @SerializedName("title")
    @Expose
    private String title;

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public Integer getAreacode() {
        return areacode;
    }

    public void setAreacode(Integer areacode) {
        this.areacode = areacode;
    }

    public String getCat1() {
        return cat1;
    }

    public void setCat1(String cat1) {
        this.cat1 = cat1;
    }

    public String getCat2() {
        return cat2;
    }

    public void setCat2(String cat2) {
        this.cat2 = cat2;
    }

    public String getCat3() {
        return cat3;
    }

    public void setCat3(String cat3) {
        this.cat3 = cat3;
    }

    public Integer getContentid() {
        return contentid;
    }

    public void setContentid(Integer contentid) {
        this.contentid = contentid;
    }

    public Integer getContenttypeid() {
        return contenttypeid;
    }

    public void setContenttypeid(Integer contenttypeid) {
        this.contenttypeid = contenttypeid;
    }

    public Integer getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Integer createdtime) {
        this.createdtime = createdtime;
    }

    public Integer getEventenddate() {
        return eventenddate;
    }

    public void setEventenddate(Integer eventenddate) {
        this.eventenddate = eventenddate;
    }

    public Integer getEventstartdate() {
        return eventstartdate;
    }

    public void setEventstartdate(Integer eventstartdate) {
        this.eventstartdate = eventstartdate;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public String getFirstimage2() {
        return firstimage2;
    }

    public void setFirstimage2(String firstimage2) {
        this.firstimage2 = firstimage2;
    }

    public Double getMapx() {
        return mapx;
    }

    public void setMapx(Double mapx) {
        this.mapx = mapx;
    }

    public Double getMapy() {
        return mapy;
    }

    public void setMapy(Double mapy) {
        this.mapy = mapy;
    }

    public Integer getMlevel() {
        return mlevel;
    }

    public void setMlevel(Integer mlevel) {
        this.mlevel = mlevel;
    }

    public Integer getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Integer modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public Integer getReadcount() {
        return readcount;
    }

    public void setReadcount(Integer readcount) {
        this.readcount = readcount;
    }

    public Integer getSigungucode() {
        return sigungucode;
    }

    public void setSigungucode(Integer sigungucode) {
        this.sigungucode = sigungucode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
