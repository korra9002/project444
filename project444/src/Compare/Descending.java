package Compare;

import java.util.Comparator;

import userVO.ChatListVO;

/**
 * 채팅 목록 정렬을 위한 클래스
 * @author BAEK
 *
 */
public class Descending implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		if (o1 instanceof ChatListVO && o2 instanceof ChatListVO) {
			ChatListVO clVO1 = (ChatListVO)o1;
			ChatListVO clVO2 = (ChatListVO)o2;
			if (clVO1.getTime().isEmpty() && clVO2.getTime().isEmpty()) {
				return 0;
			}
			if (clVO1.getTime().isEmpty()) {
				return -1;
			}
			if (clVO2.getTime().isEmpty()) {
				return 1;
			}
			
			return  (clVO1.getTime().compareTo(clVO2.getTime())*-1);
			
		}
		
		return 0;
	}
	
}