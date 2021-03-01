<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>フォロー一覧</h2>
        <table>
            <tbody>
                <tr>
                      <th class="report_name">氏名</th>
                    <th class="report_date">マイページに行く</th>
                </tr>


        <c:forEach var="followlist" items="${followlists}">
                <tr>
                    <td class="report_name"><c:out value="${followlist.followers.name}"></c:out></td>
                    <td class="report_action"><a href="<c:url value='/mypage/index?id=${followlist.followers.id}' />">詳細を見る</a></td>

                </tr>
        </c:forEach>
             </tbody>
        </table>
    </c:param>
</c:import>