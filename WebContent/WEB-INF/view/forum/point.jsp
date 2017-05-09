<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../static/header.jsp"%>
<body>
	<%@ include file="../static/nav.jsp"%>
	<div class="container">
		<br />
		<div class="row">
			<div class="col col-9">
				<div class="card-group">
					<div class="card">
						<img class="card-img-top" src="" data-src="holder.js/100px200?theme=simple&text=Hello&random=yes"/>
						<div class="card-block">
							<h4 class="card-title">Card title</h4>
							<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
							<p class="card-text">
								<small class="text-muted">Last updated 3 mins ago</small>
							</p>
						</div>
					</div>
					<div class="card">
						<img class="card-img-top" src="" data-src="holder.js/100px200?theme=simple&text=Hello&random=yes"/>
						<div class="card-block">
							<h4 class="card-title">Card title</h4>
							<p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
							<p class="card-text">
								<small class="text-muted">Last updated 3 mins ago</small>
							</p>
						</div>
					</div>
				</div>
				<div class="card-group">
					<div class="card">
						<img class="card-img-top" src="" data-src="holder.js/100px200?theme=simple&text=Hello&random=yes"/>
						<div class="card-block">
							<h4 class="card-title">Card title</h4>
							<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
							<p class="card-text">
								<small class="text-muted">Last updated 3 mins ago</small>
							</p>
						</div>
					</div>
					<div class="card">
						<img class="card-img-top" src="" data-src="holder.js/100px200?theme=simple&text=Hello&random=yes"/>
						<div class="card-block">
							<h4 class="card-title">Card title</h4>
							<p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
							<p class="card-text">
								<small class="text-muted">Last updated 3 mins ago</small>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col col-3">
				<%@ include file="../static/myself.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="../static/footer.jsp"%>
</body>
</html>