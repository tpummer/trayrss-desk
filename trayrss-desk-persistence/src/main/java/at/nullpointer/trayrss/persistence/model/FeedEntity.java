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
package at.nullpointer.trayrss.persistence.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity of a Feed
 * 
 * @author Thomas Pummer
 * @since 1.3
 * 
 */
@Data
@Entity
@Table( name = "feeds" )
@EqualsAndHashCode( exclude = { "id", "monitored", "news" } )
public class FeedEntity
        implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3298137451349476944L;

    /**
     * Id
     */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    /**
     * Url of the Feed
     */
    @Column( nullable = false, unique = true )
    private String url;

    /**
     * Intervall to check
     */
    @Column( nullable = false )
    private Long intervall;

    /**
     * Last check
     */
    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date lastAction;

    /**
     * Name of the Feed
     */
    @Column( nullable = false, unique = true )
    private String name;

    /**
     * Monitoring is active
     */
    @Column( nullable = false )
    private Boolean monitored;

    /**
     * News of this feed
     */
    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "feed" )
    private Set<NewsEntity> news = new HashSet<NewsEntity>();


    /**
     * Adds a {@link NewsEntity} and manages the coherence for the newsEntity
     * 
     * @param newsEntity
     */
    public void addNews( NewsEntity newsEntity ) {

        this.news.add( newsEntity );
        newsEntity.setFeed( this );

    }

}
