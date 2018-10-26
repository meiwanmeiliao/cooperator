package com.zl.model;

import com.zl.dto.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zhuolin
 * @program: cooperator
 * @date 2018/10/25
 * @description: ${description}
 **/
@Entity
@Table(name = "u_user")
public class User extends BaseModel {
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户编码
     */
    private String code;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 文化程度
     */
    private String education;
    /**
     * 毕业院校
     */
    private String university;
    /**
     * 专业
     */
    private String professional;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 头像id
     */
    private Long fileId;
    /**
     * 邮箱
     */
    private String mail;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
