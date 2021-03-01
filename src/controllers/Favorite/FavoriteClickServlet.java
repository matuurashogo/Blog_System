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
 * Servlet implementation class FavoriteClickServlet
 */
@WebServlet("/favorite/click")
public class FavoriteClickServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteClickServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Favorite f = new Favorite();
        Account login_id = (Account)request.getSession().getAttribute("login_account");
        Blog blog_id = em.find(Blog.class, Integer.parseInt(request.getParameter("id")));

        f.setAccount(login_id);
        f.setBlog(blog_id);

        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        em.close();
        request.getSession().setAttribute("flush", "いいねしました");

        response.sendRedirect(request.getContextPath() + "/");
    }

}
