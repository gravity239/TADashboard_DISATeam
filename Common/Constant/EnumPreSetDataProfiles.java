package Constant;

public enum EnumPreSetDataProfiles {
	// define enum with 3 variable
    Data001("Action Implementation By Status", "Action", ""),
    Data002("Test Case Execution", "Test Case", ""),
    Data003("Test Case Execution Failure Trend", "Test Case", "Related Run Results"),
    Data004("Test Case Execution History", "Test Case", "Related Run Results"),
    Data005("Test Case Execution Results", "Test Case", "Related Run Results"),
    Data006("Test Case Execution Trend", "Test Case", "Related Run Results"),
    Data007("Test Module Execution", "Test Module", ""),
    Data008("Test Module Execution Failure Trend", "Test Module", "Related Run Results"),
    Data009("Test Module Execution History", "Test Module", "Related Run Results"),
    Data010("Test Module Execution Results", "Test Module", "Related Run Results"),
    Data011("Test Module Execution Results Report", "Test Module", "Related Run Results"),
    Data012("Test Module Execution Trend", "Test Module", "Related Run Results"),
    Data013("Test Module Implementation By Priority", "Test Module", ""),
    Data014("Test Module Implementation By Status", "Test Module", ""),
    Data015("Test Module Status per Assigned Users", "Test Module", ""),
    Data016("Test Objective Executin", "Test Module", "");

	private String dataProfile;
    private String itemType;
    private String relatedData;
	
    /**
     * constructor
     * 
     * @param dataProfile
     * @param itemType
     * @param relatedData
     */

    private EnumPreSetDataProfiles(String dataProfile, String itemType, String relatedData) {
        this.dataProfile = dataProfile;
        this.itemType = itemType;
        this.relatedData = relatedData;
    }

    public String getDataProfile() {
    	return dataProfile;
    }
    
    public String getItemType() {
    	return itemType;
    }
    
    public String getRelatedData() {
    	return relatedData;
    }  
}
