package drug_intr;

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
 * Servlet implementation class drug_intr
 */
@WebServlet("/drug_intr")
public class drug_intr extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public drug_intr() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		try
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/drugbank","root","vani0410");
			Statement st=c.createStatement();
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			//st.executeUpdate("use trial");
			String drug1=request.getParameter("drug1");
			String drug2=request.getParameter("drug2");
			int count=0;
			if(drug1.matches("")||drug2.matches(""))
			{
				out.println("<font color='red'><b>Please give proper details<b></font>");
				count=-1;
			}
			ResultSet r=st.executeQuery("select * from drug_info,drug_intr where drug_info.id=drug_intr.id and drug_info.name='"+drug1+"' and drug_intr.name='"+drug2+"'");
			System.out.println("drug1 "+drug1);
			System.out.println("drug2 "+drug2);
			if (!r.next()) {
				  //System.out.println("empty");
				} else {
				  //display results
				  do {
					  out.println(r.getString("drug_intr.description"));
					  System.out.println(r.getString("drug_intr.description"));
					  count++;
				    
				  } while (r.next());
				}
			if(count==0)
			{
				out.println("NO REACTION &#9786");
			}
			/*while(r.next())
				{
					//out.println(r.getString("name")+" "+r.getString("occ")+" "+r.getString("id"));
					out.println(r.getString("drug_intr.description"));
			
				}*/
			
			
			//return r.getString("name")+" "+r.getString("occ")+" "+r.getString("id");
			
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
