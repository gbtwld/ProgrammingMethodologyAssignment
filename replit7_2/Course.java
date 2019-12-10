package replit7_2;

//이 클래스는 교과목을 나타낸다.
public class Course {
    // 교과목에 대한 객체 변수들을 선언한다
    protected String courseName;
    protected int attendScore;

    // 교과목의 이름을 주어진 값으로 초기화하면서 Course 객체를 생성한다.
    public Course(String courseName) {
        // 여기에 코드를 입력하세요
        this.courseName = courseName;
    }
    // 교과목의 이름을 반환한다
    public String getCourseName() {
        return courseName;
    }

    // 교과목의 출석 점수를 반환한다
    public int getattendScore() {
        // 여기에 코드를 입력하세요
        return attendScore;
    }

    // 교과목의 학점인  I를 반환한다
    public String getGrade() {
        // 여기에 코드를 입력하세요
        return getGrade();
    }

    // 교과목의 이름과 출석 점수를 반환한다
    public String toString(){
        // 여기에 코드를 입력하세요
        return "Coursename : " + courseName + ", Attendscore : " + attendScore;
    }
    // 교과목의 이름을 변경한다
    public void setCourseName(String newCourseName) {
        // 여기에 코드를 입력하세요
        courseName = newCourseName;
    }
    // 교과목의 출석 점수를 변경한다
    public void setattendScore(int newAttendScore) {
        // 여기에 코드를 입력하세요
        attendScore = newAttendScore;
    }

}
