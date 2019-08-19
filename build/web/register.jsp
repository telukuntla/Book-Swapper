<%-- 
    Document   : register
    Created on : Dec 6, 2018, 11:28:21 AM
    Author     : teluk
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
 <main>   
	<form action="ProfileController" method = "GET" class="login_table">
            <input type="hidden" name="action" value="register" />	
	
           <div class="login_table"> 
            <h3> Registration </h3>
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
			       <td>First Name</td>
			       <td><input type="text" name="firstname" required="required" /></td>
		            </tr>
                             <tr>
			       <td>Last Name</td>
			       <td><input type="text" name="lastname" required="required" /></td>
		             </tr>
                              <tr>
			       <td>Email</td>
			       <td><input type="text" name="email" /></td>
		             </tr>
                             <tr>
			       <td>addressField1</td>
			       <td><input type="text" name="addressField1" required="required" /></td>
		             </tr>
                             <tr>
			       <td>addressField2</td>
			       <td><input type="text" name="addressField2" /></td>
		             </tr>   
                             <tr>
			       <td>city</td>
			       <td><input type="text" name="city" required="required" /></td>
		             </tr> 
                             <tr>
			       <td>State</td>
			       <td><input type="text" name="state" required="required" /></td>
		             </tr>                             
                             <tr>
			       <td>Postal Code</td>
			       <td><input type="text" name="postalCode" required="required" /></td>
		             </tr> 
                             <tr>
			       <td>Country</td>
			       <td><input type="text" name="country" required="required" /></td>
		             </tr> 
			     <tr>
				<td><input type="submit" value="Register" /></td>
			     </tr>
			</table>           
            </div>
	</form>   
	     <div><c:out value = "${message}"/></div>
 </main>
 <%@include file="navigation.jsp"%>	
 <%@include file="footer.jsp"%>