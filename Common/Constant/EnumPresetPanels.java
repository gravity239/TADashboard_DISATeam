package Constant;

public enum EnumPresetPanels {
	// define enum with 2 variable
    Data001("Chart","Action Implementation By Status"),
    Data002("Chart","Test Case Execution Trend"),
    Data003("Chart","Test Module Execution Trend"),
    Data004("Chart","Test Module Status per Assigned Users"),
    Data005("Chart","Test Case Execution Failure Trend"),
    Data006("Chart","Test Module Execution Failure Trend"),
    Data007("Chart","Test Module Implementation By Priority"),
    Data008("Chart","Test Case Execution Results"),
    Data009("Chart","Test Module Execution Results"),
    Data010("Chart","Test Module Implementation By Status"),
    Data011("Indicator","Test Case Execution"),
    Data012("Indicator","Test Module Execution"),
    Data013("Indicator","Test Objective Execution"),
    Data014("Report","Test Module Execution Results Report"),
    Data015("Report","Test Module Execution Results Report"),
    Data016("Heat Map","Test Case Execution History"),
    Data017("Heat Map","Test Module Execution History");
	
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
	        this.panelDataProfile = panelDataProfile;
	    }

	    public String getPanelType() {
	    	return panelType;
	    }
	    
	    public String getPanelDataProfile() {
	    	return panelDataProfile;
	    }
	     
}
