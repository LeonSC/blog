<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="static/header.jsp"%>
<body>
	<%@ include file="static/nav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col col-xl-12">
				<img data-src="holder.js/100px130?theme=simple">
			</div>
		</div>
		<div class="row">
			<div class="col col-xl-12">
				<div class="card">
					<div class="card-header">Featured</div>
					<div class="card-block">
						<h4 class="card-title">Special title treatment</h4>
						<p class="card-text">With supporting text below as a natural
							lead-in to additional content.</p>
						<a href="#" class="btn btn-primary">Go somewhere</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("form").submit(function() {
				return true;
			});
		});
	</script>
	<%@ include file="static/footer.jsp"%>
</body>
</html>