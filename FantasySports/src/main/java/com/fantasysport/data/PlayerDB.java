package com.fantasysport.data;

import com.db.DataManager;
import com.fantasysports.league.League;
import com.fantasysports.league.LeagueMember;
import com.fantasysports.player.Player;
import com.fantasysports.player.PlayerStat;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;

public class PlayerDB 
{
    public static void insert(Player p)
    {
        EntityTransaction t = DataManager.getInstance().getTransaction();
        t.begin();
        DataManager.getInstance().persist(p);
        t.commit();
    }
    
    public static List getAllPlayers()
    {
        return DataManager.getInstance().createNamedQuery("Player.findAllPlayers").getResultList();    
    }
    
    public static void dropPlayer(Long teamId, Long playerId) throws EntityNotFoundException
    {
        LeagueMember l = UserDB.getLeagueMember(teamId);
        Player p = PlayerDB.getPlayer(playerId);
                
        EntityTransaction t = DataManager.getInstance().getTransaction();
        t.begin();
        l.getRoster().remove(p);
        DataManager.getInstance().persist(l);
        t.commit();
    }
    
    public static Player getPlayer(Long id) throws EntityNotFoundException
    {
        List<Player> p = DataManager.getInstance().createQuery("SELECT p FROM Player p WHERE p.id = :id").setParameter("id", id).getResultList();
        if (p.isEmpty())
            throw new EntityNotFoundException("Could not find player with id: " + id);
        else
            return p.get(0);
    }
    
    public static List<Player> getAvailablePlayers(Long teamId) throws EntityNotFoundException
    {
        LeagueMember lm = UserDB.getLeagueMember(teamId);
        League l = LeagueDB.getLeague(lm.getLeague().getId());
        List<Player> p = DataManager.getInstance().createNamedQuery("Player.findAllPlayers").getResultList();
        
        for (LeagueMember x : l.getLeagueMemberCollection())
        {
            for (Player y : x.getRoster())
            {
                p.remove(y);
            }
        }
        
        return p;
    }
    
    public static void addPlayer(Long playerId, Long teamId) throws EntityNotFoundException
    {
        LeagueMember lm = UserDB.getLeagueMember(teamId);
        Player p = PlayerDB.getPlayer(playerId);
        lm.addPlayer(p);
        
        EntityTransaction t = DataManager.getInstance().getTransaction();
        t.begin();
        DataManager.getInstance().persist(lm);
        DataManager.getInstance().flush();
        t.commit();
    }
    
    public static void setPlayerScores(Player p, int pYards, int pTD, int sYards, int sTD, int in, int f) throws EntityNotFoundException
    {
        PlayerStat ps = p.getStats();
        ps.setPassingyards(pYards);
        ps.setPassingtd(pTD);
        ps.setScrimmageyards(sYards);
        ps.setScrimmagetd(sTD);
        ps.setInterceptions(in);
        ps.setFumbles(f);
                
        EntityTransaction t = DataManager.getInstance().getTransaction();
        t.begin();
        DataManager.getInstance().persist(ps);
        DataManager.getInstance().flush();
        t.commit();
    }
}
