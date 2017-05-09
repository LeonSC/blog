<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../static/header.jsp"%>
<body>
	<%@ include file="../static/nav.jsp"%>
	<div class="container">
		<c:if test="${empty memAuth}">
			<br />
		</c:if>
		<c:if test="${not empty memAuth}">
			<div class="row" style="margin-bottom: .3rem; margin-top: .3rem;">
				<div class="col col-12">
					<a type="button" class="btn btn-info btn-sm" href="${config.rootPath}/write/layuiforum?block=${node.BM_ID}">写点什么</a>
				</div>
			</div>
		</c:if>
		<div class="row">
			<div class="col col-9">
				<ul class="list-group">
					<c:forEach var="item" items="${list}">
						<li class="list-group-item d-flex justify-content-between align-items-center">${item.title} <span class="badge badge-default badge-pill">14</span></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col col-3">
				<%@ include file="../static/myself.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="../static/footer.jsp"%>
</body>
</html>