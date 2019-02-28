import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class Register extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res){
		int r;
		res.setContentType("text/html");
		PrintWriter out=null;
		try{
			out=res.getWriter();
			String e=req.getParameter("email");
			String n=req.getParameter("name");
			String ph=req.getParameter("phone");
			String a=req.getParameter("age");
			String p=req.getParameter("pass");
			ServletContext ctx=getServletContext();
			String dbdriver=ctx.getInitParameter("dbdriver");
			String dbpath=ctx.getInitParameter("dbpath");
			String dbid=ctx.getInitParameter("dbid");
			String dbpass=ctx.getInitParameter("dbpass");
			Class.forName(dbdriver);
			Connection c=DriverManager.getConnection(dbpath,dbid,dbpass);
			Statement st=c.createStatement();
		    r=st.executeUpdate("insert into user_info values('"+e+"','"+n+"','"+ph+"','"+a+"','"+p+"')");
			if(r!=0){
				HttpSession hs=req.getSession();
				hs.setAttribute("n",n);
				hs.setAttribute("e",e);
				hs.setAttribute("p",ph);
				hs.setAttribute("a",a);
				res.sendRedirect("Profile");
			}else{
				res.sendRedirect("registererror.html");
			}
			out.close();
		}catch(Exception ex){
			out.print(ex);
			out.close();
		}
	}
}
	