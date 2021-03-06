package controllers.blogs;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.Favorite.FavoriteMethod;
import models.Account;
import models.Blog;
import utils.DBUtil;

/**
 * Servlet implementation class BlogShowServlet
 */
@WebServlet("/blog/show")
public class BlogShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Account a = (Account)request.getSession().getAttribute("login_account");
        Blog b = em.find(Blog.class, Integer.parseInt(request.getParameter("id")));
        //FavoriteCheck
        String test = FavoriteMethod.FavoriteCheck(a, b);

        em.close();

        request.setAttribute("blog", b);
        request.setAttribute("test", test);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/blog/show.jsp");
        rd.forward(request, response);
    }

}
