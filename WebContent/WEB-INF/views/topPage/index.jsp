<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <c:if test ="flush != null">
        <div id="flush_sucess">
            <c:out value="${flush}"></c:out>
        </div>
    </c:if>
    <div class="main">
    <section>
    <div class="TopList">
    <div class="TopAction">
    <a href="<c:url value='/blog/new' />">ブログを書く</a>
    </div>
    <div class="TopAction">
    <a href="<c:url value='/blog/index' />">マイページ</a>
    </div>
    <div class="TopAction">
    <a href="<c:url value='/relation/followlist' />">フォローリスト</a>
    </div>
    </div>
    </section>
    <section class="news">
        <h2 class="heading">BLOG</h2>
        <ul class="scroll-list">
            <c:forEach var="blog" items="${blogs}" varStatus="status">
                   <li class="scroll-items">
                       <a href="<c:url value='/blog/show?id=${blog.id}' />">
                       <time class="date"><fmt:formatDate value="${blog.blog_date}" pattern='yyyy-MM-dd' /></time>

                       <p class="title"><span>TITLE </span><c:out value="${blog.title}" /></p>
                       <p class="author"><span>Author</span> <c:out value="${blog.account.name}" /></p>
                       </a>
                   </li>
               </c:forEach>
           </ul>
      <div id="pagination">
          (全 ${blogs_count}件)<br />
          <c:forEach var="i" begin="1" end="${((blogs_count -1) / 15) +1}" step="1">
              <c:choose>
                  <c:when test="${i == page}">
                      <c:out value="${i}" />&nbsp;
                  </c:when>
                  <c:otherwise>
                      <a href="<c:url value='/?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                  </c:otherwise>
              </c:choose>
          </c:forEach>
      </div>
    </section>
    </div>

    <div class="sidemenu">
    <h2 class="heading">人気の記事</h2>
    <c:forEach begin="0" end="2" var="ranking" items="${Ranking}">
    <div class="Popular">
    <a href="<c:url value='/blog/show?id=${ranking.blog.id}' />">
    <img alt="img" src="http://qnimate.com/wp-content/uploads/2014/03/images2.jpg">
    <p class="sideP">TITLE: <c:out value="${ranking.blog.title}" /></p>
    <p class="sideP">Author: <c:out value="${ranking.blog.account.name}" /></p>
    <p>${ranking.blog} </p>



    </a>
    </div>
    </c:forEach>
    </div>
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