package m3102;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Encryption_Main {

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
		Font s_dream = new Font("에스코어 드림", Font.BOLD, 35);
		
		// 암호문
		JTextField ecryption = new JTextField("암호문 : ");
		ecryption.setBounds(100, 50, 120, 100);
		ecryption.setEditable(false); // 글씨 변경 안됨
		ecryption.setBorder(null);
		ecryption.setBackground(Color.WHITE);
		ecryption.setFont(s_dream);
		
		frame.add(ecryption);
		
		// 암호문 입력
		JTextField input_ecryption = new JTextField();
		input_ecryption.setBounds(240, 77, 500, 50);
		input_ecryption.setFont(s_dream);
		
		frame.add(input_ecryption);
		
		frame.setVisible(true);
		
	}

}
