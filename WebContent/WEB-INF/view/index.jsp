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
						<div id="carouselIndicators" class="carousel slide" data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
								<li data-target="#carouselIndicators" data-slide-to="1"></li>
								<li data-target="#carouselIndicators" data-slide-to="2"></li>
							</ol>
							<div class="carousel-inner" role="listbox">
								<div class="carousel-item active" data-href="http://${cache.indexPageCarouselList[0].link}">
									<img class="d-block img-fluid" src="${cache.indexPageCarouselList[0].img}" data-src="holder.js/100px160?theme=simple">
								</div>
								<div class="carousel-item" data-href="http://${cache.indexPageCarouselList[1].link}">
									<img class="d-block img-fluid" src="${cache.indexPageCarouselList[1].img}" data-src="holder.js/100px160?theme=simple">
								</div>
								<div class="carousel-item" data-href="http://${cache.indexPageCarouselList[2].link}">
									<img class="d-block img-fluid" src="${cache.indexPageCarouselList[2].img}" data-src="holder.js/100px160?theme=simple">
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
									<c:forEach var="item" items="${cache.indexPageNoticeList}" varStatus="i">
									<c:if test="${i.first}">
									<li class="nav-item"><a class="nav-link active" href="#tab${item.BM_ID}" data-toggle="tab" role="tab">${item.bar}</a></li>
									</c:if>
									<c:if test="${not i.first}">
									<li class="nav-item"><a class="nav-link" href="#tab${item.BM_ID}" data-toggle="tab" role="tab">${item.bar}</a></li>
									</c:if>
									</c:forEach>
								</ul>
							</div>
							<div class="tab-content">
								<c:forEach var="item" items="${cache.indexPageNoticeList}" varStatus="i">
								<c:if test="${i.first}">
								<div class="tab-pane fade show active" id="tab${item.BM_ID}" role="tabpanel">
									<div class="card-block custom-height-66">
										<c:if test="${not empty item.title}">
										<h4 class="card-title">${item.title}</h4>
										</c:if>
										<p class="card-text custom-height-22">${item.notice}</p>
										<c:if test="${not empty item.link}">
										<a href="http://${item.link}" class="btn btn-primary btn-sm">点击查看</a>
										</c:if>
									</div>
								</div>
								</c:if>
								<c:if test="${not i.first}">
								<div class="tab-pane fade" id="tab${item.BM_ID}" role="tabpanel">
									<div class="card-block custom-height-66">
										<c:if test="${not empty item.title}">
										<h4 class="card-title">${item.title}</h4>
										</c:if>
										<p class="card-text custom-height-22">${item.notice}</p>
										<c:if test="${not empty item.link}">
										<a href="http://${item.link}" class="btn btn-primary btn-sm">点击查看</a>
										</c:if>
									</div>
								</div>
								</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<br/>
				<div class="row">
					<div class="col col-12">
						<div class="card-columns">
							<c:forEach var="item" items="${cache.indexPageContentList}">
							<div class="card" data-href="${config.rootPath}/topic/art/${item.BM_ID}">
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