package m3102;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Substitution implements ActionListener{

	JFrame frame; // 현재 창
	JFrame b_frame; // 호출 전 창
	
	// Font 설정
	Font_setting font = new Font_setting();
	

	public Substitution(JFrame b_frame) {
		this.frame = new JFrame("3102 김유나"); // 새로운 창 생성
		this.b_frame = b_frame;
		this.frame.setLayout(new FlowLayout());
		this.frame.setSize(1100,700);	//프레임의 크기
		this.frame.setResizable(false);//창의 크기를 변경하지 못하게
		this.frame.setLocationRelativeTo(null);//창이 가운데 나오게
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame이 정상적으로 종료되게
		
		// frame 설정
		this.frame.getContentPane().setBackground(Color.WHITE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(b_frame != null) b_frame.setVisible(false); // 이전 창 없애기
		frame.setVisible(true); // 자기 자신 띄우기
		
		Screen();
		
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
		
		
		
		
	}

}
