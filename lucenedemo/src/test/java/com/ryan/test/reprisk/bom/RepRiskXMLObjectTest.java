package com.ryan.test.reprisk.bom;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.ryan.reprisk.bom.RepRiskXMLObject;
import com.ryan.reprisk.model.News;

public class RepRiskXMLObjectTest {
	private File xmlFile = null;
	@Before
	public void setData() {
		xmlFile = new File("C:\\Lucene\\RepRisk\\data\\test.xml");
	}
	
	@Test
	public void testXMLToObjectSuccess() {
		assertTrue((new RepRiskXMLObject<News>().getXMLContent(xmlFile)) instanceof News);
	}
	
	@Test
	public void testXMLToObjectEmptyFail() {
		assertFalse(new RepRiskXMLObject<News>().getXMLContent("") != null);
	}
	
	@Test
	public void testXMLToObjectNullFail() {
		assertFalse(new RepRiskXMLObject<News>().getXMLContent("C:\\nonexistingpath") != null);
	}
}
