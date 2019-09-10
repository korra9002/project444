package adminVO;

public class ProductVO {

	private String category, col_name, value;

	public ProductVO(String category, String col_name, String value) {
		super();
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
