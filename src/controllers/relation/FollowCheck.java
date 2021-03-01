package controllers.relation;

import javax.persistence.EntityManager;

import models.Account;
import utils.DBUtil;

public class FollowCheck {

    public static String followCheck(Account following,Account followers){

      EntityManager em = DBUtil.createEntityManager();
      long follow_count = em.createNamedQuery("checkfollowCount",Long.class)
                             .setParameter("following", following)
                             .setParameter("followers", followers)
                             .getSingleResult();
      em.close();
      if(follow_count > 0){
          return "フォロー解除";
      }

      return "";



    }



}


