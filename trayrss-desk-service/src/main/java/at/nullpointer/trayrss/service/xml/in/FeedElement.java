package at.nullpointer.trayrss.service.xml.in;

import org.jdom2.Element;

import at.nullpointer.trayrss.domain.Feed;

public class FeedElement {

    private Element feed;


    public FeedElement( Element feed ) {

        this.feed = feed;
    }


    protected String getAttributeText() {

        return feed.getAttributeValue( "text" );
    }


    protected String getAttributeTitle() {

        return feed.getAttributeValue( "title" );
    }


    protected String getAttributeHtmlUrl() {

        return feed.getAttributeValue( "htmlUrl" );
    }


    protected String getAttributeXmlUrl() {

        return feed.getAttributeValue( "xmlUrl" );
    }


    public Feed getFeed() {

        Feed newFeed = new Feed();

        newFeed.setName( getAttributeTitle() );
        newFeed.setUrl( getAttributeXmlUrl() );

        return newFeed;
    }

}
