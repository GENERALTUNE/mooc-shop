package org.generaltune.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhumin on 2017/3/8.
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

    private static final long serialVersionUID = 4910225916550731446L;

    private Long id;
    private short region;
    private String username;
    private int status;
    private String type;
    private Date createtime;
    private Date updatetime;
    private long version;
    private String name;
    private long phone;
    private String email;
    private String description;

    public User() {
    }

    public User(Long id, short region, String username, int status, String type, Date createtime, Date updatetime, long version, String name, long phone, String email, String description) {
        this.id = id;
        this.region = region;
        this.username = username;
        this.status = status;
        this.type = type;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.version = version;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.description = description;
    }

    public User(short region, String username, int status, String type, Date createtime, Date updatetime, long version, String name, long phone, String email, String description) {
        this.region = region;
        this.username = username;
        this.status = status;
        this.type = type;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.version = version;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="region")
    public short getRegion() {
        return region;
    }

    public void setRegion(short region) {
        this.region = region;
    }

    @Column(name = "username", length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name="status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    @Column(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Column(name="createtime")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createday) {
        this.createtime = createtime;
    }
    @Column(name = "updatetime")
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updateday) {
        this.updatetime = updatetime;
    }
    @Column(name = "version")
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }
    @Column(name = "phone")
    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
//        StringBuffer sb = new StringBuffer();
//        sb.append("Id: ").append(this.id).append(", firstName: ").append(this.firstname).append(", lastName: ")
//                .append(this.lastname).append(", Designation: ").append(this.designation).append(", Salary: ")
//                .append(this.salary);
//        return sb.toString();

        return "User{" +
                "id=" + id +
                ", region=" + region +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", type='" + type + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
