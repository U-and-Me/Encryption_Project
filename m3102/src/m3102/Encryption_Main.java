package m3102;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Encryption_Main {
	
	String decryption; // ��ȣ��
	String encryption; // ��ȣ��

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
		Font s_dream_35 = new Font("�����ھ� �帲", Font.BOLD, 35);
		Font s_dream_20 = new Font("�����ھ� �帲", Font.BOLD, 20);
		Font s_dream_15 = new Font("�����ھ� �帲", Font.BOLD, 15);

		// ��ȣŰ
		JTextField key_ecryption = new JTextField("��ȣŰ ");
		key_ecryption.setBounds(100, 50, 120, 100);
		key_ecryption.setEditable(false); // �۾� ���� �ȵ�
		key_ecryption.setBorder(null);
		key_ecryption.setBackground(Color.WHITE);
		key_ecryption.setFont(s_dream_35);

		frame.add(key_ecryption);

		// ��ȣŰ �Է�
		JTextField input_key_ecryption = new JTextField();
		input_key_ecryption.setBounds(240, 77, 500, 50);
		input_key_ecryption.setFont(s_dream_35);

		frame.add(input_key_ecryption);

		// ��
		JTextField text = new JTextField("�� ");
		text.setBounds(100, 130, 120, 100);
		text.setEditable(false); // �۾� ���� �ȵ�
		text.setBorder(null);
		text.setBackground(Color.WHITE);
		text.setFont(s_dream_35);

		frame.add(text);

		// �� �Է�
		JTextField input_text = new JTextField();
		input_text.setBounds(240, 157, 500, 50);
		input_text.setFont(s_dream_35);

		frame.add(input_text);

		// ��ȣ��
		JTextField title_ecryption = new JTextField("��ȣ�� : ");
		title_ecryption.setBounds(100, 280, 120, 100);
		title_ecryption.setEditable(false); // �۾� ���� �ȵ�
		title_ecryption.setBorder(null);
		title_ecryption.setBackground(Color.WHITE);
		title_ecryption.setFont(s_dream_35);

		frame.add(title_ecryption);

		// ��ȣȭ ���
		JTextField output_ecryption = new JTextField();
		output_ecryption.setBounds(240, 307, 500, 50);
		output_ecryption.setEditable(false); // �۾� ���� �ȵ�
		output_ecryption.setBackground(Color.WHITE);
		output_ecryption.setFont(s_dream_35);

		frame.add(output_ecryption);

		// ��ȣ��
		JTextField title_decryption = new JTextField("��ȣ�� : ");
		title_decryption.setBounds(100, 365, 120, 100);
		title_decryption.setEditable(false); // �۾� ���� �ȵ�
		title_decryption.setBorder(null);
		title_decryption.setBackground(Color.WHITE);
		title_decryption.setFont(s_dream_35);

		frame.add(title_decryption);

		// ��ȣ�� ���
		JTextField output_decryption = new JTextField();
		output_decryption.setBounds(240, 392, 500, 50);
		output_decryption.setEditable(false); // �۾� ���� �ȵ�
		output_decryption.setBackground(Color.WHITE);
		output_decryption.setFont(s_dream_35);

		frame.add(output_decryption);
		
		// ��ȣȭ ��ư
		JButton btn_ecryption = new JButton("��ȣ");
		btn_ecryption.setBounds(770, 307, 100, 50);
		btn_ecryption.setBorder(new RoundedBorder(30));
		btn_ecryption.setBackground(Color.WHITE);
		btn_ecryption.setFocusable(false);
		btn_ecryption.setFont(s_dream_15);
		
		frame.add(btn_ecryption);
		
		// ��ȣȭ ��ư
		JButton btn_decryption = new JButton("��ȣ");
		btn_decryption.setBounds(770, 392, 100, 50);
		btn_decryption.setBorder(new RoundedBorder(30));
		btn_decryption.setBackground(Color.WHITE);
		btn_decryption.setFocusable(false);
		btn_decryption.setFont(s_dream_15);
		
		frame.add(btn_decryption);
		
		// ġȯ���� ��ư
		JButton btn_substitution = new JButton("ġȯ ���� ����");
		btn_substitution.setBounds(30, 590, 200, 50);
		btn_substitution.setBorder(new RoundedBorder(30));
		btn_substitution.setBackground(Color.WHITE);
		btn_substitution.setFocusable(false);
		btn_substitution.setFont(s_dream_20);
		
		frame.add(btn_substitution);

		frame.setVisible(true);

	}
	
	public static void Encryption() {
		
	}

}
