package otc_drug;
import java.io.*;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class otc extends DefaultHandler
{
	boolean id=false;
	boolean drug_bank=false;
	boolean toxicity=false;
	boolean indication=false;
	boolean drug_name=false;
	boolean otc_drug=false;
	boolean others=false;
	boolean oth=false;
	boolean drug=false;
	String attrName;
	String attrVal;
	int o;
	int l;
	int flag;
	String s_id=new String();
	String s_name=new String();
	String s_tox=new String();
	String s_ind=new String();
	public static void main(String[]args) throws Exception
	{
		SAXParserFactory s=SAXParserFactory.newInstance();
		SAXParser sp=s.newSAXParser();
		otc handler=new otc();
		sp.parse(new FileInputStream("drugbank.xml"), handler);
	}
	public void startDocument() throws SAXException
	{
		System.out.println("<?xml version='1.0' encoding='UTF-8'?>");
		System.out.println("<drugbank>");
	}
	public void startElement(String uri,String localName,String qName,Attributes a) throws SAXException
	{
		 if(qName.equalsIgnoreCase("toxicity"))
		 {
			 toxicity=true;
			 o=0;
		 }
		 else if(qName.equalsIgnoreCase("drugbank"))
		 {
			 drug_bank=true;
		 }
		 else  if(qName.equalsIgnoreCase("name"))
		 {
				drug_name=true;
		 }
		 else if(qName.equalsIgnoreCase("drug"))
		{
				drug=true;
				flag=1;
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
		 else if(qName.equalsIgnoreCase("indication"))
		 {
				indication=true;
				o=0;
		 }
		 else if(qName.equalsIgnoreCase("over-the-counter"))
		 {
			 	otc_drug=true;
		 }
		 else  if(qName.equalsIgnoreCase("name"))
		 {
				drug_name=true;
		 }
		 else  
		 {
				others=true;
		 }
		
	}
	public void characters(char[] ch,int start,int length) throws SAXException
	{
		char otc[]=new char[length];
		char name[]=new char[length];
		char tox[]=new char[length];
		char ind[]=new char[length];
		String s2=new String();
		if(toxicity)
		{
			int j=0;
			s2=s_tox;
			for(int i=start;i<(start+length);i++)
			{
				tox[j]=ch[i];
				j++;
			}
			s_tox=String.copyValueOf(tox, 0,length);
			if(s_tox.startsWith("<"))
			{
				s_tox=s_tox.replace("<", "&lt;");
				o=1;
			}
			else if(s_tox.startsWith(">"))
			{
				s_tox=s_tox.replace(">", "&gt;");
				o=1;
			}
			if(o==1)
			{
				s2=s2.concat(s_tox);
				s_tox=s2;
			}
		}
		else if(indication)
		{
			int j=0;
			s2=s_ind;
			for(int i=start;i<(start+length);i++)
			{
				ind[j]=ch[i];
				j++;
			}
			s_ind=String.copyValueOf(ind, 0,length);
			if(s_ind.startsWith("<"))
			{
				s_ind=s_ind.replace("<", "&lt;");
				o=1;
			}
			else if(s_ind.startsWith(">"))
			{
				s_ind=s_ind.replace(">", "&gt;");
				o=1;
			}
			if(o==1)
			{
				s2=s2.concat(s_ind);
				s_ind=s2;
			}

		}
		else if(otc_drug)
		{
			for(int i=start,j=0;i<(start+length);i++,j++)
			{
				otc[j]=ch[i];
			}
			String s1=new String();
			s1=String.copyValueOf(otc,0,length);
			if(s1.equals("true")&&flag==1)
			{
				flag=0;
				System.out.println("<drug>");
				System.out.println("<name>"+s_name+"</name>");
				System.out.println("<drungbank-id>"+s_id+"</drugbank-id>");
				System.out.println("<indication>" +s_ind+"</indication>");
				System.out.println("<toxicity>"+s_tox+"</toxicity>");
				System.out.println("</drug>");
			}
		}
		else if(drug_name &&!others)
		{
			int j=0;
			for(int i=start;i<(start+length);i++)
			{
				name[j]=ch[i];
				j++;
			}
			s_name=String.copyValueOf(name, 0,length);
		}
		else if(id &&!others)
		{
			int j=0;
			for(int i=start;i<(start+length);i++)
			{
				name[j]=ch[i];
				j++;
			}
			s_id=String.copyValueOf(name, 0,length);
			System.out.print(s2);
		}
		
	}
	public void endElement(String uri,String localName,String qName) throws SAXException
	{
		 if(qName.equalsIgnoreCase("toxicity"))
		 {
				toxicity=false;
				o=0;
		 }
		 else if(qName.equalsIgnoreCase("indication"))
		 {
				indication=false;
				o=0;
		 }
		 if(qName.equalsIgnoreCase("drugbank"))
		 {
			 drug_bank=false;
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
		 else if(qName.equalsIgnoreCase("over-the-counter"))
		 {
			 otc_drug=false;
		 }
		 else if(qName.equalsIgnoreCase("name"))
		 {
				drug_name=false;
		 }
		else if(qName.equalsIgnoreCase("drug"))
			{
				drug=false;
				flag=1;
			}
		 else  
		 {
				others=false;
		 }
	}
	public void endDocument() throws SAXException
	{
		System.out.println("</drugbank>");
	}
}
