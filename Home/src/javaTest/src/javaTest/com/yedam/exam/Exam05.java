package javaTest.src.javaTest.com.yedam.exam;

import java.util.Scanner;


public class Exam05 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		BoardService service = new BoardServiceImpl();
		
		boolean cnt = true;
		int num =0;
		while (true) {
			System.out.println("=======================================");
			System.out.println("1.추가 2.수정 3.삭제 4.리스트 5.종료");
			System.out.println("=======================================");
			System.out.print("선택> ");
			int menu = scn.nextInt();scn.nextLine();
			
			if (menu == 1) {
				if(cnt) {
					System.out.println("게시글 번호 입력.");
					num = scn.nextInt();	
				} else {
					System.out.println("게시글 번호는 " + num + "입니다");
					
				}
				scn.nextLine();
				System.out.println("제목입력.");
				String title = scn.nextLine();
				System.out.println("내용입력.");
				String contents = scn.nextLine();
				Board board = new Board(num, title, contents);
				service.insert(board);
				num++;
				cnt=false;
				
			} else if(menu == 2) {
				System.out.println("게시글 번호 입력.");
				int num2 = scn.nextInt();scn.nextLine();
				System.out.println("변경내용입력.");
				String contents = scn.nextLine();
				Board board = new Board(num2, null, contents);
				service.update(board);
				
			} else if(menu == 3) {
				System.out.println("게시글 번호 입력.");
				int num3 = scn.nextInt();
				service.delete(num3);
				
			} else if(menu == 4) {
				service.show();
				
			} else if(menu == 5) {
				break;
			}
			
		}//while
		System.out.println("프로그램 종료");
	}
}
