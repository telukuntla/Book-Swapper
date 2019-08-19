<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
	   <main>
	       <header class="header_blue">
	           <div>Home->Category</div>
	       </header>
               
               <c:choose>              
                 <c:when test="${requestScope.items != null}">
    
             <section>
              <table>      
                     <tr>
                         <td><a class = "buttonLink" href="CatalogController?catelogCategory=Technology"> <c:out value="Technology" /></a></td>
                     </tr>      
              </table> 
                     
               <table class = "myitems_table">      
               <c:forEach items="${requestScope.items}" var="item">
                  <c:if test = "${item.category == 'Technology'}">
                        <tr>
                            <td><a href="CatalogController?itemCode=${item.itemcode}"> <c:out value="${item.name}" /></a></td>
                        </tr>
                   </c:if>
                </c:forEach>  
               </table>        
	       </section>
               
          <section>             
               <table>      
                     <tr>
                         <td><a class = "buttonLink" href="CatalogController?catelogCategory=Science Fiction"> <c:out value="Science Fiction" /></a></td>
                     </tr>      
               </table> 
  
             <table class = "myitems_table">             
               <c:forEach items="${requestScope.items}" var="item">
                    <c:if test = "${item.category == 'Science Fiction'}"> 
                        <tr>
                            <td><a href="CatalogController?itemCode=${item.itemcode}"> <c:out value="${item.name}" /></a></td>
                        </tr>
                    </c:if>    
                </c:forEach>                   
             </table>                       
	      </section>                              
                                     
               </c:when>
            <c:when test="${sessionScope.items != null}"> 
            <section>
              <table>      
                     <tr>
                         <td><a class = "buttonLink" href="CatalogController?catelogCategory=Technology"> <c:out value="Technology" /></a></td>
                     </tr>      
              </table> 
                     
               <table class = "myitems_table">      
               <c:forEach items="${sessionScope.items}" var="item">
                  <c:if test = "${item.category == 'Technology'}">
                        <tr>
                            <td><a href="CatalogController?itemCode=${item.itemcode}"> <c:out value="${item.name}" /></a></td>
                        </tr>
                   </c:if>
                </c:forEach>  
               </table>        
	       </section>
               
          <section>             
               <table>      
                     <tr>
                         <td><a class = "buttonLink" href="CatalogController?catelogCategory=Science Fiction"> <c:out value="Science Fiction" /></a></td>
                     </tr>      
               </table> 
  
             <table class = "myitems_table">             
               <c:forEach items="${sessionScope.items}" var="item">
                    <c:if test = "${item.category == 'Science Fiction'}"> 
                        <tr>
                            <td><a href="CatalogController?itemCode=${item.itemcode}"> <c:out value="${item.name}" /></a></td>
                        </tr>
                    </c:if>    
                </c:forEach>                   
             </table>                       
	      </section>                                          
               </c:when>             
               </c:choose>
                 
	   </main>
	
 <%@include file="navigation.jsp"%>	
 <%@include file="footer.jsp"%>
 

