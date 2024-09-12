package vn.edu.iuh.fit.demo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "account", schema = "week1")
@NamedQueries({
        @NamedQuery(name = "Account.findByAccountId", query = "select a from Account a where upper(a.accountId) = upper(:accountId)"),
        @NamedQuery(name = "Account.findAll", query = "select a from Account a"),
        @NamedQuery(name = "Account.existsByAccountId", query = "select (count(a) > 0) from Account a where a.accountId = :accountId"),
})

public class Account {
    @Id
    @Column(name = "account_id", nullable = false, length = 50)
    private String accountId;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 50)
    private String phone;

    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Byte status;

    public Account() {
    }

    public Account(String accountId, String fullName, String password, String email, String phone, Byte status) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

}