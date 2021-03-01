package controllers.relation;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.Relation;
import utils.DBUtil;

/**
 * Servlet implementation class FollowListServlet
 */
@WebServlet("/relation/followlist")
public class FollowListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Account following = (Account)request.getSession().getAttribute("login_account");

        List<Relation> followlist = em.createNamedQuery("getFollowList",Relation.class)
                                      .setParameter("following",following)
                                      .getResultList();
        request.setAttribute("followlists", followlist);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/relation/followList.jsp");
        rd.forward(request, response);
    }

}
