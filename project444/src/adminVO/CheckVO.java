package adminVO;

public class CheckVO {

	private String category, col_name, value;

	public CheckVO(String category, String col_name, String value) {
		this.category = category;
		this.col_name = col_name;
		this.value = value;
	}

	public String getCategory() {
		return category;
	}

	public String getCol_name() {
		return col_name;
	}

	public String getValue() {
		return value;
	}

	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}

	@Override
	public String toString() {
		return "CheckVO [category=" + category + ", col_name=" + col_name + ", value=" + value + "]";
	}
	
	
}
