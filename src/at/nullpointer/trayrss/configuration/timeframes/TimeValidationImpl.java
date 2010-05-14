package at.nullpointer.trayrss.configuration.timeframes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import at.nullpointer.trayrss.configuration.ReferenceCollection;

public class TimeValidationImpl implements TimeValidation {

	@Override
	public boolean isAllowed() {
		
		boolean allowed = true;
		
		Calendar now = new GregorianCalendar();
				
		Vacation vac = new Vacation(ReferenceCollection.CONFIG_VACATION_START_VALUE,
									ReferenceCollection.CONFIG_VACATION_END_VALUE);
		
		allowed = allowed && vac.isAllowed(now);
		
		ReferenceCollection.LOG.debug("Allowed after Vacationcheck: "+ allowed);
		
		Timeframe frame = new Timeframe(ReferenceCollection.CONFIG_TIMEFRAMES_VALUE);
		
		allowed = allowed && frame.isAllowed(now);
		
		ReferenceCollection.LOG.debug("Allowed after Timeframecheck: "+ allowed);
				
		Week week = new Week(ReferenceCollection.CONFIG_MONITORINGDAYSMO_VALUE,
							 ReferenceCollection.CONFIG_MONITORINGDAYSTU_VALUE,
							 ReferenceCollection.CONFIG_MONITORINGDAYSWE_VALUE,
							 ReferenceCollection.CONFIG_MONITORINGDAYSTH_VALUE,
							 ReferenceCollection.CONFIG_MONITORINGDAYSFR_VALUE,
							 ReferenceCollection.CONFIG_MONITORINGDAYSSA_VALUE,
							 ReferenceCollection.CONFIG_MONITORINGDAYSSU_VALUE);
		
		allowed = allowed && week.isAllowed(now);
		
		ReferenceCollection.LOG.debug("Allowed after Weekcheck: "+ allowed);
		
		return allowed;
	}

}
