<%-- 
    Document   : site-navigation
    Created on : Oct 2, 2018, 6:54:39 PM
    Author     : saikr
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<nav>
    <ul>
              <li id="SignIn"><a href="ProfileController?action=signin">Sign In</a></li>
              <li><a href="myitems.jsp">My Books</a></li>
              <li><a href="ProfileController?action=myswaps">My Swaps</a></li>	
	      <li><a href="#">Cart</a></li>
              <li><a><a href="register.jsp">Register</a>  </a></li>
    </ul>
</nav>  


