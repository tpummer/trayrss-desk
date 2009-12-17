package at.nullpointer.trayrss.configuration.feeds.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.Hibernate;


@Entity
@Table(name = "news")
public class News implements Serializable {

	private Long id;
	private String author;
	private String title;
	private Date publishedDate;
	private Date updatedDate;
	private String uri;
	private Feed feed;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column (nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Column
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	@ManyToOne
	@JoinColumn(name = "feed_id")
	public Feed getFeed(){
		return feed;
	}
	public void setFeed(Feed feed){
		this.feed = feed;
	}
		
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(0)) return false;
		
		News news = (News) o;
		
		if(this.author != null && this.author.equals(news.getAuthor())){
		} else return false;
		
		if(this.title != null && this.title.equals(news.getTitle())){
		} else return false;
		
		if(this.publishedDate != null && this.publishedDate.equals(news.getPublishedDate())){
		} else return false;
		
		if(this.updatedDate != null && this.updatedDate.equals(news.getUpdatedDate())){
		} else return false;
		
		if(this.uri != null && this.uri.equals(news.getUri())){
		} else return false;
		
		if(this.feed != null && this.feed.equals(news.getFeed())){
		} else return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		
		int result;
		result = (author != null ? author.hashCode() : 0);
		result = 29 * result + (title != null ? title.hashCode() : 0);
		result = 29 * result + (publishedDate != null ? publishedDate.hashCode() : 0);
		result = 29 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
		result = 29 * result + (uri != null ? uri.hashCode() : 0); 
		result = 29 * result + (feed != null ? feed.hashCode() : 0);
		return result;	
	}
}
