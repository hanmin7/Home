package question_java;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuizController implements Initializable {
	Connection conn;
	int numi = 1;

	@FXML
	Button btnOk, btnNext, btnClose;
	@FXML
	Label select1, select2, select3, quiz;
	@FXML
	RadioButton rad1, rad2, rad3;
	@FXML
	ToggleGroup group;

	ObservableList<Quiz> alist, blist;

	int nextNum = 1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		} catch (Exception e) {
			e.printStackTrace();
		}

		blist = FXCollections.observableArrayList();
		alist = getQuizList();
//		for(Quiz q : alist)
//		System.out.println(q);
//		blist = getQuizList();
		
		
		int[] intAry = new int[alist.size()];
		int aryLength = intAry.length;

		for (int i = 0; i < aryLength;) {
			int temp = (int) (Math.random() * aryLength);
//			System.out.println("temp: " + temp);
			intAry[i] = temp;
			int j = i;
			for (; j > 0;) {
				if (i != 0) {
					if (intAry[i] == intAry[j - 1]) {
						break;
					}
					j--;
				}
			}
			if (j != 0)
				continue;
			i++;
		}//intAry 중복처리  인덱스값 랜덤
		
		
//		for(int i=0; i<5; i++)
//			System.out.println(intAry[i]);
		
		for (int i = 0; i < alist.size(); i++) {

			blist.add(alist.get(intAry[i]));
		}

		getAnsList(blist.get(0));

		btnOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleBtnOkAction(event);
			}
		});

	}

	public ObservableList<Quiz> getQuizList() {
		ObservableList<Quiz> list = FXCollections.observableArrayList();
//		Random random = new Random();
//		numi = random.nextInt(5) + 1;   
		String sql = "select num, quiz, select1, select2, select3, r1, r2, r3, answer, answerlist  from question";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Quiz board = new Quiz(rs.getInt("num"), rs.getString("quiz"), rs.getString("select1"),
						rs.getString("select2"), rs.getString("select3"), rs.getInt("r1"), rs.getInt("r2"),
						rs.getInt("r3"), rs.getString("answer"), rs.getString("answerlist"));
				list.add(board);
			}
			for(Quiz q : list)
				System.out.println(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void getAnsList(Quiz quizAns) {
		String[] quz = new String[3];
		quz[0] = quizAns.getSelect1();
		quz[1] = quizAns.getSelect2();
		quz[2] = quizAns.getSelect3();

		Random random = new Random();
		int num1 = random.nextInt(3);
		int num2 = random.nextInt(3);
		if (num1 == num2) {
			while (true) {
				num2 = random.nextInt(3);
				if (num1 != num2) {
					break;
				}
			}
		}
		int num3 = random.nextInt(3);
		if (num3 == num1 || num3 == num2) {
			while (true) {
				num3 = random.nextInt(3);
				if (num3 != num1 && num3 != num2) {
					break;
				}
			}
		}
		quiz.setText(quizAns.getQuiz());
		select1.setText(quz[num1]);
		select2.setText(quz[num2]);
		select3.setText(quz[num3]);
	} //getAnsList 문제에 대한 보기들 랜덤 뿌리기

	
	public void handleBtnOkAction(ActionEvent ae) {
		Stage addStage = new Stage(StageStyle.UTILITY);
		addStage.initModality(Modality.WINDOW_MODAL);
		addStage.initOwner(btnOk.getScene().getWindow());

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Answer.fxml")); // scene 담아야함
			Scene scene = new Scene(parent);

			addStage.setScene(scene);
			addStage.setResizable(false);
			addStage.show();

			Button btnNext = (Button) parent.lookup("#btnNext");
			btnNext.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					addStage.close();
					try {
						getAnsList(blist.get(nextNum++));
					} catch (IndexOutOfBoundsException e) {
						System.out.println("ㅊㅋㅊㅋ");
						// 문제 다 돌렸을 때 구현 넣기
					}

				}
			});

			Button btnClose = (Button) parent.lookup("#btnClose");
			btnClose.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {

					addStage.close();
					Platform.exit();
				}
			});

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void handleBtnCancelAction(ActionEvent e) {
		Platform.exit();

	}

}
