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

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.nullpointer.trayrss.configuration.feeds.db.News;

public class NewsDAOImpl implements NewsDAO {

	@Override
	public void deleteById(Long id, Session session) {
		Transaction tx = session.beginTransaction();
		
		session.delete(session.load(News.class, id));
		
		tx.commit();

	}

	@Override
	public News findNewsById(Long id, Session session) {
		Transaction tx = session.beginTransaction();
		News news = (News) session.load(News.class, id);

		tx.commit();

		return news;
	}

	@Override
	public Collection<News> getNews(Session session) {
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select n from News n");
		List<News> news = (List<News>)query.list();

		tx.commit();

		return news;
	}

	@Override
	public void save(News news, Session session) {
		Transaction tx = session.beginTransaction();
		
		//TODO noch nicht unique
		session.save(news);

		tx.commit();

	}

	@Override
	public News getNewsByData(News news, Session session) {
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("select n from News n where n.uri = :uri")
					  .setParameter("uri", news.getUri());
		News erg = (News)query.uniqueResult();
		
		tx.commit();
		
		return erg;
		
	}

}
