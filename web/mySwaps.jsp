<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@include file="header.jsp"%>
	<main>
	        <header class = "header_blue">
			<div>Home->My Books</div>
                </header>
	 
			<h2>${sessionScope.theUser.userId}'s Books for Swap</h2>	
			<hr>
			<table class="myitems_table">
				<tr class = "font_bold">
					<td>Book</td>
					<td>Swap Offer</td>
					<td></td>
				</tr>
		
                       <c:forEach items="${requestScope.requestedFromList}" var="offer">                          
                         <tr>       
                             <td><a href="CatalogController?itemCode=${offer.itemcode}"> ${offer.itemName} </a> </td>
                             <td><a href="CatalogController?itemCode=${offer.requestedItemcode}"> ${offer.requestedItemName} </a> </td>

                           <c:if test="${offer.requestedFrom == sessionScope.theUser.userId}" >
                          
                            <td> <div> <button onclick="location.href='ProfileController?theItem=${offer.itemcode}&theItemRequested=${offer.requestedItemcode}&action=withdraw'" class= "Blue-Button">Withdraw</button>
                           </c:if>  
                                <c:if test="${offer.requestedFrom!= sessionScope.theUser.userId}" >
                                  <td> <div> <button onclick="location.href='ProfileController?theItem=${offer.itemcode}&theItemRequested=${offer.requestedItemcode}&action=accept'" class= "Blue-Button">Accept</button> &nbsp; <button onclick="location.href='ProfileController?theItem=${offer.itemcode}&theItemRequested=${offer.requestedItemcode}&action=reject'" class= "Blue-Button">Reject</button></div> </td>
                                </c:if>                                                 
                         </tr>      
                       </c:forEach>    
                   
                         
                      <c:forEach items="${requestScope.requestedToList}" var="offer">                          
                         <tr>  
                             <td><a href="CatalogController?itemCode=${offer.requestedItemcode}"> ${offer.requestedItemName} </a> </td>
                             <td><a href="CatalogController?itemCode=${offer.itemcode}"> ${offer.itemName} </a> </td>
                            
                           <c:if test="${offer.requestedFrom == sessionScope.theUser.userId}" >          
                          
                            <td> <div> <button onclick="location.href='ProfileController?theItem=${offer.requestedItemcode}&theItemRequested=${offer.itemcode}&action=withdraw'" class= "Blue-Button">Withdraw</button>
                            </c:if>  
                                <c:if test="${offer.requestedFrom!= sessionScope.theUser.userId}" >
                                  <td> <div> <button onclick="location.href='ProfileController?theItem=${offer.requestedItemcode}&theItemRequested=${offer.itemcode}&action=accept'" class= "Blue-Button">Accept</button> &nbsp; <button onclick="location.href='ProfileController?theItem=${offer.requestedItemcode}&theItemRequested=${offer.itemcode}&action=reject'" class= "Blue-Button">Reject</button></div> </td>
                                </c:if>                                                 
                         </tr>      
                       </c:forEach>                                              
                     
			</table>
			<hr>	 
			<br> <br> <br>   	 			
	</main>
<%@include file="navigation.jsp"%>		
<%@include file="footer.jsp"%>
