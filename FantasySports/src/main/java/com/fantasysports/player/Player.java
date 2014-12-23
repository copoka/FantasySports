package com.fantasysports.player;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYER")
@NamedQueries({
    @NamedQuery(name = "Player.findAllPlayers", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findPlayerByPlayerid", query = "SELECT p FROM Player p WHERE p.id = :id"),
    @NamedQuery(name = "Player.findPlayerByName", query = "SELECT p FROM Player p WHERE p.name = :name"),
    @NamedQuery(name = "Player.findPlayerByPosition", query = "SELECT p FROM Player p WHERE p.position = :position")})
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name="STATID")
    PlayerStat stats;
    @Column(name = "NAME")
    private String name;
    @Column(name = "POSITION")
    private String position;
    
    //@PrimaryKeyJoinColumn(name="ID",referencedColumnName="PLAYERID")
    //@ManyToOne(cascade=CascadeType.ALL)
    //private LeagueMember lm;
   
    public Player() {
        stats = new PlayerStat();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    public PlayerStat getStats() {
        return stats;
    }

    public void setStats(PlayerStat p) {
        this.stats = p;
    }
    
    public double calculateScore()
    {
        if (this.getStats()==null)
            return 0;
        double score = 0;
        score += this.getStats().getPassingyards() / 25;
        score += this.getStats().getPassingtd() * 4;
        score += this.getStats().getScrimmageyards() / 10;
        score += this.getStats().getScrimmagetd() * 6;
        score -= this.getStats().getInterceptions() * 2;
        score -= this.getStats().getFumbles() * 2;
        return score;
    }
}
