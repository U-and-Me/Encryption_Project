package m3102;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Substitution implements ActionListener{

	JFrame frame; // ���� â
	JFrame b_frame; // ȣ�� �� â
	
	// Font ����
	Font_setting font = new Font_setting();
	

	public Substitution(JFrame b_frame) {
		this.frame = new JFrame("3102 ������"); // ���ο� â ����
		this.b_frame = b_frame;
		this.frame.setLayout(new FlowLayout());
		this.frame.setSize(1100,700);	//�������� ũ��
		this.frame.setResizable(false);//â�� ũ�⸦ �������� ���ϰ�
		this.frame.setLocationRelativeTo(null);//â�� ��� ������
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame�� ���������� ����ǰ�
		
		// frame ����
		this.frame.getContentPane().setBackground(Color.WHITE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(b_frame != null) b_frame.setVisible(false); // ���� â ���ֱ�
		frame.setVisible(true); // �ڱ� �ڽ� ����
		
		Screen();
		
	}
	
	// ġȯ ���� ȭ�� �����ϱ�
	public void Screen() {
		frame.setLayout(null);
		frame.setVisible(true);
		
		JButton title = new JButton("ġȯ ���� ����");
		title.setBounds(20, 20, 200, 50);
		title.setBorder(new RoundedBorder(30));
		title.setBackground(Color.WHITE);
		title.setFocusable(false);
		title.setFont(font.s_dream_20);
		title.setEnabled(false);

		frame.add(title);
		
		
		
		
	}

}
