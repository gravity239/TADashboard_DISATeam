package DataObject;

public class TAPage {
	private String pageName = null;
	private String pageParent = null;
	private int numberOfColumn = 0;
	private String pageBefore = null;
	private boolean isPublic = false;
	
	public TAPage(String pageName)
	{
		setPageName(pageName);
	}

	public TAPage(String pageName, String pageParent, int numberOfColumn, String pageBefore, boolean isPublic)
	{
		this.pageName = pageName;
		this.pageParent = pageParent;
		this.numberOfColumn = numberOfColumn;
		this.pageBefore = pageBefore;
		this.isPublic = isPublic;
	}
	
	public TAPage(String pageName, String pageParent, int numberOfColumn, String pageBefore)
	{
		this.pageName = pageName;
		this.pageParent = pageParent;
		this.numberOfColumn = numberOfColumn;
		this.pageBefore = pageBefore;		
	}
	
	public void setPageName(String pageName)
	{
		this.pageName = pageName;
	}
	
	public void setPageParent(String pageParent)
	{
		this.pageParent = pageParent;
	}
	
	public void setNumberOfColumn(int numberOfColumn)
	{
		this.numberOfColumn = numberOfColumn;
	}
	
	public void setPageBefore(String pageBefore)
	{
		this.pageBefore = pageBefore;
	}
	
	public void setPublic(boolean isPublic)
	{
		this.isPublic = isPublic;
	}
	
	public String getPageName()
	{
		return this.pageName;
	}
	
	public String getPageParent()
	{
		return this.pageParent;
	}
	
	public String getPageBefore()
	{
		return this.pageBefore;
	}
	
	public int getNumberOfColumn()
	{
		return this.numberOfColumn;
	}
	
	public boolean isPublic()
	{
		return this.isPublic;
	}
}
