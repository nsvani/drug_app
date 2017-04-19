package connection;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
public class table_name extends DefaultHandler
{
	boolean name=false;
	boolean acc_no=false;
	boolean des=false;
	boolean ind=false;
	boolean tox=false;
	boolean grp=false;
	boolean food=false;
	boolean drug=false;
	boolean drug_bank=false;
	boolean others=false;
	String name_=new String();
	String no=new String();
	String tox_=new String();
	String ind_=new String();
	String des_=new String();
	String grp_=new String();
	String food_=new String();
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
		else if(qName.equalsIgnoreCase("toxicity"))
		{
			tox=true;
		}
		else if(qName.equalsIgnoreCase("indication"))
		{
			ind=true;
		}
		else if(qName.equalsIgnoreCase("group"))
		{
			grp=true;
		}
		else if(qName.equalsIgnoreCase("food-interaction"))
		{
			food=true;
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
			try
			{
				copy();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			drug=false;
			des_=new String();
			ind_=new String();
			tox_=new String();
			food_=new String();
		}
		else if(qName.equalsIgnoreCase("drugbank"))
		{
			drug_bank=false;
		}
		else if(qName.equalsIgnoreCase("description"))
		{
			des=false;
		}
		else if(qName.equalsIgnoreCase("toxicity"))
		{
			tox=false;
		}
		else if(qName.equalsIgnoreCase("indication"))
		{
			ind=false;
		}
		else if(qName.equalsIgnoreCase("group"))
		{
			grp=false;
		}
		else if(qName.equalsIgnoreCase("food-interaction"))
		{
			food=false;
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
		if(name&&!others)
		{
			//s2=name_;
			name_=String.copyValueOf(ch,start,length);
			//name_=s2.concat(name_);
		}
		else if(acc_no&&!others)
		{
			//s2=no;
			no=String.copyValueOf(ch,start,length);
			//no=s2.concat(no);
			//no=no.trim();
		}
		else if(des&&!others)
		{
			s2=des_;
			des_=String.copyValueOf(ch,start,length);
			des_=s2.concat(des_);
			//System.out.println(des_);
		}
		else if(ind)
		{
			s2=ind_;
			ind_=String.copyValueOf(ch,start,length);
			ind_=s2.concat(ind_);
		}
		else if(tox)
		{
			s2=tox_;
			tox_=String.copyValueOf(ch,start,length);
			tox_=s2.concat(tox_);
		}
		else if(grp)
		{
			//s2=grp_;
			grp_=String.copyValueOf(ch,start,length);
			//grp_=s2.concat(grp_);
		}
		else if(food)
		{
			s2=food_;
			food_=String.copyValueOf(ch,start,length);
			food_=s2.concat(food_);
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
			sql = "CREATE TABLE if not exists DRUG_info (id INTEGER not NULL primary key,name VARCHAR(255),access_no varchar(10),grp varchar(20),description varchar(2500),indication varchar(2000),toxicity varchar(9000),food_interactions varchar(1000))";
			st.executeUpdate(sql);
			System.out.println("created table");
			st.executeUpdate("insert into DRUG_info(id,name,access_no,grp,description,indication,toxicity,food_interactions)values('"+i+"','"+name_+"','"+no+"','"+grp_+"','"+des_+"','"+ind_+"','"+tox_+"','"+food_+"');");
			i++;
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
		table_name handler=new table_name();
		Reader reader=new InputStreamReader(new FileInputStream("copy_mod_drug_bank.xml"),"UTF-8");
		InputSource is=new InputSource(reader);
		is.setEncoding("UTF-8");
		sp.parse(is, handler);
	}
}
