package at.nullpointer.trayrss.configuration.model;

public enum LanguageShortcut {
	DE ("de"),
	EN ("en");
	
	private final String SHORTCUT;
	
	LanguageShortcut(String shortcut){
		this.SHORTCUT = shortcut;
	}
	
	public String getShortcut(){
		return this.SHORTCUT;
	}

}
