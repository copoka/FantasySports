package com.fantasysports.player;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYERSTAT")
@NamedQueries({
    @NamedQuery(name = "PlayerStat.findAll", query = "SELECT p FROM PlayerStat p"),
    @NamedQuery(name = "PlayerStat.findByStatid", query = "SELECT p FROM PlayerStat p WHERE p.id = :id"),
    @NamedQuery(name = "PlayerStat.findByPlayerid", query = "SELECT p FROM PlayerStat p WHERE p.id = :id"),})
public class PlayerStat implements Serializable {
    @Column(name = "PASSINGYARDS")
    private Integer passingyards;
    @Column(name = "PASSINGTD")
    private Integer passingtd;
    @Column(name = "SCRIMMAGEYARDS")
    private Integer scrimmageyards;
    @Column(name = "SCRIMMAGETD")
    private Integer scrimmagetd;
    @Column(name = "INTERCEPTIONS")
    private Integer interceptions;
    @Column(name = "FUMBLES")
    private Integer fumbles;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public PlayerStat() {
        this.passingyards=0;
        this.passingtd=0;
        this.scrimmageyards=0;
        this.scrimmagetd=0;
        this.interceptions=0;
        this.fumbles=0;
    }

    public PlayerStat(Long statid, int passingyards, int passingtd, int scrimmageyards, int scrimmagetd, int interceptions, int fumbles) {
        this.id = statid;
        this.passingyards = passingyards;
        this.passingtd = passingtd;
        this.scrimmageyards = scrimmageyards;
        this.scrimmagetd = scrimmagetd;
        this.interceptions = interceptions;
        this.fumbles = fumbles;
    }

    public Long getStatid() {
        return id;
    }

    public void setStatid(Long statid) {
        this.id = statid;
    }

    public Integer getPassingyards() {
        return passingyards;
    }

    public void setPassingyards(Integer passingyards) {
        this.passingyards = passingyards;
    }

    public Integer getPassingtd() {
        return passingtd;
    }

    public void setPassingtd(Integer passingtd) {
        this.passingtd = passingtd;
    }

    public Integer getScrimmageyards() {
        return scrimmageyards;
    }

    public void setScrimmageyards(Integer scrimmageyards) {
        this.scrimmageyards = scrimmageyards;
    }

    public Integer getScrimmagetd() {
        return scrimmagetd;
    }

    public void setScrimmagetd(Integer scrimmagetd) {
        this.scrimmagetd = scrimmagetd;
    }

    public Integer getInterceptions() {
        return interceptions;
    }

    public void setInterceptions(Integer interceptions) {
        this.interceptions = interceptions;
    }

    public Integer getFumbles() {
        return fumbles;
    }

    public void setFumbles(Integer fumbles) {
        this.fumbles = fumbles;
    }
}
