<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="header.jsp"%>
<body>
	<div class="container-fluid">
		<%@ include file="nav.jsp"%>
		<c:forEach var="item" items="${forum.block}">
			<div class="row">
				<div class="col col-6">
					<div class="media">
						<img class="d-flex align-self-center mr-3" src="${item.value.icon}" style="height:5rem;width:5rem"/>
						<div class="media-body">
							<a class="btn btn-link" href="${config.rootPath}/admin/forum/forumsetting?bmid=${item.value.BM_ID}" style="line-height:0;padding:0">${item.value.name}</a>
							<c:if test="${not empty item.value.intro}">
							<br style="margin:0"/>
							<small>${item.value.intro}</small>
							</c:if>
							<hr style="margin:.2rem 0 0"/>
							<small style="line-height:0;padding:0;margin:0"><i class="fa fa-user" aria-hidden="true"></i> </small>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			<div class="col col-12">
				<a class="btn btn-link" href="${config.rootPath}/admin/forum/forumsetting?okey=${item.value.BM_ID}">新增分区块</a>
			</div>
		</div>
			<hr />
		</c:forEach>
		<div class="row">
			<div class="col col-12">
				<a class="btn btn-link" href="${config.rootPath}/admin/forum/forumsetting">新增总区块</a>
			</div>
		</div>
	</div>
</body>
</html>