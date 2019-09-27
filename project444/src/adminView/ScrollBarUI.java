package adminView;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;;

public class ScrollBarUI extends BasicScrollBarUI {
	/*
     * (non-Javadoc)
     *  ScrollBar 를 구성할때의 색상을 지정, thumbRect와 trackRect 과 같은 필드는 api 를 참조하면 그림과 함께 어느것인지
     * 자세하게 나온다.
     * @see javax.swing.plaf.basic.BasicScrollBarUI#configureScrollBarColors()
     */
    protected void configureScrollBarColors() {
        thumbRect.width = 5;
        trackRect.width = 5;
       
        thumbColor = new Color(0xFFCC66);
        thumbDarkShadowColor = new Color(0xFFFFFF);
        thumbHighlightColor = new Color(0xFFFFFF);
        thumbLightShadowColor = new Color(0xFFFFFF);
        trackColor = new Color(0xFFFFFF);
        trackHighlightColor = new Color(0xFFBF3F);
       
//        AWTUtilitiesWrapper.setComponentShape((Window)thumbRect, shape);
    }

    /*
     * (non-Javadoc)
     *  ScrollBar 상단에 화살표 모양의 버튼 색상 지정
     * @see javax.swing.plaf.basic.BasicScrollBarUI#createDecreaseButton(int)
     */
    protected JButton createDecreaseButton(int orientation) {
        JButton button = new BasicArrowButton(orientation);
        button.setBackground(new Color(0xFFCC66));
        button.setForeground(new Color(0xFFCC66));
        return button;
    }

    /*
     * (non-Javadoc)
     *  ScrollBar 하단에 화살표 모양의 버튼 색상 지정
     * @see javax.swing.plaf.basic.BasicScrollBarUI#createIncreaseButton(int)
     */
    protected JButton createIncreaseButton(int orientation) {
        JButton button = new BasicArrowButton(orientation);
        button.setBackground(new Color(0xFFCC66));
        button.setForeground(new Color(0xFFCC66));
        return button;
    }
    @Override
    protected Dimension getMaximumThumbSize() {
        // TODO Auto-generated method stub
        return new Dimension(10, 20);
    }
}

