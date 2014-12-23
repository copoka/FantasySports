/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasysports;

import com.fantasysports.player.Player;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author steven.muschler
 */
public class FantasyFootball {

    /**
     * @param args the command line arguments
     */
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void setUpClass() throws Exception {
        // 1. Acquire Entity Manager 
        setEmf(Persistence.createEntityManagerFactory("FantasyFootballPU"));
        setEm(getEmf().createEntityManager());
    }
        
    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void setEmf(EntityManagerFactory emf) {
        FantasyFootball.emf = emf;
    }

    public static EntityManager getEm() {
        return em;
    }

    public static void setEm(EntityManager em) {
        FantasyFootball.em = em;
    }    
}
