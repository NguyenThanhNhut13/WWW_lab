package vn.edu.iuh.fit.demo.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role", schema = "week1")
@NamedQueries({
        @NamedQuery(name = "Role.findAll", query = "select r from Role r"),
        @NamedQuery(name = "Role.findByRoleId", query = "select r from Role r where r.roleId = :roleId"),
        @NamedQuery(name = "Role.existsByRoleId", query = "select (count(r) > 0) from Role r where r.roleId = :roleId"),
        @NamedQuery(name = "Role.updateRoleByRoleId", query = "update Role r set r.roleName = :roleName, r.description = :description, r.status = :status where r.roleId = :roleId"),
        @NamedQuery(name = "Role.deleteByRoleId", query = "delete from Role r where r.roleId = :roleId")
})
public class Role {
    @Id
    @Column(name = "role_id", nullable = false, length = 50)
    private String roleId;

    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "status", nullable = false)
    private Byte status;

    @OneToMany(mappedBy = "role")
    private Set<GrantAccess> grantAccesses = new LinkedHashSet<>();

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Set<GrantAccess> getGrantAccesses() {
        return grantAccesses;
    }

    public void setGrantAccesses(Set<GrantAccess> grantAccesses) {
        this.grantAccesses = grantAccesses;
    }

}