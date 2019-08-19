<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
 <main>   
	<form action="ProfileController" method = "GET" class="login_table">
            <input type="hidden" name="action" value="signin" />	
	
           <div class="login_table"> 
            <h3> Login to Application </h3>
			<table>
				<tr>
					<td>User Name or Email ID</td>
					<td><input type="text" name="username" required="required" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="userpass" required="required" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Login" /></td>
				</tr>
			</table>
            </div>
	</form>   
	     <div><c:out value = "${message}"/></div>
 </main>
 <%@include file="navigation.jsp"%>	
 <%@include file="footer.jsp"%>
 