package m3102;

import java.util.ArrayList;

public class Encryption {

	private static String key;
	private static String str;
	private static String encryption;

	public static char alphabetBoard[][] = new char[5][5]; // 5x5 ǥ
	public static String zCheck =""; // z�� �ִ��� üũ
	public static String blankCheck="";	// ���� üũ	
	public static boolean oddFlag = false; //���ڼ� ���
		
	// ��ȣŰ, �� �Է¹޾Ƽ� ��ȣȭ �����ϱ�
	public Encryption(String key, String str, char[][] alphabetBoard, String blankCheck, boolean oddFlag) {
		Encryption.key = key;
		Encryption.str = str;
		Encryption.blankCheck = blankCheck;
		Encryption.alphabetBoard = alphabetBoard.clone();
		Encryption.oddFlag = oddFlag;
	}

	public String start() {		
		// ���� ���� �� ���ĺ� �ٲٱ�
		remove();
		
		// ��ȣ�� �����
		encryption = strEncryption(key, str);

		return encryption;
	}
	
	public String getzCheck() {
		return zCheck;
	}
	
	public String getblankCheck() {
		return blankCheck;
	}
	
	public String getstr() {
		return str;
	}
	
	public boolean getoddFlag() {
		return oddFlag;
	}

	// �� ���� ����, 'z'�� 'q'�� �ٲٱ�
	private static void remove() {
		for( int i = 0 ; i < str.length() ; i++ ) 
		{
			if(str.charAt(i)==' ') //��������
			{
				str = str.substring(0,i)+str.substring(i+1,str.length());
				blankCheck+=10;
			}
			else
			{
				blankCheck+=0;
			}
			if(str.charAt(i)=='Z') //Z�� Q�� �ٲ��༭ ó����.
			{
				str = str.substring(0,i)+'Q'+str.substring(i+1,str.length());
				zCheck+=1;
			}
			else 
			{
				zCheck+=0;
			}
		}
	}

	

	// ��ȣ�� ����
	private static String strEncryption(String key, String str){
		ArrayList<char[]> playFair = new ArrayList<char[]>();	// ��
		ArrayList<char[]> encPlayFair = new ArrayList<char[]>();	// ��ȣ���� 2���� ���� ArrayList
		int x1 = 0 , x2 = 0 , y1 = 0, y2 = 0;
		String encStr ="";	// ��ȣ��

		// ���� 2���� �߶� �ߺ� üũ �� 'x' �߰�
		for( int i = 0 ; i < str.length() ; i+=2 )
		{
			// ���� 2���� �ڸ���
			char[] tmpArr = new char[2];
			tmpArr[0] = str.charAt(i);
			try{
				if( str.charAt(i) == str.charAt(i+1)) // �������� ���� ���ĺ��� ������ 'x' �߰�
				{
					tmpArr[1] = 'X';
					i--;
				}else{
					tmpArr[1] = str.charAt(i+1);
				}
			}catch(StringIndexOutOfBoundsException e) 
			{
				tmpArr[1] = 'X'; // ���ĺ� ������ Ȧ���� ��� �������� 'x' �߰�
				oddFlag = true;
			}
			playFair.add(tmpArr);	// ArrayList�� �߰�
		}

		// 2���� �ڸ� ���� ���
		for(int i = 0 ; i < playFair.size() ; i++ )
		{
			System.out.print(playFair.get(i)[0]+""+playFair.get(i)[1]+" ");
		}
		System.out.println();

		// ��ȣ�� �����ϱ�
		for(int i = 0 ; i < playFair.size() ; i++ )
		{
			char[] tmpArr = new char[2];
			for( int j = 0 ; j < alphabetBoard.length ; j++ ) // ���߾�ȣ�� ��ġ�� ���� üũ
			{
				for( int k = 0 ; k < alphabetBoard[j].length ; k++ )
				{
					// 5x5 ǥ�� ��ȣȭ�� ���ڿ� ������ Ȯ���Ͽ� x, y ��ǥ �˾Ƴ���
					if(alphabetBoard[j][k] == playFair.get(i)[0]) // i��° ���� �迭�� 0��° �ε���
					{
						x1 = j;
						y1 = k;
					}
					if(alphabetBoard[j][k] == playFair.get(i)[1]) // i��° ���� �迭�� 1��° �ε���
					{
						x2 = j;
						y2 = k;
					}
				}
			}


			if(x1==x2) //���� �������
			{
				tmpArr[0] = alphabetBoard[x1][(y1+1)%5];
				tmpArr[1] = alphabetBoard[x2][(y2+1)%5];
			}
			else if(y1==y2) //���� ���� ���
			{
				tmpArr[0] = alphabetBoard[(x1+1)%5][y1];
				tmpArr[1] = alphabetBoard[(x2+1)%5][y2];
			} 
			else //��, �� ��� �ٸ����
			{
				tmpArr[0] = alphabetBoard[x2][y1];
				tmpArr[1] = alphabetBoard[x1][y2];
			}

			encPlayFair.add(tmpArr);

		}

		// return�� ��ȣ�� ���ڿ��� ���� �־��ֱ�
		for(int i = 0 ; i < encPlayFair.size() ; i++)
		{
			encStr += encPlayFair.get(i)[0]+""+encPlayFair.get(i)[1]+" ";
		}

		return encStr;
	}
}
