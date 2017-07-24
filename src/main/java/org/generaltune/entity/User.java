package org.generaltune.entity;

import java.util.Date;

/**
 * Created by zhumin on 2017/3/9.
 */
public class User {
    private long uid;
    private String username;
    private String name;
    private String password;
    private String descripiton;
    private long phone;
    private String email;
    private int status;
    private short region;
    private String type;
    private Date updatetime;
    private long version;
    private Date createtime;
    private Date birthday;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripiton() {
        return descripiton;
    }

    public void setDescripiton(String descripiton) {
        this.descripiton = descripiton;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public short getRegion() {
        return region;
    }

    public void setRegion(short region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", descripiton='" + descripiton + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", region=" + region +
                ", type='" + type + '\'' +
                ", updatetime=" + updatetime +
                ", version=" + version +
                ", createtime=" + createtime +
                ", birthday=" + birthday +
                '}';
    }
}
