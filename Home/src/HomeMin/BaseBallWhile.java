package HomeMin;

import java.util.Scanner;

public class BaseBallWhile {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] com = new int[3];
		while(true) {
		for(int i=0; i<com.length; i++) {
			com[i] = (int) (Math.random() * 9) + 1;
		}
		for (int i = 0; i < com.length; i++) {
			for (int j = 0; j < i; j++) {
				boolean loopingNum = true;
				if(com[i] == com[j]) {
					while (loopingNum) {
						com[j] = (int) (Math.random() * 9) + 1;
						if(com[i] != com[j]) {
							loopingNum = false;
						}
					}
				}
			}
		
		}
		if(com[0] == com[1]) {
			break;
		}
		}
			System.out.print(com[0] + " " +com[1] +" "+ com[2]);
		int count = 1;
		int[] user = new int[3];
		while(true) {
//			System.out.println();
//			System.out.println(count + "회차 ========");
//			System.out.println("첫번째 값 입력");
//			int num1 = scn.nextInt();
//			System.out.println("두번째 값 입력");
//			int num2 = scn.nextInt();
//			System.out.println("세번째 값 입력");
//			int num3 = scn.nextInt();
//			
//			int[] user = {num1,num2,num3};
			
			System.out.println("\n");
			System.out.println(count + "회차 ==========");
			for(int i=0; i<user.length; i++) {
				System.out.println((i+1) + "번째 숫자를 입력하세요");
				user[i]=scn.nextInt();
				
				while(user[i]>=10 || user[i]<=0) {
					System.out.println("1~9사이의 숫자를 입력하세요");
					user[i]=scn.nextInt();
				}
			}
			
			int strike=0;
			int ball=0;
			
			for(int i=0; i<com.length; i++) {
				if(com[i] == user[i]) {
					strike++;
				}
				for (int j=0; j<com.length; j++) {
					if(i != j) {
						if(com[i] == user[j]) {
							ball++;
						}
					}
				}
			}
			if(strike == 3) {
				System.out.println("3 스트라이크 !!  " + count + "회차에 성공!!");
				break;
			}else if(strike==0 && ball==0) {
				System.out.println("----------------------아웃");
			}
			System.out.print("[");
			for(int i=0; i < user.length; i++) {
				if(i == user.length-1) {
					System.out.print(user[i]);
					continue;
				}
				System.out.print(user[i]+ ", ");
			}
			System.out.print("] 스트라이크: " + strike + " 볼: " + ball);
//			System.out.println("[" + user[0] + ", " + user[1] + ", " + user[2] + "] 스트라이크: " + strike + " 볼: " + ball);
			count++;
			
			if(count == 11) {
				System.out.println("10회 모두 종료 !! 실패!!");				
				break;
			}
		}

	}
}
