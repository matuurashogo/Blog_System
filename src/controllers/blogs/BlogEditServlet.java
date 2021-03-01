package controllers.blogs;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.Blog;
import utils.DBUtil;

/**
 * Servlet implementation class BlogEditServlet
 */
@WebServlet("/blog/edit")
public class BlogEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Blog b = em.find(Blog.class,  Integer.parseInt(request.getParameter("id")));

        em.close();

        Account login_account = (Account)request.getSession().getAttribute("login_account");
        if(b != null && login_account.getId() == b.getAccount().getId()){
            request.setAttribute("blog", b);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("report_id", b.getId());
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/blog/edit.jsp");
        rd.forward(request, response);
    }

}
