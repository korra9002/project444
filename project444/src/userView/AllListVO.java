package userView;

public class AllListVO {
	private String image, productName, productCode, area, inputDate;
	int price;
	 
	public AllListVO(String image, String productName, String area, int price, String inputDate) {
		super();
		this.image = image;
		this.productName = productName;
		this.area = area;
		this.price = price;
		this.inputDate = inputDate;
	}
	public String getImage() {
		return image;
	}
	public String getProductName() {
		return productName;
	}
	public String getArea() {
		return area;
	}
	public String getInputDate() {
		return inputDate;
	}
	public int getPrice() {
		return price;
	}
	
	
	
}
