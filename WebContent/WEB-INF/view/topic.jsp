<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="timeValues" class="java.util.Date"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="static/header.jsp"%>
<body>
	<%@ include file="static/nav.jsp"%>
	<%@ include file="static/navFunction.jsp"%>
	<div class="container">
		<div class="row">
		<div class="col col-9">
		<!-- 
		<div class="row" style="margin-bottom:.3rem">
			<div class="col col-12">
			<button type="button" class="btn btn-outline-success btn-sm">${cache.titleCache[topic].name}</button>
			<div class="btn-group btn-group-sm">
				<c:forEach var="item" items="${cache.titleCache[topic].manager}">
				<button type="button" class="btn btn-secondary">${empty item.value.nickname ? "匿名大佬": item.value.nickname}</button>
				</c:forEach>
			</div>
			</div>
		</div>
		 -->
		<c:if test="${not empty toplist}">
		<div class="row">
			<div class="col col-12">
				<ul class="list-group">
					<c:forEach var="item" items="${toplist}">
					<li class="list-group-item" data-href="${config.rootPath}/topic/art/${item.BM_ID}">
						<table>
							<tr>
								<td><img class="rounded float-left custom-max-75-75" src="${item.cover}" data-src="holder.js/75x75?theme=simple&text=TOP&random=yes"/></td>
								<td style="width:1rem"></td>
								<td><h5 class="mt-0">${item.title} <c:if test="${item.price!=0&&not empty item.price}"><small><i class="fa fa-cc-visa fa-1" aria-hidden="true"></i></small></c:if></h5>
								<h6 style="margin-bottom:0">${item.intro}</h6>
								<small><c:set target="${timeValues}" value="${item.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" /></small></td>
							</tr>
						</table>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<br/>
		</c:if>
		<div class="row">
			<div class="col col-12">
				<div class="card-group">
					<c:forEach var="item" items="${page.list}" begin="0" end="3">
					<div class="card" data-href="${config.rootPath}/topic/art/${item.BM_ID}" style="max-width:25%">
						<img class="card-img-top img-fluid custom-height-180" src="${item.cover}" data-src="holder.js/100px180?theme=simple&text=I&random=yes">
						<div class="card-block">
							<h4 class="card-title" style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">${item.title} <c:if test="${item.price!=0&&not empty item.price}"><small><i class="fa fa-cc-visa fa-1" aria-hidden="true"></i></small></c:if></h4>
							<p class="card-text">${item.intro}</p>
							<p class="card-text"><small class="text-muted"><c:set target="${timeValues}" value="${item.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" /></small></p>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<br/>
		<div class="row">
			<div class="col col-12">
				<ul class="list-group">
					<c:forEach var="item" items="${page.list}" begin="4">
					<li class="list-group-item" data-href="${config.rootPath}/topic/art/${item.BM_ID}">
						<table>
							<tr>
								<td><img class="rounded float-left custom-max-70-70" src="${item.cover}" data-src="holder.js/70x70?theme=simple&text=S&random=yes"/></td>
								<td style="width:1rem"></td>
								<td><h5 class="mt-0">${item.title}
								<c:if test="${item.price!=0&&not empty item.price}"><small><i class="fa fa-cc-visa fa-1" aria-hidden="true"></i></small></c:if>
								</h5>
								<h6 style="margin-bottom:0">${item.intro}</h6>
								<small><c:set target="${timeValues}" value="${item.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" /></small></td>
							</tr>
						</table>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<c:if test="${page.totalPages > 1}">
		<br/>
		<div class="row">
			<div class="col col-12">
				<nav>
					<ul class="pagination pagination-sm">
						<li class="page-item <c:if test="${page.nowPage eq 1}">disabled</c:if>"><a class="page-link" href="${config.rootPath}/topic/${topic}?p=${page.nowPage-1}" tabindex="-1">Previous</a></li>
						<c:forEach var="i" begin="1" end="${page.totalPages}">
						<li class="page-item"><a class="page-link" href="${config.rootPath}/topic/${topic}?p=${i}">${i}</a></li>
						</c:forEach>
						<li class="page-item <c:if test="${page.nowPage eq page.totalPages}">disabled</c:if>"><a class="page-link" href="${config.rootPath}/topic/${topic}?p=${page.nowPage+1}">Next</a></li>
					</ul>
				</nav>
			</div>
		</div>
		</c:if>
		</div>
		<div class="col col-3">
		<%@ include file="static/myself.jsp"%>
		</div>
		</div>
	</div>
	<%@ include file="static/footer.jsp" %>
</body>
</html>