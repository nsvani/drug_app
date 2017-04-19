package drug;
import java.io.*;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;


public class drug extends DefaultHandler 
{
	boolean drug_name=false;
	boolean id=false;
	boolean drug=false;
	boolean des=false;
	boolean group=false;
	boolean tox=false;
	boolean ind=false;
	boolean drug_interactions=false;
	boolean food_interactions=false;
	boolean drug_bank=false;
	int c=0;
	String attrName;
	String attrVal;
	int l;
	boolean oth=false;
	public static void main(String[]args) throws Exception
	 {
		SAXParserFactory s=SAXParserFactory.newInstance();
		SAXParser sp=s.newSAXParser();
		drug handler=new drug();
		Reader reader=new InputStreamReader(new FileInputStream("drugbank.xml"),"UTF-8");
		InputSource is=new InputSource(reader);
		is.setEncoding("UTF-8");
		sp.parse(is, handler);
		
	}
		public void startDocument() throws SAXException
	{
		System.out.println("<?xml version='1.0' encoding='UTF-8'?>");
	}
	public void startElement(String uri,String localName,String qName,Attributes a) throws SAXException
	{
		if(qName.equalsIgnoreCase("name"))
		{
			drug_name=true;
		}
		else if(qName.equalsIgnoreCase("drugbank-id"))
		{
			l = a.getLength();
			for (int i = 0; i < l; i++) 
			{
				 attrName = a.getQName(i);
				 attrVal = a.getValue(i);
				if(attrName.equalsIgnoreCase("primary") &&attrVal.equalsIgnoreCase("true"))
				{
					id=true;
				}
				
			}
			if(l==0)
			{
				oth=true;
			}
			
			
		}
		else if(qName.equalsIgnoreCase("drug"))
		{
			drug=true;
		}
		else if(qName.equalsIgnoreCase("description"))
		{
			des=true;
		}
		else if(qName.equalsIgnoreCase("group"))
		{
			group=true;
		}
		else if(qName.equalsIgnoreCase("toxicity"))
		{
			tox=true;
		}
		else if(qName.equalsIgnoreCase("indication"))
		{
			ind=true;
		}
		else if(qName.equalsIgnoreCase("drug-interactions"))
		{
			drug_interactions=true;
		}
		else if(qName.equalsIgnoreCase("food-interactions"))
		{
			food_interactions=true;
		}
		else if(qName.equalsIgnoreCase("drugbank"))
		{
			drug_bank=true;
		}
		else
		{
			c++;
		}
		if(drug_interactions||group||food_interactions)
		{
			System.out.print("<"+qName+">");
		}
		else if(c==0 && !oth)
		{
			System.out.print("<"+qName+">");
		}
		
	}
public void characters(char[] ch,int start,int length) throws SAXException
	{
	char A[]=new char[length];
	int j=0;
	//System.out.println("entered char");
	for(int i=start;i<(start+length);i++)
	{
		A[j]=ch[i];
		j++;
	}
	//System.out.println("entered char left for");
	String s1=new String(A);
	String s2=new String();
	if(s1.startsWith("<"))
	{
		s2=s1.replace("<", "&lt;");
	}
	else if(s1.startsWith(">"))
	{
		s2=s1.replace(">", "&gt;");
	}
	else 
	{
		s2=s1;
	}
	if(drug_interactions)
	{
			System.out.print(s2);
	}
		else  if(group)
		{
				
			System.out.print(s2);
		} 
		 else if(food_interactions)
			{
			 System.out.print(s2);
			}
		else if(c==0) 
		{ 
		 if(des)
		{
			 System.out.print(s2);
		}
		
		else if(ind)
		{
			System.out.print(s2);
		}
		else if(tox)
		{
			System.out.print(s2);
		}
		else if(id)
		{
			System.out.print(s2);
		}
		
		else if(drug_name)
		{
			if(attrName.equalsIgnoreCase("primary") &&attrVal.equalsIgnoreCase("true"))
			{
				System.out.print(s2);
			}
		}
	  } 
	}
	public void endElement(String uri,String localName,String qName) throws SAXException
	{
		if(drug_interactions||group||food_interactions)
		{
			System.out.println("</"+qName+">");
		}
		else if(c==0 &&!oth)
		{
			System.out.println("</"+qName+">");
		}
		if(qName.equalsIgnoreCase("name"))
		{
			drug_name=false;
		}
		else if(qName.equalsIgnoreCase("drugbank-id"))
		{
			if(attrName.equalsIgnoreCase("primary") &&attrVal.equalsIgnoreCase("true"))
			{
				id=false;
			}
			if(l==0)
			{
				oth=false;
			}
		}
		else if(qName.equalsIgnoreCase("drug"))
		{
			drug=false;
		}
		else if(qName.equalsIgnoreCase("description"))
		{
			des=false;
		}
		else if(qName.equalsIgnoreCase("group"))
		{
			group=false;
		}
		else if(qName.equalsIgnoreCase("toxicity"))
		{
			tox=false;
		}
		else if(qName.equalsIgnoreCase("indication"))
		{
			ind=false;
			//System.out.print("<"+qName+">");
		}
		else if(qName.equalsIgnoreCase("drug-interactions"))
		{
			drug_interactions=false;
		}
		else if(qName.equalsIgnoreCase("food-interactions"))
		{
			food_interactions=false;
		}
		else if(qName.equalsIgnoreCase("drugbank"))
		{
			drug_bank=false;
		}
		else
		{
			c--;
		}
		
	}
	public void endDocument() throws SAXException
	{
		System.out.println("");
	}
	
}


