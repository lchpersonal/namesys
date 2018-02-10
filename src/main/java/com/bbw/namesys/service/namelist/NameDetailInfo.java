package com.bbw.namesys.service.namelist;

import java.util.Date;

public class NameDetailInfo {
    private int id;
    private String username;
    private String name;  //姓名
    private int sex;
    private int age;
    private String nativePlace;//'籍贯'
    private String workplace;//'工作地'
    private String health;//'健康状况';
    private String education;//'文化程度';
    private String characteristics;//'体貌特征';
    private String relationship;//'与我的关系';
    private String coexistenceMode;//'相处模式';
    private String evaluateMe;//'如何评价我';
    private String workExperience;//'工作经历';
    private String lifeExperience;//'重要的人生阅历';
    private String income;//'收入情况';
    private String entrepreneurship;//'是否支持经商和创业';
    private String vacations;//
    private String maritalStatus;  //'婚姻状况';
    private String memberOfFamily;//'家庭成员';
    private String familyStatus;//'家庭地位';
    private String familyIncomeAndSupport;//'家庭主要收入和支持';
    private String character;//'个人性格';
    private String policyConcern;//'政策关注度';
    private String loveReading;//'是否爱看书';
    private String hobby;//'兴趣爱好';
    private String specialty;//'特长';
    private String topicOfLike;//'喜欢聊的话题';
    private String dream;//'梦想';
    private String concept;//'思想观念';
    private String reasonOfInvite;//'约他的理由';
    private String others;//'其他';
    private int echelon;//'0' COMMENT '梯队';
    private int pavingTimes;//'0' COMMENT '铺垫次数';
    private Date addTime;//'添加时间';
    private Date updateTime;//'更新时间';
    private int status; //状态1：已邀约，2邀约成功，3邀约失败，4带成，5带失败

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getCoexistenceMode() {
        return coexistenceMode;
    }

    public void setCoexistenceMode(String coexistenceMode) {
        this.coexistenceMode = coexistenceMode;
    }

    public String getEvaluateMe() {
        return evaluateMe;
    }

    public void setEvaluateMe(String evaluateMe) {
        this.evaluateMe = evaluateMe;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getLifeExperience() {
        return lifeExperience;
    }

    public void setLifeExperience(String lifeExperience) {
        this.lifeExperience = lifeExperience;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getEntrepreneurship() {
        return entrepreneurship;
    }

    public void setEntrepreneurship(String entrepreneurship) {
        this.entrepreneurship = entrepreneurship;
    }

    public String getVacations() {
        return vacations;
    }

    public void setVacations(String vacations) {
        this.vacations = vacations;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMemberOfFamily() {
        return memberOfFamily;
    }

    public void setMemberOfFamily(String memberOfFamily) {
        this.memberOfFamily = memberOfFamily;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getFamilyIncomeAndSupport() {
        return familyIncomeAndSupport;
    }

    public void setFamilyIncomeAndSupport(String familyIncomeAndSupport) {
        this.familyIncomeAndSupport = familyIncomeAndSupport;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getPolicyConcern() {
        return policyConcern;
    }

    public void setPolicyConcern(String policyConcern) {
        this.policyConcern = policyConcern;
    }

    public String getLoveReading() {
        return loveReading;
    }

    public void setLoveReading(String loveReading) {
        this.loveReading = loveReading;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getTopicOfLike() {
        return topicOfLike;
    }

    public void setTopicOfLike(String topicOfLike) {
        this.topicOfLike = topicOfLike;
    }

    public String getDream() {
        return dream;
    }

    public void setDream(String dream) {
        this.dream = dream;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getReasonOfInvite() {
        return reasonOfInvite;
    }

    public void setReasonOfInvite(String reasonOfInvite) {
        this.reasonOfInvite = reasonOfInvite;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public int getEchelon() {
        return echelon;
    }

    public void setEchelon(int echelon) {
        this.echelon = echelon;
    }

    public int getPavingTimes() {
        return pavingTimes;
    }

    public void setPavingTimes(int pavingTimes) {
        this.pavingTimes = pavingTimes;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
