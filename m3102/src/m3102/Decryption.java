package m3102;

import java.util.ArrayList;

public class Decryption {

	private static String key;
	private static String str;
	private static String decryption;

	public static char alphabetBoard[][] = new char[5][5]; // 5x5 표
	public static boolean oddFlag = false; //글자수 출력
	public static String zCheck =""; // z가 있는지 체크

	public Decryption(String key, String encryption, String zCheck, char[][] alphabetBoard) {
		Decryption.key = key;
		Decryption.str = encryption;
		Decryption.zCheck = zCheck;
		Decryption.alphabetBoard = alphabetBoard.clone();
	}
	
	public String start() {
		
		// 복호문 만들기
		decryption = strDecryption(key, str, zCheck);

		return decryption;
	}

	// 복호문 생성
	private static String strDecryption(String key, String str, String zCheck) {
		ArrayList<char[]> playFair = new ArrayList<char[]>();	 // 암호문을 2개씩 잘라 저장
		ArrayList<char[]> decPlayFair = new ArrayList<char[]>(); // 복호화된 문자를 저장
		int x1 = 0 , x2 = 0 , y1 = 0, y2 = 0; //쌍자 암호 두 글자의 각각의 행,열 값
		String decStr ="";	// 복호문

		int lengthOddFlag = 1;

		// 암호문을 2개씩 잘라 playFair에 저장
		for( int i = 0 ; i < str.length() ; i+=2 )
		{
			char[] tmpArr = new char[2];
			tmpArr[0] = str.charAt(i);
			tmpArr[1] = str.charAt(i+1);
			playFair.add(tmpArr);
		}

		// 암호문을 평문으로 되돌리기 위한 작업 = 복호문 생성
		for(int i = 0 ; i < playFair.size() ; i++ )
		{

			char[] tmpArr = new char[2];
			for( int j = 0 ; j < alphabetBoard.length ; j++ )
			{
				// 5x5 표와 암호화된 문자와 같은지 확인하여 x, y 좌표 알아내기
				for( int k = 0 ; k < alphabetBoard[j].length ; k++ )
				{
					if(alphabetBoard[j][k] == playFair.get(i)[0])	// i번째 글자 배열의 0번째 인덱스
					{
						x1 = j;
						y1 = k;
					}
					if(alphabetBoard[j][k] == playFair.get(i)[1])	// i번째 글자 배열의 1번째 인덱스
					{
						x2 = j;
						y2 = k;
					}
				}
			}

			if(x1==x2) //행이 같은 경우 각각 바로 아래열 대입
			{
				tmpArr[0] = alphabetBoard[x1][(y1+4)%5];
				tmpArr[1] = alphabetBoard[x2][(y2+4)%5];
			}
			else if(y1==y2) //열이 같은 경우 각각 바로 옆 열 대입
			{
				tmpArr[0] = alphabetBoard[(x1+4)%5][y1];
				tmpArr[1] = alphabetBoard[(x2+4)%5][y2];
			}
			else //행, 열 다른경우 각자 대각선에 있는 곳.
			{
				tmpArr[0] = alphabetBoard[x2][y1];
				tmpArr[1] = alphabetBoard[x1][y2];
			}

			decPlayFair.add(tmpArr); // 복호화된 문자열 저장
		}

		// 중복된 문자열을 기존 문자열로 되돌려놓기
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

		// 'q' 였던 문자열을 'z'로 돌려놓기
		for(int i = 0 ; i < zCheck.length() ; i++ )
		{
			if( zCheck.charAt(i) == '1' ) 
				decStr = decStr.substring(0,i)+'z'+decStr.substring(i+1,decStr.length());

		}

		if(oddFlag) decStr = decStr.substring(0,decStr.length()-1);

		return decStr; // 복호문 return 하기
	}

}
