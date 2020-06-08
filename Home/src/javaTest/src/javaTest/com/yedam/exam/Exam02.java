package javaTest.src.javaTest.com.yedam.exam;

import java.util.Scanner;

public class Exam02 {
	public static void main(String[] args) {
		//학생수와 학생들의 점수를 입력받아 최고점수, 평균점수를 구하는 프로그램
		//학생수/점수/점수리스트/최고/평균점수
		boolean run = true;
		int studentNum = 0;
		int[] scores = null;
		Scanner scn = new Scanner(System.in);
		
		while(run) {
			System.out.println("---------------------------------------------");
			System.out.println("1.학생수 2.점수입력 3.점수리스트 4.최고점수 5.평균점수 6.종료");
			System.out.println("---------------------------------------------");
			System.out.print("선택> ");
			
			int selectNo = scn.nextInt();scn.nextLine();
			if(selectNo == 1) {
				System.out.println("학생수를 입력하세요");
				studentNum = scn.nextInt();
				scores = new int[studentNum];
				System.out.println("배열이 생성되었습니다");
				
			} else if (selectNo == 2) {
				int i = 0;
				for (int score : scores) {
					System.out.println("점수를 입력하세요");
					score = scn.nextInt();
					scores[i++] = score;
				}
				
			} else if (selectNo == 3) {
				for(int i=0; i<scores.length; i++) {
					System.out.println("scores : " + i + ", "+ scores[i]);
				}
				
				
			} else if (selectNo == 4) {
				int maxValue = Integer.MIN_VALUE;
				for(int score: scores) {
		    		if(score > maxValue)
		    		maxValue = score;
		    	}
				System.out.println("최고점수: " + maxValue);
				
			} else if (selectNo == 5) {
				int sum = 0;
				for(int score : scores) {
					sum += score;
				}
				double avg = (double) sum / scores.length;
				System.out.println("평균점수: " + avg);
				
				
			} else if (selectNo == 6) {
				run = false;
			}//if
		}//while
		System.out.println("프로그램 종료");
	}
}
