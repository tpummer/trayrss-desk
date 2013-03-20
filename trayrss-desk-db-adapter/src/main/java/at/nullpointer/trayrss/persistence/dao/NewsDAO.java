/*
 * TrayRSS - simply notification of feed information (c) 2009-2011 TrayRSS Developement Team visit the project at
 * http://trayrss.nullpointer.at/
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package at.nullpointer.trayrss.persistence.dao;

import java.sql.SQLException;
import java.util.Collection;

import at.nullpointer.trayrss.persistence.model.NewsEntity;

/**
 * Provides Accessors and Persistence Functions for {@link NewsEntity}
 * 
 * @author Thomas Pummer
 * 
 */
public interface NewsDAO {

    /**
     * Get all {@link NewsEntity}
     * 
     * @return all {@link NewsEntity}
     */
    public Collection<NewsEntity> getNews();


    /**
     * Get {@link NewsEntity} by id
     * 
     * @param id
     * @return {@link NewsEntity}
     */
    public NewsEntity findNewsById( Long id );


    /**
     * Save {@link NewsEntity}
     * 
     * @param news
     */
    public void save( NewsEntity news )
            throws SQLException;


    /**
     * Delete {@link NewsEntity}
     * 
     * @param id
     */
    public void deleteById( Long id );


    /**
     * Get {@link NewsEntity} by data comparison
     * 
     * @param news
     * @return
     */
    public NewsEntity getNewsByData( NewsEntity news );


    /**
     * Deletes every {@link NewsEntity} older than 2 months
     * 
     * @param id
     */
    public void deleteOlderThanTwoMonth( Long id );

}
