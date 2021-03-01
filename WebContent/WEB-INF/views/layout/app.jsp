<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>ブログシステム</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
    <header class="header">
        <h1 class="logo"><a href="<c:url value='/' />">Sample Site</a></h1>
        <div class="headerflex">
         <c:if  test="${sessionScope.login_account != null}">

            <p class="headerA"><c:out  value="${sessionScope.login_account.name}" /></p>
             <p class="headerA"><a  href="<c:url value='/logout' />">ログアウト</a></p>

         </c:if>
         <c:if test="${sessionScope.login_account.admin_flag == 1}">
            <a class="headerA" href="<c:url value='/account/index' />">アカウント管理</a>
         </c:if>
         </div>
    </header>
    <div class="wrapper clearfix">
        ${param.content}
    </div>
    <footer class="footer">
    <p class="copyright">Copyright @ 2021 SAMPLE SITE</p>
    </footer>
    </body>
</html>