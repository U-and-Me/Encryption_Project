package m3102;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.accessibility.AccessibleIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Encryption_Main {

	static String decryption; // 복호문
	static String encryption; // 암호문
	static String key; // 암호키
	static String str; // 평문
	
	public static char alphabetBoard[][] = new char[5][5]; // 5x5 표
	public static String zCheck =""; // z가 있는지 체크
	public static String blankCheck="";	// 공백 체크	
	public static boolean oddFlag; //글자수 출력

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 창 띄우기
		JFrame frame = new JFrame("3102 김유나");
		frame.setSize(1100,700);								//프레임의 크기
		frame.setResizable(false);								//창의 크기를 변경하지 못하게
		frame.setLocationRelativeTo(null);						//창이 가운데 나오게
		frame.setLayout(null);									//레이아웃을 내맘대로 설정가능하게 해줌.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//JFrame이 정상적으로 종료되게

		// frame 설정
		frame.getContentPane().setBackground(Color.WHITE);

		// Font 설정
		Font_setting font = new Font_setting();

		// 암호키
		JTextField key_ecryption = new JTextField("암호키 ");
		key_ecryption.setBounds(100, 50, 120, 100);
		key_ecryption.setEditable(false); // 글씨 변경 안됨
		key_ecryption.setBorder(null);
		key_ecryption.setBackground(Color.WHITE);
		key_ecryption.setFont(font.s_dream_35);

		frame.add(key_ecryption);

		// 암호키 입력
		JTextField input_key_ecryption = new JTextField();
		input_key_ecryption.setBounds(240, 77, 500, 50);
		input_key_ecryption.setFont(font.s_dream_35);

		frame.add(input_key_ecryption);

		// 평문
		JTextField text = new JTextField("평문 ");
		text.setBounds(100, 130, 120, 100);
		text.setEditable(false); // 글씨 변경 안됨
		text.setBorder(null);
		text.setBackground(Color.WHITE);
		text.setFont(font.s_dream_35);

		frame.add(text);

		// 평문 입력
		JTextField input_text = new JTextField();
		input_text.setBounds(240, 157, 500, 50);
		input_text.setFont(font.s_dream_35);

		frame.add(input_text);

		// 암호문
		JTextField title_ecryption = new JTextField("암호문 : ");
		title_ecryption.setBounds(100, 280, 120, 100);
		title_ecryption.setEditable(false); // 글씨 변경 안됨
		title_ecryption.setBorder(null);
		title_ecryption.setBackground(Color.WHITE);
		title_ecryption.setFont(font.s_dream_35);

		frame.add(title_ecryption);

		// 암호화 출력
		JTextField output_ecryption = new JTextField();
		output_ecryption.setBounds(240, 307, 500, 50);
		output_ecryption.setEditable(false); // 글씨 변경 안됨
		output_ecryption.setBackground(Color.WHITE);
		output_ecryption.setFont(font.s_dream_35);

		frame.add(output_ecryption);

		// 복호문
		JTextField title_decryption = new JTextField("복호문 : ");
		title_decryption.setBounds(100, 365, 120, 100);
		title_decryption.setEditable(false); // 글씨 변경 안됨
		title_decryption.setBorder(null);
		title_decryption.setBackground(Color.WHITE);
		title_decryption.setFont(font.s_dream_35);

		frame.add(title_decryption);

		// 복호문 출력
		JTextField output_decryption = new JTextField();
		output_decryption.setBounds(240, 392, 500, 50);
		output_decryption.setEditable(false); // 글씨 변경 안됨
		output_decryption.setBackground(Color.WHITE);
		output_decryption.setFont(font.s_dream_35);

		frame.add(output_decryption);

		// 암호화 버튼
		JButton btn_ecryption = new JButton("암호");
		btn_ecryption.setBounds(770, 307, 100, 50);
		btn_ecryption.setBorder(new RoundedBorder(30));
		btn_ecryption.setBackground(Color.WHITE);
		btn_ecryption.setFocusable(false);
		btn_ecryption.setFont(font.s_dream_15);

		frame.add(btn_ecryption);

		// 복호화 버튼
		JButton btn_decryption = new JButton("복호");
		btn_decryption.setBounds(770, 392, 100, 50);
		btn_decryption.setBorder(new RoundedBorder(30));
		btn_decryption.setBackground(Color.WHITE);
		btn_decryption.setFocusable(false);
		btn_decryption.setFont(font.s_dream_15);

		frame.add(btn_decryption);

		// 치환과정 버튼
		JButton btn_substitution = new JButton("치환 과정 보기");
		btn_substitution.setBounds(30, 590, 200, 50);
		btn_substitution.setBorder(new RoundedBorder(30));
		btn_substitution.setBackground(Color.WHITE);
		btn_substitution.setFocusable(false);
		btn_substitution.setFont(font.s_dream_20);

		frame.add(btn_substitution);

		// 초기화 버튼
		JButton btn_reset = new JButton("초기화");
		btn_reset.setBounds(950, 590, 120, 50);
		btn_reset.setBorder(new RoundedBorder(30));
		btn_reset.setBackground(Color.WHITE);
		btn_reset.setFocusable(false);
		btn_reset.setFont(font.s_dream_15);

		frame.add(btn_reset);

		frame.setVisible(true);

		// 초기화 버튼 클릭시
		btn_reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				input_key_ecryption.setText("");
				input_text.setText("");
				output_ecryption.setText("");
				output_decryption.setText("");
				
				key = "";
				str = "";	
				alphabetBoard = new char[5][5];  // 5x5 표
				zCheck =""; // z가 있는지 체크
				blankCheck="";	// 공백 체크	
				oddFlag = false; //글자수 출력

				input_key_ecryption.setFocusable(true);
			}
		});

		// 암호화 버튼 클릭시
		btn_ecryption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				key = input_key_ecryption.getText().toUpperCase();
				str = input_text.getText().toUpperCase();

				// 암호화에 쓰일 암호판 세팅
				setBoard(key);
				
				encryption = Encryption(key, str); // 암호화한 문장 넣기
				output_ecryption.setText(encryption); // 암호문 보여주기
			}
		});

		// 복호화 버튼 클릭시
		btn_decryption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				decryption = Decryption();
				System.out.println(decryption + "   " +blankCheck);
				// 복호문에 공백을 넣어주기
				for( int i = 0 ; i < decryption.length() ; i++)
				{
					if(blankCheck.charAt(i)=='1')
					{
						decryption = decryption.substring(0,i)+" "+decryption.substring(i,decryption.length());
					}
				}
				output_decryption.setText(decryption);
			}
		});
		
		// 치환 과정 보기 클릭시
		btn_substitution.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				key = input_key_ecryption.getText();
				str = input_text.getText();
				
				Substitution sub = new Substitution(frame, alphabetBoard, key, str, encryption);
				sub.Screen();
				
			}
		});
	}

	public static String Encryption(String key, String str) {
		Encryption e = new Encryption(key, str, alphabetBoard, blankCheck, oddFlag);

		// 암호화된 암호문 받기
		String _encryption = e.start();
		
		// z가 있는지 체크하는 변수 받아오기
		zCheck = e.getzCheck();
		
		// 공백이 있는지 체크하는 변수 받아오기
		blankCheck = e.getblankCheck();
		
		// 변경된 str 받아오기
		str = e.getstr();
		
		// 글자 수 출력하는 변수 받아오기
		oddFlag = e.getoddFlag();
		

		return _encryption;
	}

	public static String Decryption() {

		// 암호문 공백 제거
		for( int i = 0 ; i < encryption.length() ; i++ ) 
		{
			if(encryption.charAt(i)==' ') //공백제거
				encryption = encryption.substring(0,i)+encryption.substring(i+1,encryption.length());
		}
		
		Decryption d = new Decryption(key, encryption, zCheck, alphabetBoard, oddFlag);

		// 암호화된 암호문 받기
		String _decryption = d.start();

		return _decryption;
	}

	// 5x5 암호판 만들기
	private static void setBoard(String key) {
		String keyForSet = "";					// 암호키에서 중복된 문자열을 제거한 문자열
		boolean duplicationFlag = false;		// 문자 중복 체크
		int keyLengthCount = 0;					// alphabetBoard에 keyForSet을 넣기 위한 count변수

		key += "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 	// 키에 모든 알파벳을 추가

		// 암호키 중복 처리
		for( int i = 0 ; i < key.length() ; i++ ) 
		{
			for( int j = 0 ; j < keyForSet.length() ; j++ )
			{
				if(key.charAt(i)==keyForSet.charAt(j))	// 중복일 경우
				{
					duplicationFlag = true;
					break;
				}
			}
			// 중복이 아닐 경우
			if(!(duplicationFlag)) keyForSet += key.charAt(i);	// keyForSet 변수에 문자 추가하기
			duplicationFlag = false;
		}

		// 5x5 표에 알파벳 추가
		for( int i = 0 ; i < alphabetBoard.length ; i++ )
		{
			for( int j = 0; j <alphabetBoard[i].length ; j++ )
			{
				alphabetBoard[i][j] = keyForSet.charAt(keyLengthCount++);
			}
		}	

		// 5x5 표 출력
		for( int i = 0 ; i < alphabetBoard.length ; i++ )
		{
			for( int j = 0; j <alphabetBoard[i].length ; j++ )
			{
				System.out.print(alphabetBoard[i][j]+"-");
			}
			System.out.println();
		}				

	}

}
