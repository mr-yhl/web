package cn.com.mryhl.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户实体
 */
public class User implements Serializable {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(final String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }


}
