package com.servlets;

import com.fantasysport.data.LeagueDB;
import com.fantasysport.data.PlayerDB;
import com.fantasysports.league.TeamOwner;
import com.fantasysports.player.Player;
import java.io.IOException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author steven.muschler
 */
@WebServlet(name = "SetScoresServlet", urlPatterns = {"/SetScores"})
public class SetScoresServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            String url = "/EditScores";
            String msg;
            
            TeamOwner user = (TeamOwner) session.getAttribute("user");

            Long playerId = Long.parseLong(request.getParameter("id"));
            int passingYards = Integer.parseInt(request.getParameter("passingyards"));
            int passingTD = Integer.parseInt(request.getParameter("passingtd"));
            int scrimmageYards = Integer.parseInt(request.getParameter("scrimmageyards"));
            int scrimmageTD = Integer.parseInt(request.getParameter("scrimmagetd"));
            int interceptions = Integer.parseInt(request.getParameter("interceptions"));
            int fumbles = Integer.parseInt(request.getParameter("fumbles"));

            try
            {
                Player player = PlayerDB.getPlayer(playerId);
                PlayerDB.setPlayerScores(player, passingYards, passingTD, scrimmageYards, scrimmageTD, interceptions, fumbles);
            }
            catch(EntityNotFoundException e)
            {
                msg = e.getMessage();
                url = "/status.jsp";
            }
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
