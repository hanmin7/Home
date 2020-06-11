package question_java;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Quiz {
	private SimpleIntegerProperty num;
	private SimpleStringProperty quiz;
	private SimpleStringProperty select1;
	private SimpleStringProperty select2;
	private SimpleStringProperty select3;
	private SimpleIntegerProperty r1;
	private SimpleIntegerProperty r2;
	private SimpleIntegerProperty r3;
	private SimpleStringProperty answer;
	private SimpleStringProperty answerlist;

	public Quiz(int num, String quiz, String select1, String select2, String select3, int r1, int r2, int r3,
			String answer, String answerlist) {
		super();
		this.num = new SimpleIntegerProperty(num);
		this.quiz = new SimpleStringProperty(quiz);
		this.select1 = new SimpleStringProperty(select1);
		this.select2 = new SimpleStringProperty(select2);
		this.select3 = new SimpleStringProperty(select3);
		this.r1 = new SimpleIntegerProperty(r1); 
		this.r2 = new SimpleIntegerProperty(r2);
		this.r3 = new SimpleIntegerProperty(r3);
		this.answer = new SimpleStringProperty(answer);
		this.answerlist = new SimpleStringProperty(answerlist);

	} // num

	public void setNum(int num) {
		this.num.set(num);
	}

	public int getNum() {
		return this.num.get();
	}

	public SimpleIntegerProperty numProperty() {
		return this.num;

	} // quiz

	public void setQuiz(String quiz) {
		this.quiz.set(quiz);
	}

	public String getQuiz() {
		return this.quiz.get();
	}

	public SimpleStringProperty quizProperty() {
		return this.quiz;
	}

	// select1
	public void setSelect1(String select1) {
		this.select1.set(select1);
	}

	public String getSelect1() {
		return this.select1.get();
	}

	public SimpleStringProperty select1Property() {
		return this.select1;
	}

	// select2
	public void setSelect2(String select2) {
		this.select2.set(select2);
	}

	public String getSelect2() {
		return this.select2.get();
	}

	public SimpleStringProperty select2Property() {
		return this.select2;

	} // select3

	public void setSelect3(String select3) {
		this.select3.set(select3);
	}

	public String getSelect3() {
		return this.select3.get();
	}

	public SimpleStringProperty select3Property() {
		return this.select3;
	}

	// r1
	public void setR1(int r1) {
		this.r1.set(r1);
	}

	public int getR1() {
		return this.r1.get();
	}

	public SimpleIntegerProperty r1Property() {
		return this.r1;
	}

	// r2
	public void setR2(int r2) {
		this.r2.set(r2);
	}

	public int getR2() {
		return this.r2.get();
	}

	public SimpleIntegerProperty r2Property() {
		return this.r2;
	}

	// r3
	public void setR3(int r3) {
		this.r3.set(r3);
	}

	public int getR3() {
		return this.r3.get();
	}

	public SimpleIntegerProperty r3Property() {
		return this.r3;
	}

	// answer
	public void setAnswer(String answer) {
		this.answer.set(answer);
	}

	public String getAnswer() {
		return this.answer.get();
	}

	public SimpleStringProperty answerProperty() {
		return this.answer;
	}

	// answerlist
	public void setAnswerlist(String answerlist) {
		this.answerlist.set(answerlist);
	}

	public String getAnswerlist() {
		return this.answerlist.get();
	}

	public SimpleStringProperty answerlistProperty() {
		return this.answerlist;
	}

}