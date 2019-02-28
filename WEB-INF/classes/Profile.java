import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class Profile extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res){
		res.setContentType("text/html");
		PrintWriter out=null;
		try{
			out=res.getWriter();
			HttpSession hs=req.getSession(false);
			if(hs!=null){
			out.print("<html>");
			out.print("<body>");
			out.print("<h1> Login Logout App </h1>");
			out.print("<hr>");
			out.print("Welcome :"+(String)hs.getAttribute("n")+", <a href='Logout'>Logout</a>");
			out.print("<hr>");
			ServletContext ctx=getServletContext();
			Integer c=(Integer)ctx.getAttribute("count");
			if(c==null){
				c=0;
			}
			ctx.setAttribute("count",++c);
			out.print("No. of Visitors: "+c);
			out.print("<hr>");
			out.print("Email: <b>"+(String)hs.getAttribute("e")+"<br><br>");
			out.print("Phone: <b>"+(String)hs.getAttribute("p")+"<br><br>");
			out.print("Age: <b>"+(String)hs.getAttribute("a")+"<br><br>");
			out.print("</body>");
			out.print("</html>");
			}else{
				res.sendRedirect("Loginerror2.html");
			}
		}catch(Exception ex){
			out.print(ex);
			out.close();
		}
	}
}