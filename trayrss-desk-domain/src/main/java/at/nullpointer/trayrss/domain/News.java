package at.nullpointer.trayrss.domain;

import java.util.Date;

import lombok.Data;

@Data
public class News {

    /**
     * Author
     */
    private String author;

    /**
     * Title
     */
    private String title;

    /**
     * Published Date
     */
    private Date publishedDate;

    /**
     * Update Date
     */
    private Date updatedDate;

    /**
     * URI to full article, is the unique key of the news entry
     */
    private String uri;

    /**
     * Feed the News belongs to
     */
    private String feedUrl;

    /**
     * Last apperance in Notification
     */
    private Date lastRead = new Date();

    /**
     * Read Count
     */
    private Long readCount = new Long( 0 );

}
