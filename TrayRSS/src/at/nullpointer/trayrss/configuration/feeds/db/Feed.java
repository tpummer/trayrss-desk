package at.nullpointer.trayrss.configuration.feeds.db;
/**
    RSSTray - simply alerting at new Feed Information
    Copyright (C) 2009 Thomas Pummer
    conatct me fake (at) sprossenwanne.at

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */

import java.io.Serializable;
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

import org.hibernate.Hibernate;

/**
 * Represents a RSS or Atom Feed
 * 
 * @author thefake
 *
 */

@Entity
@Table(name = "feed")
public class Feed implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	
	private Long id;
	private String url;
	private Long intervall;
	private Date lastAction;
	private String name;
	private Boolean monitored;
	private List<News> news = new ArrayList<News>();
	
	
	
	@Column(nullable = false)
	public Boolean isMonitored(){
		return monitored;
	}
	public void setMonitored(Boolean monitored) {
		this.monitored = monitored;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false)
	public long getIntervall() {
		return intervall;
	}
	public void setIntervall(Long intervall) {
		this.intervall = intervall;
	}
	
	@Column(nullable = false)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastAction() {
		return lastAction;
	}
	public void setLastAction(Date lastAction) {
		this.lastAction = lastAction;
	}
	
	@Column(nullable = true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "feed")
	public List<News> getNews(){
		return news;
	}
	
	public void setNews(List<News> news){
		this.news = news;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		
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
