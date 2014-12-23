package com.db;

import com.fantasysport.data.PlayerDB;
import com.fantasysports.player.Player;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataManager 
{
    private static DataManager dm = null;
    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;
    
    private DataManager()
    {
        try
        {
            emf = Persistence.createEntityManagerFactory("FantasyFootballPU");
            em = emf.createEntityManager();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static EntityManager getInstance()
    {
        if (dm==null)
        {
            dm = new DataManager();
        }
        return dm.getEm();
    }
    
    private EntityManager getEm()
    {
        return em;
    }
}