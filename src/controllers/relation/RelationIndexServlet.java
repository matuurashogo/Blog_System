package controllers.relation;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.Relation;
import utils.DBUtil;

/**
 * Servlet implementation class RelationIndexServlet
 */
@WebServlet("/relation/follow")
public class RelationIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RelationIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
            {
            this.doPost(request, response);
            }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Relation r = new Relation();
        Account a = em.find(Account.class, Integer.parseInt(request.getParameter("id")));

        r.setFollowing((Account)request.getSession().getAttribute("login_account"));

        r.setFollowers(a);

        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
        em.close();
        request.getSession().setAttribute("flush", "フォローしました");

        response.sendRedirect(request.getContextPath() + "/blog/index");

    }

}
