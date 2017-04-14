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
		<div class="row">
			<div class="col col-12">
				<img data-src="holder.js/100px130?theme=simple">
			</div>
		</div>
		<div class="row">
			<div class="col col-12">
				<%@ include file="static/navFunction.jsp"%>
			</div>
		</div>
		<div class="row">
			<div class="col col-9">
				<div class="row">
					<div class="col col-12">
						<hr/>
						<form>
							<div class="form-group">
								<label class="mr-sm-2">发布到</label>
								<select class="custom-select mb-2 mr-sm-2 mb-sm-0">
									<c:forEach var="item" items="${cache.titleCache}">
										<option value="${item.BM_ID}">${item.name}</option>
									</c:forEach>
								</select>
							</div>
							<!-- <fieldset class="form-group row">
								<legend class="col-form-legend col-sm-2">Radios</legend>
								<div class="col-sm-10">
									<div class="form-check">
										<label class="form-check-label"> <input
											class="form-check-input" type="radio" name="gridRadios"
											id="gridRadios1" value="option1" checked> Option one
											is this and that&mdash;be sure to include why it's great
										</label>
									</div>
									<div class="form-check">
										<label class="form-check-label"> <input
											class="form-check-input" type="radio" name="gridRadios"
											id="gridRadios2" value="option2"> Option two can be
											something else and selecting it will deselect option one
										</label>
									</div>
								</div>
							</fieldset> -->
							<!-- <div class="form-group row">
								<label class="col-sm-2">Checkbox</label>
								<div class="col-sm-10">
									<div class="form-check">
										<label class="form-check-label"> <input
											class="form-check-input" type="checkbox"> Check me
											out
										</label>
									</div>
								</div>
							</div> -->
							<div class="form-group row">
								<div class="col-12">
									<button type="submit" class="btn btn-primary">发布</button>
									<a href="${config.rootPath}/write" class="btn btn-primary">再改改</a>
								</div>
							</div>
						</form>
						<br/>
						<h3 class="card-title">${draft.title}</h3>
						<small><c:set target="${timeValues}" value="${draft.BM_TIME}" property="time" /><fmt:formatDate value="${timeValues}" type="both" /></small>
						<hr/>
						${draft.content}
					</div>
				</div>
			</div>
			<div class="col col-3">
				<%@ include file="myself.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="static/footer.jsp" %>
</body>
</html>