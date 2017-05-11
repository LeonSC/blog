<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="timeValues" class="java.util.Date"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../static/header.jsp"%>
<body>
	<%@ include file="../static/nav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col col-12">
				<a class="btn btn-link btn-sm" href="${config.rootPath}/forum">论坛首页</a> / <a class="btn btn-link btn-sm" href="${config.rootPath}/forum/node/${point.topic}">${forum.blockmap[point.topic].name}</a>
			</div>
		</div>
		<div class="row">
			<div class="col col-9">
				<div class="media">
					<img class="d-flex mr-3" src="${point.user.headerIcon}" data-src="holder.js/100px200?theme=simple&text=Hello&random=yes" width="120rem"/>
					<div class="media-body" style="overflow:hidden">
						<small class="text-muted">
							<i class="fa fa-id-card-o" aria-hidden="true"></i> ${point.user.nickname} 
							<i class="fa fa-trophy" aria-hidden="true"></i> ${point.user.lv}
							发布于 : <c:set target="${timeValues}" value="${point.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" />
						</small>
						<hr/>
						<h5 class="mt-0">${point.title}</h5>
						${point.content}
					</div>
				</div>
				<c:if test="${not empty page.list}">
						<c:forEach var="item" items="${page.list}">
						<hr/>
						<div class="media">
							<img class="d-flex mr-3" src="${item.user.headerIcon}" data-src="holder.js/100px200?theme=simple&text=Hello&random=yes" width="120rem"/>
							<div class="media-body" style="overflow:hidden">
								<small class="text-muted"> 
									<i class="fa fa-id-card-o" aria-hidden="true"></i> ${item.user.nickname} 
									<i class="fa fa-trophy" aria-hidden="true"></i> ${item.user.lv} 
									发布于 : <c:set target="${timeValues}" value="${item.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" />
								</small>
								<hr/>
								${item.content}
							</div>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${page.totalPages > 1}">
					<br />
					<div class="row">
						<div class="col col-12">
							<nav>
								<ul class="pagination pagination-sm">
									<li class="page-item <c:if test="${page.nowPage eq 1}">disabled</c:if>"><a class="page-link" href="${config.rootPath}/forum/point/${point.BM_ID}?p=${page.nowPage-1}" tabindex="-1">Previous</a></li>
									<c:forEach var="i" begin="1" end="${page.totalPages}">
										<li class="page-item"><a class="page-link" href="${config.rootPath}/forum/point/${point.BM_ID}?p=${i}">${i}</a></li>
									</c:forEach>
									<li class="page-item <c:if test="${page.nowPage eq page.totalPages}">disabled</c:if>"><a class="page-link" href="${config.rootPath}/forum/point/${point.BM_ID}?p=${page.nowPage+1}">Next</a></li>
								</ul>
							</nav>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty memAuth}">
					<div class="row">
						<div class="col col-12">
							<form method="post" action="${config.rootPath}/write/forumreply">
								<div class="form-group">
									<small class="form-text text-muted">快速回复</small> <input type="hidden" name="contentid" value="${point.BM_ID}">
									<textarea class="form-control" name="reply" rows="3"></textarea>
								</div>
								<button type="submit" class="btn btn-outline-primary btn-sm">提交</button>
							</form>
						</div>
					</div>
				</c:if>
			</div>
			<div class="col col-3">
				<%@ include file="../static/myself.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="../static/footer.jsp"%>
</body>
</html>