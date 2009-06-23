/**
 * Obsolet
 */

/**
    RSSTray - simply alerting at new Feed Information
    Copyright (C) 2009 Thomas Pummer
    conatct me fake (at) sprossenwanne.at

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
import java.io.Serializable;
import java.net.URL;
import java.util.Date;


public class FeedInfo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;

	private URL url;
	private long intervall;
	private Date lastAction;
	
	public FeedInfo(URL url, long intervall, Date lastAction) {
		this.url = url;
		this.intervall = intervall;
		this.lastAction = lastAction;
	}
	
	
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public long getIntervall() {
		return intervall;
	}
	public void setIntervall(long intervall) {
		this.intervall = intervall;
	}
	public Date getLastAction() {
		return lastAction;
	}
	public void setLastAction(Date lastAction) {
		this.lastAction = lastAction;
	}

}
