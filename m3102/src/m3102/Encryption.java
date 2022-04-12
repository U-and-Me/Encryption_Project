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
	public Encryption(String key, String str) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.str = str;
	}

	public String start() {

		// ��ȣȭ�� ���� ��ȣ�� ����
		setBoard(key);

		// ���� ���� �� ���ĺ� �ٲٱ�
		remove();
		
		// ��ȣ�� �����
		encryption = strEncryption(key, str);

		return encryption;
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
			if(str.charAt(i)=='z') //z�� q�� �ٲ��༭ ó����.
			{
				str = str.substring(0,i)+'q'+str.substring(i+1,str.length());
				zCheck+=1;
			}
			else 
			{
				zCheck+=0;
			}
		}
	}

	// 5x5 ��ȣ�� �����
	private static void setBoard(String key) {
		String keyForSet = "";					// ��ȣŰ���� �ߺ��� ���ڿ��� ������ ���ڿ�
		boolean duplicationFlag = false;		// ���� �ߺ� üũ
		int keyLengthCount = 0;					// alphabetBoard�� keyForSet�� �ֱ� ���� count����

		key += "abcdefghijklmnopqrstuvwxyz"; 	// Ű�� ��� ���ĺ��� �߰�

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
					tmpArr[1] = 'x';
					i--;
				}else{
					tmpArr[1] = str.charAt(i+1);
				}
			}catch(StringIndexOutOfBoundsException e) 
			{
				tmpArr[1] = 'x'; // ���ĺ� ������ Ȧ���� ��� �������� 'x' �߰�
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
