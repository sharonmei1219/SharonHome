package com.sharonhome.sudoku.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.python.util.PythonInterpreter;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class TestCallingPython {

	@Test
	public void test() throws UnsupportedOperationException, SOAPException {
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		
		String url = "http://135.251.31.180:8008/";
		try {
			for(int i = 0; i < 100; i ++){
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
			System.out.println("response:");
			soapResponse.writeTo(System.out);}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static SOAPMessage createSOAPRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		String serverURI = "http://135.251.31.180:8008/";
		
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("example", serverURI);
		
		SOAPBody soapBody = envelope.getBody();
		SOAPElement adder = soapBody.addChildElement("Adder");
		SOAPElement a = adder.addChildElement("a");
		a.addTextNode("2");
		SOAPElement b = adder.addChildElement("b");
		b.addTextNode("3");
		
		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI  + "Adder");
		
		soapMessage.saveChanges();
		System.out.print("Request SOAP Message = ");
		soapMessage.writeTo(System.out);
		System.out.println();
		return soapMessage;
	}
}
