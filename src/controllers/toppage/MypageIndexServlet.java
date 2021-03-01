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

import controllers.relation.FollowCheck;
import models.Account;
import models.Blog;
import utils.DBUtil;

/**
 * Servlet implementation class MypageIndexServlet
 */
@WebServlet("/mypage/index")
public class MypageIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Account mypageId = em.find(Account.class,Integer.parseInt(request.getParameter("id")));
        Account login_id = (Account)request.getSession().getAttribute("login_account");


        String check = FollowCheck.followCheck(login_id,mypageId);

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }
        List<Blog> blogs = em.createNamedQuery("getMyAllBlogs", Blog.class)
                             .setParameter("account", mypageId)
                             .setFirstResult(15 * (page - 1))
                             .setMaxResults(15)
                             .getResultList();

        long blogs_count = (long)em.createNamedQuery("getMyBlogsCount", Long.class)
                                      .setParameter("account", mypageId)
                                    .getSingleResult();

        em.close();

        request.setAttribute("page", page);
        request.setAttribute("blogs", blogs);
        request.setAttribute("test", mypageId);
        request.setAttribute("check", check);
        request.setAttribute("blogs_count", blogs_count);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/mypage.jsp");
        rd.forward(request, response);
    }

}
