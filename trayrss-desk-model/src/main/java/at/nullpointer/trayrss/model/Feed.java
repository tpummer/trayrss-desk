package at.nullpointer.trayrss.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Feeds")
public class Feed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String url;
	
	@Column(nullable = false)
	private Long intervall;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastAction;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Boolean monitored;
	
	@OneToMany
	private List<News> news = new ArrayList<News>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getIntervall() {
		return intervall;
	}

	public void setIntervall(Long intervall) {
		this.intervall = intervall;
	}

	public Date getLastAction() {
		return lastAction;
	}

	public void setLastAction(Date lastAction) {
		this.lastAction = lastAction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getMonitored() {
		return monitored;
	}

	public void setMonitored(Boolean monitored) {
		this.monitored = monitored;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null) return false; //TODO Classtest
		
		if(o instanceof Feed){
			Feed feed = (Feed) o;
			
			if(this.url != null){
				if(!this.url.equals(feed.getUrl())) return false;
			}
			if(this.name != null){
				if(!this.name.equals(feed.getName())) return false;
			}
			if(this.lastAction != null){
				if(!this.lastAction.equals(feed.getLastAction())) return false;
			}
			if(this.intervall != null){
				if(!this.intervall.equals(feed.getIntervall())) return false;
			}
			return true;
		} else {
			return false;
		}

	}
	
	@Override
	public int hashCode() {
		int result;
		result = (url != null ? url.hashCode() : 0);
		result = 29 * result + (name != null ? name.hashCode() : 0);
		result = 29 * result + (lastAction != null ? lastAction.hashCode() : 0);
		result = 29 * result + (intervall != null ? intervall.hashCode() : 0);
		result = 29 * result + (news != null ? news.hashCode() : 0); 
		return result;	
	}

}

