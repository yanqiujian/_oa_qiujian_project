package com.qiujian.dto;

import java.io.Serializable;

public class History implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer hsId;
    private String hsZhuguanname;
    private String hsTime;
    private String hsGanwei;
    private String hsJihao;
    private String hsFangshi;
    private String hsDidian;
    private String hsApplyname;
    private String hsDepartment;
    private String hsZhuangtai;
    private String hsPrid;

    public String getHsPrid() {
        return hsPrid;
    }

    public void setHsPrid(String hsPrid) {
        this.hsPrid = hsPrid;
    }

    public Integer getHsId() {
        return hsId;
    }

    public void setHsId(Integer hsId) {
        this.hsId = hsId;
    }

    public String getHsZhuguanname() {
        return hsZhuguanname;
    }

    public void setHsZhuguanname(String hsZhuguanname) {
        this.hsZhuguanname = hsZhuguanname;
    }

    public String getHsTime() {
        return hsTime;
    }

    public void setHsTime(String hsTime) {
        this.hsTime = hsTime;
    }

    public String getHsGanwei() {
        return hsGanwei;
    }

    public void setHsGanwei(String hsGanwei) {
        this.hsGanwei = hsGanwei;
    }

    public String getHsJihao() {
        return hsJihao;
    }

    public void setHsJihao(String hsJihao) {
        this.hsJihao = hsJihao;
    }

    public String getHsFangshi() {
        return hsFangshi;
    }

    public void setHsFangshi(String hsFangshi) {
        this.hsFangshi = hsFangshi;
    }

    public String getHsDidian() {
        return hsDidian;
    }

    public void setHsDidian(String hsDidian) {
        this.hsDidian = hsDidian;
    }

    public String getHsApplyname() {
        return hsApplyname;
    }

    public void setHsApplyname(String hsApplyname) {
        this.hsApplyname = hsApplyname;
    }

    public String getHsDepartment() {
        return hsDepartment;
    }

    public void setHsDepartment(String hsDepartment) {
        this.hsDepartment = hsDepartment;
    }

    public String getHsZhuangtai() {
        return hsZhuangtai;
    }

    public void setHsZhuangtai(String hsZhuangtai) {
        this.hsZhuangtai = hsZhuangtai;
    }

    @Override
    public String toString() {
        return "History{" +
                "hsId=" + hsId +
                ", hsZhuguanname='" + hsZhuguanname + '\'' +
                ", hsTime='" + hsTime + '\'' +
                ", hsGanwei='" + hsGanwei + '\'' +
                ", hsJihao='" + hsJihao + '\'' +
                ", hsFangshi='" + hsFangshi + '\'' +
                ", hsDidian='" + hsDidian + '\'' +
                ", hsApplyname='" + hsApplyname + '\'' +
                ", hsDepartment='" + hsDepartment + '\'' +
                ", hsZhuangtai='" + hsZhuangtai + '\'' +
                '}';
    }
}
