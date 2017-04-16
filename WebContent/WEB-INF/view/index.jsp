<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<div id="carouselExampleIndicators" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
								<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
								<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
							</ol>
							<div class="carousel-inner" role="listbox">
								<div class="carousel-item active">
									<img class="d-block img-fluid" src="holder.js/100px160?theme=simple" alt="First slide">
								</div>
								<div class="carousel-item">
									<img class="d-block img-fluid" src="holder.js/100px160?theme=simple" alt="Second slide">
								</div>
								<div class="carousel-item">
									<img class="d-block img-fluid" src="holder.js/100px160?theme=simple" alt="Third slide">
								</div>
							</div>
						</div>
					</div>
				</div>
				<br/>
				<div class="row">
					<div class="col col-12">
						<div class="card text-center">
							<div class="card-header">
								<ul class="nav nav-tabs card-header-tabs">
									<li class="nav-item"><a class="nav-link active" href="#">Active</a></li>
									<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
									<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a></li>
								</ul>
							</div>
							<div class="card-block">
								<h4 class="card-title">Special title treatment</h4>
								<p class="card-text">With supporting text below as a natural
									lead-in to additional content.</p>
								<a href="#" class="btn btn-primary">Go somewhere</a>
							</div>
						</div>
					</div>
				</div>
				<br/>
				<div class="row">
					<div class="col col-12">
						<div class="card-columns">
							<div class="card">
								<img class="card-img-top img-fluid" src="holder.js/100px160?theme=simple"
									alt="Card image cap">
								<div class="card-block">
									<h4 class="card-title">Card title that wraps to a new line</h4>
									<p class="card-text">This is a longer card with supporting
										text below as a natural lead-in to additional content. This
										content is a little bit longer.</p>
								</div>
							</div>
							<div class="card p-3">
								<blockquote class="card-block card-blockquote">
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
										Integer posuere erat a ante.</p>
									<footer>
										<small class="text-muted"> Someone famous in <cite
											title="Source Title">Source Title</cite>
										</small>
									</footer>
								</blockquote>
							</div>
							<div class="card">
								<img class="card-img-top img-fluid" src="holder.js/100px160?theme=simple"
									alt="Card image cap">
								<div class="card-block">
									<h4 class="card-title">Card title</h4>
									<p class="card-text">This card has supporting text below as
										a natural lead-in to additional content.</p>
									<p class="card-text">
										<small class="text-muted">Last updated 3 mins ago</small>
									</p>
								</div>
							</div>
							<div class="card card-inverse card-primary p-3 text-center">
								<blockquote class="card-blockquote">
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
										Integer posuere erat.</p>
									<footer>
										<small> Someone famous in <cite title="Source Title">Source
												Title</cite>
										</small>
									</footer>
								</blockquote>
							</div>
							<div class="card text-center">
								<div class="card-block">
									<h4 class="card-title">Card title</h4>
									<p class="card-text">This card has supporting text below as
										a natural lead-in to additional content.</p>
									<p class="card-text">
										<small class="text-muted">Last updated 3 mins ago</small>
									</p>
								</div>
							</div>
							<div class="card">
								<img class="card-img img-fluid" src="holder.js/100px160?theme=simple" alt="Card image">
							</div>
							<div class="card p-3 text-right">
								<blockquote class="card-blockquote">
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
										Integer posuere erat a ante.</p>
									<footer>
										<small class="text-muted"> Someone famous in <cite
											title="Source Title">Source Title</cite>
										</small>
									</footer>
								</blockquote>
							</div>
							<div class="card">
								<div class="card-block">
									<h4 class="card-title">Card title</h4>
									<p class="card-text">This is a wider card with supporting
										text below as a natural lead-in to additional content. This
										card has even longer content than the first to show that equal
										height action.</p>
									<p class="card-text">
										<small class="text-muted">Last updated 3 mins ago</small>
									</p>
								</div>
							</div>
						</div>
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