    package uncc.nbad;

    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import javax.servlet.http.HttpSession;
    import org.mindrot.jbcrypt.BCrypt;
    import org.owasp.esapi.ESAPI;
    import org.owasp.esapi.errors.IntrusionException;
    import org.owasp.esapi.errors.ValidationException;

    /**
     * Servlet implementation class ProfileController
     */
    @WebServlet("/ProfileController")
    public class ProfileController extends HttpServlet {
            private static final long serialVersionUID = 1L;

        /**
         * Default constructor. 
         */
        public ProfileController() {
            // TODO Auto-generated constructor stub
        }

            /**
             * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
             */
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

           try{        
               HttpSession session=request.getSession();  

               itemDB DB = new itemDB();
               List<Item> items = DB.getAllItems();

               String action = "";

               if(request.getParameter("action")!=null){
                    action =  ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false);
               }
             //This condition handles request parameter signout and invalidates the session 
                if(action.equalsIgnoreCase("signout")) {	 		

                     session.invalidate();                

                     getServletContext()
                    .getRequestDispatcher("/categories.jsp")
                    .forward(request, response); 	
                     }	                   
                  //This condition handles requested action 'myswaps', redirects to mySwaps page after setting
                 // required request parameters
                   if(action.equalsIgnoreCase("myswaps")){
                        try{   
                            HttpSession current_session = request.getSession();
                            Userprofile current_profile = (Userprofile) current_session.getAttribute("currentProfile");	

                            List<Offer> list = offersDB.getOffers(current_profile.getUserId());   

                            List<Offer> requestedFromList = offersDB.getOffersReqFromUser(current_profile.getUserId());
                            List<Offer> requestedToList = offersDB.getOffersReqToUser(current_profile.getUserId());

                            request.setAttribute("offers", list);
                            request.setAttribute("requestedFromList", requestedFromList);
                            request.setAttribute("requestedToList", requestedToList);

                            getServletContext()
                           .getRequestDispatcher("/mySwaps.jsp")
                           .forward(request, response);    
                         }catch(Exception e){
                             getServletContext()
                            .getRequestDispatcher("/mySwaps.jsp")
                            .forward(request, response); 
                     }        
                        
           } 
            // This condition handles requested action 'confirmswap',handles bussiness logic for 
           // sending swap request to the concerned user
             if(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equalsIgnoreCase("confirmswap")){

               String booktoswapwith = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("booktoswapwith"),"HTTPParameterValue", 200, false);             
               String booktoswap =  ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("book"),"HTTPParameterValue", 200, false);                

               if(booktoswapwith.equalsIgnoreCase(booktoswap)){               
                   request.setAttribute("swapmessage", "please choose a different item from the list of available items :)");

                   for(Item itm: items)                          
                      if(booktoswapwith.equalsIgnoreCase(itm.getItemcode())){

                          request.setAttribute("item1", itm);
                          break;
                       }

                    getServletContext()
                    .getRequestDispatcher("/swap.jsp")
                     .forward(request, response);         

             }else{

                 String itemOwner = itemDB.getItemOwner(booktoswapwith); 
                 offersDB offersDB = new offersDB();

                 Userprofile profile = (Userprofile)session.getAttribute("currentProfile");
                 offersDB.addOffer(profile.getUserId(), itemOwner, booktoswap, booktoswapwith);

                 itemDB.UpdateStatus(booktoswapwith, "pending");  
                 itemDB.UpdateStatus(booktoswap, "pending");

                 HttpSession current_session = request.getSession();					
                 Userprofile current_profile = (Userprofile) current_session.getAttribute("currentProfile"); 

                 List<Useritem> userItems  = current_profile.getUserItems();			     	
                 List<Useritem> modified_Items  = new ArrayList<Useritem>(); 

                for(Useritem useritem : userItems) {
                    if(useritem.getUserItem().equalsIgnoreCase(booktoswap)) {		     			

                         useritem.setStatus("pending");

                         }	     					     					     					     						     			      	
                    modified_Items.add(useritem);

                }                                                             
                 current_profile.setUserItems(modified_Items);		     	
                 session.setAttribute("currentProfile", current_profile);  
                 session.setAttribute("useritems", current_profile.getUserItems());

                 getServletContext()
                   .getRequestDispatcher("/myitems.jsp")
                    .forward(request, response);                           
           }
           //This condition handles requested action- 'register' and handles bussines logic for user registration       
           }if( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equals("register")){

                User user = new User();   
                
               //UpdatableBCrypt is utility class that has pre defined methods for salting and hashing password.
               //JBCrypt-0.4.jar is the library              
                UpdatableBCrypt bCrypt = new UpdatableBCrypt();
             
                user.setUserId(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("username"),"HTTPParameterValue", 200, false));                
               
               // hash is the method present in the utility class, this method adds salt before performing hash.
                user.setPassword( bCrypt.hash(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("userpass"),"HTTPParameterValue", 200, false)));   
                user.setFirstName(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("firstname"),"HTTPParameterValue", 200, false));
                user.setLastName(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("lastname"),"HTTPParameterValue", 200, false) );  
                user.setEmail(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("email"),"HTTPParameterValue", 200, false));               
                user.setAddressField1(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("addressField1"),"HTTPParameterValue", 200, false));               

                if(request.getParameter("addressField2")!=null & !request.getParameter("addressField2").isEmpty()){          

                    user.setAddressFiled2( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("addressField2"),"HTTPParameterValue", 200, false));              

                }
                user.setCity(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("city"),"HTTPParameterValue", 200, false));
                user.setState(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("state"),"HTTPParameterValue", 200, false));                                        
                user.setPostalCode(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("postalCode"),"HTTPParameterValue", 200, false));           
                user.setCountry(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("country"),"HTTPParameterValue", 200, false));               

                UserDB.addUser(user);

                getServletContext()
                    .getRequestDispatcher("/login.jsp")
                     .forward(request, response);     
           }
          //This condition handles requested action-item feed back and redirects to itemFeedback page
           if( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equals("itemfeedback")){

                String itemCode =   request.getParameter("theItem"); 
                String itemName =   request.getParameter("swapItemName");

                request.setAttribute("itemCode", itemCode);
                request.setAttribute("itemName", itemName);

                User user = (User)session.getAttribute("theUser");

               if(FeedbackDB.itemFeedbackPresent(itemCode,user.getUserId())){

                    String feedbackMessage = "you have already rated the book: " + itemName ;       
                    request.setAttribute("feedbackMessage", feedbackMessage);

                    getServletContext()
                            .getRequestDispatcher("/myitems.jsp")
                             .forward(request, response);        
               }else{

                  getServletContext()
                        .getRequestDispatcher("/itemFeedback.jsp")
                         .forward(request, response); 
               }
           }
            //This condition handles requested action -submit feedback, and handles bussiness logic for submitting 
            //item feedback
             if(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equals("submitFeedback")){

                String itemCode = request.getParameter("itemCode");
                String rating= request.getParameter("rating");

                User user = (User)session.getAttribute("theUser");           
                FeedbackDB.addItemFeedback(itemCode,user.getUserId(), rating);     

                getServletContext()
                    .getRequestDispatcher("/myitems.jsp")
                     .forward(request, response);  

           } 
           // This condition handles requested action-userfeedback and redirects to userfeedback page          
             if(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equals("userfeedback")){

                  String itemCode =   request.getParameter("theItem");              
                  User user =  (User)session.getAttribute("theUser");
                  String userForFeedback = FeedbackDB.getUserForFeedBack(itemCode, user.getUserId());       

                  if(userForFeedback == null){         
                      userForFeedback = FeedbackDB.getUserForFeedBack1(itemCode, user.getUserId());
              }

               if(FeedbackDB.userFeedBackPresent(user.getUserId(),userForFeedback)){

                   String feedbackMessage = "You have already rated the user: "+ userForFeedback ;

                   request.setAttribute("feedbackMessage", feedbackMessage);
                   getServletContext()
                    .getRequestDispatcher("/myitems.jsp")
                     .forward(request, response); 

               }

              request.setAttribute("userForFeedback", userForFeedback);
              request.setAttribute("itemCode", itemCode);

              getServletContext()
                    .getRequestDispatcher("/userFeedback.jsp")
                     .forward(request, response);  
           } 
             // This condition handles requested action-submitUserFeedback and handles bussines
            // logic for recording user feedback 
             if(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equals("submitUserFeedback")){

                String itemCode = request.getParameter("itemCode");
                String rating = request.getParameter("rating");
                String toUser =  request.getParameter("userForFeedback");    
                User user =  (User)session.getAttribute("theUser");            
                String fromUser = user.getUserId();

                FeedbackDB.addUserFeedback(itemCode, fromUser, toUser, rating);

                getServletContext()
                    .getRequestDispatcher("/myitems.jsp")
                     .forward(request, response);  
           }  
               try {
               // This condition handles requested action-signin and handles bussines logic for signing in 
               //user.
                if( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equalsIgnoreCase("signin")) {

                    List<Item> custom_list = new ArrayList<Item>();        

                  if(session.getAttribute("theUser") == null) {       	
                     try {

                        if( request.getParameter("username") == null || request.getParameter("username").trim().isEmpty() ||  request.getParameter("userpass") == null || request.getParameter("userpass").trim().isEmpty() ){

                           getServletContext()
                            .getRequestDispatcher("/login.jsp")
                            .forward(request, response);           

                     }else{                      

                 String username = "";
                 String password = "";
           try{           
                    username = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("username"),"HTTPParameterValue", 200, false);
                    password = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("userpass"),"HTTPParameterValue", 200, false);         

              } catch (ValidationException ex) {

                    String message = "Enter valid username or password";             
                    request.setAttribute("message",ESAPI.encoder().encodeForHTML(message));

                       getServletContext()
                                    .getRequestDispatcher("/login.jsp")
                                    .forward(request, response);              

               } catch (IntrusionException ex) {

               }               
               if(UserDB.isValidUser(username, password)){

                    User user_current = new User();                               					
                    user_current = UserDB.getUser(username);
                    session.setAttribute("theUser", user_current);	

                    Userprofile user_profile = UserDB.getUserProfile(username);

                    session.setAttribute("rating", FeedbackDB.getUserRating(username));                  
                    session.setAttribute("currentProfile", user_profile);
                    session.setAttribute("useritems", user_profile.getUserItems());

                    Userprofile profile = (Userprofile)session.getAttribute("currentProfile");      	 
                    List<Useritem> existing_availablelist =  profile.getUserItems();             	  

                    for(Item i : items){      
                         custom_list.add(i);        
                      }      

                    for(Item it : items ){                             
                        for(Useritem it1 : existing_availablelist ){                  
                            if(it1.getUserItem().equalsIgnoreCase(it.getItemcode())){
                                custom_list.remove(it);                       
                            }
                        }         
                    }                                               
                    session.setAttribute("items", custom_list);

                             getServletContext()
                            .getRequestDispatcher("/myitems.jsp")
                            .forward(request, response);

                          }else{

                       String message = "username or password incorrect";                  
                       request.setAttribute("message",ESAPI.encoder().encodeForHTML(message));

                       getServletContext()
                                    .getRequestDispatcher("/login.jsp")
                                    .forward(request, response); 
                       }             
                     }                                  

                  }catch (Exception e) {
                                    // TODO Auto-generated catch block
                   e.printStackTrace();
              }	    	 		
             }else{
                     getServletContext()
                            .getRequestDispatcher("/myitems.jsp")
                            .forward(request, response);               
                 }     

             }
           }       

                catch (Exception e) {
                            // TODO Auto-generated catch block
                  e.printStackTrace();
                    }


                String item_inQues =  ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false);
                boolean flag_itempresent = false;

                Userprofile userprofie =  (Userprofile)session.getAttribute("currentProfile");       
                List<Useritem> useritems = userprofie.getUserItems();       

                    for(Useritem item : useritems) {    	

                        if(item.getUserItem().equalsIgnoreCase(item_inQues)){
                                flag_itempresent = true;
                                break;
                        }  	    	
                    }        
                if(flag_itempresent) {
                
                     if (request.getParameterMap().containsKey("action")){		 

                         //This condition handles requested action-update, which gets triggered when
                         //user press update button in myitems page
                         if( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equalsIgnoreCase("update")) {

                               HttpSession current_session = request.getSession();
                               Userprofile current_profile = (Userprofile) current_session.getAttribute("currentProfile");			 			
                               Useritem user_item =  current_profile.getUserItems().get(0);
                               List<Useritem> useritems1 =  current_profile.getUserItems();  	   

                               for(Useritem item : useritems1){

                                      if( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("swapItemName"),"HTTPParameterValue", 200, false).equalsIgnoreCase(item.getItemName())){                          
                                          user_item = item;
                                      }                   
                                 }                                        
                             // when user clicks update button on myitems page, it verfies if the
                             // corresponding item status, if staus is pending, user gets redirected to 
                             // to myswaps page
                              if(user_item.getStatus().equalsIgnoreCase("pending")) {                                        

                                  List<Offer> list = offersDB.getOffers(current_profile.getUserId());                      

                                  List<Offer> requestedFromList = offersDB.getOffersReqFromUser(current_profile.getUserId());
                                  List<Offer> requestedToList = offersDB.getOffersReqToUser(current_profile.getUserId());

                                  request.setAttribute("swapItem", user_item);	     	
                                  request.setAttribute("offers", list);
                                  request.setAttribute("requestedFromList", requestedFromList);
                                  request.setAttribute("requestedToList", requestedToList);

                                     getServletContext()
                                    .getRequestDispatcher("/mySwaps.jsp")
                                    .forward(request, response);       

                              }else{}

                             if(user_item.getStatus().equalsIgnoreCase("this item is available for swap") ||  user_item.getStatus().equalsIgnoreCase("this item was swapped")) {	     		 	     		              

                                 for(Item item1 : items) {	            	 
                                     if(item1.getItemcode().equalsIgnoreCase(user_item.getUserItem())){            		 	            		 

                                        request.setAttribute("item", item1);
                                        request.setAttribute("userItem", user_item);
                                        request.setAttribute("itemStatus", user_item.getStatus());

                                            getServletContext()
                                            .getRequestDispatcher("/item.jsp")
                                            .forward(request, response);      	            			            	
                                     }            	            	 
                                 }
                                }	     		  	     	  

                                     } else if( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equalsIgnoreCase("accept") || ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equalsIgnoreCase("withdraw") || ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equalsIgnoreCase("reject")) {

                                           HttpSession current_session = request.getSession();					
                                           Userprofile current_profile = (Userprofile) current_session.getAttribute("currentProfile");								 			 
                                           Useritem user_item =  current_profile.getUserItems().get(0);
                                          //This condition handles requested action - accept, which gets triggered when user press
                                          //accept button on myswaps page
                                          if(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equalsIgnoreCase("accept")) {		     			  			     			 			    	  

                                            List<Useritem> userItems  = current_profile.getUserItems();			     	
                                            List<Useritem> modified_Items  = new ArrayList<Useritem>(); 

                                            for(Useritem useritem : userItems) {			     		 
                                                    if(useritem.getUserItem().equalsIgnoreCase( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false))) {		     			
                                                            useritem.setStatus("this item was swapped");

                                                            modified_Items.add(useritem);	

                                                    }else {		     			
                                                            modified_Items.add(useritem);		     					     			
                                                    }			     					     		
                                            }
                                           itemDB.UpdateStatus( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false),"this item was swapped" );
                                           itemDB.UpdateStatus( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItemRequested"),"HTTPParameterValue", 200, false), "this item was swapped");

                                           offersDB.acceptSwapOffer( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItemRequested"),"HTTPParameterValue", 200, false),current_profile.getUserId());

                                           current_profile.setUserItems(modified_Items);		     	
                                           session.setAttribute("currentProfile", current_profile);
                                           session.setAttribute("useritems", current_profile.getUserItems());

                                            getServletContext()
                                            .getRequestDispatcher("/myitems.jsp")
                                            .forward(request, response);   

                                 }
                              if( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equalsIgnoreCase("reject") || ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equalsIgnoreCase("withdraw")) {	        	

                                 //This condition handles requested action reject, which gets triggered when user press
                                 //reject button on myswaps page
                                 if( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equalsIgnoreCase("reject")){      

                                    itemDB.UpdateStatus( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false),"this item is available for swap" );
                                    itemDB.UpdateStatus( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItemRequested"),"HTTPParameterValue", 200, false),"this item is available for swap");
                                    offersDB.rejectSwapOffer( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItemRequested"),"HTTPParameterValue", 200, false), current_profile.getUserId());         

                                    List<Useritem> userItems  = current_profile.getUserItems();			     	
                                    List<Useritem> modified_Items  = new ArrayList<Useritem>(); 

                                    for(Useritem useritem : userItems) {			     		 
                                        if(useritem.getUserItem().equalsIgnoreCase( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false))) {		     			
                                                useritem.setStatus("this item is available for swap");

                                                modified_Items.add(useritem);	

                                        }else {		     			
                                                modified_Items.add(useritem);		     					     			
                                        }			     					     		
                                    }  
                                    current_profile.setUserItems(modified_Items);		     	
                                    session.setAttribute("currentProfile", current_profile);
                                    session.setAttribute("useritems", current_profile.getUserItems());

                                    getServletContext() 
                                   .getRequestDispatcher("/myitems.jsp")
                                   .forward(request, response);  

                                        }
                                  //This condition handles requested action withdraw, which gets triggered when user press
                                 //withdraw button on myswaps page     
                                 if( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"),"HTTPParameterValue", 200, false).equalsIgnoreCase("withdraw")){

                                       offersDB.withdrawSwapOffer( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false)   , current_profile.getUserId());
                                       itemDB.UpdateStatus( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false) ,"this item is available for swap");
                                       itemDB.UpdateStatus( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItemRequested"),"HTTPParameterValue", 200, false) , "this item is available for swap");

                                        List<Useritem> userItems  = current_profile.getUserItems();			     	
                                        List<Useritem> modified_Items  = new ArrayList<Useritem>(); 

                                        for(Useritem useritem : userItems) {			     		 
                                                if(useritem.getUserItem().equalsIgnoreCase( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false))) {		     			
                                                    useritem.setStatus("this item is available for swap");                                         
                                                    modified_Items.add(useritem);	

                                                }else {		     			
                                                    modified_Items.add(useritem);		     					     			
                                                    }			     					     		
                                                }            

                                                current_profile.setUserItems(modified_Items);		     	
                                                session.setAttribute("currentProfile", current_profile);
                                                session.setAttribute("useritems", current_profile.getUserItems());

                                     }                           
                                     getServletContext() 
                                    .getRequestDispatcher("/myitems.jsp")
                                    .forward(request, response);  

                                     }} else if(action.equalsIgnoreCase("delete") ) {
                                       //This condition handles requested action detere, which gets triggered when user press
                                       //delete button on myitems page     
                                           HttpSession current_session = request.getSession();					
                                           Userprofile current_profile = (Userprofile) current_session.getAttribute("currentProfile");                             

                                            List<Useritem> userItems  = current_profile.getUserItems();			     	
                                            List<Useritem> modified_Items  = new ArrayList<Useritem>(); 

                                            for(Useritem useritem : userItems) {

                                                    if(useritem.getUserItem().equalsIgnoreCase( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false))) {		     			


                                                    }else {		     			
                                                            modified_Items.add(useritem);		     					     			
                                                    }		      	
                                             }	

                                          DB.delete_item(current_profile.getUserId(), ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false));

                                           current_profile.setUserItems(modified_Items);		     	
                                           session.setAttribute("currentProfile", current_profile);  
                                           session.setAttribute("useritems", current_profile.getUserItems());

                                           getServletContext()
                                          .getRequestDispatcher("/myitems.jsp")
                                          .forward(request, response); 

                                     }		
                               }
                 }
                //This condition handles requested action - offer, which gets triggered when user 
                //clicks 'see the offer' button on item page
                if(action.equalsIgnoreCase("offer")) {				 

                     List<Useritem> available_list = new ArrayList<Useritem>();	
                     HttpSession current_session = request.getSession();
                     Userprofile current_profile = (Userprofile) current_session.getAttribute("currentProfile");	
                     List<Useritem> userItems  = current_profile.getUserItems();		

                if( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("status"),"HTTPParameterValue", 200, false).equalsIgnoreCase(" pending")){       

                    String code = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false);

                    request.setAttribute("item", DB.getItem(code));
                    request.setAttribute("offermessage", "Sorry, no offers can be made on pending items");
                 
                    getServletContext()
                   .getRequestDispatcher("/item.jsp")
                   .forward(request, response);
                }else if ( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("status"),"HTTPParameterValue", 200, false).equalsIgnoreCase(" this item was swapped")){     

                          String code = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false);
                          request.setAttribute("item", DB.getItem(code));

                          request.setAttribute("offermessage", "Sorry, no offers can be made, as this item was already swapped");
                                                   getServletContext()
                                           .getRequestDispatcher("/item.jsp")
                                           .forward(request, response);         
                }else{                                                                 
                      for(Useritem item: userItems ) {

                         if(item.getStatus().equalsIgnoreCase("this item is available for swap")){					 				 
                                 available_list.add(item);					 				

                         } 
                       }
                      if(available_list.size()!=0){     

                           request.setAttribute("availablelist", available_list);                                                     
                           String code =  ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false);

                            for(Item itm: items)                          
                              if(code.equalsIgnoreCase(itm.getItemcode())){

                                  request.setAttribute("item1", itm);
                                  break;
                          }

                         getServletContext()
                        .getRequestDispatcher("/swap.jsp")
                         .forward(request, response); 
                         }

                     if(available_list.size()==0) {				 
                         request.setAttribute("offermessage", "Sorry, You do not have any available items for swapping. Please add more items to start swapping again!");

                         for(Item item : items) {

                             if(item.getItemcode().equalsIgnoreCase( ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("theItem"),"HTTPParameterValue", 200, false))) {

                                     request.setAttribute("item", item);	
                                     getServletContext()
                            .getRequestDispatcher("/item.jsp")
                            .forward(request, response); 
                             }					 				 
                         }							 
                     }
      }

       }
                    if(action.equalsIgnoreCase("myswaps")){

                        HttpSession current_session = request.getSession();
                        Userprofile current_profile = (Userprofile) current_session.getAttribute("currentProfile");	

                        List<Offer> list = offersDB.getOffers(current_profile.getUserId());   

                        List<Offer> requestedFromList = offersDB.getOffersReqFromUser(current_profile.getUserId());
                        List<Offer> requestedToList = offersDB.getOffersReqToUser(current_profile.getUserId());

                        request.setAttribute("offers", list);
                        request.setAttribute("requestedFromList", requestedFromList);
                        request.setAttribute("requestedToList", requestedToList);

                         getServletContext()
                        .getRequestDispatcher("/mySwaps.jsp")
                        .forward(request, response);     
                        }        
                         if(action.equalsIgnoreCase("signout")) {	 		
                             session.invalidate();                

                             getServletContext()
                            .getRequestDispatcher("/categories.jsp")
                            .forward(request, response); 	
                         }	   

           }catch(Exception e){
                e.printStackTrace();
           }
         }          		 
            /**
             * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
             */
            protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    // TODO Auto-generated method stub
                    doGet(request, response);
            }

    }
