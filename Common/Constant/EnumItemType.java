package Constant;

public enum EnumItemType {
	item1("Test Modules", 0), 
	item2("Test Cases", 1), 
	item3("Test Objectives", 2), 
	item4("Data Sets", 3), 
	item5("Actions", 4), 
	item6("Interface Entities", 5), 
	item7("Test Results", 6), 
	item8("Test Case Results", 7);

	private String itemType;
	private int priority;

	/**
	 * constructor
	 * 	 
	 * @param itemType
	 */
	private EnumItemType(String itemType, int priority) {
		this.itemType = itemType;
		this.priority = priority;		
	}
	
	public String getItemType() {
    	return itemType;
    }
	
	public int getItemTypePriority() {
    	return priority;
    }
}

