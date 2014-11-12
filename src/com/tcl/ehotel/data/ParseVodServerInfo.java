package com.tcl.ehotel.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class ParseVodServerInfo {
	private MyHandler handler ;
	public ParseVodServerInfo(InputSource is) throws ParserConfigurationException, SAXException, FileNotFoundException, IOException{
		handler = new MyHandler();  
    	SAXParserFactory factory = SAXParserFactory.newInstance();  	    	 
    	SAXParser parser = factory.newSAXParser();
    	XMLReader reader = parser.getXMLReader();  
    	reader.setContentHandler(handler);
		reader.parse(is);
	}
	public VodServerInfo getData(){
		return handler.getData();
	}
	
	class MyHandler extends DefaultHandler{
		VodServerInfo datas;
		
		//private ArrayList<String> tempItem =new ArrayList<String>();
		MyHandler(){
			this.datas = new VodServerInfo();
		}
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			// TODO Auto-generated method stub
			super.characters(ch, start, length);
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			super.endElement(uri, localName, qName);
		}
		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.startDocument();
		}
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			if(localName.equalsIgnoreCase("response")) {
				datas.ip = attributes.getValue("ip");
				datas.name = attributes.getValue("name");
				datas.password =attributes.getValue("pass");
				datas.path =attributes.getValue("path");
			}
			super.startElement(uri, localName, qName, attributes);
		}
		public VodServerInfo getData(){
			return datas ;
		}
	}
}
	

