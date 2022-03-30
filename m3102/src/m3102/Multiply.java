package m3102;

import java.awt.AlphaComposite;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.Descriptor;

public class Multiply {
	
	public static char alphabetBoard[][] = new char[5][5]; // 5x5 표
	public static boolean oddFlag = false; //글자수 출력
	public static String zCheck =""; // z가 있는지 체크

	public static void main(String[] args) {
		
		String decryption; // 복호문
		String encryption; // 암호문
		
		Scanner sc = new Scanner(System.in);
		System.out.print("암호화에 쓰일 키를 입력하세요 : ");
		String key = sc.nextLine();					// 암호키 입력
		System.out.print("암호화할 문자열을 입력하세요 : ");
		String str =  sc.nextLine();				// 평문 입력
		String blankCheck="";	// 공백 체크	

		setBoard(key);							//암호화에 쓰일 암호판 세팅
		
		// 평문 공백 제거, 'z'를 'q'로 바꾸기
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
			if(str.charAt(i)=='z') //z를 q로 바꿔줘서 처리함.
			{
				str = str.substring(0,i)+'q'+str.substring(i+1,str.length());
				zCheck+=1;
			}
			else 
			{
				zCheck+=0;
			}
		}
		
		encryption = strEncryption(key, str);	// return된 암호문을 변수에 저장
		
		// 암호문 출력
		System.out.println("암호화된 문자열 : " + encryption);

		// 암호문 공백 제거
		for( int i = 0 ; i < encryption.length() ; i++ ) 
		{
			if(encryption.charAt(i)==' ') //공백제거
				encryption = encryption.substring(0,i)+encryption.substring(i+1,encryption.length());
		}
		
		decryption = strDecryption(key, encryption, zCheck);	// return된 복호문을 변수에 저장
		
		// 복호문에 공백을 넣어주기
		for( int i = 0 ; i < decryption.length() ; i++)
		{
			if(blankCheck.charAt(i)=='1')
			{
				decryption = decryption.substring(0,i)+" "+decryption.substring(i,decryption.length());
			}
		}
		
		// 복호문 출력
		System.out.println("복호화된 문자열 : " + decryption);
		
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
					tmpArr[1] = 'x';
					i--;
				}else{
					tmpArr[1] = str.charAt(i+1);
				}
			}catch(StringIndexOutOfBoundsException e) 
			{
				tmpArr[1] = 'x'; // 알파벳 개수가 홀수일 경우 마지막에 'x' 추가
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

	// 5x5 암호판 만들기
	private static void setBoard(String key) {
		String keyForSet = "";					// 암호키에서 중복된 문자열을 제거한 문자열
		boolean duplicationFlag = false;		// 문자 중복 체크
		int keyLengthCount = 0;					// alphabetBoard에 keyForSet을 넣기 위한 count변수
		
		key += "abcdefghijklmnopqrstuvwxyz"; 	// 키에 모든 알파벳을 추가
		
		// 암호키 중복 처리
		for( int i = 0 ; i < key.length() ; i++ ) 
		{
			for( int j = 0 ; j < keyForSet.length() ; j++ )
			{
				if(key.charAt(i)==keyForSet.charAt(j))	// 중복일 경우
				{
					duplicationFlag = true;
					break;
				}
			}
			// 중복이 아닐 경우
			if(!(duplicationFlag)) keyForSet += key.charAt(i);	// keyForSet 변수에 문자 추가하기
			duplicationFlag = false;
		}
		
		// 5x5 표에 알파벳 추가
		for( int i = 0 ; i < alphabetBoard.length ; i++ )
		{
			for( int j = 0; j <alphabetBoard[i].length ; j++ )
			{
				alphabetBoard[i][j] = keyForSet.charAt(keyLengthCount++);
			}
		}	
		
		// 5x5 표 출력
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