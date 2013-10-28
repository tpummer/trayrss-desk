package at.nullpointer.trayrss.service.xml.in;

import org.jdom2.Element;

import at.nullpointer.trayrss.domain.Feed;

/**
 * Wrapper for an {@link Element} for easy accessing
 * 
 * @author Thomas Pummer
 * @since 1.6
 * 
 */
public class FeedElement {

    /**
     * Wrapped element
     */
    private final Element feed;


    /**
     * Constructor
     * 
     * @param feedToWrap
     */
    public FeedElement( final Element feedToWrap ) {

        this.feed = feedToWrap;
    }


    protected String getAttributeText() {

        return this.feed.getAttributeValue( "text" );
    }


    protected String getAttributeTitle() {

        return this.feed.getAttributeValue( "title" );
    }


    protected String getAttributeHtmlUrl() {

        return this.feed.getAttributeValue( "htmlUrl" );
    }


    protected String getAttributeXmlUrl() {

        return this.feed.getAttributeValue( "xmlUrl" );
    }


    /**
     * Returns a Feed Domain Object
     * 
     * @return Feed
     */
    public Feed getFeed() {

        Feed newFeed = new Feed();

        newFeed.setName( this.getAttributeTitle() );
        newFeed.setUrl( this.getAttributeXmlUrl() );

        return newFeed;
    }

}
