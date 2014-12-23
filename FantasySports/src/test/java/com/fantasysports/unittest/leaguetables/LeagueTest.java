package com.fantasysports.unittest.leaguetables;

import com.fantasysports.unittest.basic.AbstractJPATest;
import com.fantasysports.league.League;
import com.fantasysports.league.LeagueMember;
import com.fantasysports.league.TeamOwner;
import javax.persistence.EntityTransaction;
import org.junit.Test;
import static org.junit.Assert.*;

public class LeagueTest extends AbstractJPATest {
    
    @Test
    public void testCreate() {
        TeamOwner user = new TeamOwner();
        user.setUsername("user");
        user.setPassword("pass");

        League league = new League();
        league.setLeaguename("Somewhere");
        league.setCommissioner(user);
        
        LeagueMember l = new LeagueMember();
        l.setLeagueId(league);
        l.setMemberid(user);
        
        EntityTransaction tx = getEm().getTransaction();

        tx.begin();
        getEm().persist(user);
        getEm().persist(league);
        getEm().persist(l);
        getEm().flush();
        tx.commit();
        assertNotNull("ID should have been generated and populated after persist",
                user.getId());
        assertNotNull("ID should have been generated and populated after persist",
                league.getId());
        assertNotNull("ID should have been generated and populated after persist",
                l.getId());
    }
}
