package userView;


   import java.awt.Color;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import userControl.MarketMainEvt;


   public class MarketMain extends JPanel {
      
      public static final int DOUBLE_CLICK=2;
       
      
      private DefaultComboBoxModel<String> dcbm, dcbm1;
      private JComboBox<String> jcbArea, jcbCategory;
      private JTextField jtfSearch;
      private JButton jbSearch, jbRecent, jbPrice, jbRefresh;
      private JRadioButton jrbSubject, jrbId;
      
      
      private DefaultTableModel dtmProductList;
      private JTable jtProductList;
      private String id; 
      
        // ����
      
      
      @SuppressWarnings("serial")
      public MarketMain(String id) throws SQLException {
//         super("�۾���");

         
      this.id=id;
         
      //////////////////////// Ȩ ȭ�� //////////////////////////////
      
//      Ȩ, ī�װ�, �۾���, ä��, MyPage
             
      
      //JComboBox
      String[] dataArea= {"������ü","������", "������", "���ϱ�", "������", "���Ǳ�", "������", "���α�", "��õ��",
            "�����", "������", "���빮��", "���۱�", "������", "���빮��", "���ʱ�", "������", "���ϱ�",
            "���ı�", "��õ��", "��������", "��걸", "����", "���α�", "�߱�", "�߶���"};
      
      dcbm = new DefaultComboBoxModel<String>(dataArea);   
      jcbArea=new JComboBox<String>(dcbm);
      
      String[] dataCategory= {"ī�װ���ü", "������/����", "����/���׸���",    "���Ƶ�/���Ƶ���",
            "��Ȱ/������ǰ",    "�����Ƿ�", "������ȭ", "��Ƽ/�̿�", "�����м�/��ȭ",
            "������/����", "����/���", "����/Ƽ��/����", "�ݷ�������ǰ", "��Ÿ �߰�ǰ"};
      
      dcbm1 = new DefaultComboBoxModel<String>(dataCategory);   
      jcbCategory=new JComboBox<String>(dcbm1);
      
      jtfSearch=new JTextField(10);
      jbSearch=new JButton("�˻�");

      
      
      //JRadioButton
      jrbSubject=new JRadioButton("����", true);
      jrbId=new JRadioButton("���̵�");
      ButtonGroup bg=new ButtonGroup(); //�ΰ� ���ÿ� ������ �� ������
      
      bg.add(jrbSubject);
      bg.add(jrbId);

      
      //JButton 
      jbRecent=new JButton("�ֽż�");
      jbPrice=new JButton("���ݼ�");
      jbRefresh=new JButton("���ΰ�ħ");

      
      //JTable
      
      String[] productColumn= {"�̹���", "��ǰ��", "����", "����", "�ð�", "ī�װ�", "�Ǹ��� ID" };
      
      dtmProductList=new DefaultTableModel(productColumn, 0) {
    	  
    	  @Override
			public Class<?> getColumnClass(int column) {
				// �Էµ� �� �ϳ��� ��� �÷��� ���� ������ Ŭ������ ��ȯ�ϴ� ��
				// 0�� ���� �Էµ� �� �ϳ��� ������� ó��
    		  Class t= getValueAt(0, column).getClass();
//    		 System.err.println(t);
				return t;
			}

            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            };
      
      };
            jtProductList=new JTable(dtmProductList);              
      
      JScrollPane jspProductList=new JScrollPane(jtProductList);

      
		//////////////���̺� ������ ��� ���� ����//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm = jtProductList.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i =0; i < tcm.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			if(i!=0) {
				
				tcm.getColumn(i).setCellRenderer(dtcr);
			}//end if
		}//end for
		//////////////���̺� ������ ��� ���� ��//////////////
      
//      
//      @Override
//      public boolean isCellEditable(String row, int column) {
//         return false;
//      }//isCellEditable
//   };
//   jtProductList = new JTable(dtmProductList) {
//      @Override
//      public Class<?> getColumnClass(int column) {
//         return getValueAt(0, column).getClass();
//      }//getColumnClass
//   };    
      

      
            
      //����Ʈ ũ��, �̵�, ���� �Ұ����ϰ� ����
      
      
      
      
      jtProductList.setRowHeight(100);
      jtProductList.getTableHeader().setReorderingAllowed(false);
      
      jtProductList.getColumnModel().getColumn(0).setPreferredWidth(30);
      jtProductList.getColumnModel().getColumn(1).setPreferredWidth(150);
      jtProductList.getColumnModel().getColumn(2).setPreferredWidth(10);
      jtProductList.getColumnModel().getColumn(3).setPreferredWidth(20);
      jtProductList.getColumnModel().getColumn(4).setPreferredWidth(80);
      jtProductList.getColumnModel().getColumn(5).setPreferredWidth(30);
      jtProductList.getColumnModel().getColumn(6).setPreferredWidth(30);
      
//      setResizable(false);
   
      System.out.println("gdged");
      
      // ������Ʈ ��ġ 
      setLayout(null);
      
      add(jspProductList);
      add(jcbArea);
      add(jtfSearch);
      add(jbSearch);
      
      add(jcbCategory);
      
      
      add(jbRecent);
      add(jbPrice);
      add(jbRefresh);
            
      add(jrbSubject);
      add(jrbId);
      
      jspProductList.setBounds(20, 180, 760, 600);
      jcbArea.setBounds(20, 20, 180, 40);
      jcbCategory.setBounds(20, 80, 180, 40);
      jtfSearch.setBounds(220, 20, 360, 35);
      jbSearch.setBounds(600, 20, 180, 35);
      jrbSubject.setBounds(310, 60, 90, 35);
      jrbId.setBounds(440, 60, 90, 35);
      jbRecent.setBounds(490, 130, 90, 35);
      jbPrice.setBounds(590, 130, 90, 35);
      jbRefresh.setBounds(690, 130, 90, 35);
      setBounds(100, 100, 750, 750);
      
      //������ ����ȭ
      setVisible(true);
      
		setBackground(new Color(0xf6f2ef));
		
//		jbSearch.setBackground(new Color(0xFFB495));
//		jbRecent.setBackground(new Color(0xFFB495));
//		jbPrice.setBackground(new Color(0xFFB495));
//		jbRefresh.setBackground(new Color(0xFFB495));
		jbSearch.setBackground(new Color(0xFFCC66));
		jbRecent.setBackground(new Color(0xFFCC66));
		jbPrice.setBackground(new Color(0xFFCC66));
		jbRefresh.setBackground(new Color(0xFFCC66));

		jcbArea.setBackground(new Color(0xFFFFFF));
		jcbCategory.setBackground(new Color(0xFFFFFF));
		
		jrbSubject.setBackground(new Color(0xf6f2ef));
		jrbId.setBackground(new Color(0xf6f2ef));
		jtProductList.getTableHeader().setBackground(new Color(0xFFCC66));
		jspProductList.getVerticalScrollBar().setBackground(new Color(0xFFFFFF));
		
		jspProductList.getVerticalScrollBar().setUI(new BasicScrollBarUI()
        {
			
            @Override
            protected void configureScrollBarColors()
            {
                this.thumbColor = new Color(0xFFCC66);
            }
            
        });
		
		
		
		
		
   //�̺�Ʈ ó��
      
      MarketMainEvt mme=new MarketMainEvt(this, id,null);
      
      jcbArea.addActionListener(mme);
      jcbCategory.addActionListener(mme);
      jtfSearch.addActionListener(mme);
      jbSearch.addActionListener(mme);
      jbRecent.addActionListener(mme);
      jbPrice.addActionListener(mme);
      jbRefresh.addActionListener(mme);
      jrbSubject.addActionListener(mme);
      jrbId.addActionListener(mme);   
      jtProductList.addMouseListener(mme);
      
   
      
      
      
//      jtProductList.addMouseListener(new MouseAdapter() {
//         //���̺� ����Ŭ���Ǿ��� �� 
//         @Override
//         public void mouseClicked(MouseEvent me) {
//            switch(me.getClickCount()) {
//            case DOUBLE_CLICK : 
//               
//            int selectedRow=jtProductList.getSelectedRow();
//            
//            new MarketDetailBuyer();
//               
//            }
//             
      
//         }//mouseClicked
//         
//         
//      });
   
//      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
      }//MarketMain

      public DefaultComboBoxModel<String> getDcbm() {
         return dcbm;
      }

      public DefaultComboBoxModel<String> getDcbm1() {
         return dcbm1;
      }

      public JComboBox<String> getJcbArea() {
         return jcbArea;
      }

      public JComboBox<String> getJcbCategory() {
         return jcbCategory;
      } 
 
      public JTextField getJtfSearch() {
         return jtfSearch;
      }

      public JButton getJbSearch() {
         return jbSearch;
      }

      public JButton getJbRecent() {
         return jbRecent;
      }

      public JButton getJbPrice() {
         return jbPrice;
      }

      public JButton getJbRefresh() {
         return jbRefresh;
      }

      public JRadioButton getJrbSubject() {
         return jrbSubject;
      }

      public JRadioButton getJrbId() {
         return jrbId;
      }

      public DefaultTableModel getDtmProductList() {
         return dtmProductList;
      }

      public JTable getJtProductList() {
         return jtProductList;
      }
      
      
      

   }//MarketMain

