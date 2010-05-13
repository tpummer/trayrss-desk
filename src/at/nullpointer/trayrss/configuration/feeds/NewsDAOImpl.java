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
package at.nullpointer.trayrss.configuration.feeds;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.db.News;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class NewsDAOImpl implements NewsDAO {

	public void deleteById(Long id) {
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();
		Transaction tx = session.beginTransaction();
		
		session.delete(session.load(News.class, id));
		
		tx.commit();
		session.close();

	}

	public News findNewsById(Long id) {
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();
		Transaction tx = session.beginTransaction();
		News news = (News) session.get(News.class, id);

		tx.commit();
		session.close();

		return news;
	}

	public Collection<News> getNews() {
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select n from News n");
		List<News> news = (List<News>)query.list();

		tx.commit();
		session.close();

		return news;
	}

	public void save(News news) {
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();
		Transaction tx = session.beginTransaction();
		
		if(news.getId() != null && session.load(News.class, news.getId()) != null){
			session.update(news);
		} else {
			session.save(news);
	
		}
		
		tx.commit();
		session.close();

	}

	public News getNewsByData(News news) {
		Session session = ReferenceCollection.SESSION_FACTORY.openSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("select n from News n where n.uri = :uri")
					  .setParameter("uri", news.getUri());
		News erg = null;
		try {
			erg = (News)query.uniqueResult();
		} catch (HibernateException e) {
			ReferenceCollection.LOG.error(news.getTitle()+ " at "+news.getUri()+" has a duplicated entry!");
			ReferenceCollection.LOG.debug(e.toString());
			ReferenceCollection.LOG.debug(query.getQueryString());
		}
		
		tx.commit();
		session.close();
		
		return erg;
		
	}

	@Override
	public void deleteOlderThanTwoMonth(Long id) {
Session session = ReferenceCollection.SESSION_FACTORY.openSession();
		
		
		Transaction tx = session.beginTransaction();
		
		Date twoMonthsAgo = new Date();
		
		int month = twoMonthsAgo.getMonth();
		month = month - 2;
		if(month < 1){
			month = month+12;
			twoMonthsAgo.setYear(twoMonthsAgo.getYear()-1);
		}
		twoMonthsAgo.setMonth(month);
			
		String hqlN = "delete from News n where feed_id = "+id.longValue() + " and UPDATEDDATE < :date";
		
		Query queryN = session.createQuery(hqlN).setDate("date", twoMonthsAgo);
		
		int rowN = queryN.executeUpdate();
		
		tx.commit();
		session.close();
	}

}
