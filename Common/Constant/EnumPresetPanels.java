package Constant;

public enum EnumPresetPanels {
	// define enum with 2 variable
    Data001("Charts","Action Implementation By Status"),
    Data002("Charts","Test Case Execution Trend"),
    Data003("Charts","Test Module Execution Trend"),
    Data004("Charts","Test Module Status per Assigned Users"),
    Data005("Charts","Test Case Execution Failure Trend"),
    Data006("Charts","Test Module Execution Failure Trend"),
    Data007("Charts","Test Module Implementation By Priority"),
    Data008("Charts","Test Case Execution Results"),
    Data009("Charts","Test Module Execution Results"),
    Data010("Charts","Test Module Implementation By Status"),
    Data011("Indicators","Test Case Execution"),
    Data012("Indicators","Test Module Execution"),
    Data013("Indicators","Test Objective Execution"),
    Data014("Reports","Test Module Execution Results Report"),
    Data015("Reports","Test Module Execution Results Report"),
    Data016("Heat Maps","Test Case Execution History"),
    Data017("Heat Maps","Test Module Execution History");
	
	private String panelType;
	private String panelDataProfile;
	
	 /**
     * constructor
     * 
     * @param panelType
     * @param panelDataProfile
     */

	 private EnumPresetPanels(String panelType, String panelDataProfile) {
	        this.panelType = panelType;
	        this.panelDataProfile = panelDataProfile.replace(" ", "\u00A0");
	    }

	    public String getPanelType() {
	    	return panelType;
	    }
	    
	    public String getPanelDataProfile() {
	    	return panelDataProfile;
	    }
	     
}
