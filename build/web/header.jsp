<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 

<html lang = "en">
    <head>
        <meta charset = "UTF-8">
        <title>Book Swap</title> 
	    <link rel = "stylesheet" type ="text/css" href="style.css">
    </head> 
  
    <body>
        <header class = "header_blue">
	    <h1> Book Swapper <img src="img1.jpg" alt="Book Swap"> </h1> 
              <c:choose>
 	  	<c:when test="${sessionScope.theUser!=null}">
  	   		<ul>
                          <li class="topright" id="SignIn"> UserName: ${sessionScope.theUser.userId}</li>                          
  	   		  <li id="SignOut"><a href="ProfileController?action=signout">SignOut</a></li>
                          <li> User Rating: ${sessionScope.rating}</li>
                        </ul>
  		</c:when> 
 	 	<c:otherwise>
                    <div>Not Signed in.</div>
 	        </c:otherwise>
          </c:choose>   	   
        </header>
  
 <jsp:include page="site-navigation.jsp" />   
