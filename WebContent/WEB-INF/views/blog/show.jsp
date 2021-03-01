<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${blog != nyll}">
                <h2>ブログ 詳細ページ</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>名前</th>
                            <td><c:out value="${blog.account.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${blog.blog_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${blog.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${blog.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${blog.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>
                <c:if test="${sessionScope.login_account.id == blog.account.id}">
                    <p class="goodbtn"><a href="<c:url value='/blog/edit?id=${blog.id}' />">編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした</h2>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${test == 'いいね'}">
                <p class="goodbtn"><a href="<c:url value='/favorite/unclick?id=${blog.id}' />">いいねを取り消す</a></p>
            </c:when>
            <c:otherwise>
                 <p class="goodbtn"><a href="<c:url value='/favorite/click?id=${blog.id}' />">いいね</a></p>
            </c:otherwise>
        </c:choose>




        <c:choose>
            <c:when test="${sessionScope.login_account.id == blog.account.id}">
                <p class="goodbtn"><a href="<c:url value='/blog/index' />">マイページ</a></p>
            </c:when>
            <c:otherwise>
                <p class="goodbtn"><a href="<c:url value='/mypage/index?id=${blog.account.id}' />">名前のマイページへ</a></p>
            </c:otherwise>
        </c:choose>

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