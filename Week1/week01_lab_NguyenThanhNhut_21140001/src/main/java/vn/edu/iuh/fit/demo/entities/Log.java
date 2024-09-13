package vn.edu.iuh.fit.demo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "log", schema = "week1")
@NamedQueries({
        @NamedQuery(name = "Log.findAll", query = "select l from Log l"),
        @NamedQuery(name = "Log.findById", query = "select l from Log l where l.id = :id"),
        @NamedQuery(name = "Log.updateById", query = "update Log l set l.accountId = :accountId, l.loginTime = :loginTime, l.logoutTime = :logoutTime, l.notes = :notes where l.id = :id"),
        @NamedQuery(name = "Log.existsById", query = "select (count(l) > 0) from Log l where l.id = :id"),

})
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "account_id", nullable = false, length = 50)
    private String accountId;

    @ColumnDefault("current_timestamp()")
    @Column(name = "login_time", nullable = false)
    private Instant loginTime;

    @ColumnDefault("current_timestamp()")
    @Column(name = "logout_time", nullable = false)
    private Instant logoutTime;

    @ColumnDefault("''")
    @Column(name = "notes", nullable = false, length = 250)
    private String notes;

    public Log() {
    }

    public Log(String accountId, Instant loginTime, String notes) {
        this.accountId = accountId;
        this.loginTime = loginTime;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Instant getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Instant loginTime) {
        this.loginTime = loginTime;
    }

    public Instant getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Instant logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}