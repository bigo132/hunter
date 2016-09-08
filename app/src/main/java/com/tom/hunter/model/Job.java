package com.tom.hunter.model;

import java.util.Date;

/**
 * Created by txu1 on 9/5/2016.
 */
public class Job {

    /**
     * 唯一ID
     */
    private String id;

    /**
     * 职位名称
     */
    private String name;

    /**
     * 职位描述
     */
    private String description;

    /**
     * 发布时间
     */
    private Date postDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 保留时长
     */
    private int days;

    /**
     * 职位状态
     */
    private int status;

    /**
     * 职位级别
     */
    private int level;

    /**
     * 最小薪资(月)
     */
    private double minSalary;

    /**
     * 最大薪资(月)
     */
    private double maxSalary;

    /**
     * 推荐费
     */
    private double referralFee;

    /**
     * 年薪
     */
    private double yearSalary;

    /**
     * poster 发布者
     */
    private String poster;

    /**
     * type 0: 全职 1:兼职
     */
    private int type;


    /**
     * 学历要求
     */
    private int academicLevel;

    /**
     * 工作经验
     */
    private int experience;

    /**
     * 公司
     */
    private Company company;

    public Job(String id, Company company, int days, String description, String name, int status, int type, double maxSalary, double minSalary) {
        this.id = id;
        this.company = company;
        this.days = days;
        this.description = description;
        this.name = name;
        this.postDate = new Date();
        this.status = status;
        this.type = type;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDays() {
        return days;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getReferralFee() {
        return referralFee;
    }

    public void setReferralFee(double referralFee) {
        this.referralFee = referralFee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public double getYearSalary() {
        return yearSalary;
    }

    public void setYearSalary(double yearSalary) {
        this.yearSalary = yearSalary;
    }

    public String getSimpleInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append(getExperience());
        sb.append(" | ");
        sb.append(getAcademic());
        sb.append(" | ");
        sb.append(getSimpleAddress());
        return sb.toString();
    }

    public String getSalary() {
        StringBuffer sb = new StringBuffer();
        if (minSalary == 0 || maxSalary == 0) {
            sb.append("面谈");
        } else {
            sb.append(minSalary / 1000 + "K -" + maxSalary / 1000 + "K");
        }
        return sb.toString();
    }

    public String getExperience() {
        StringBuffer sb = new StringBuffer();
        if (experience > 0) {
            sb.append(experience + " 年以上");
        } else {
            sb.append("不限");
        }
        return sb.toString();
    }

    public String getAcademic() {
        String academic = null;
        switch (academicLevel) {
            case 0:
                academic = "本科";
                break;
            case 1:
                academic = "专科";
                break;
            case 2:
                academic = "硕士";
                break;
            case 3:
                academic = "博士";
                break;
            default:
                academic = "不限";
                break;
        }
        return academic;
    }

    public String getSimpleAddress() {
        if (company != null) {
            return company.getSimpleLocation();
        }
        return null;
    }
}
