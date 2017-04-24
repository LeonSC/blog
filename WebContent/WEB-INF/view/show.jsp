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
				<hr/>
				<div class="row">
					<div class="col col-12">
						<div class="media">
							<img class="d-flex mr-3" data-src="holder.js/75x75?theme=simple">
							<div class="media-body">
								<h6>1234</h6>
								Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
							</div>
						</div>
					</div>
				</div>
				<br/>
				<div class="row">
					<div class="col col-12">
						<div class="media">
							<img class="d-flex mr-3" data-src="holder.js/75x75?theme=simple">
							<div class="media-body">
								Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
							</div>
						</div>
					</div>
				</div>
				<hr/>
				<div class="row">
					<div class="col col-12">
						<form>
							<div class="form-group">
								<small class="form-text text-muted">快速回复</small> 
								<input type="text" class="form-control">
							</div>
							<button type="submit" class="btn btn-outline-primary btn-sm">提交</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col col-3">
				<%@ include file="static/myself.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="static/footer.jsp" %>
</body>
</html>