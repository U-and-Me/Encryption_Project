package m3102;

import java.util.ArrayList;

public class Encryption {

	private static String key;
	private static String str;
	private static String encryption;

	public static char alphabetBoard[][] = new char[5][5]; // 5x5 표
	public static String zCheck =""; // z가 있는지 체크
	public static String blankCheck="";	// 공백 체크	
	public static boolean oddFlag = false; //글자수 출력
		
	// 암호키, 평문 입력받아서 암호화 진행하기
	public Encryption(String key, String str, char[][] alphabetBoard, String blankCheck, boolean oddFlag) {
		Encryption.key = key;
		Encryption.str = str;
		Encryption.blankCheck = blankCheck;
		Encryption.alphabetBoard = alphabetBoard.clone();
		Encryption.oddFlag = oddFlag;
	}

	public String start() {		
		// 공백 제거 및 알파벳 바꾸기
		remove();
		
		// 암호문 만들기
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

	// 평문 공백 제거, 'z'를 'q'를 바꾸기
	private static void remove() {
		for( int i = 0 ; i < str.length() ; i++ ) 
		{
			if(str.charAt(i)==' ') //공백제거
			{
				str = str.substring(0,i)+str.substring(i+1,str.length());
				blankCheck+=10;
			}
			else
			{
				blankCheck+=0;
			}
			if(str.charAt(i)=='Z') //Z를 Q로 바꿔줘서 처리함.
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

	

	// 암호문 생성
	private static String strEncryption(String key, String str){
		ArrayList<char[]> playFair = new ArrayList<char[]>();	// 평문
		ArrayList<char[]> encPlayFair = new ArrayList<char[]>();	// 암호문을 2개씩 넣을 ArrayList
		int x1 = 0 , x2 = 0 , y1 = 0, y2 = 0;
		String encStr ="";	// 암호문

		// 평문을 2개씩 잘라서 중복 체크 및 'x' 추가
		for( int i = 0 ; i < str.length() ; i+=2 )
		{
			// 평문을 2개씩 자르기
			char[] tmpArr = new char[2];
			tmpArr[0] = str.charAt(i);
			try{
				if( str.charAt(i) == str.charAt(i+1)) // 연속으로 같은 알파벳이 나오면 'x' 추가
				{
					tmpArr[1] = 'X';
					i--;
				}else{
					tmpArr[1] = str.charAt(i+1);
				}
			}catch(StringIndexOutOfBoundsException e) 
			{
				tmpArr[1] = 'X'; // 알파벳 개수가 홀수일 경우 마지막에 'x' 추가
				oddFlag = true;
			}
			playFair.add(tmpArr);	// ArrayList에 추가
		}

		// 2개씩 자른 것을 출력
		for(int i = 0 ; i < playFair.size() ; i++ )
		{
			System.out.print(playFair.get(i)[0]+""+playFair.get(i)[1]+" ");
		}
		System.out.println();

		// 암호문 생성하기
		for(int i = 0 ; i < playFair.size() ; i++ )
		{
			char[] tmpArr = new char[2];
			for( int j = 0 ; j < alphabetBoard.length ; j++ ) // 다중암호의 위치를 각각 체크
			{
				for( int k = 0 ; k < alphabetBoard[j].length ; k++ )
				{
					// 5x5 표와 암호화된 문자와 같은지 확인하여 x, y 좌표 알아내기
					if(alphabetBoard[j][k] == playFair.get(i)[0]) // i번째 글자 배열의 0번째 인덱스
					{
						x1 = j;
						y1 = k;
					}
					if(alphabetBoard[j][k] == playFair.get(i)[1]) // i번째 글자 배열의 1번째 인덱스
					{
						x2 = j;
						y2 = k;
					}
				}
			}


			if(x1==x2) //행이 같은경우
			{
				tmpArr[0] = alphabetBoard[x1][(y1+1)%5];
				tmpArr[1] = alphabetBoard[x2][(y2+1)%5];
			}
			else if(y1==y2) //열이 같은 경우
			{
				tmpArr[0] = alphabetBoard[(x1+1)%5][y1];
				tmpArr[1] = alphabetBoard[(x2+1)%5][y2];
			} 
			else //행, 열 모두 다른경우
			{
				tmpArr[0] = alphabetBoard[x2][y1];
				tmpArr[1] = alphabetBoard[x1][y2];
			}

			encPlayFair.add(tmpArr);

		}

		// return할 암호문 문자열에 내용 넣어주기
		for(int i = 0 ; i < encPlayFair.size() ; i++)
		{
			encStr += encPlayFair.get(i)[0]+""+encPlayFair.get(i)[1]+" ";
		}

		return encStr;
	}
}
