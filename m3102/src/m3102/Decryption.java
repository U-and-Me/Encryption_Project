package m3102;

import java.util.ArrayList;

public class Decryption {

	private static String key;
	private static String str;
	private static String decryption;

	public static char alphabetBoard[][] = new char[5][5]; // 5x5 ǥ
	public static boolean oddFlag = false; //���ڼ� ���
	public static String zCheck =""; // z�� �ִ��� üũ

	public Decryption(String key, String encryption, String zCheck, char[][] alphabetBoard) {
		Decryption.key = key;
		Decryption.str = encryption;
		Decryption.zCheck = zCheck;
		Decryption.alphabetBoard = alphabetBoard.clone();
	}
	
	public String start() {
		
		// ��ȣ�� �����
		decryption = strDecryption(key, str, zCheck);

		return decryption;
	}

	// ��ȣ�� ����
	private static String strDecryption(String key, String str, String zCheck) {
		ArrayList<char[]> playFair = new ArrayList<char[]>();	 // ��ȣ���� 2���� �߶� ����
		ArrayList<char[]> decPlayFair = new ArrayList<char[]>(); // ��ȣȭ�� ���ڸ� ����
		int x1 = 0 , x2 = 0 , y1 = 0, y2 = 0; //���� ��ȣ �� ������ ������ ��,�� ��
		String decStr ="";	// ��ȣ��

		int lengthOddFlag = 1;

		// ��ȣ���� 2���� �߶� playFair�� ����
		for( int i = 0 ; i < str.length() ; i+=2 )
		{
			char[] tmpArr = new char[2];
			tmpArr[0] = str.charAt(i);
			tmpArr[1] = str.charAt(i+1);
			playFair.add(tmpArr);
		}

		// ��ȣ���� ������ �ǵ����� ���� �۾� = ��ȣ�� ����
		for(int i = 0 ; i < playFair.size() ; i++ )
		{

			char[] tmpArr = new char[2];
			for( int j = 0 ; j < alphabetBoard.length ; j++ )
			{
				// 5x5 ǥ�� ��ȣȭ�� ���ڿ� ������ Ȯ���Ͽ� x, y ��ǥ �˾Ƴ���
				for( int k = 0 ; k < alphabetBoard[j].length ; k++ )
				{
					if(alphabetBoard[j][k] == playFair.get(i)[0])	// i��° ���� �迭�� 0��° �ε���
					{
						x1 = j;
						y1 = k;
					}
					if(alphabetBoard[j][k] == playFair.get(i)[1])	// i��° ���� �迭�� 1��° �ε���
					{
						x2 = j;
						y2 = k;
					}
				}
			}

			if(x1==x2) //���� ���� ��� ���� �ٷ� �Ʒ��� ����
			{
				tmpArr[0] = alphabetBoard[x1][(y1+4)%5];
				tmpArr[1] = alphabetBoard[x2][(y2+4)%5];
			}
			else if(y1==y2) //���� ���� ��� ���� �ٷ� �� �� ����
			{
				tmpArr[0] = alphabetBoard[(x1+4)%5][y1];
				tmpArr[1] = alphabetBoard[(x2+4)%5][y2];
			}
			else //��, �� �ٸ���� ���� �밢���� �ִ� ��.
			{
				tmpArr[0] = alphabetBoard[x2][y1];
				tmpArr[1] = alphabetBoard[x1][y2];
			}

			decPlayFair.add(tmpArr); // ��ȣȭ�� ���ڿ� ����
		}

		// �ߺ��� ���ڿ��� ���� ���ڿ��� �ǵ�������
		for(int i = 0 ; i < decPlayFair.size() ; i++) 
		{
			if(i!=decPlayFair.size()-1 && decPlayFair.get(i)[1]=='x' 
					&& decPlayFair.get(i)[0]==decPlayFair.get(i+1)[0])
			{	
				decStr += decPlayFair.get(i)[0];
			}
			else
			{
				decStr += decPlayFair.get(i)[0]+""+decPlayFair.get(i)[1];
			}
		}

		// 'q' ���� ���ڿ��� 'z'�� ��������
		for(int i = 0 ; i < zCheck.length() ; i++ )
		{
			if( zCheck.charAt(i) == '1' ) 
				decStr = decStr.substring(0,i)+'z'+decStr.substring(i+1,decStr.length());

		}

		if(oddFlag) decStr = decStr.substring(0,decStr.length()-1);

		return decStr; // ��ȣ�� return �ϱ�
	}

}
