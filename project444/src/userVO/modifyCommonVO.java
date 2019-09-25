package userVO;

import javax.swing.ImageIcon;

public class modifyCommonVO {
	
	private ImageIcon image; 
	private String productCode, productName, category, sellerID, pDetail;
	int price;
	
	
	public modifyCommonVO(ImageIcon image, String productCode, String productName, String category, String sellerID,
			String pDetail, int price) {
		super();
		this.image = image;
		this.productCode = productCode;
		this.productName = productName;
		this.category = category;
		this.sellerID = sellerID;
		this.pDetail = pDetail;
		this.price = price;
	}
	
	public ImageIcon getImage() {
		return image;
	}
	public String getProductCode() {
		return productCode;
	}
	public String getProductName() {
		return productName;
	}
	public String getCategory() {
		return category;
	}
	public String getSellerID() {
		return sellerID;
	}
	public String getpDetail() {
		return pDetail;
	}
	public int getPrice() {
		return price;
	}
	

	

	

}
