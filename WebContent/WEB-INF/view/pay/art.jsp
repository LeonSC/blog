<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../static/header.jsp"%>
<body>
	<%@ include file="../static/nav.jsp"%>
	<div class="container">
		<%@ include file="../static/navFunction.jsp"%>
		<div class="row">
			<div class="col col-9">
				<div class="row">
					<div class="col col-12 text-center">
					<br/>
					<h3>购买</h3><br/>
					<p class="h3"><small class="text-muted">价格:</small>${c.price}<small class="text-muted">元</small></p><br/>
					<a type="button" class="btn btn-primary" href="${config.rootPath}/pay/sure">支付</a>
					<a type="button" class="btn btn-primary" href="${config.rootPath}/pay/sure?secret=1">匿名支付</a>
					<a type="button" class="btn btn-link" href="${config.rootPath}/topic/${c.topic}">取消</a>
					</div>
				</div>
			</div>
			<div class="col col-3">
				<%@ include file="../static/myself.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="../static/footer.jsp" %>
</body>
</html>