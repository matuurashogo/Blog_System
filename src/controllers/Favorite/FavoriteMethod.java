package controllers.Favorite;

import javax.persistence.EntityManager;

import models.Account;
import models.Blog;
import utils.DBUtil;

public class FavoriteMethod {
    public static String FavoriteCheck(Account user,Blog blog){
        EntityManager em = DBUtil.createEntityManager();
        long favorite_count = em.createNamedQuery("FavoriteCheck",Long.class)
                                 .setParameter("user", user)
                                 .setParameter("blog",blog)
                                 .getSingleResult();
        if(0 < favorite_count){
            return "いいね";
        }
      return "";
    }
}
