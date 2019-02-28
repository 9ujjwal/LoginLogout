import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class Logout extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res){
		res.setContentType("text/html");
		PrintWriter out=null;
		try{
			out=res.getWriter();
			HttpSession hs=req.getSession(false);
			if(hs!=null){
				hs.invalidate();
				res.sendRedirect("index.html");
			}else{
				res.sendRedirect("loginerror2.html");
			}
			out.close();
		}catch(Exception ex){
			out.print(ex);
			out.close();
		}
	}
}
	