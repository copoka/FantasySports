package com.fantasysports.unittest.rostertables;

import com.fantasysports.unittest.basic.AbstractJPATest;
import com.fantasysports.league.League;
import com.fantasysports.league.LeagueMember;
import com.fantasysports.league.TeamOwner;
import com.fantasysports.player.Player;
import com.fantasysports.player.PlayerStat;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.junit.Test;
import static org.junit.Assert.*;

public class RosterTest extends AbstractJPATest {
    
    @Test
    public void testCreate() {
        TeamOwner user = new TeamOwner();
        user.setUsername("Username");
        user.setPassword("Password");

        League league = new League();
        league.setLeaguename("AFC");
        league.setCommissioner(user);

        League league2 = new League();
        league2.setLeaguename("NFC");
        league2.setCommissioner(user);
      
        //Create Player
        Player player = new Player();
        player.setName("Andre Johnson");
        player.setPosition("WR");
        
        //Create Player Stats
        PlayerStat stat = new PlayerStat();
        stat.setPassingtd(0);
        stat.setPassingyards(0);
        stat.setScrimmagetd(3);
        stat.setScrimmageyards(347);
        stat.setFumbles(1);
        stat.setInterceptions(0);
        
        //Add stats to player
        player.setStats(stat);        
        
        //Add 2nd player
        Player player2 = new Player();
        player2.setName("Peyton Manning");
        player2.setPosition("QB");
        
        //Add 2nd player stats
        PlayerStat stat2 = new PlayerStat();
        stat2.setPassingtd(4);
        stat2.setPassingyards(57);
        stat2.setScrimmagetd(3);
        stat2.setScrimmageyards(225);
        stat2.setFumbles(2);
        stat2.setInterceptions(2);
        
        //Add stats to 2nd player
        player2.setStats(stat2);        

        //Add player to team
        LeagueMember l = new LeagueMember();
        l.setLeagueId(league);
        l.setMemberid(user);
        l.addPlayer(player);
        l.addPlayer(player2);
        
        LeagueMember l2 = new LeagueMember();
        l2.setLeagueId(league2);
        l2.setMemberid(user);
        l2.addPlayer(player2);
        
        EntityTransaction tx = getEm().getTransaction();

        tx.begin();
        getEm().persist(user);
        getEm().persist(league);
        getEm().persist(league2);
        getEm().persist(player);
        getEm().persist(player2);
        getEm().persist(l);
        getEm().persist(l2);
        getEm().flush();
        tx.commit();
        assertNotNull("ID should have been generated and populated after persist",
                user.getId());
        assertNotNull("ID should have been generated and populated after persist",
                league.getId());
        assertNotNull("ID should have been generated and populated after persist",
                l.getId());
        
        Query q = getEm().createQuery ("SELECT count(l.roster) AS NUM FROM LeagueMember l WHERE l.id = :mem");
        q.setParameter("mem", l.getId());
        long results = (long) q.getSingleResult();
        assertEquals("Only one player should be on this team",(long) 2,results);
    }
}
