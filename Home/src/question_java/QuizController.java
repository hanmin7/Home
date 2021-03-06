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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuizController implements Initializable {
	Connection conn;
	int numi = 1;

	@FXML
	Button btnOk, btnNext, btnClose;
	@FXML
	Label select1, select2, select3, quiz, scoreLabel;
	@FXML
	RadioButton rad1, rad2, rad3;
	@FXML
	ToggleGroup group;

	ObservableList<Quiz> alist, blist;

	int nextNum = 1;
	int nextAnum = 0;
	String match;
	int score = 0;

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
		} // intAry 중복처리 인덱스값 랜덤

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

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldVal, Toggle newVal) {
				if (newVal != null) {// newVal에 담는데 밑에서 deselect해서 널값으로 넘어옴. 그래서 토글누르면 값이널값이 아니게되니까 널값이 아닐 때
					int test = 1;
					if (newVal.getUserData().equals(select1.getUserData())) {
//						System.out.println("1");
						// 내가 누른버튼의 getUserData랑 라벨1의 getUserData랑 묶고
						// 라벨1의 getText랑 blist에서 가져오는 보기1번이랑 비교. getSelect1이 답이 들어있으니 같으면 정답. 다르면 오답.
						// 그래서 라벨1이랑 묶여서 보기1,2,3비교하고 라벨2랑묶어서 보기1,2,3비교하고 라벨3이랑 묶어서 보기1,2,3 비교.
						// 같은식으로 반복돌리는데 좀더코드를 줄이는 방법은??
						if (select1.getText().equals(blist.get(nextAnum).getSelect1())) {
//							System.out.println("정답");
							match = "정답입니다";
							score++;
						} else if (select1.getText().equals(blist.get(nextAnum).getSelect2())) {
//							System.out.println("오답");
							match = "오답입니다";
						} else if (select1.getText().equals(blist.get(nextAnum).getSelect3())) {
//							System.out.println("오답");
							match = "오답입니다";
						}
					} else if (newVal.getUserData().equals(select2.getUserData())) {
//						System.out.println("2");
						if (select2.getText().equals(blist.get(nextAnum).getSelect1())) {
//							System.out.println("정답");
							match = "정답입니다";
							score++;
						} else if (select2.getText().equals(blist.get(nextAnum).getSelect2())) {
//							System.out.println("오답");
							match = "오답입니다";
						} else if (select2.getText().equals(blist.get(nextAnum).getSelect3())) {
//							System.out.println("오답");
							match = "오답입니다";
						}
					} else if (newVal.getUserData().equals(select3.getUserData())) {
//						System.out.println("3");
						if (select3.getText().equals(blist.get(nextAnum).getSelect1())) {
//							System.out.println("정답");
							match = "정답입니다";
							score++;
						} else if (select3.getText().equals(blist.get(nextAnum).getSelect2())) {
//							System.out.println("오답");
							match = "오답입니다";
						} else if (select3.getText().equals(blist.get(nextAnum).getSelect3())) {
//							System.out.println("오답");
							match = "오답입니다";
						}
					}
				}
			}

		});

		scoreLabel.setText(Integer.toString(score));

	} // initialize

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
//			for(Quiz q : list)
//				System.out.println(q);
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
	} // getAnsList 문제에 대한 보기들 랜덤 뿌리기

	public void handleBtnOkAction(ActionEvent ae) {
		Stage addStage = new Stage(StageStyle.UTILITY);
		addStage.initModality(Modality.WINDOW_MODAL);
		addStage.initOwner(btnOk.getScene().getWindow());

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Answer.fxml"));
			Scene scene = new Scene(parent);
			addStage.setScene(scene);
			addStage.setResizable(false);
			addStage.show();

			Label answerLabel = (Label) parent.lookup("#answerLabel");
			answerLabel.setText(match);
			// 정답매칭 후 정답이다 오답이다뿌려주기

			Label answerList = (Label) parent.lookup("#answerList");
			answerList.setText(blist.get(nextAnum++).getAnswerlist());

			Button btnNext = (Button) parent.lookup("#btnNext");
			btnNext.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (rad1.isSelected()) {
						rad1.setSelected(false);
					} else if (rad2.isSelected()) {
						rad2.setSelected(false);
					} else if (rad3.isSelected()) {
						rad3.setSelected(false);
					}

					addStage.close();
					scoreLabel.setText(Integer.toString(score));

					try {
						getAnsList(blist.get(nextNum++));
					} catch (IndexOutOfBoundsException e) {
						System.out.println("ㅊㅋㅊㅋ");
//						messagePopup("모든 문제를 다 풀었습니다!!짝짝짝!!");
						messageDialog("모든 문제를 다 풀었습니다!! 짝짝짝!!");
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

	
	
	public void messageDialog(String message) {
		Stage customStage = new Stage(StageStyle.UTILITY);
		customStage.initModality(Modality.WINDOW_MODAL);
		customStage.initOwner(btnOk.getScene().getWindow());
		customStage.setTitle("ㅊㅋㅊㅋ");
		
		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(400, 150);
		
		Button button = new Button("종료");
		button.setLayoutX(336);
		button.setLayoutY(104);
//		button.setOnAction(e->customStage.close());
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		
		
		Label label = new Label(message);
		label.setLayoutX(87);
		label.setLayoutY(33);
		label.setPrefHeight(15);
		label.setPrefWidth(290);
		
		ap.getChildren().add(button);
		ap.getChildren().add(label);

		Scene scene = new Scene(ap);
		customStage.setScene(scene);
		customStage.show();
		
		
	}
	
	
	
	
	
	
//	public void messagePopup(String message) {
//
//		// 컨테이너(HBox) 생성.
//		HBox hbox = new HBox();
//		hbox.setStyle("-fx-background-color:black; -fx-background-radius: 5;");
//		hbox.setAlignment(Pos.CENTER);
//
//		// 컨트롤(Label)
//		Label label = new Label();
//		HBox.setMargin(label, new Insets(5, 5, 5, 5));
//		label.setText(message);
//		label.setStyle("-fx-text-fill:white;");
//
//		// 컨테이너에 컨트롤 담기
//		hbox.getChildren().add(label);
//
//		// 팝업 생성. 컨테이너 담아서 팝업 호출.
//		Popup popup = new Popup();
//		popup.getContent().add(hbox);
//		popup.setAutoHide(true); // 포커스가 다른곳으로 가면 사라짐.
//		popup.show(btnOk.getScene().getWindow()); // 윈도우를 가져와야함
//
//	}

}
