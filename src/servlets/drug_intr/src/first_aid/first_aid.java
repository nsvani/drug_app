package first_aid;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class first_aid
 */
@WebServlet("/first_aid")
public class first_aid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public first_aid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] name=new String[10];
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/drugbank","root","vani0410");
			Statement st=c.createStatement();
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			String[] symp=request.getParameterValues("symp");
			
			String sql="create table temp_1 (name varchar(50),c int,primary key(name))";
			st.executeUpdate(sql);
			for(int j=0;j<symp.length;j++)
			{
				sql="select distinct name from otc where ind ='"+symp[j]+"'";
				System.out.println(sql);
				ResultSet r=st.executeQuery(sql);
			//	int i=0;
				out.println(j+1+";");
				while(r.next())
				{
					
					//out.println(r.getString("name")+";");
					String sql1="insert into temp(name,c) values('"+r.getString("name")+"', 1) ON DUPLICATE KEY UPDATE c = c + 1";
					System.out.println(sql1);
					Statement st2=c.createStatement();
					st2.executeUpdate(sql1);
					
				}
				//can optimize this query.... will always calculate max for every row
				sql="select name from temp	where c= (select max(c) from temp)";
				r=st.executeQuery(sql);
				while(r.next())
				{
					out.println(r.getString("name")+";");
				}
				
			}
			sql="drop table temp_1";
			st.executeUpdate(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
