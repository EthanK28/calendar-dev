<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="pageTitle" value="Welcome to myCalendar!" scope="request" />
<html>
<head>
<title>myCalendar: <c:out value="${pageTitle}" />
</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<c:url var="resourceUrl" value="/resources" />
<link href="${resourceUrl}/bootstrap-3.3.1/css/bootstrap.css"
	rel="stylesheet" />
<link href="${resourceUrl}/css/custom.css" rel="stylesheet" />
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body class="header">
	<div class="container">
		<jsp:include page="../includes/header.jsp" />
		<c:set var="eventstest">${events}</c:set>
		<h1 class="headline">모든 Events 출력</h1>
		<div>
			<table class="table table-bordered table-hover">
				<caption>events</caption>
				<thead>
					<tr class="danger">
						<th>id</th>
						<th>when</th>
						<th>summary</th>
						<th>description</th>
						<th>owner</th>
						<th>numLikes</th>
						<th>eventLevel</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="event" items="${events}">
						<%-- <fmt:formatDate value="${event.when}" type="date" dateStyle="short" /> --%>
						<tr class="info">
							<td>${event.id}</td>
							<td><fmt:formatDate value="${event.when.time}" type="date" dateStyle="full" /></td>
							<td>${event.summary}</td>
							<td>${event.description}</td>
							<td>${event.owner}</td>
							<td>${event.numLikes}</td>
							<td>${event.eventLevel}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<jsp:include page="../includes/footer.jsp" />
	</div>
</body>
</html>