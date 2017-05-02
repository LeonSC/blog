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
					<h3>小哥哥, 您的票票私活不足以支付哦.</h3><br/>
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