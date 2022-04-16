package m3102;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class Substitution implements ActionListener{

	private static String str; // 평문
	private static String encryption; // 암호문
	private static String key; // 암호키

	public static char alphabetBoard[][] = new char[5][5]; // 5x5 표

	JFrame frame; // 현재 창
	JFrame b_frame; // 호출 전 창

	// Font 설정
	Font_setting font = new Font_setting();


	public Substitution(JFrame b_frame, char[][] alphabetBoard, String key, String str, String encryption) {
		this.frame = new JFrame("3102 김유나"); // 새로운 창 생성
		this.b_frame = b_frame;
		this.frame.setLayout(new FlowLayout());
		this.frame.setSize(1100,700);	//프레임의 크기
		this.frame.setResizable(false);//창의 크기를 변경하지 못하게
		this.frame.setLocationRelativeTo(null);//창이 가운데 나오게
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame이 정상적으로 종료되게

		Substitution.alphabetBoard = alphabetBoard.clone();
		Substitution.key = key;
		Substitution.str = str;
		Substitution.encryption = encryption;

		// frame 설정
		this.frame.getContentPane().setBackground(Color.WHITE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(b_frame == null) b_frame.setVisible(false); // 이전 창 없애기
		frame.setVisible(true); // 자기 자신 띄우기

	}

	// 치환 과정 화면 구현하기
	public void Screen() {
		frame.setLayout(null);
		frame.setVisible(true);

		JButton title = new JButton("치환 과정 보기");
		title.setBounds(20, 20, 200, 50);
		title.setBorder(new RoundedBorder(30));
		title.setBackground(Color.WHITE);
		title.setFocusable(false);
		title.setFont(font.s_dream_20);
		title.setEnabled(false);

		frame.add(title);

		// 5x5 표 String으로 변환하기
		String[][] data = new String[5][5];
		String[] _columnNmaes = {"1", "2", "3", "4", "5"};

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				data[i][j] = Character.toString(alphabetBoard[i][j]);
				//System.out.println(data[i][j]);
			}
		}

		// 테이블 모델 만들기
		DefaultTableModel model = new DefaultTableModel(data, _columnNmaes);

		// 테이블에 생성 및 모델 초기화
		JTable table = new JTable(model);

		// 테이블 컬럼명 제거
		table.setTableHeader(null);

		// 테이블 사이즈 조절
		int heigth = 80;
		table.setRowHeight(heigth);

		// 테이블 값 수정 불가
		table.setEnabled(false);

		// 값 글씨 수정
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for(int i = 0; i < 5; i++)
			tcm.getColumn(i).setCellRenderer(dtcr);

		// 테이블 올려놓기
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.add(table);
		scrollpane.setViewportView(table);
		scrollpane.setBounds(50, 150, 400, 403);

		frame.add(scrollpane);


		// 암호키 보여주기
		JTextField txt_key = new JTextField("암호키 : " + key);
		txt_key.setBounds(500, 40, 400, 50);
		txt_key.setEditable(false); // 글씨 변경 안됨
		txt_key.setBorder(null);
		txt_key.setBackground(Color.WHITE);
		txt_key.setFont(font.s_dream_30);

		frame.add(txt_key);

		// 평문 보여주기
		JTextField txt_str = new JTextField("평문 : " + str);
		txt_str.setBounds(500, 90, 500, 50);
		txt_str.setEditable(false); // 글씨 변경 안됨
		txt_str.setBorder(null);
		txt_str.setBackground(Color.WHITE);
		txt_str.setFont(font.s_dream_30);

		frame.add(txt_str);

		// 암호문 보여주기
		JTextField txt_ecryption = new JTextField("암호문 : " + encryption);
		txt_ecryption.setBounds(500, 500, 600, 50);
		txt_ecryption.setEditable(false); // 글씨 변경 안됨
		txt_ecryption.setBorder(null);
		txt_ecryption.setBackground(Color.WHITE);
		txt_ecryption.setFont(font.s_dream_30);

		frame.add(txt_ecryption);

		// 돌아가기 버튼
		JButton btn_back = new JButton("돌아가기");
		btn_back.setBounds(930, 590, 120, 50);
		btn_back.setBackground(Color.WHITE);
		btn_back.setFocusable(false);
		btn_back.setFont(font.s_dream_15);

		frame.add(btn_back);

		// 돌아가기 버튼 클릭시
		btn_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				b_frame.setVisible(true); // 이전 창 복원
				frame.setVisible(false); // 현재 창 지우기

			}
		});

		// 두 글자씩 자른 표 보여주기
		removeBlank();
		String[][] data_model = new String[2][encryption.length() / 2];
		int str_index = 0;
		int enc_index = 0;
		for(int i = 0; i < data_model[0].length; i++) {
			// 평문 2개씩 자르기
			try{
				data_model[0][i] = Character.toString(str.charAt(str_index));
				if( str.charAt(str_index) == str.charAt(str_index+1)) // 연속으로 같은 알파벳이 나오면 'x' 추가
				{
					data_model[0][i] += "X";
					str_index--;
				}else{
					data_model[0][i] += Character.toString(str.charAt(str_index+1));
				}
				str_index += 2;
				
			}catch(StringIndexOutOfBoundsException e) 
			{
				data_model[0][i] += 'X'; // 알파벳 개수가 홀수일 경우 마지막에 'x' 추가
			}

			// 암호문을 2개씩 넣기
			data_model[1][i] = Character.toString(encryption.charAt(enc_index)) + Character.toString(encryption.charAt(enc_index + 1));
			
			System.out.print(data_model[0][i] + " ");
			
			System.out.println(data_model[1][i]);
			enc_index += 2;
		}

		// 테이블 모델 만들기
		DefaultTableModel model_sample = new DefaultTableModel(data_model, _columnNmaes);
		
		// 테이블에 생성 및 모델 초기화
		JTable table2 = new JTable(model_sample);

		// 테이블 컬럼명 제거
		table2.setTableHeader(null);		

		// 테이블 값 수정 불가
		table2.setEnabled(false);

		// 값 글씨 수정
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm2 = table2.getColumnModel();
		for(int i = 0; i < 5; i++)
			tcm2.getColumn(i).setCellRenderer(dtcr);

		// 테이블 올려놓기
		JScrollPane scrollpane2 = new JScrollPane();
		scrollpane2.add(table2);
		scrollpane2.setViewportView(table2);
		scrollpane2.setBounds(500, 185, 200, 163);

		frame.add(scrollpane2);



	}

	public void removeBlank() {
		// 공백 제거
		for( int i = 0 ; i < str.length() ; i++ ) 
		{
			// 평문 공백 제거
			if(str.charAt(i)==' ')
				str = str.substring(0,i)+str.substring(i+1,str.length());			
		}
	}

}
