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
package at.nullpointer.trayrss.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import at.nullpointer.trayrss.model.News;

public class NewsDAO {
	private EntityManager em;
	
	public NewsDAO(EntityManager em){
		this.em = em;
	}
	
	public void createEntity(News entity) {
		em.persist(entity);
		em.flush();
		em.refresh(entity);
	}

	public News findEntity(Long id) {
		return em.find(News.class, id);
	}

	public List<News> retrieveEntity(News template) {

		StringBuilder sqlBuild = new StringBuilder("SELECT n FROM News n");
		StringBuilder whereClause = new StringBuilder(" WHERE");

		boolean constraint = false;

		if (template.getAuthor() != null) {
			constraint = true;
			whereClause.append(" e.author = '");
			whereClause.append(template.getAuthor());
			whereClause.append("' AND");
		}

		if (template.getFeed() != null) {
			constraint = true;
			whereClause.append(" e.feed = '");
			whereClause.append(template.getFeed());
			whereClause.append("' AND");
		}
		
		if (template.getTitle() != null) {
			constraint = true;
			whereClause.append(" e.title = '");
			whereClause.append(template.getTitle());
			whereClause.append("' AND");
		}
		
		if (template.getUri() != null) {
			constraint = true;
			whereClause.append(" e.uri = '");
			whereClause.append(template.getUri());
			whereClause.append("' AND");
		}

		if (constraint) {
			whereClause.delete(whereClause.length() - 3, whereClause.length());
			sqlBuild.append(whereClause.toString());
		}

		Query query = em.createQuery(sqlBuild.toString());
		return (List<News>) query.getResultList();
	}

	public News refreshEntity(News entity) {
		em.refresh(entity);
		return entity;
	}

	public void updateEntity(News entity) {
		em.merge(entity);
	}

	public void removeEntity(News entity) {
		em.remove(entity);
	}

}
