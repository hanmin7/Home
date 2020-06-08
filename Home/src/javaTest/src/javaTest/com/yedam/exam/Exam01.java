package javaTest.src.javaTest.com.yedam.exam;

public class Exam01 {
	public static void main(String[] args) {
		
		//2의 배수의 합(3의 배수는 제외):1734
		int sum1 = 0;
		for (int i = 1; i <= 100; i++) {
			if ((i % 3 == 0) || (!(i % 2 == 0))) {
				continue;
			}
			sum1 = sum1 + i;
		}
		System.out.println("2의 배수의 합 : " + sum1);

		// 3의 배수의 합(2의 배수 제외):867
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			if ((i % 2 == 0) || (!(i % 3 == 0))) {
				continue;
			}
			sum = sum + i;
		}
		System.out.println("3의 배수의 합: " + sum);
		
	}
}//왜답이 나오지??