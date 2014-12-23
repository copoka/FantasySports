package com.fantasysports.unittest.playertables;

import com.fantasysports.unittest.basic.AbstractJPATest;
import com.fantasysports.player.Player;
import com.fantasysports.player.PlayerStat;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author steven.muschler
 */
public class PlayerTest extends AbstractJPATest {
    
    @Test
    public void testCreate() {
        Player player = new Player();
        player.setName("Drew Brees");
        player.setPosition("QB");
        
        PlayerStat stat = new PlayerStat();
        stat.setPassingtd(1);
        stat.setPassingyards(50);
        stat.setScrimmagetd(3);
        stat.setScrimmageyards(200);
        stat.setFumbles(2);
        stat.setInterceptions(1);
        
        player.setStats(stat);
        
        EntityTransaction tx = getEm().getTransaction();

        tx.begin();
        getEm().persist(player);
        tx.commit();
        assertNotNull("ID should have been generated and populated after persist",
                player.getId());
        int p = player.getId();

        List<Player> players
                = getEm().createNamedQuery("Player.findAllPlayers").getResultList();
        assertTrue(players.size() >= 1);
        
        List<PlayerStat> stats
                = getEm().createNamedQuery("PlayerStat.findAll").getResultList();
        assertTrue(stats.size() >= 1);
    }
/*    
    @Test
    public void testDelete() {
        EntityTransaction tx = getEm().getTransaction();
        tx.begin();
        Query query = getEm().createQuery("DELETE FROM Player");
        int deleteRecords = query.executeUpdate();
        
        query = getEm().createQuery("DELETE FROM PlayerStat");
        deleteRecords = query.executeUpdate();
        tx.commit();
        
        List<Player> players
                = getEm().createNamedQuery("Player.findAllPlayers").getResultList();
        assertTrue(players.isEmpty());
        
        List<PlayerStat> stats
                = getEm().createNamedQuery("PlayerStat.findAll").getResultList();
        assertTrue(stats.isEmpty());
    }
*/    
}
