package com.fantasysports.league;

import com.fantasysports.player.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author steven.muschler
 */
@Entity
@Table(name = "LEAGUEMEMBER")
@NamedQueries({
    @NamedQuery(name = "LeagueMember.findAll", query = "SELECT l FROM LeagueMember l")})
public class LeagueMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JoinColumn(name = "LEAGUEID", referencedColumnName = "ID")
    @ManyToOne
    private League leagueId;
    @JoinColumn(name = "MEMBERID", referencedColumnName = "ID")
    @ManyToOne
    private TeamOwner memberId;
    
    //@PrimaryKeyJoinColumn(name="ID",referencedColumnName="LEAGUEMEMBERID")
    //@OneToMany(cascade=CascadeType.ALL,mappedBy="lm")
    //@JoinTable(name="ROSTER")
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name="ROSTER",
            joinColumns={@JoinColumn(name="LEAGUEMEMBERID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="PLAYERID", referencedColumnName="ID")})
    private List<Player> roster;

    public LeagueMember() {
        roster = new ArrayList<>();
    }

    public LeagueMember(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public League getLeague() {
        return leagueId;
    }

    public void setLeagueId(League l) {
        this.leagueId = l;
    }

    public TeamOwner getMemberId() {
        return memberId;
    }

    public void setMemberid(TeamOwner m) {
        this.memberId = m;
    }
    
    public Collection<Player> getRoster() {
        return this.roster;
    }

    public void addPlayer(Player p) {
        this.roster.add(p);
    }
    
    public double calculateTeamScore()
    {
        double score = 0;
        for (Player p : this.getRoster())
        {
            score += p.calculateScore();
        }
        return score;
    }
}
