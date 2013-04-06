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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Entity of a News Item
 * 
 * @author Thomas Pummer
 * @since 1.3
 * 
 */
@Entity
@Data
@Table( name = "news" )
public class NewsEntity
        implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7559699508085982831L;

    /**
     * Id
     */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    /**
     * Author
     */
    @Column
    private String author;

    /**
     * Title
     */
    @Column( nullable = false )
    private String title;

    /**
     * Published Date
     */
    @Column
    @Temporal( TemporalType.TIMESTAMP )
    private Date publishedDate;

    /**
     * Update Date
     */
    @Column
    @Temporal( TemporalType.TIMESTAMP )
    private Date updatedDate;

    /**
     * URI to full article
     */
    @Column( unique = true )
    private String uri;

    /**
     * Feed the News belongs to
     */
    @ManyToOne
    @JoinColumn( name = "feed_id" )
    private FeedEntity feed;

    /**
     * Last apperance in Notification
     */
    @Column
    private Date lastRead = new Date();

    /**
     * Read COunt
     */
    @Column
    private Long readCount = new Long( 0 );


    @Override
    public boolean equals( Object o ) {

        if ( o == null ) {
            return false;
        }
        if ( o == this ) {
            return true;
        }
        if ( o.getClass() != getClass() ) {
            return false;
        }
        NewsEntity news = (NewsEntity)o;
        return new EqualsBuilder().append( author, news.author ).append( title, news.title ).append( uri, news.uri )
                .append( feed, news.feed ).isEquals()
                && ( this.publishedDate != null && this.publishedDate.compareTo( news.getPublishedDate() ) == 0 );

        // @EqualsAndHashCode( exclude = { "id", "monitored", "news" } )

    }


    @Override
    public int hashCode() {

        return new HashCodeBuilder( 17, 37 ).append( author ).append( title ).append( publishedDate )
                .append( updatedDate ).append( uri ).append( feed ).toHashCode();
    }


    /**
     * Increase the read Count
     * 
     * @param summand - the additional count
     */
    public void increaseReadCount( long summand ) {

        this.setReadCount( this.getReadCount() + summand );
    }
}
