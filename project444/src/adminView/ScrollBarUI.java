package adminView;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;;

public class ScrollBarUI extends BasicScrollBarUI {
	/*
     * (non-Javadoc)
     *  ScrollBar �� �����Ҷ��� ������ ����, thumbRect�� trackRect �� ���� �ʵ�� api �� �����ϸ� �׸��� �Բ� ���������
     * �ڼ��ϰ� ���´�.
     * @see javax.swing.plaf.basic.BasicScrollBarUI#configureScrollBarColors()
     */
    protected void configureScrollBarColors() {
        thumbRect.width = 5;
        trackRect.width = 5;
       
        thumbColor = new Color(0xFFCC66);
        thumbDarkShadowColor = new Color(0xFFCC66);
        thumbHighlightColor = new Color(0xFFCC66);
        thumbLightShadowColor = new Color(0xFFCC66);
        trackColor = new Color(0xFFCC66);
        trackHighlightColor = new Color(0xFFCC66);
       
//        AWTUtilitiesWrapper.setComponentShape((Window)thumbRect, shape);
    }

    /*
     * (non-Javadoc)
     *  ScrollBar ��ܿ� ȭ��ǥ ����� ��ư ���� ����
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
     *  ScrollBar �ϴܿ� ȭ��ǥ ����� ��ư ���� ����
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

