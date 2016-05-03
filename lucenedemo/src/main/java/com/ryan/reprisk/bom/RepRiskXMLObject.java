package com.ryan.reprisk.bom;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.ryan.reprisk.model.News;

public class RepRiskXMLObject<E> {
	private JAXBContext jaxbContext = null;

	{
		try {
			jaxbContext = JAXBContext.newInstance(News.class);
		} catch (JAXBException e) {		
			e.printStackTrace();
		}
	}

	public E getXMLContent(String string) {
		return this.getXMLContent(new File(string));
	}
	
	public E getXMLContent(File xmlFile) {
		Unmarshaller jaxbUnmarshaller;
		try {
			jaxbUnmarshaller = this.jaxbContext.createUnmarshaller();
			return (E) jaxbUnmarshaller.unmarshal(xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
