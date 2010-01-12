package at.nullpointer.trayrss.checks;

import at.nullpointer.trayrss.configuration.ReferenceCollection;

import javax.swing.*;

public class CheckLib {
	
	public static boolean checkLong(String input){
		
		try {
			Long.parseLong(input);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean longMessage(boolean checkLong, String field) {
		
		if(!checkLong){
			JOptionPane.showMessageDialog(null,
					String.format(ReferenceCollection.config_long_valid_text, field),
					ReferenceCollection.config_long_valid_title,
					JOptionPane.ERROR_MESSAGE);
		}
		
		return checkLong;
	}

}
