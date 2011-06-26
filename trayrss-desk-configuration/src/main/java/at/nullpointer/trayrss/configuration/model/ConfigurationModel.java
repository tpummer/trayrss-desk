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
package at.nullpointer.trayrss.configuration.model;

import java.util.Date;
import java.util.Set;

import at.nullpointer.trayrss.model.Feed;

/**
 * <p>The ConfigurationModel is the connection between the
 * configuration GUI and the persistence of the configuration.</p>
 * 
 * @author Thomas Pummer
 */
public class ConfigurationModel {
	
	private Integer displayCount;
	private Integer displayTime;
	private LanguageShortcut language;
	private Set<Feed> feeds;
	private Boolean isTimeFrameActivated;
	private Boolean isMondayEnabled;
	private Boolean isTuesdayEnabled;
	private Boolean isWednesdayEnabled;
	private Boolean isThursdayEnabled;
	private Boolean isFridayEnabled;
	private Boolean isSaturdayEnabled;
	private Boolean isSundayEnabled;
	private Set<SingleTimeFrame> timeFrames;
	private Date vacationStart;
	private Date vacationEnd;
	/**
	 * @return the displayCount
	 */
	public Integer getDisplayCount() {
		return displayCount;
	}
	/**
	 * @param displayCount the displayCount to set
	 */
	public void setDisplayCount(Integer displayCount) {
		this.displayCount = displayCount;
	}
	/**
	 * @return the displayTime
	 */
	public Integer getDisplayTime() {
		return displayTime;
	}
	/**
	 * @param displayTime the displayTime to set
	 */
	public void setDisplayTime(Integer displayTime) {
		this.displayTime = displayTime;
	}
	/**
	 * @return the language
	 */
	public LanguageShortcut getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(LanguageShortcut language) {
		this.language = language;
	}
	/**
	 * @return the feeds
	 */
	public Set<Feed> getFeeds() {
		return feeds;
	}
	/**
	 * @param feeds the feeds to set
	 */
	public void setFeeds(Set<Feed> feeds) {
		this.feeds = feeds;
	}
	/**
	 * @return the isTimeFrameActivated
	 */
	public Boolean getIsTimeFrameActivated() {
		return isTimeFrameActivated;
	}
	/**
	 * @param isTimeFrameActivated the isTimeFrameActivated to set
	 */
	public void setIsTimeFrameActivated(Boolean isTimeFrameActivated) {
		this.isTimeFrameActivated = isTimeFrameActivated;
	}
	/**
	 * @return the isMondayEnabled
	 */
	public Boolean getIsMondayEnabled() {
		return isMondayEnabled;
	}
	/**
	 * @param isMondayEnabled the isMondayEnabled to set
	 */
	public void setIsMondayEnabled(Boolean isMondayEnabled) {
		this.isMondayEnabled = isMondayEnabled;
	}
	/**
	 * @return the isTuesdayEnabled
	 */
	public Boolean getIsTuesdayEnabled() {
		return isTuesdayEnabled;
	}
	/**
	 * @param isTuesdayEnabled the isTuesdayEnabled to set
	 */
	public void setIsTuesdayEnabled(Boolean isTuesdayEnabled) {
		this.isTuesdayEnabled = isTuesdayEnabled;
	}
	/**
	 * @return the isWednesdayEnabled
	 */
	public Boolean getIsWednesdayEnabled() {
		return isWednesdayEnabled;
	}
	/**
	 * @param isWednesdayEnabled the isWednesdayEnabled to set
	 */
	public void setIsWednesdayEnabled(Boolean isWednesdayEnabled) {
		this.isWednesdayEnabled = isWednesdayEnabled;
	}
	/**
	 * @return the isThursdayEnabled
	 */
	public Boolean getIsThursdayEnabled() {
		return isThursdayEnabled;
	}
	/**
	 * @param isThursdayEnabled the isThursdayEnabled to set
	 */
	public void setIsThursdayEnabled(Boolean isThursdayEnabled) {
		this.isThursdayEnabled = isThursdayEnabled;
	}
	/**
	 * @return the isFridayEnabled
	 */
	public Boolean getIsFridayEnabled() {
		return isFridayEnabled;
	}
	/**
	 * @param isFridayEnabled the isFridayEnabled to set
	 */
	public void setIsFridayEnabled(Boolean isFridayEnabled) {
		this.isFridayEnabled = isFridayEnabled;
	}
	/**
	 * @return the isSaturdayEnabled
	 */
	public Boolean getIsSaturdayEnabled() {
		return isSaturdayEnabled;
	}
	/**
	 * @param isSaturdayEnabled the isSaturdayEnabled to set
	 */
	public void setIsSaturdayEnabled(Boolean isSaturdayEnabled) {
		this.isSaturdayEnabled = isSaturdayEnabled;
	}
	/**
	 * @return the isSundayEnabled
	 */
	public Boolean getIsSundayEnabled() {
		return isSundayEnabled;
	}
	/**
	 * @param isSundayEnabled the isSundayEnabled to set
	 */
	public void setIsSundayEnabled(Boolean isSundayEnabled) {
		this.isSundayEnabled = isSundayEnabled;
	}
	/**
	 * @return the timeFrames
	 */
	public Set<SingleTimeFrame> getTimeFrames() {
		return timeFrames;
	}
	/**
	 * @param timeFrames the timeFrames to set
	 */
	public void setTimeFrames(Set<SingleTimeFrame> timeFrames) {
		this.timeFrames = timeFrames;
	}
	/**
	 * @return the vacationStart
	 */
	public Date getVacationStart() {
		return vacationStart;
	}
	/**
	 * @param vacationStart the vacationStart to set
	 */
	public void setVacationStart(Date vacationStart) {
		this.vacationStart = vacationStart;
	}
	/**
	 * @return the vacationEnd
	 */
	public Date getVacationEnd() {
		return vacationEnd;
	}
	/**
	 * @param vacationEnd the vacationEnd to set
	 */
	public void setVacationEnd(Date vacationEnd) {
		this.vacationEnd = vacationEnd;
	}
	
	
}
