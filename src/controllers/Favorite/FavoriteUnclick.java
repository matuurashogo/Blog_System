package controllers.Favorite;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.Blog;
import models.Favorite;
import utils.DBUtil;

/**
 * Servlet implementation class FavoriteUnclick
 */
@WebServlet("/favorite/unclick")
public class FavoriteUnclick extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteUnclick() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Account a = (Account)request.getSession().getAttribute("login_account");
        Blog b = em.find(Blog.class,Integer.parseInt(request.getParameter("id")));

        Favorite r = em.createNamedQuery("FavoriteGet",Favorite.class)
                        .setParameter("user", a)
                        .setParameter("blog", b)
                        .getSingleResult();

        em.getTransaction().begin();
        em.remove(r);
        em.getTransaction().commit();
        em.close();

        request.getSession().setAttribute("flush", "いいねを取り消しました");
        response.sendRedirect(request.getContextPath() + "/");
    }

}
