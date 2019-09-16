package adminVO;

public class ProductVO {

	private String category, col_name, value, query;

	public ProductVO(String category, String col_name, String value, String query) {
		this.category = category;
		this.col_name = col_name;
		this.value = value;
		this.query = query;
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

	public String getQuery() {
		return query;
	}

	@Override
	public String toString() {
		return "ProductVO [category=" + category + ", col_name=" + col_name + ", value=" + value + ", query=" + query
				+ "]";
	}

}//class
