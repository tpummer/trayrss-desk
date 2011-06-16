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

import at.nullpointer.trayrss.configuration.feeds.db.Feed;

/**
 * <p>The ConfigurationModel interface is the connection between the
 * configuration GUI and the persistence of the configuration.</p>
 * 
 * @author Thomas Pummer
 * @version 1.0
 */
public interface ConfigurationModel {
	
	public Integer getDisplayCount();
	public Integer getDisplayTime();
	public LanguageShortcut getLanguage();
	public Feed getFeed();
	public Boolean getIsTimeframeActivated();
	public Boolean getIsMondayEnabled();
	public Boolean getIsTuesdayEnabled();
	public Boolean getIsWednesdayEnabled();
	public Boolean getIsThursdayEnabled();
	public Boolean getIsFridayEnabled();
	public Boolean getIsSaturdayEnabled();
	public Boolean getIsSundayEnabled();
	public Set<String> getTimeFrames();
	public Date getVacationStart();
	public Date getVacationEnd();

}
