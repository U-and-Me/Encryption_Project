package m3102;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Encryption_Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// â ����
		JFrame frame = new JFrame("3102 ������");
		frame.setSize(1100,700);								//�������� ũ��
		frame.setResizable(false);								//â�� ũ�⸦ �������� ���ϰ�
		frame.setLocationRelativeTo(null);						//â�� ��� ������
		frame.setLayout(null);									//���̾ƿ��� ������� ���������ϰ� ����.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//JFrame�� ���������� ����ǰ�
		
		// frame ����
		frame.getContentPane().setBackground(Color.WHITE);
		
		// font ����
		Font s_dream = new Font("�����ھ� �帲", Font.BOLD, 35);
		
		// ��ȣ��
		JTextField ecryption = new JTextField("��ȣ�� : ");
		ecryption.setBounds(100, 50, 120, 100);
		ecryption.setEditable(false); // �۾� ���� �ȵ�
		ecryption.setBorder(null);
		ecryption.setBackground(Color.WHITE);
		ecryption.setFont(s_dream);
		
		frame.add(ecryption);
		
		// ��ȣ�� �Է�
		JTextField input_ecryption = new JTextField();
		input_ecryption.setBounds(240, 77, 500, 50);
		input_ecryption.setFont(s_dream);
		
		frame.add(input_ecryption);
		
		frame.setVisible(true);
		
	}

}
