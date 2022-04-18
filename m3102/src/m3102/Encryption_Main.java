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

	static String decryption; // ��ȣ��
	static String encryption; // ��ȣ��
	static String key; // ��ȣŰ
	static String str; // ��
	
	public static char alphabetBoard[][] = new char[5][5]; // 5x5 ǥ
	public static String zCheck =""; // z�� �ִ��� üũ
	public static String blankCheck="";	// ���� üũ	
	public static boolean oddFlag; //���ڼ� ���

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

		// Font ����
		Font_setting font = new Font_setting();

		// ��ȣŰ
		JTextField key_ecryption = new JTextField("��ȣŰ ");
		key_ecryption.setBounds(100, 50, 120, 100);
		key_ecryption.setEditable(false); // �۾� ���� �ȵ�
		key_ecryption.setBorder(null);
		key_ecryption.setBackground(Color.WHITE);
		key_ecryption.setFont(font.s_dream_35);

		frame.add(key_ecryption);

		// ��ȣŰ �Է�
		JTextField input_key_ecryption = new JTextField();
		input_key_ecryption.setBounds(240, 77, 500, 50);
		input_key_ecryption.setFont(font.s_dream_35);

		frame.add(input_key_ecryption);

		// ��
		JTextField text = new JTextField("�� ");
		text.setBounds(100, 130, 120, 100);
		text.setEditable(false); // �۾� ���� �ȵ�
		text.setBorder(null);
		text.setBackground(Color.WHITE);
		text.setFont(font.s_dream_35);

		frame.add(text);

		// �� �Է�
		JTextField input_text = new JTextField();
		input_text.setBounds(240, 157, 500, 50);
		input_text.setFont(font.s_dream_35);

		frame.add(input_text);

		// ��ȣ��
		JTextField title_ecryption = new JTextField("��ȣ�� : ");
		title_ecryption.setBounds(100, 280, 120, 100);
		title_ecryption.setEditable(false); // �۾� ���� �ȵ�
		title_ecryption.setBorder(null);
		title_ecryption.setBackground(Color.WHITE);
		title_ecryption.setFont(font.s_dream_35);

		frame.add(title_ecryption);

		// ��ȣȭ ���
		JTextField output_ecryption = new JTextField();
		output_ecryption.setBounds(240, 307, 500, 50);
		output_ecryption.setEditable(false); // �۾� ���� �ȵ�
		output_ecryption.setBackground(Color.WHITE);
		output_ecryption.setFont(font.s_dream_35);

		frame.add(output_ecryption);

		// ��ȣ��
		JTextField title_decryption = new JTextField("��ȣ�� : ");
		title_decryption.setBounds(100, 365, 120, 100);
		title_decryption.setEditable(false); // �۾� ���� �ȵ�
		title_decryption.setBorder(null);
		title_decryption.setBackground(Color.WHITE);
		title_decryption.setFont(font.s_dream_35);

		frame.add(title_decryption);

		// ��ȣ�� ���
		JTextField output_decryption = new JTextField();
		output_decryption.setBounds(240, 392, 500, 50);
		output_decryption.setEditable(false); // �۾� ���� �ȵ�
		output_decryption.setBackground(Color.WHITE);
		output_decryption.setFont(font.s_dream_35);

		frame.add(output_decryption);

		// ��ȣȭ ��ư
		JButton btn_ecryption = new JButton("��ȣ");
		btn_ecryption.setBounds(770, 307, 100, 50);
		btn_ecryption.setBorder(new RoundedBorder(30));
		btn_ecryption.setBackground(Color.WHITE);
		btn_ecryption.setFocusable(false);
		btn_ecryption.setFont(font.s_dream_15);

		frame.add(btn_ecryption);

		// ��ȣȭ ��ư
		JButton btn_decryption = new JButton("��ȣ");
		btn_decryption.setBounds(770, 392, 100, 50);
		btn_decryption.setBorder(new RoundedBorder(30));
		btn_decryption.setBackground(Color.WHITE);
		btn_decryption.setFocusable(false);
		btn_decryption.setFont(font.s_dream_15);

		frame.add(btn_decryption);

		// ġȯ���� ��ư
		JButton btn_substitution = new JButton("ġȯ ���� ����");
		btn_substitution.setBounds(30, 590, 200, 50);
		btn_substitution.setBorder(new RoundedBorder(30));
		btn_substitution.setBackground(Color.WHITE);
		btn_substitution.setFocusable(false);
		btn_substitution.setFont(font.s_dream_20);

		frame.add(btn_substitution);

		// �ʱ�ȭ ��ư
		JButton btn_reset = new JButton("�ʱ�ȭ");
		btn_reset.setBounds(950, 590, 120, 50);
		btn_reset.setBorder(new RoundedBorder(30));
		btn_reset.setBackground(Color.WHITE);
		btn_reset.setFocusable(false);
		btn_reset.setFont(font.s_dream_15);

		frame.add(btn_reset);

		frame.setVisible(true);

		// �ʱ�ȭ ��ư Ŭ����
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
				alphabetBoard = new char[5][5];  // 5x5 ǥ
				zCheck =""; // z�� �ִ��� üũ
				blankCheck="";	// ���� üũ	
				oddFlag = false; //���ڼ� ���

				input_key_ecryption.setFocusable(true);
			}
		});

		// ��ȣȭ ��ư Ŭ����
		btn_ecryption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				key = input_key_ecryption.getText().toUpperCase();
				str = input_text.getText().toUpperCase();

				// ��ȣȭ�� ���� ��ȣ�� ����
				setBoard(key);
				
				encryption = Encryption(key, str); // ��ȣȭ�� ���� �ֱ�
				output_ecryption.setText(encryption); // ��ȣ�� �����ֱ�
			}
		});

		// ��ȣȭ ��ư Ŭ����
		btn_decryption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				decryption = Decryption();
				System.out.println(decryption + "   " +blankCheck);
				// ��ȣ���� ������ �־��ֱ�
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
		
		// ġȯ ���� ���� Ŭ����
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

		// ��ȣȭ�� ��ȣ�� �ޱ�
		String _encryption = e.start();
		
		// z�� �ִ��� üũ�ϴ� ���� �޾ƿ���
		zCheck = e.getzCheck();
		
		// ������ �ִ��� üũ�ϴ� ���� �޾ƿ���
		blankCheck = e.getblankCheck();
		
		// ����� str �޾ƿ���
		str = e.getstr();
		
		// ���� �� ����ϴ� ���� �޾ƿ���
		oddFlag = e.getoddFlag();
		

		return _encryption;
	}

	public static String Decryption() {

		// ��ȣ�� ���� ����
		for( int i = 0 ; i < encryption.length() ; i++ ) 
		{
			if(encryption.charAt(i)==' ') //��������
				encryption = encryption.substring(0,i)+encryption.substring(i+1,encryption.length());
		}
		
		Decryption d = new Decryption(key, encryption, zCheck, alphabetBoard, oddFlag);

		// ��ȣȭ�� ��ȣ�� �ޱ�
		String _decryption = d.start();

		return _decryption;
	}

	// 5x5 ��ȣ�� �����
	private static void setBoard(String key) {
		String keyForSet = "";					// ��ȣŰ���� �ߺ��� ���ڿ��� ������ ���ڿ�
		boolean duplicationFlag = false;		// ���� �ߺ� üũ
		int keyLengthCount = 0;					// alphabetBoard�� keyForSet�� �ֱ� ���� count����

		key += "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 	// Ű�� ��� ���ĺ��� �߰�

		// ��ȣŰ �ߺ� ó��
		for( int i = 0 ; i < key.length() ; i++ ) 
		{
			for( int j = 0 ; j < keyForSet.length() ; j++ )
			{
				if(key.charAt(i)==keyForSet.charAt(j))	// �ߺ��� ���
				{
					duplicationFlag = true;
					break;
				}
			}
			// �ߺ��� �ƴ� ���
			if(!(duplicationFlag)) keyForSet += key.charAt(i);	// keyForSet ������ ���� �߰��ϱ�
			duplicationFlag = false;
		}

		// 5x5 ǥ�� ���ĺ� �߰�
		for( int i = 0 ; i < alphabetBoard.length ; i++ )
		{
			for( int j = 0; j <alphabetBoard[i].length ; j++ )
			{
				alphabetBoard[i][j] = keyForSet.charAt(keyLengthCount++);
			}
		}	

		// 5x5 ǥ ���
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
