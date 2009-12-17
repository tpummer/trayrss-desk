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

import java.util.Collection;

import org.hibernate.Session;

import at.nullpointer.trayrss.configuration.feeds.db.News;

public interface NewsDAO {
	
	public Collection<News> getNews(Session session);

	public News findNewsById(Long id, Session session);

	public void save(News news, Session session);
	
	public void deleteById(Long id, Session session);

	public News getNewsByData(News news, Session session);

}
