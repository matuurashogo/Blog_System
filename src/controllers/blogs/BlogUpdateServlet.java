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

import models.Blog;
import models.validators.BlogValidators;
import utils.DBUtil;

/**
 * Servlet implementation class BlogUpdateServlet
 */
@WebServlet("/blog/update")
public class BlogUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Blog b = em.find(Blog.class, (Integer)(request.getSession().getAttribute("report_id")));

            b.setBlog_date(Date.valueOf(request.getParameter("blog_date")));
            b.setTitle(request.getParameter("title"));
            b.setContent(request.getParameter("content"));
            b.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = BlogValidators.validate(b);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("blog", b);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/blog/edit.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました");

                request.getSession().removeAttribute("report_id");

                response.sendRedirect(request.getContextPath() + "/blog/index");


            }

        }
    }

}
