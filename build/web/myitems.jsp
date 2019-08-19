<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@include file="header.jsp"%>
	<main>
	        <header class = "header_blue">
			<div>Home->My Books</div>
	        </header>
	                <h3> ${requestScope.feedbackMessage}</h3>
			<h2>${sessionScope.theUser.userId}'s Books</h2>	
			<hr>
			<table class="myitems_table">
                            <tr class = "font_bold">
                                <td>Book</td>
			        <td>Category</td>
			        <td>My Rating</td>
			        <td>Swap Status</td>
				<td></td>
			    </tr>                  
                   <c:forEach items="${sessionScope.useritems}" var="current">
                            <tr>                        
                                <td> <a href="CatalogController?itemCode=${current.userItem}">${current.itemName} </a> </td>
			        <td>${current.category}</td>
				<td class="rating">${current.rating} </td>
				<td class="tick_swap">${current.status}</td>                              
                                
                      <c:choose>
 	             	 <c:when test="${current.status == 'this item was swapped'}">
  	   	      
                           <td> <div>  <button onclick="location.href= 'ProfileController?action=itemfeedback&swapItemName=${current.itemName}&theItem=${current.userItem}'" class= "Blue-Button">Item Feedback</button> &nbsp; <button onclick="location.href= 'ProfileController?action=userfeedback&theItem=${current.userItem}'" class= "Blue-Button">User Feedback</button> </div> </td>
                             
  		         </c:when> 
 	 	         <c:otherwise>                  
                            <td> <div>  <button onclick="location.href= 'ProfileController?action=update&swapItemName=${current.itemName}&theItem=${current.userItem}'" class= "Blue-Button">Update</button> &nbsp; <button onclick="location.href= 'ProfileController?action=delete&theItem=${current.userItem}'" class= "Blue-Button">Delete</button> </div> </td> 
 	                 </c:otherwise>
                      </c:choose>   	                                                              						                  
                           </tr>  
                            
                  </c:forEach>

			</table>
			<hr>	 
			<br> <br> <br>   	 	
       </main>
<%@include file="navigation.jsp"%>	
<%@include file="footer.jsp"%>
