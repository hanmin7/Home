package javaTest.src.javaTest.com.yedam.exam;

import java.util.ArrayList;
import java.util.List;

public class BoardServiceImpl implements BoardService {
	List<Board> list = new ArrayList<>();

	@Override
	public void insert(Board board) {
		list.add(board);
	}

	@Override
	public void update(Board board) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getNum()==board.getNum()) {
				list.get(i).setContents(board.getContents());
			}
		}
	}

	@Override
	public void delete(int num) {
		for(int i=0; i<list.size(); i++) {
			if (list.get(i).getNum()==num) {
				list.remove(i);
			}
		}
	}

	@Override
	public void show() {
		for(Board board : list) {
			System.out.println(board.toString());
		}
	}
	
	
}
