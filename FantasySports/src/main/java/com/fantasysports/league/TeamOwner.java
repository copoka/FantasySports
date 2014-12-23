package com.fantasysports.league;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "TEAMOWNER")
@NamedQueries({
    @NamedQuery(name = "TeamOwner.findAll", query = "SELECT m FROM TeamOwner m"),
    @NamedQuery(name = "TeamOwner.findById", query = "SELECT m FROM TeamOwner m WHERE m.id = :id"),})
public class TeamOwner implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    
    @JoinTable(name = "LEAGUEMEMBER", joinColumns = {
    @JoinColumn(name = "MEMBERID", referencedColumnName = "ID")})
    @OneToMany(cascade = CascadeType.ALL)
    private List<LeagueMember> teams;

    public TeamOwner() {
    }

    public Long getId() {
        return id;
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

    public List<LeagueMember> getTeams() {
        return teams;
    }
}
