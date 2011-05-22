package at.nullpointer.trayrss.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String author;
	
	@Column (nullable = false)
	private String title;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishedDate;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	@Column
	private String uri;
	
	@ManyToOne
	@JoinColumn(name = "feed_id")
	private Feed feed;
	
	@Column
    private Date lastRead = new Date();
	
	@Column
    private Long readCount = new Long(0);
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public Date getLastRead() {
		return lastRead;
	}

	public void setLastRead(Date lastRead) {
		this.lastRead = lastRead;
	}

	public Long getReadCount() {
		return readCount;
	}

	public void setReadCount(Long readCount) {
		this.readCount = readCount;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null) return false; //TODO Classcomparison
		
		if (o instanceof News){
			News news = (News) o;
			
			if(this.author != null && this.author.equals(news.getAuthor())){
			} else return false;
			
			if(this.title != null && this.title.equals(news.getTitle())){
			} else return false;

	        //TODO Hibernate Problem this.date = Timestamp
			//if(this.publishedDate != null && ((Date)(this.publishedDate)).equals(news.getPublishedDate())){
	         if(this.publishedDate != null && this.publishedDate.compareTo(news.getPublishedDate()) == 0){
			} else return false;
			
			if(this.uri != null && this.uri.equals(news.getUri())){
			} else return false;
			
			if(this.feed != null && this.feed.equals(news.getFeed())){
			} else return false;
			
			return true;
		} else {
			return false;
		}
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
	
	public void increaseReadCount(long summand){
		this.setReadCount(this.getReadCount() + summand);
	}
}
