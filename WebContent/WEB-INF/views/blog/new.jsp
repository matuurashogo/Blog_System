<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>ブログ新規登録ページ</h2>

        <form method="POST" action="<c:url value='/blog/create' />">
            <c:import url="_form.jsp" />
        </form>

        <p class="goodbtn"><a href="<c:url value='/blog/index' /> ">一覧に戻る</a></p>
    </c:param>
</c:import>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>
    <body>

    </body>
</html>