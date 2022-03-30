package m3102;

import java.awt.AlphaComposite;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.Descriptor;

public class Multiply {
	
	public static char alphabetBoard[][] = new char[5][5]; // 5x5 ǥ
	public static boolean oddFlag = false; //���ڼ� ���
	public static String zCheck =""; // z�� �ִ��� üũ

	public static void main(String[] args) {
		
		String decryption; // ��ȣ��
		String encryption; // ��ȣ��
		
		Scanner sc = new Scanner(System.in);
		System.out.print("��ȣȭ�� ���� Ű�� �Է��ϼ��� : ");
		String key = sc.nextLine();					// ��ȣŰ �Է�
		System.out.print("��ȣȭ�� ���ڿ��� �Է��ϼ��� : ");
		String str =  sc.nextLine();				// �� �Է�
		String blankCheck="";	// ���� üũ	

		setBoard(key);							//��ȣȭ�� ���� ��ȣ�� ����
		
		// �� ���� ����, 'z'�� 'q'�� �ٲٱ�
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
		
		encryption = strEncryption(key, str);	// return�� ��ȣ���� ������ ����
		
		// ��ȣ�� ���
		System.out.println("��ȣȭ�� ���ڿ� : " + encryption);

		// ��ȣ�� ���� ����
		for( int i = 0 ; i < encryption.length() ; i++ ) 
		{
			if(encryption.charAt(i)==' ') //��������
				encryption = encryption.substring(0,i)+encryption.substring(i+1,encryption.length());
		}
		
		decryption = strDecryption(key, encryption, zCheck);	// return�� ��ȣ���� ������ ����
		
		// ��ȣ���� ������ �־��ֱ�
		for( int i = 0 ; i < decryption.length() ; i++)
		{
			if(blankCheck.charAt(i)=='1')
			{
				decryption = decryption.substring(0,i)+" "+decryption.substring(i,decryption.length());
			}
		}
		
		// ��ȣ�� ���
		System.out.println("��ȣȭ�� ���ڿ� : " + decryption);
		
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

}