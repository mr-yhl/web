package cn.com.mryhl.domain;

/**
 * 角色实体
 */
public class Role {
    private Integer id;
    private String role_name;
    private String role_desc;

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getRole_name() {
        return this.role_name;
    }

    public void setRole_name(final String role_name) {
        this.role_name = role_name;
    }

    public String getRole_desc() {
        return this.role_desc;
    }

    public void setRole_desc(final String role_desc) {
        this.role_desc = role_desc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                ", role_desc='" + role_desc + '\'' +
                '}';
    }
}
