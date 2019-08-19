package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class mySwaps_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/header.jsp");
    _jspx_dependants.add("/navigation.jsp");
    _jspx_dependants.add("/footer.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=US-ASCII");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write(" \n");
      out.write("\n");
      out.write("<html lang = \"en\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset = \"UTF-8\">\n");
      out.write("        <title>Book Swap</title> \n");
      out.write("\t    <link rel = \"stylesheet\" type =\"text/css\" href=\"style.css\">\n");
      out.write("    </head> \n");
      out.write("  \n");
      out.write("    <body>\n");
      out.write("        <header class = \"header_blue\">\n");
      out.write("\t    <h1> Book Swapper <img src=\"img1.jpg\" alt=\"Book Swap\"> </h1> \n");
      out.write("              <c:choose>\n");
      out.write(" \t  \t<c:when test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.theUser!=null}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("  \t   \t\t<li class=\"topright\" id=\"SignIn\"><a href=\"#\"> UserName: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.theUser.userId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </a></li>\n");
      out.write("                        <br>\n");
      out.write("  \t   \t\t<li id=\"SignOut\"><a href=\"ProfileController?action=signout\">SignOut</a></li>\n");
      out.write("  \t\t</c:when>\n");
      out.write(" \t \t<c:otherwise>\n");
      out.write("                    <div class=\"toprightdown\">Not Signed in.</div>\n");
      out.write(" \t        </c:otherwise>\n");
      out.write("          </c:choose>   \t   \n");
      out.write("        </header>\n");
      out.write("  \n");
      out.write(" ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "site-navigation.jsp", out, false);
      out.write("   \n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t<main>\n");
      out.write("\t        <header class = \"header_blue\">\n");
      out.write("\t\t\t<div>Home->My Books</div>\n");
      out.write("\t\t\t</header>\n");
      out.write("\t \n");
      out.write("\t\t\t<h2>John's Books for Swap</h2>\t\n");
      out.write("\t\t\t<hr>\n");
      out.write("\t\t\t<table class=\"myitems_table\">\n");
      out.write("\t\t\t\t<tr class = \"font_bold\">\n");
      out.write("\t\t\t\t\t<td>Book</td>\n");
      out.write("\t\t\t\t\t<td>Swap Offer</td>\n");
      out.write("\t\t\t\t\t<td></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\n");
      out.write("                       <c:forEach items=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${offers}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" var=\"offer\">\n");
      out.write("                           \n");
      out.write("                       <script>    alert(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${offer}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("); </script>\n");
      out.write("                         <tr>       \n");
      out.write("                             <td><a href=\"item.jsp\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${offer.itemcode}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </a> </td>\n");
      out.write("                             <td><a href=\"item.jsp\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${offer.requestedItemName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </a> </td>\n");
      out.write("                             \n");
      out.write("                           <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${offer.requestedFrom == sessionScope.theUser.userId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" >\n");
      out.write("                          \n");
      out.write("                            <td> <div> <button onclick=\"location.href='ProfileController?theItem=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${offer.itemcode}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&action=withdraw'\" class= \"Blue-Button\">Withdraw</button>\n");
      out.write("                           </c:if>  \n");
      out.write("                         <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${offer.requestedFrom!= sessionScope.theUser.userId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" >\n");
      out.write("                         <td> <div> <button onclick=\"location.href='ProfileController?theItem=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${offer.itemcode}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&action=accept'\" class= \"Blue-Button\">Accept</button> &nbsp; <button onclick=\"location.href='myitems.jsp'\" class= \"Blue-Button\">Reject</button> </div> </td>\n");
      out.write("                         </c:if>\n");
      out.write("                                                  \n");
      out.write("                         </tr>      \n");
      out.write("                        </c:forEach>    \n");
      out.write("                          \n");
      out.write("                                <tr>\n");
      out.write("                    <td> <a href=\"item.jsp\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${swapItem.itemName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </a> </td>\n");
      out.write("\t\t\t\t    <td><a href=\"item.jsp\">The Forever War</a></td>   \n");
      out.write("                    <td> <div> <button onclick=\"location.href='ProfileController?theItem=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${swapItem.userItem}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&action=accept'\" class= \"Blue-Button\">Accept</button> &nbsp; <button onclick=\"location.href='myitems.jsp'\" class= \"Blue-Button\">Reject</button> </div> </td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("                                \n");
      out.write("                                \n");
      out.write("\t\t\t\t<tr>\n");
      out.write("                    <td> <a href=\"item.jsp\">The City Outside the World</a> </td>\n");
      out.write("\t\t\t\t    <td> <a href=\"item.jsp\">What Technology Wants</a></td>\n");
      out.write("\t\t\t        <td> <div> <button onclick=\"location.href='myitems.jsp'\" class= \"Blue-Button\"> Widthdraw Swap </button> &nbsp; </div> </td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t</table>\n");
      out.write("\t\t\t<hr>\t \n");
      out.write("\t\t\t<br> <br> <br>   \t \t\t\t\n");
      out.write("\t\t</main>\n");
      out.write("<aside>   \t\n");
      out.write("    <nav>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"index.jsp\">Home</a></li>                 \n");
      out.write("            <li><a href=\"CatalogController\">Category</li>\n");
      out.write("            <li><a href=\"about.jsp\">About</a></li><br>\t\n");
      out.write("\t    <li><a href=\"#\">store</a></li>\t\n");
      out.write("            <li><a href=\"contact.jsp\">contact Us</a></li>\n");
      out.write("\t</ul>\n");
      out.write("    </nav>  \n");
      out.write("</aside>");
      out.write("\t\t\n");
      out.write("\n");
      out.write("\t    <footer>\n");
      out.write("            <p>@SaiKrishna Book Swap Inc</p>\n");
      out.write("\t    </footer>\t\n");
      out.write("\t</body>\t\n");
      out.write("</html>");
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
