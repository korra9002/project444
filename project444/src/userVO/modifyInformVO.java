package userVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class modifyInformVO{
	private String id ,thing, value;

	public modifyInformVO(String id, String thing, String value) {
		super();
		this.id = id;
		this.thing = thing;
		this.value = value;
	}//modifyInformVO

	public String getId() {
		return id;
	}//getId

	public String getThing() {
		return thing;
	}//getThing

	public String getValue() {
		return value;
	}//getValue

	@Override
	public String toString() {
		return "modifyInformVO [id=" + id + ", thing=" + thing + ", value=" + value + "]";
	}//toString
	
	

}//class
