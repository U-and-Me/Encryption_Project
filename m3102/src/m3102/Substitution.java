package m3102;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Substitution implements ActionListener{

	private static String str; // ��
	private static String encryption; // ��ȣ��
	private static String key; // ��ȣŰ

	public static char alphabetBoard[][] = new char[5][5]; // 5x5 ǥ
	
	int[] find_first = {0, 0, 0, 0};
	int[] find_second = {0, 0, 0, 0};
	int front1 = 1, front2 = 1;
	int count1 = 0, count2 = 0;

	JFrame frame; // ���� â
	JFrame b_frame; // ȣ�� �� â

	// Font ����
	Font_setting font = new Font_setting();


	public Substitution(JFrame b_frame, char[][] alphabetBoard, String key, String str, String encryption) {
		this.frame = new JFrame("3102 ������"); // ���ο� â ����
		this.b_frame = b_frame;
		this.frame.setLayout(new FlowLayout());
		this.frame.setSize(1100,700);	//�������� ũ��
		this.frame.setResizable(false);//â�� ũ�⸦ �������� ���ϰ�
		this.frame.setLocationRelativeTo(null);//â�� ��� ������
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame�� ���������� ����ǰ�

		Substitution.alphabetBoard = alphabetBoard.clone();
		Substitution.key = key;
		Substitution.str = str;
		Substitution.encryption = encryption;

		// frame ����
		this.frame.getContentPane().setBackground(new Color(255, 255, 249));

	}

	// ġȯ ���� ȭ�� �����ϱ�
	public void Screen() {
		b_frame.setVisible(false);
		frame.setLayout(null);
		frame.setVisible(true);

		JTextField title = new JTextField("ġȯ ���� ����");
		title.setBounds(20, 20, 200, 50);
		title.setBackground(new Color(247, 238, 255));
		title.setEditable(false); // �۾� ���� �ȵ�
		title.setBorder(null);
		title.setHorizontalAlignment(JTextField.CENTER);
		title.setFocusable(false);
		title.setFont(font.s_dream_20);

		frame.add(title);

		// 5x5 ǥ String���� ��ȯ�ϱ�
		String[][] data = new String[5][5];
		String[] _columnNmaes = {"1", "2", "3", "4", "5"};

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				data[i][j] = Character.toString(alphabetBoard[i][j]);
				//System.out.println(data[i][j]);
			}
		}

		// ���̺� �� �����
		DefaultTableModel model = new DefaultTableModel(data, _columnNmaes);

		// ���̺� ���� �� �� �ʱ�ȭ
		JTable table = new JTable(model) {
			private static final long serialVersionUID = -3247460932338175705L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c =  super.prepareRenderer(renderer, row, column);
				Color color = Color.WHITE;
				c.setBackground(color);
				
				return c;
			}
		};

		// ���̺� �÷��� ����
		table.setTableHeader(null);

		// ���̺� ������ ����
		int height = 80;
		table.setRowHeight(height);

		// ���̺� �� ���� �Ұ�
		table.setEnabled(false);

		// �� �۾� ����
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for(int i = 0; i < 5; i++)
			tcm.getColumn(i).setCellRenderer(dtcr);

		// ���̺� �÷�����
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.add(table);
		scrollpane.setViewportView(table);
		scrollpane.setBounds(50, 150, 400, 403);


		frame.add(scrollpane);


		// ��ȣŰ �����ֱ�
		JTextField txt_key = new JTextField("��ȣŰ : " + key);
		txt_key.setBounds(500, 40, 400, 50);
		txt_key.setEditable(false); // �۾� ���� �ȵ�
		txt_key.setBorder(null);
		txt_key.setBackground(new Color(255, 255, 249));
		txt_key.setFont(font.s_dream_30);

		frame.add(txt_key);

		// �� �����ֱ�
		JTextField txt_str = new JTextField("�� : " + str);
		txt_str.setBounds(500, 90, 500, 50);
		txt_str.setEditable(false); // �۾� ���� �ȵ�
		txt_str.setBorder(null);
		txt_str.setBackground(new Color(255, 255, 249));
		txt_str.setFont(font.s_dream_30);

		frame.add(txt_str);

		// ��ȣ�� �����ֱ�
		JTextField txt_ecryption = new JTextField("��ȣ�� : " + encryption);
		txt_ecryption.setBounds(500, 500, 600, 50);
		txt_ecryption.setEditable(false); // �۾� ���� �ȵ�
		txt_ecryption.setBorder(null);
		txt_ecryption.setBackground(new Color(255, 255, 249));
		txt_ecryption.setFont(font.s_dream_25);

		frame.add(txt_ecryption);

		// ���ư��� ��ư
		JButton btn_back = new JButton("���ư���");
		btn_back.setBounds(930, 590, 120, 50);
		btn_back.setBackground(Color.WHITE);
		btn_back.setFocusable(false);
		btn_back.setFont(font.s_dream_15);

		frame.add(btn_back);

		// ���ư��� ��ư Ŭ����
		btn_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				b_frame.setVisible(true); // ���� â ����
				frame.setVisible(false); // ���� â �����

			}
		});

		// �� ���ھ� �ڸ� ǥ �����ֱ�
		removeBlank();
		String[][] data_model = new String[2][encryption.length() / 2];
		String[] _columnNmaes2 = new String[encryption.length() / 2];
		int str_index = 0;
		int enc_index = 0;
		str = str.toUpperCase();
		for(int i = 0; i < data_model[0].length; i++) {
			_columnNmaes2[i] = "1";
			// �� 2���� �ڸ���
			try{
				data_model[0][i] = Character.toString(str.charAt(str_index));
				if( str.charAt(str_index) == str.charAt(str_index+1)) // �������� ���� ���ĺ��� ������ 'x' �߰�
				{
					data_model[0][i] += "X";
					str_index--;
				}else{
					data_model[0][i] += Character.toString(str.charAt(str_index+1));
				}
				str_index += 2;
				
			}catch(StringIndexOutOfBoundsException e) 
			{
				data_model[0][i] += 'X'; // ���ĺ� ������ Ȧ���� ��� �������� 'x' �߰�
			}

			// ��ȣ���� 2���� �ֱ�
			data_model[1][i] = Character.toString(encryption.charAt(enc_index)) + Character.toString(encryption.charAt(enc_index + 1));
			
			System.out.print(data_model[0][i] + " ");
			
			System.out.println(data_model[1][i]);
			enc_index += 2;
		}

		// ���̺� �� �����
		DefaultTableModel model_sample = new DefaultTableModel(data_model, _columnNmaes2);
		
		// ���̺� ���� �� �� �ʱ�ȭ / ���̺� �� ���� �Ұ� �� ���� ����
		JTable table2 = new JTable(model_sample) {
			private static final long serialVersionUID = 6987844225566095745L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				
				Color color1 = new Color(238, 255, 246);
				Color color2 = new Color(219, 244, 255);
				
				// ǥ ���� ����
				if(row % 2 == 0) {
					c.setBackground(color1);
				}else {
					c.setBackground(color2);
				}
				
				return c;
			}
		};
		
		table2.setFillsViewportHeight(true);

		// ���̺� �÷��� ����
		table2.setTableHeader(null);		
		
		// ���̺� ���� ����
		int height2 = 80;
		table2.setRowHeight(height2);

		// �� �۾� ����
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm2 = table2.getColumnModel();
		for(int i = 0; i < data_model[0].length; i++)
			tcm2.getColumn(i).setCellRenderer(dtcr);
		
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // ���� ����

		// ���̺� �÷�����
		JScrollPane scrollpane2 = new JScrollPane();
		scrollpane2.add(table2);
		scrollpane2.setViewportView(table2);
		scrollpane2.setBounds(500, 220, 500, 163);
		
		frame.add(scrollpane2);

		table2.addMouseListener(new java.awt.event.MouseListener() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				front1 = 1;
				front2 = 1;
				count1 = 0;
				count2 = 0;
				JTable jt = (JTable) e.getSource();
				int select_row = jt.getSelectedRow();
				int select_column = jt.getSelectedColumn();
				jt.setFillsViewportHeight(true);

				// ��ȣ���� �������� ��쿡�� ó��
				if(select_row != 0) {
					Object value = jt.getValueAt(select_row, select_column);
					Object str = jt.getValueAt(select_row - 1, select_column);
					
					// ������ ���� ù��° ���� ��������
					char value_first = value.toString().charAt(0);
					// ������ ���� �ι�° ���� ��������
					char value_second = value.toString().charAt(1);
					
					// ������ ���� ù��° �� ��������
					char str_first = str.toString().charAt(0);
					// ������ ���� �ι�° �� ��������
					char str_second = str.toString().charAt(1);
					
					//System.out.println(first + "   " + second);
					
					for(int i = 0; i < 5; i++) {
						for(int j = 0; j < 5; j++) {
							if(alphabetBoard[i][j] == value_first) {
								//System.out.println(alphabetBoard[i][j]);
								find_first[2] = i;
								find_first[3] = j;
								if(count2 < 1) {
									front2 = 1;
									count2 = count2 + 1;
								}
								
							}else if(alphabetBoard[i][j] == value_second) {
								//System.out.println(alphabetBoard[i][j]);
								find_second[2] = i;
								find_second[3] = j;
								if(count2 < 1) {
									front2 = 2;
									count2 = count2 + 1;
								}
							}
							if(alphabetBoard[i][j] == str_first) {
								//System.out.println(alphabetBoard[i][j]);
								find_first[0] = i;
								find_first[1] = j;
								if(count2 < 1) {
									front1 = 1;
									count1 = count1 + 1;
								}
								
							}else if(alphabetBoard[i][j] == str_second) {
								//System.out.println(alphabetBoard[i][j]);
								find_second[0] = i;
								find_second[1] = j;
								if(count2 < 1) {
									front1 = 2;
									count1 = count1 + 1;
								}
							}
						}
					}
					
					// ���̺� ���� �� �� �ʱ�ȭ
					JTable copy_table = new JTable(model) {
						private static final long serialVersionUID = 1L;

						@Override
						public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
							Component c = super.prepareRenderer(renderer, row, column);

							Color color1 = new Color(238, 255, 246); // green
							Color color2 = new Color(219, 244, 255); // blue

							// ǥ ���� ����
							if(row == find_first[0] && column == find_first[1] || row == find_second[0] && column == find_second[1] || row == find_first[2] && column == find_first[3] || row == find_second[2] && column == find_second[3]) {
								if(row == find_first[2] && column == find_first[3] || row == find_second[2] && column == find_second[3]) {
									c.setBackground(color2);
								}else
									c.setBackground(color1);
							}
							else
								c.setBackground(Color.WHITE);
							
							return c;
						}
					};

					// ���̺� �÷��� ����
					copy_table.setTableHeader(null);

					// ���̺� ������ ����
					int height = 80;
					copy_table.setRowHeight(height);

					// ���̺� �� ���� �Ұ�
					copy_table.setEnabled(false);

					// �� �۾� ����
					DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
					dtcr.setHorizontalAlignment(SwingConstants.CENTER);
					TableColumnModel tcm = copy_table.getColumnModel();
					for(int i = 0; i < 5; i++)
						tcm.getColumn(i).setCellRenderer(dtcr);

					// ���̺� �÷�����
					JScrollPane scrollpane = new JScrollPane();
					scrollpane.add(copy_table);
					scrollpane.setViewportView(copy_table);
					scrollpane.setBounds(50, 150, 400, 403);

					frame.add(scrollpane);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
	}

	public void removeBlank() {
		// ���� ����
		for( int i = 0 ; i < str.length() ; i++ ) 
		{
			// �� ���� ����
			if(str.charAt(i)==' ')
				str = str.substring(0,i)+str.substring(i+1,str.length());			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
