<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		<div align="center">
			<form:form role="form" action="register" method="post"
				commandName="userForm">
				<table>
					<thead>
						<tr>
							<div class="form-group">
								<td colspan="2" align="center"><h2>회원 가입</h2></td>
							</div>
						</tr>
					</thead>
<%-- 					<tr>
						<div class="form-group">
							<td><form:label path="id">User ID:</form:label></td>
							<td><form:input path="id" class="form-control" placeholder="ID 입력" /></td>
						</div>
					</tr> --%>
					<tr>
						<td><form:label path="id">User Name:</form:label></td>
						<td><form:input path="name" class="form-control" 	/></td>
					</tr>
					<tr>
						<td><form:label path="id">Password:</form:label></td>
						<td><form:password path="password" class="form-control" /></td>
					</tr>
					<tr>
						<td><form:label path="id">E-mail:</form:label> </td>
						<td><form:input path="email" class="form-control" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="button"
							value="Register" /></td>
					</tr>
				</table>
			</form:form>
			<jsp:include page="../includes/footer.jsp" />
		</div>

	</div>

</body>
</html>