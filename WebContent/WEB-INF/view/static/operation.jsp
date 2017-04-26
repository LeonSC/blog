<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty memAuth}">
	<c:if test="${not empty cache.titleCache[c.topic].manager[memAuth.BM_ID]}">
		<small class="text-muted">
		<a href="${config.rootPath}/topic/switchtop/${c.topic}/${c.BM_ID}"> 
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
</c:if>