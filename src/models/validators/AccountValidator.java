package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Account;
import utils.DBUtil;

public class AccountValidator {
    public static List<String> validate(Account a,Boolean codeDuplicateCheckFlag,Boolean passwordCheckFlag){
        List<String> errors = new ArrayList<String>();

        String code_error = validateCode(a.getCode(), codeDuplicateCheckFlag);
        if(!code_error.equals("")){
            errors.add(code_error);
        }

        String name_error = validateName(a.getName());
        if(!name_error.equals("")){
            errors.add(name_error);
        }

        String password_error = validatePassword(a.getPassword(),passwordCheckFlag);
        if(!password_error.equals("")){
            errors.add(password_error);
        }
        return errors;
    }

    private static String validateCode(String code, Boolean codeDuplicateCheckFlag){
        if(code == null || code.equals("")){
            return "社員番号を入力してください";
        }

        if(codeDuplicateCheckFlag){
          EntityManager em = DBUtil.createEntityManager();
          long account_count = (long)em.createNamedQuery("checkRegisteredCode",Long.class).setParameter("code", code).getSingleResult();
          em.close();
          if(account_count > 0){
              return "入力したIDはすでに登録されています";
          }
        }
        return "";
    }

        private static String validateName(String name){
            if(name == null || name.equals("")){
                return "氏名を入力してください";
            }
            return "";
        }

        private static String validatePassword(String password,Boolean passwordCheckFlag){
            if(passwordCheckFlag && (password == null || password.equals(""))){
                return "パスワードを入力してください";
            }
            return "";
        }
}
