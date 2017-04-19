package drug_int;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
public class drug_int extends DefaultHandler
{
	boolean name=false;
	boolean acc_no=false;
	boolean des=false;
	boolean interact=false;
	boolean drug=false;
	boolean drug_bank=false;
	boolean others=false;
	String name_=new String();
	String no=new String();
	String des_=new String();
	int i=1;
	public void startDocument() throws SAXException
	{
		System.out.println("started reading xml file");
	}
	public void startElement(String uri,String localName,String qName,Attributes a) throws SAXException
	{
		if(qName.equalsIgnoreCase("name"))
		{
			name=true;
		}
		else if(qName.equalsIgnoreCase("drugbank-id"))
		{
			acc_no=true;
		}
		else if(qName.equalsIgnoreCase("drug"))
		{
			drug=true;
		}
		else if(qName.equalsIgnoreCase("drugbank"))
		{
			drug_bank=true;
		}
		else if(qName.equalsIgnoreCase("description"))
		{
			des=true;
		}
		else if(qName.equalsIgnoreCase("drug-interaction"))
		{
			interact=true;
		}
		else
		{
			others=true;
		}
	}
	public void endElement(String uri,String localName,String qName) throws SAXException
	{
		if(qName.equalsIgnoreCase("name"))
		{
			name=false;
		}
		else if(qName.equalsIgnoreCase("drugbank-id"))
		{
			acc_no=false;
		}
		else if(qName.equalsIgnoreCase("drug"))
		{
			drug=false;
			i++;
		}
		else if(qName.equalsIgnoreCase("drugbank"))
		{
			drug_bank=false;
		}
		else if(qName.equalsIgnoreCase("description"))
		{
			des=false;
		}
		else if(qName.equalsIgnoreCase("drug-interaction"))
		{
			try
			{
				copy();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			interact=false;
			des_=new String();
		}
		else
		{
			others=false;
		}
	}
	public void endDocument() throws SAXException
	{
		System.out.println("end of reading xml file");
		
	}
	public void characters(char[] ch,int start,int length) throws SAXException
	{
		String s2=new String();
		if(name&&interact)
		{
			name_=String.copyValueOf(ch,start,length);
		}
		else if(acc_no&&interact)
		{
			no=String.copyValueOf(ch,start,length);
		}
		else if(des&&interact)
		{
			s2=des_;
			des_=String.copyValueOf(ch,start,length);
			des_=s2.concat(des_);
		}
	}
	public void copy() throws Exception
	{
		try
		{
			//SETUP CONNECTION WITH DATABASE
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","vani0410");
			//STATEMENTS ALLOW TO ISSUE QUERY TO DB
			Statement st=c.createStatement();
			String sql="create database if not exists drugbank" ;
			st.executeUpdate(sql);
			st.executeUpdate("use drugbank");
			System.out.println("selected.....");
			sql = "CREATE TABLE if not exists DRUG_intr(id INTEGER not NULL,name VARCHAR(255),access_no varchar(10),description varchar(2500))";
			st.executeUpdate(sql);
			System.out.println("created table");
			st.executeUpdate("insert into DRUG_intr(id,name,access_no,description)values('"+i+"','"+name_+"','"+no+"','"+des_+"');");
			
			//GET THE RESULT OF THE QUERY
			//ResultSet r=st.executeQuery("select * from DRUG_trial2");
			/*while(r.next())
			{
				System.out.println(r.getString("id")+" "+r.getString("name")+" "+r.getString("access_no"));
			}*/
			
			System.out.println(i +" "+ name_+" "+no);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[]args) throws Exception
	 {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		SAXParserFactory s=SAXParserFactory.newInstance();
		SAXParser sp=s.newSAXParser();
		drug_int handler=new drug_int();
		Reader reader=new InputStreamReader(new FileInputStream("copy_mod_drug_bank.xml"),"UTF-8");
		InputSource is=new InputSource(reader);
		is.setEncoding("UTF-8");
		sp.parse(is, handler);
	}
}
