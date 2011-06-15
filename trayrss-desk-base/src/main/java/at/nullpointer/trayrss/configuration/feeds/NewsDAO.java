/*
    TrayRSS - simply notification of feed information
    (c) 2009-2011 TrayRSS Developement Team
    visit the project at http://trayrss.nullpointer.at/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.

 */
package at.nullpointer.trayrss.configuration.feeds;

import at.nullpointer.trayrss.configuration.feeds.db.News;

import java.util.Collection;

public interface NewsDAO {
	
	public Collection<News> getNews();

	public News findNewsById(Long id);

	public void save(News news);
	
	public void deleteById(Long id);

	public News getNewsByData(News news);

	public void deleteOlderThanTwoMonth(Long id);

}
