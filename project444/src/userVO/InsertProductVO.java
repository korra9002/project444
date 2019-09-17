package userVO;

public class InsertProductVO {
	
	private String img, category, subject, pDetail;
	private int Price;
	public InsertProductVO(String img, String category, String subject, String pDetail, int price) {
		super();
		this.img = img;
		this.category = category;
		this.subject = subject;
		this.pDetail = pDetail;
		Price = price;
	}
	public String getImg() {
		return img;
	}
	public String getCategory() {
		return category;
	}
	public String getSubject() {
		return subject;
	}
	public String getpDetail() {
		return pDetail;
	}
	public int getPrice() {
		return Price;
	}
	
	

}
