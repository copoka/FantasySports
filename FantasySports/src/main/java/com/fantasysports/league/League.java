package com.fantasysports.league;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LEAGUE")
@NamedQueries({
    @NamedQuery(name = "League.findAll", query = "SELECT l FROM League l"),
    @NamedQuery(name = "League.findById", query = "SELECT l FROM League l WHERE l.id = :id")})
public class League implements Serializable {
    @OneToMany(mappedBy = "leagueId", cascade = CascadeType.ALL)
    private Collection<LeagueMember> leagueMemberCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "LEAGUENAME")
    private String leaguename;
    
    @JoinColumn(name = "COMMISHID")
    @OneToOne
    private TeamOwner commissioner;

    public League() {
        leagueMemberCollection = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getLeaguename() {
        return leaguename;
    }

    public void setLeaguename(String leaguename) {
        this.leaguename = leaguename;
    }

    public TeamOwner getCommissioner() {
        return commissioner;
    }

    public void setCommissioner(TeamOwner commissioner) {
        this.commissioner = commissioner;
    }

    public Collection<LeagueMember> getLeagueMemberCollection() {
        return this.leagueMemberCollection;
    }
    
    public void addMember(LeagueMember lm) {
        this.leagueMemberCollection.add(lm);
    }
}
