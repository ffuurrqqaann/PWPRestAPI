<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>User Page</title>
<style type="text/css">
.error {
	color:#ff0000;
}

.errorblock {
	color:#000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding:8px;
	margin:16px; 
}
</style>
</head>
<body>
	<h1>User Signup</h1>

	<form:form commandName="signup">
		<table>
			<tr>
				<td>Username:</td>
				<td><form:input path="username" /></td>
				<td><form:errors path="username" title="Name must not be empty"></form:errors></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" /></td>
				<td><form:errors path="password" title="Password must not be empty"></form:errors></td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td><form:password path="confirmPassword" /></td>
				<td><form:errors path="confirmPassword" title="Password must not be empty"></form:errors></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Signup"></td>
			</tr>
		</table>
	</form:form>
	 
	
</body>
</html>