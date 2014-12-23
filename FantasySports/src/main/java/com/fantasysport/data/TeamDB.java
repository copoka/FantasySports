package com.fantasysport.data;

import com.db.DataManager;
import com.fantasysports.league.LeagueMember;
import com.fantasysports.league.TeamOwner;
import java.util.List;

public class TeamDB 
{
    public static List<LeagueMember> getTeams(TeamOwner t)
    {
        return DataManager.getInstance().createQuery("SELECT l FROM LeagueMember l WHERE l.memberId = :id").setParameter("id", t).getResultList();
    }
    
    public static LeagueMember getTeam(Long teamId)
    {
        return (LeagueMember) DataManager.getInstance().createQuery("SELECT l FROM LeagueMember l where l.id = :id").setParameter("id", teamId).getSingleResult();
    }
}
