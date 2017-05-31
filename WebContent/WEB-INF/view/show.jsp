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
				<div class="row">
					<div class="col col-12">
						<div class="media">
							<img class="d-flex rounded mr-3 custom-max-75-75" src="${c.user.headerIcon}" data-src="holder.js/75x75?theme=simple&text=P&random=yes">
							<div class="media-body">
								<h3>${c.title}</h3>
								<small class="text-muted">${c.user.nickname} 于 <c:set target="${timeValues}" value="${c.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" /></small>
								<%@ include file="static/operation.jsp"%>
							</div>
						</div>
						<hr/>
						<div class="custom-min-height-22">
						${c.content}
						</div>
					</div>
				</div>
				<c:if  test="${not empty replyPage.list}">
				<hr/>
				<c:forEach var="item" items="${replyPage.list}" varStatus="i">
				<div class="row">
					<div class="col col-12">
						<div class="media">
							<img class="d-flex mr-3 custom-max-75-75" src="${item.user.headerIcon}" data-src="holder.js/75x75?theme=simple&text=P&random=yes">
							<div class="media-body">
								<h6>${item.user.nickname}<small>(${item.user.username} <c:set target="${timeValues}" value="${item.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" />)</small></h6>
								${item.content}
							</div>
						</div>
					</div>
				</div>
				<c:if test="${not i.last}">
				<br/>
				</c:if>
				</c:forEach>
				</c:if>
				<c:if test="${page.totalPages > 1}">
					<br />
					<div class="row">
						<div class="col col-12">
							<nav>
								<ul class="pagination pagination-sm">
									<li class="page-item <c:if test="${page.nowPage eq 1}">disabled</c:if>"><a class="page-link" href="${config.rootPath}/topic/art/${c.BM_ID}?p=${page.nowPage-1}" tabindex="-1">Previous</a></li>
									<c:forEach var="i" begin="1" end="${page.totalPages}">
										<li class="page-item"><a class="page-link" href="${config.rootPath}/topic/art/${c.BM_ID}?p=${i}">${i}</a></li>
									</c:forEach>
									<li class="page-item <c:if test="${page.nowPage eq page.totalPages}">disabled</c:if>"><a class="page-link" href="${config.rootPath}/topic/art/${c.BM_ID}?p=${page.nowPage+1}">Next</a></li>
								</ul>
							</nav>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty memAuth}">
				<hr/>
				<div class="row">
					<div class="col col-12">
						<form method="post" action="${config.rootPath}/write/reply">
							<div class="form-group">
								<small class="form-text text-muted">快速回复</small>
								<input type="hidden" name="contentid" value="${c.BM_ID}">
								<textarea class="form-control" name="reply" rows="3"></textarea>
							</div>
							<button type="submit" class="btn btn-outline-primary btn-sm">提交</button>
						</form>
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