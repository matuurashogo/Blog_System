package controllers.blogs;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.Blog;
import models.validators.BlogValidators;
import utils.DBUtil;

/**
 * Servlet implementation class BlogCreateServlet
 */
@WebServlet("/blog/create")
public class BlogCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId()));
            EntityManager em = DBUtil.createEntityManager();

            Blog b = new Blog();

            b.setAccount((Account)request.getSession().getAttribute("login_account"));

            Date blog_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("blog_date");
            if(rd_str != null && !rd_str.equals("")){
                blog_date = Date.valueOf(request.getParameter("blog_date"));
            }
            b.setBlog_date(blog_date);

            b.setTitle(request.getParameter("title"));
            b.setContent(request.getParameter("content"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            b.setCreated_at(currentTime);
            b.setUpdated_at(currentTime);

            List<String> errors = BlogValidators.validate(b);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("blog", b);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/blog/new.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.persist(b);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/blog/index");

            }
    }

}
