/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasysport.data;

import com.db.DataManager;
import com.fantasysports.league.League;
import com.fantasysports.league.LeagueMember;
import com.fantasysports.league.TeamOwner;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;

public class LeagueDB {
    public static League createLeague(String leagueName, TeamOwner commissioner)
    {        
        EntityTransaction tx = DataManager.getInstance().getTransaction();
        tx.begin();
        
        League l = new League();
        l.setLeaguename(leagueName);
        l.setCommissioner(commissioner);
        DataManager.getInstance().persist(l);        
        
        LeagueMember lm = new LeagueMember();
        lm.setLeagueId(l);
        lm.setMemberid(commissioner);
        DataManager.getInstance().persist(lm);        

        l.addMember(lm);
        DataManager.getInstance().persist(lm);        
        
        DataManager.getInstance().flush();
        tx.commit();
        return l;
    }
    
    public static boolean leagueExists(String leagueName)
    {
        List<League> l = DataManager.getInstance().createQuery("SELECT l FROM League l WHERE l.leaguename = :ln").setParameter("ln", leagueName).getResultList();
        return !l.isEmpty();
    }
    
    public static List<League> getAvailableLeagues(TeamOwner user) throws EntityNotFoundException
    {
        List<LeagueMember> listTo = DataManager.getInstance().createQuery("SELECT x FROM LeagueMember x WHERE x.memberId=:id").setParameter("id", user).getResultList();

        List<League> l = DataManager.getInstance().createQuery("SELECT l FROM League l WHERE SIZE(l.leagueMemberCollection) < 8").getResultList();
        for (LeagueMember lm : listTo)
        {
            l.remove(lm.getLeague());
        }

        if (l.isEmpty())
            throw new EntityNotFoundException("Could not find available leagues for: " + user.getUsername());
        
        return l;

        //List<League> l = DataManager.getInstance().createQuery("SELECT l FROM League l, IN(l.leagueMemberCollection) lmc WHERE NOT(lmc.memberId = :t) INTERSECT SELECT l FROM League l WHERE SIZE(l.leagueMemberCollection) < 8").setParameter("t", user).getResultList();
        //List<League> l = DataManager.getInstance().createQuery("SELECT l FROM League l, IN(l.leagueMemberCollection) lmc WHERE NOT(lmc.memberId = :t)").setParameter("t", user).getResultList();
        //List<League> l = DataManager.getInstance().createQuery("SELECT l FROM League l WHERE SIZE(l.leagueMemberCollection) < 8").getResultList();        
    }
    
    public static boolean joinLeague(Long leagueId, TeamOwner user)
    {
        League l = null;
        try
        {
            l = LeagueDB.getLeague(leagueId);
        }
        catch(EntityNotFoundException e)
        {
            return false;
        }
        
        EntityTransaction tx = DataManager.getInstance().getTransaction();
        tx.begin();      
        
        LeagueMember lm = new LeagueMember();
        lm.setLeagueId(l);
        lm.setMemberid(user);
        DataManager.getInstance().persist(lm);        

        l.addMember(lm);
        DataManager.getInstance().persist(lm);        
        
        DataManager.getInstance().flush();
        tx.commit();
        
        return true;
    }
    
    public static League getLeague(Long id) throws EntityNotFoundException
    {
        List<League> l = DataManager.getInstance().createQuery("SELECT l FROM League l JOIN FETCH l.leagueMemberCollection WHERE l.id = :lid").setParameter("lid", id).getResultList();
        if (l.isEmpty())
            throw new EntityNotFoundException("Could not find league with id: " + id);
        else
            return l.get(0);
    }
}
