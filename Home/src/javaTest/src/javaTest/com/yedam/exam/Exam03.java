package javaTest.src.javaTest.com.yedam.exam;

import java.util.Scanner;

public class Exam03 {
	public static void main(String[] args) {
		//두 매개값을 입력받아 두 수 사이의 정수합을 구하는 프로그램
		//forSum(int a, int b) : a~b사이의 합(for)
		//whileSum(int a, int b): a~b사이의 합(while문)
		//??문제 이해 못함~ 딴거부터~~
		int a = 0;
		int b = 0;
		Scanner scn = new Scanner(System.in);
		
//		System.out.println("매개값  a를 입력하세요");
//		a = scn.nextInt();
//		System.out.println("매개값  b를 입력하세요");
//		b = scn.nextInt();
//		int fSum = forSum(a,b);
//		System.out.println("for 합: " + fSum);
		
	
		System.out.println("매개값  c를 입력하세요");
		a = scn.nextInt();
		System.out.println("매개값  b를 입력하세요");
		b = scn.nextInt();
		int wSum = whileSum(a,b);
		System.out.println("while 합: " + wSum);
		
		
	}
	
	public static int forSum(int a, int b) {
		int sum = 0;
		for(int i=a; i<=b; i++) {
			sum = sum + i;
		}
		return sum;
	} 
	
	public static int whileSum(int pa, int pb){
		int sum2 = 0;
		int i = 0;
		while(i<=pb) {
			if(i == 0) {
				i = pa;
			}
			sum2 = sum2 + i;
			i++;
			if(i>pb) {
				break;
			}
		}
		return sum2;
	}
	
}
