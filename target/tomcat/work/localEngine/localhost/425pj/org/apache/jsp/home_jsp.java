package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("<title>My JSP 'home.jsp' starting page</title>\r\n");
      out.write("\r\n");
      out.write("<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"expires\" content=\"0\">\r\n");
      out.write("<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("<!--\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\r\n");
      out.write("\t-->\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/navigation.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	if (request.getSession().getAttribute("account") == null) {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		return;
	}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<body bgcolor=\"#EFF4FB\">\r\n");
      out.write("\t<h2 align=\"center\">CS425 Database Final Project</h2>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<div align=\"center\">\r\n");
      out.write("\t\t<span style=\"font: bold;\">team member:</span><br><br>\r\n");
      out.write("\t\tYijun Mao <a href=\"mailto:ymao3@iit.edu\">ymao3@iit.edu</a> <br>\r\n");
      out.write("\t\tHan Wang <a href=\"mailto:hwang78@iit.edu\">hwang78@iit.edu</a><br>\r\n");
      out.write("\t\tRuizhe Li<br>\r\n");
      out.write("\t\tYubo Diao<br>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<br>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<div style=\"margin-left: 20px\">\r\n");
      out.write("\r\n");
      out.write("\t\t<span>Project Requirement Document:&nbsp;<a href=\"/425pj/files/CS425DatabaseProjectRequirement.pdf\">DatabaseProject-Spring2013.pdf </a> </span><br>\r\n");
      out.write("\t\t<span>Source Code:&nbsp;<a href=\"/425pj/files/425Project.zip\">425Project.zip </a> </span><br>\r\n");
      out.write("\t\t<span>ER Design Diagram:&nbsp;<a href=\"/425pj/files/425Proj_ER_Diagram.pdf\">ER_Diagram.pdf </a> </span><br>\r\n");
      out.write("\t\t<span>Design Document:&nbsp;<a href=\"/425pj/files/425Proj_ER_Diagram.pdf\"></a> </span><br>\r\n");
      out.write("\t\t<span>Test Plan Document:&nbsp;<a href=\"/425pj/files/425Proj_ER_Diagram.pdf\"></a> </span><br>\r\n");
      out.write("\t\t<span>Manual:&nbsp;<a href=\"/425pj/files/425Proj_ER_Diagram.pdf\"></a> </span><br>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
