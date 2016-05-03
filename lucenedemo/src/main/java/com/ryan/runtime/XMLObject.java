package com.ryan.runtime;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.ryan.reprisk.model.News;

public class XMLObject {
	public static void main(String[] args) {
		try {

			File file = new File("C:\\Lucene\\RepRisk\\data\\test.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(News.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			News news = (News) jaxbUnmarshaller.unmarshal(file);
			System.out.println(news);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
