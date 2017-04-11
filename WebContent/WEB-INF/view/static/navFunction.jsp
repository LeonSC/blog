<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav">
	<li class="nav-item"><a class="nav-link" href="${config.rootPath}">首页</a></li>
	<c:forEach var="item" items="${cache.titleCache}">
	<li class="nav-item"><a class="nav-link" href="${config.rootPath}/topical/${item.BM_ID}">${item.name}</a></li>
	</c:forEach>
</ul>