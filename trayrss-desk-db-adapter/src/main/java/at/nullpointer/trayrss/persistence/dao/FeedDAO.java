/*
 * TrayRSS - simply notification of feed information (c) 2009-2013 TrayRSS Developement Team visit the project at
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

import java.util.Collection;

import at.nullpointer.trayrss.persistence.model.FeedEntity;

/**
 * Provides Accessors and Persistence Functions for {@link FeedEntity}
 * 
 * @author Thomas Pummer
 * 
 */
public interface FeedDAO {

    /**
     * Get all {@link FeedEntity}
     * 
     * @return all {@link FeedEntity}
     */
    Collection<FeedEntity> getFeeds();


    /**
     * Get {@link FeedEntity} by id
     * 
     * @param id
     * @return {@link FeedEntity}
     */
    FeedEntity findFeedById( Long id );


    /**
     * Save {@link FeedEntity}
     * 
     * @param feed
     */
    void save( FeedEntity feed );


    /**
     * Delete {@link FeedEntity}
     * 
     * @param id
     */
    void deleteById( Long id );

}