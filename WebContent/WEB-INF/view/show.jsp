<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="timeValues" class="java.util.Date"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="static/header.jsp"%>
<body>
	<%@ include file="static/nav.jsp"%>
	<div class="container">
		<%@ include file="static/navFunction.jsp"%>
		<div class="row">
			<div class="col col-9">
				<div class="row">
					<div class="col col-12">
						<h3>${c.title}</h3>
						<small class="text-muted">${c.user.nickname} 于 <c:set target="${timeValues}" value="${c.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" /></small>
						<hr/>
						${c.content}
					</div>
				</div>
				<c:if  test="${not empty replylist}">
				<hr/>
				<c:forEach var="item" items="${replylist}" varStatus="i">
				<div class="row">
					<div class="col col-12">
						<div class="media">
							<img class="d-flex mr-3 custom-max-75-75" src="${item.headericon}" data-src="holder.js/75x75?theme=simple">
							<div class="media-body">
								<h6>${item.nickname}<small>(${item.username} <c:set target="${timeValues}" value="${item.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" />)</small></h6>
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