package com.tom.hunter.model;


import java.util.List;

/**
 * Created by txu1 on 9/5/2016.
 */
public class Company {

    /**
     * 公司LOGO
     */
    private String logo;

    /**
     * 公司名
     */
    private String name;

    /**
     * 公司简介
     */
    private String description;

    /**
     * 公司类型: 国企，外企，台商独资
     */
    private int type;

    /**
     * 雇员人数
     */
    private int employeeCount;

    /**
     * 网站
     */
    private String website;

    /**
     * 公司电话
     */
    private String telephone;

    /**
     * 公司评级
     */
    private int rateLevel;

    /**
     * 公司地址
     */
    private Address address;

    /**
     * 公司环境
     */
    private List<Photo> photos;

    public Company(Address address, String description, int employeeCount, String logo, String name, int rateLevel, String telephone, int type, String website, List<Photo> photos) {
        this.address = address;
        this.description = description;
        this.employeeCount = employeeCount;
        this.logo = logo;
        this.name = name;
        this.rateLevel = rateLevel;
        this.telephone = telephone;
        this.type = type;
        this.website = website;
        this.photos = photos;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRateLevel() {
        return rateLevel;
    }

    public void setRateLevel(int rateLevel) {
        this.rateLevel = rateLevel;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * Simple company info for list item
     *
     * @return
     */
    public String getSimpleInfo() {
        return name + "(" + employeeCount + ")";
    }

    /**
     * Simple company location address
     *
     * @return
     */
    public String getSimpleLocation() {
        if (address != null) {
            return address.getCity() + address.getCounty();
        }
        return null;
    }
}
