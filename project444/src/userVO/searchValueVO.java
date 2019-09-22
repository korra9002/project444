package userVO;

public class searchValueVO {
	private int areaIndex, categoryIndex;
	private String textValue, rbFlag, sortFlag;

	public searchValueVO(int areaIndex, int categoryIndex, String textValue, String rbFlag, String sortFlag) {
		super();
		this.areaIndex = areaIndex;
		this.categoryIndex = categoryIndex;
		this.textValue = textValue;
		this.rbFlag = rbFlag;
		this.sortFlag = sortFlag;
	}

	public int getAreaIndex() {
		return areaIndex;
	}

	public int getCategoryIndex() {
		return categoryIndex;
	}

	public String getTextValue() {
		return textValue;
	}

	public String getRbFlag() {
		return rbFlag;
	}

	public String getSortFlag() {
		return sortFlag;
	}

}
