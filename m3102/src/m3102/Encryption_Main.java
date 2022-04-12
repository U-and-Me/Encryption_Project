package m3102;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Encryption_Main {
	
	static String decryption; // 복호문
	static String encryption; // 암호문
	static String key; // 암호키
	static String str; // 평문

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

		// font 설정
		Font s_dream_35 = new Font("에스코어 드림", Font.BOLD, 35);
		Font s_dream_20 = new Font("에스코어 드림", Font.BOLD, 20);
		Font s_dream_15 = new Font("에스코어 드림", Font.BOLD, 15);

		// 암호키
		JTextField key_ecryption = new JTextField("암호키 ");
		key_ecryption.setBounds(100, 50, 120, 100);
		key_ecryption.setEditable(false); // 글씨 변경 안됨
		key_ecryption.setBorder(null);
		key_ecryption.setBackground(Color.WHITE);
		key_ecryption.setFont(s_dream_35);

		frame.add(key_ecryption);

		// 암호키 입력
		JTextField input_key_ecryption = new JTextField();
		input_key_ecryption.setBounds(240, 77, 500, 50);
		input_key_ecryption.setFont(s_dream_35);

		frame.add(input_key_ecryption);

		// 평문
		JTextField text = new JTextField("평문 ");
		text.setBounds(100, 130, 120, 100);
		text.setEditable(false); // 글씨 변경 안됨
		text.setBorder(null);
		text.setBackground(Color.WHITE);
		text.setFont(s_dream_35);

		frame.add(text);

		// 평문 입력
		JTextField input_text = new JTextField();
		input_text.setBounds(240, 157, 500, 50);
		input_text.setFont(s_dream_35);

		frame.add(input_text);

		// 암호문
		JTextField title_ecryption = new JTextField("암호문 : ");
		title_ecryption.setBounds(100, 280, 120, 100);
		title_ecryption.setEditable(false); // 글씨 변경 안됨
		title_ecryption.setBorder(null);
		title_ecryption.setBackground(Color.WHITE);
		title_ecryption.setFont(s_dream_35);

		frame.add(title_ecryption);

		// 암호화 출력
		JTextField output_ecryption = new JTextField();
		output_ecryption.setBounds(240, 307, 500, 50);
		output_ecryption.setEditable(false); // 글씨 변경 안됨
		output_ecryption.setBackground(Color.WHITE);
		output_ecryption.setFont(s_dream_35);

		frame.add(output_ecryption);

		// 복호문
		JTextField title_decryption = new JTextField("복호문 : ");
		title_decryption.setBounds(100, 365, 120, 100);
		title_decryption.setEditable(false); // 글씨 변경 안됨
		title_decryption.setBorder(null);
		title_decryption.setBackground(Color.WHITE);
		title_decryption.setFont(s_dream_35);

		frame.add(title_decryption);

		// 복호문 출력
		JTextField output_decryption = new JTextField();
		output_decryption.setBounds(240, 392, 500, 50);
		output_decryption.setEditable(false); // 글씨 변경 안됨
		output_decryption.setBackground(Color.WHITE);
		output_decryption.setFont(s_dream_35);

		frame.add(output_decryption);
		
		// 암호화 버튼
		JButton btn_ecryption = new JButton("암호");
		btn_ecryption.setBounds(770, 307, 100, 50);
		btn_ecryption.setBorder(new RoundedBorder(30));
		btn_ecryption.setBackground(Color.WHITE);
		btn_ecryption.setFocusable(false);
		btn_ecryption.setFont(s_dream_15);
		
		frame.add(btn_ecryption);
		
		// 복호화 버튼
		JButton btn_decryption = new JButton("복호");
		btn_decryption.setBounds(770, 392, 100, 50);
		btn_decryption.setBorder(new RoundedBorder(30));
		btn_decryption.setBackground(Color.WHITE);
		btn_decryption.setFocusable(false);
		btn_decryption.setFont(s_dream_15);
		
		frame.add(btn_decryption);
		
		// 치환과정 버튼
		JButton btn_substitution = new JButton("치환 과정 보기");
		btn_substitution.setBounds(30, 590, 200, 50);
		btn_substitution.setBorder(new RoundedBorder(30));
		btn_substitution.setBackground(Color.WHITE);
		btn_substitution.setFocusable(false);
		btn_substitution.setFont(s_dream_20);
		
		frame.add(btn_substitution);
		
		// 초기화 버튼
		JButton btn_reset = new JButton("초기화");
		btn_reset.setBounds(950, 590, 120, 50);
		btn_reset.setBorder(new RoundedBorder(30));
		btn_reset.setBackground(Color.WHITE);
		btn_reset.setFocusable(false);
		btn_reset.setFont(s_dream_15);
		
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
				
				input_key_ecryption.setFocusable(true);
			}
		});
		
		// 암호화 버튼 클릭시
		btn_ecryption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				key = input_key_ecryption.getText();
				str = input_text.getText();
				encryption = Encryption(key, str); // 암호화한 문장 넣기
				output_ecryption.setText(encryption); // 암호문 보여주기
			}
		});
	}
	
	public static String Encryption(String key, String str) {
		Encryption e = new Encryption(key, str);
		
		String _encryption = e.start();
		
		return _encryption;
	}
	
	public static String Decryption() {
		
		String _decryption = null;
		
		return _decryption;
	}

}
