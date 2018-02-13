package Constant;

public enum EnumItemType {
	item1("Test Modules", 1), 
	item2("Test Cases", 2), 
	item3("Test Objectives", 3), 
	item4("Data Sets", 4), 
	item5("Actions", 5), 
	item6("Interface Entities", 6), 
	item7("Test Results", 7), 
	item8("Test Case Results", 8);

	private String itemType;
	private int index;

	/**
	 * constructor
	 * 	 
	 * @param itemType
	 */
	private EnumItemType(String itemType, int index) {
		this.itemType = itemType;
		this.index = index;		
	}
	
	public String getItemType() {
    	return itemType;
    }
	
	public int getItemTypeIndex() {
    	return index;
    }
}

