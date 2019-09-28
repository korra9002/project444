package Compare;

import java.util.Comparator;

import userVO.ChatListVO;

public class Descending implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		if (o1 instanceof ChatListVO && o2 instanceof ChatListVO) {
			ChatListVO clVO1 = (ChatListVO)o1;
			ChatListVO clVO2 = (ChatListVO)o2;
			
			return clVO1.getTime().isEmpty() ? -1 : clVO1.getTime().compareTo(clVO2.getTime())*-1;
			
		}
		
		return 0;
	}
	
}