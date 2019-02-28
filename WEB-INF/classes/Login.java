import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class Login extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res){
		res.setContentType("text/html");
		PrintWriter out=null;
		try{
			out=res.getWriter();
			String e=req.getParameter("email");
			String p=req.getParameter("pass");
			ServletContext ctx=getServletContext();
			String dbdriver=ctx.getInitParameter("dbdriver");
			String dbpath=ctx.getInitParameter("dbpath");
			String dbid=ctx.getInitParameter("dbid");
			String dbpass=ctx.getInitParameter("dbpass");
			Class.forName(dbdriver);
			Connection c=DriverManager.getConnection(dbpath,dbid,dbpass);
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("Select * from user_info where email='"+e+"'and pass='"+p+"'");
			if(rs.next()){
				HttpSession hs=req.getSession();
				hs.setAttribute("n",rs.getString("name"));
				hs.setAttribute("e",rs.getString("email"));
				hs.setAttribute("p",rs.getString("phone"));
				hs.setAttribute("a",rs.getString("age"));
				res.sendRedirect("Profile");
			}else{
				res.sendRedirect("loginerror.html");
			}
			out.close();
		}catch(Exception ex){
			out.print(ex);
			out.close();
		}
	}
}
	