<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="header.jsp"%>
<body>
	<div class="container-fluid">
		<%@ include file="nav.jsp"%>
		<form method="post" action="${config.rootPath}/admin/deposit/topicsubmit">
			<div class="form-group row">
				<div class="form-group col-4">
					<label class="col-form-label">名称</label> <input type="text" class="form-control" placeholder="名称比如充100送50" name="title">
				</div>
				<div class="form-group col-4">
					<label class="col-form-label">发卡数量</label> <input type="number" class="form-control" value="1" min="1" max="500" name="count">
				</div>
				<div class="form-group col-4">
					<label class="col-form-label">单卡金额</label> <input type="number" class="form-control" value="1" min="1" max="500" name="price">
				</div>
			</div>
			<div class="form-group row">
				<div class="form-group col-4">
					<button type="submit" class="btn btn-primary">提交</button>
				</div>
			</div>
		</form>
		<div class="row">
			<div class="col col-12">
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>名称</th>
							<th>发卡数量</th>
							<th>单卡金额</th>
							<th>发行次数</th>
							<th>发行总量</th>
							<th>剩余数量</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${dtlist}" varStatus="i">
						<tr>
							<th scope="row">${i.count}</th>
							<td>${item.title}</td>
							<td>${item.count}</td>
							<td>${item.price}</td>
							<td>${item.frequency}</td>
							<td>${item.frequency * item.count}</td>
							<td>${item.rest}</td>
							<td>
								<c:if test="${item.rest eq 0}"><a href="${config.rootPath}/admin/deposit/depositcreate?bmid=${item.BM_ID}">发行</a></c:if>
								<a href="#">删除</a>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<form class="form-inline" method="POST" action="${config.rootPath}/admin/deposit/senddepositcard">
									<c:choose>
										<c:when test="${error == -1}">
										<div class="input-group mb-2 mr-sm-2 mb-sm-0 has-danger">
											<div class="input-group-addon">没有用户</div>
											<input type="text" class="form-control" placeholder="email" name="email">
										</div>
										</c:when>
										<c:when test="${error == -2}">
										<div class="input-group mb-2 mr-sm-2 mb-sm-0 has-danger">
											<div class="input-group-addon">没有绑定邮箱</div>
											<input type="text" class="form-control" placeholder="email" name="email">
										</div>
										</c:when>
										<c:otherwise>
										<div class="input-group mb-2 mr-sm-2 mb-sm-0">
											<div class="input-group-addon">发送给</div>
											<input type="text" class="form-control" placeholder="email" name="email">
										</div>
										</c:otherwise>
									</c:choose>
									<input type="hidden" value="${item.BM_ID}" name="depoisttopicid"/>
									<button type="submit" class="btn btn-primary">提交</button>
								</form>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								已发送未兑换: 
								<c:forEach var="sen" items="${item.sent}" varStatus="s">
									<c:if test="${s.first}">${sen.account}</c:if>
									<c:if test="${not s.first}"> , ${sen.account}</c:if>
								</c:forEach>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>