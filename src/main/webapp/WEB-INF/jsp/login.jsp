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
<body onload="document.f.j_username.focus()">
	<h1>User Login</h1>
	
	<c:if test="${not empty error}">
		<div class="errorblock" >
			Your Login was unseccessful. <br/>
			Username and/or password is Incorrect.
		</div>
	</c:if>
	
	<form action="j_spring_security_check" name="f" method="post">
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="j_username" value="" /></td>
				<!-- <td><form:errors path="name" title="Name must not be empty"></form:errors></td> -->
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="j_password" /></td>
				<!-- <td><form:errors path="password" title="Password must not be empty"></form:errors></td> -->
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="submit" value="Login"></td>
			</tr>
		</table>
	</form>
	
	<div>Don't have an account <a href="signupUser.html">Signup Now</a></div>
	 
	
</body>
</html>