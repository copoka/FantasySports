/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasysport.data;

import com.db.DataManager;
import com.fantasysports.league.LeagueMember;
import com.fantasysports.league.TeamOwner;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;

public class UserDB {
    public static TeamOwner getUser(Long l)
    {
        return (TeamOwner) DataManager.getInstance().createQuery("SELECT u FROM TeamOwner u WHERE u.id = :l").setParameter("l", l).getSingleResult();
    }
    
    public static TeamOwner login(String username, String password)
    {
        if (username==null||password==null)
            return null;
        else
        {
            List<TeamOwner> l = DataManager.getInstance().createQuery("SELECT u FROM TeamOwner u WHERE u.username = :username AND u.password = :password").setParameter("username", username).setParameter("password", password).getResultList();
            if (l.isEmpty())
                return null;
            else
                return (TeamOwner) l.get(0); 
        }    
    }
    
    public static TeamOwner register(String username, String password)
    {        
        EntityTransaction tx = DataManager.getInstance().getTransaction();
        tx.begin();
        TeamOwner t = new TeamOwner();
        t.setUsername(username);
        t.setPassword(password);
        DataManager.getInstance().persist(t);
        DataManager.getInstance().flush();
        tx.commit();
        return t;
    }
    
    public static boolean userExists(String username)
    {
        List<TeamOwner> l = DataManager.getInstance().createQuery("SELECT u FROM TeamOwner u WHERE u.username = :username").setParameter("username", username).getResultList();
        return !l.isEmpty();
    }
    
    public static LeagueMember getLeagueMember(Long id) throws EntityNotFoundException
    {
        List<LeagueMember> lm = DataManager.getInstance().createQuery("SELECT u FROM LeagueMember u WHERE u.id = :id").setParameter("id", id).getResultList();
        if (lm.isEmpty())
            throw new EntityNotFoundException("Could not find league member with id: " + id);
        else
            return lm.get(0);
    }
}
