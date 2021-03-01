package controllers.toppage;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Blog;
import models.Favorite;
import utils.DBUtil;

/**
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();



        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }
        List<Blog> blogs = em.createNamedQuery("getAllBlogs", Blog.class)
                             .setFirstResult(15 * (page - 1))
                             .setMaxResults(15)
                             .getResultList();

        long blogs_count = em.createNamedQuery("getBlogsCount", Long.class)
                                    .getSingleResult();

        //ランキング

         String sql ="SELECT *, " +
                     "COUNT(blog_id) AS cnt " +
                     "FROM favorite " +
                     " GROUP BY blog_id " +
                     " ORDER BY COUNT(blog_id) DESC";
         List<Favorite> RankingList = em.createNativeQuery(sql,Favorite.class)
                                         .getResultList();








        em.close();

        request.setAttribute("blogs", blogs);
        request.setAttribute("blogs_count", blogs_count);
        request.setAttribute("page", page);
        request.setAttribute("Ranking",RankingList);


        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);
    }

}
