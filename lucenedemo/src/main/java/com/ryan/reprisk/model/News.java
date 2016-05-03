package com.ryan.reprisk.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="news-item")
public class News {
	
	private String id;
	private Date date;
	private String title;
	private String source;
	private String author;
	private String text;
	
	public String getId() {
		return id;
	}
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	@XmlElement
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	@XmlElement
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getText() {
		return text;
	}
	@XmlElement
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "News [id=" + id + ", date=" + date + ", title=" + title + ", source=" + source + ", author=" + author
				+ ", text=" + text + "]";
	}
}
