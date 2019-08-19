<%@include file="header.jsp"%>
      <main>
        <header class = "header_blue">Home->Categories->Item</header> <br>  
        <button onclick="location.href= 'CatalogController'"> &nbsp; Back</button> 
            <table class="table_margin">
                <tr>
                    <td> <img src="${item.imageurl}" id="item_imageSize" alt="Book"></td>
		    <td> <h4>${item.name} </h4> by ${item.author} <br><br>  <br><br>  </td>                         
                    <td>
                        <p class="font_bold"> ${item.headline} </p>
                        <p> ${item.description}   </p>
                    </td> 
                </tr>
               <tr>     
                   <td> <h4>Rating: ${item.rating} </h4> 
                   </td>  
               </tr>              
               <tr> 
                  <td>Status: <c:out value = "${itemStatus}"/> </td>
                   <td> <c:out value = "${offermessage}"/> </td>
               </tr>  
               <tr>
             	 <td> <div> <button onclick="location.href= 'ProfileController?action=offer&status= <c:out value = "${itemStatus}"/>&theItem=${item.itemcode}'" class= "Blue-Button">See the Offer</button> </div>  </td>
              </tr>                    
	    </table>
    </main>
               
<%@include file="navigation.jsp"%>	
<%@include file="footer.jsp"%>
