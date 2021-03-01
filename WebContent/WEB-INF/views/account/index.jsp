<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <c:if test="${flush != null}">
    <div id="flush_success">
    <c:out value="${flush}"></c:out>
    </div>
    </c:if>
    <h2 class="Description">アカウント 一覧</h2>
    <table class="account_list">
        <tbody>
            <tr>
                <th>ログインID</th>
                <th>名前</th>
                <th>操作</th>
            </tr>
            <c:forEach var="account" items="${accounts}">
            <tr class="row${status.count % 2}">
                <td><c:out value="${account.code}" /></td>
                <td><c:out value="${account.name}" /></td>
                <td>
                    <c:choose>
                        <c:when test="${account.delete_flag == 1}">
                            （削除）
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value='/account/show?id=${account.id}' />">詳細を表示</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>

    <div id="pagination">
        (全 ${account_count}件)<br />
        <c:forEach var="i" begin="1" end="${((account_count -1)/ 15) +1}" step="1">
            <c:choose>
                <c:when test="${i ==page }">
                    <c:out value="${i}"/>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/account/index?page${i}'/>"><c:out value="${i}" /></a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
    <p class="goodbtn"><a href="<c:url value='/account/new' />">新規アカウントの作成</a></p>

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