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
		<!-- 
		<div class="row">
			<div class="col col-12">
				<ul class="list-group">
					<li class="list-group-item">
						<table>
							<tr>
								<td><img class="rounded float-left" src="holder.js/75x75?theme=simple"/></td>
								<td style="width:1rem"></td>
								<td>Cras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odio<br style="margin:0;"/><small class="text-muted clearfix">Last updated 3 mins ago</small></td>
							</tr>
						</table>
					</li>
					<li class="list-group-item">
						<table>
							<tr>
								<td><img class="rounded float-left" src="holder.js/75x75?theme=simple"/></td>
								<td style="width:1rem"></td>
								<td>Cras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odio<br style="margin:0;"/><small class="text-muted clearfix">Last updated 3 mins ago</small></td>
							</tr>
						</table>
					</li>
					<li class="list-group-item">
						<table>
							<tr>
								<td><img class="rounded float-left" src="holder.js/75x75?theme=simple"/></td>
								<td style="width:1rem"></td>
								<td>Cras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odioCras justo odio<br style="margin:0;"/><small class="text-muted clearfix">Last updated 3 mins ago</small></td>
							</tr>
						</table>
					</li>
				</ul>
			</div>
		</div>
		<br/>
		 -->
		<div class="row">
			<div class="col col-12">
				<div class="card-group">
					<c:forEach var="item" items="${list}" begin="0" end="3">
					<div class="card" data-href="${config.rootPath}/topic/art/${item.BM_ID}">
						<img class="card-img-top" src="${item.cover}" class="img-fluid custom-max" data-src="holder.js/100px160?theme=simple&text=I&random=yes">
						<div class="card-block">
							<h4 class="card-title">${item.title}</h4>
							<p class="card-text">${item.intro}</p>
							<p class="card-text"><small class="text-muted"><c:set target="${timeValues}" value="${item.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" /></small></p>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<br/>
		<div class="row">
			<div class="col col-12">
				<ul class="list-group">
					<c:forEach var="item" items="${list}" begin="4">
					<li class="list-group-item" data-href="${config.rootPath}/topic/art/${item.BM_ID}">
						<table>
							<tr>
								<td><img class="rounded float-left custom-max-70-70" src="${item.cover}" data-src="holder.js/70x70?theme=simple&text=S&random=yes"/></td>
								<td style="width:1rem"></td>
								<td><h5 class="mt-0">${item.title}</h5>
								<h6 style="margin-bottom:0">${item.intro}</h6>
								<small><c:set target="${timeValues}" value="${item.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" /></small></td>
							</tr>
						</table>
					</li>
					</c:forEach>
				</ul>
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