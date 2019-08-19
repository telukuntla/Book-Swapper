<%-- 
    Document   : userFeedback
    Created on : Dec 5, 2018, 11:59:38 PM
    Author     : teluk
--%>

<%@include file="header.jsp"%>
     <main>
         <header class = "header_blue">
             <div>Home-> User Rating</div>
         </header>

         <form action="ProfileController" method = "GET" >   
             <input type="hidden" name="itemCode" value= <c:out value = "${requestScope.itemCode}"/>  />           
             <input type="hidden" name="action" value="submitUserFeedback" />
	     <input type="hidden" name="userForFeedback" value=${requestScope.userForFeedback} />
             
             <div>Rating For the User: <c:out value = "${requestScope.userForFeedback}"/> </div> <br>

             <input type="radio" name="rating" value=5 checked />Out Standing <br>
             <input type="radio" name="rating" value=4 />Excellent <br>
             <input type="radio" name="rating" value=3 />Satisfactory <br>
             <input type="radio" name="rating" value=2 />Below average <br>
             <input type="radio" name="rating" value=1 />Not Satisfied <br>
             <input type="submit" value="Submit" />		
	</form>   
       </main>
<%@include file="navigation.jsp"%>	
<%@include file="footer.jsp"%>