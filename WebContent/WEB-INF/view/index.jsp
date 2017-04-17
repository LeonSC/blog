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
						<ul class="nav nav-tabs" role="tablist">
							<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#zzzz" role="tab">重要通知</a></li>
							<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#dddd" role="tab">次要通知</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade show active" id="zzzz" role="tabpanel">
								<div class="card text-center border-top-0">
									<div class="card-header">Featured</div>
									<div class="card-block">
										<h4 class="card-title">Special title treatment</h4>
										<p class="card-text custom-height-25">重要通知很重要.</p>
										<a href="#" class="btn btn-primary">Go somewhere</a>
									</div>
									<div class="card-footer text-muted">2 days ago</div>
								</div>
							</div>
							<div class="tab-pane fade" id="dddd" role="tabpanel">
								<div class="card text-center border-top-0">
									<div class="card-header">Featured</div>
									<div class="card-block">
										<h4 class="card-title">Special title treatment</h4>
										<p class="card-text custom-height-25">次要通知不是那么次要.</p>
										<a href="#" class="btn btn-primary">Go somewhere</a>
									</div>
									<div class="card-footer text-muted">2 days ago</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<br/>
				<div class="row">
					<div class="col col-12">
						<div class="card-columns">
							<c:forEach var="item" items="${cache.indexPageContentList}">
							<div class="card">
								<c:if test="${not empty item.cover}">
								<img class="card-img-top img-fluid custom-max" src="${item.cover}" data-src="holder.js/100px160?theme=simple">
								</c:if>
								<div class="card-block">
									<h4 class="card-title text-truncate">${item.title}</h4>
									<p class="card-text">${item.intro}</p>
									<p class="card-text"><small class="text-muted"><c:set target="${timeValues}" value="${item.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" /></small></p>
								</div>
							</div>
							</c:forEach>
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