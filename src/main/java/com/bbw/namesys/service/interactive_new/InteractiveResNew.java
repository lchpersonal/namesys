package com.bbw.namesys.service.interactive_new;

public class InteractiveResNew {
    private int id;
    private String net;
    private String name;
    private String sex;
    private int age;
    private String nativeplace;
    private String parent;
    private String tradition;
    private String payfor;
    private String tel;
    private String wechart;
    private String other;
    private String info;

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getTradition() {
        return tradition;
    }

    public void setTradition(String tradition) {
        this.tradition = tradition;
    }

    public String getPayfor() {
        return payfor;
    }

    public void setPayfor(String payfor) {
        this.payfor = payfor;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWechart() {
        return wechart;
    }

    public void setWechart(String wechart) {
        this.wechart = wechart;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public String format(){
        return String.format("【所属网】%s\n" +
                        "【姓名】%s\n" +
                        "【性别】%s\n" +
                        "【籍贯】%s\n" +
                        "【年龄】%s\n" +
                        "【推荐人】%s\n" +
                        "【传统行业】%s\n" +
                        "【付出工作】%s\n" +
                        "【电话】%s\n" +
                        "【微信】%s\n" +
                        "【备注】%s", getNet(), getName(), getSex(), getNativeplace(), getAge(), getParent(),
                getTradition(), getPayfor(), getTel(), getWechart(), getOther());
    }
}
