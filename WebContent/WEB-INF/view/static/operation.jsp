<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty memAuth}">
	<c:if test="${not empty cache.titleCache[c.topic].manager[memAuth.BM_ID]}">
		<small class="text-muted">
		<a href="${config.rootPath}/topic/switchtop/${c.BM_ID}"> 
		<c:if test="${empty c.top}">
			置顶
		</c:if> <c:if test="${c.top == 0}">
			置顶
		</c:if> <c:if test="${c.top != 0}">
			取消置顶
		</c:if>
		</a>
		</small>
	</c:if>
	<c:if test="${(not empty cache.titleCache[c.topic].manager[memAuth.BM_ID]) or (c.user.BM_ID == memAuth.BM_ID)}">
		<small class="text-muted">
		<a href="${config.rootPath}/topic/delete/${c.BM_ID}">删除</a>
		</small>
	</c:if>
</c:if>