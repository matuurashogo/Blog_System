<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${account != null}">
                <h2 class="Description">id: ${account.id}のアカウント情報編集ページ</h2>
                <p>(パスワードは変更する場合のみ入力してください)</p>
                <form method="POST" action="<c:url value='/account/update' />">
                    <c:import url="_form.jsp" />
                </form>

                <p class="goodbtn"><a href="#" onclick="confirmDestroy();">このアカウント情報を削除する</a></p>
                <form method="POST" action="<c:url value='/account/destroy' />">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>
                <script>
                    function confirmDestroy() {
                        if(confirm("本当に削除してよろしいですか？")) {
                            document.forms[1].submit();
                        }
                    }
                </script>
            </c:when>
            <c:otherwise>
             <h2>お探しのデータは見つかりませんでした。</h2>
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