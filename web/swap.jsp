<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
	    <main>
	        <header class = "header_blue">Home->Categories->Swap</header> <br>  
	        <table class="table_margin">
                <tr>
                    <td> <img src="${item1.imageurl}" id="item_imageSize" alt="Block Chain"></td>
		          <td> <h4>${item1.name}</h4> by ${item1.author} <br> <br> <button onclick="location.href= 'myitems.jsp'" class= "Blue-Button">Swap it</button> <br><br> <button onclick="location.href= 'myitems.jsp'" class= "Blue-Button">Rate it</button> </td>
                
               <td>        </td>           
                <td>
                <p class="font_bold"> ${item1.headline} </p>
                <p>${item1.description}</p>
                </td> 
               </tr>	                    
	      </table>
	
       <h3> Select item form your available swaps </h3>         
       
      <form action="ProfileController" method="GET">            
     	<c:forEach items="${availablelist}" var="item">
        	<input type="radio" name="book" value=${item.userItem} checked> <a href="CatalogController?itemCode=${item.userItem}"> "${item.itemName}" </a>    <br>
        </c:forEach>   
                <br>
                <input type="submit" value="Confirm Swap"/>
                <input type="hidden" name="action" value="confirmswap" />
                <input type="hidden" name="booktoswapwith" value=${item1.itemcode} />
      </form>
           <c:out value = "${swapmessage}"/>
       <br>
      <a>  &nbsp; </a>      
                      
          </main>
<%@include file="navigation.jsp"%>	
<%@include file="footer.jsp"%>
