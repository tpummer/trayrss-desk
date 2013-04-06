package at.nullpointer.trayrss.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Feed {

    /**
     * Url of the Feed, is the unique key of the feed
     */
    private String url;

    /**
     * Intervall to check
     */
    private Long intervall;

    /**
     * Last check
     */
    private Date lastAction;

    /**
     * Name of the Feed
     */
    private String name;

    /**
     * Monitoring is active
     */
    private Boolean monitored;

    /**
     * News of this feed
     */
    private List<News> news = new ArrayList<News>();

}
