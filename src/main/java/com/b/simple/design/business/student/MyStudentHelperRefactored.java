package com.b.simple.design.business.student;
public class MyStudentHelperRefactored {

	public static final int MAX_B_GRADE = 80;
	public static final int MIN_B_GRADE = 51;
	public static final int MIN_A_GRADE = 91;
	public static final int MAX_A_GRADE = 100;
	public static final String YES = "YES";
	public static final String NO = "NO";
	public static final String MAYBE = "MAYBE";
	public static final int NOT_GOOD_MARK = 20;

	/* PROBLEM 1 */
	/*
	* You get a grade B if marks are between 51 and 80 (both inclusive). Except for Maths where the upper limit is increased by 10.
	*/
	public boolean isGradeB(int marks, boolean isMaths) {

		if (MIN_B_GRADE <= marks && marks <= MAX_B_GRADE + getUpperLimit(isMaths)) {
			return true;
		} else {
			return false;
		}
	}

	private int getUpperLimit(boolean isMaths) {

		if (isMaths) {
			return 10;
		} else {
			return 0;
		}
	}

	/* PROBLEM 2 */
	/*
	You are awarded a grade based on your marks.
	Grade A = 91 to 100, Grade B = 51 to 90, Otherwise Grade C
	Except for Maths where marks to get a Grade are 5 higher than required for other subjects.
	*/

	public String getGrade(int mark, boolean isMaths) {

		if (isGradeA(mark, isMaths)) {
			return "A";
		} else if (isBGrade(mark, isMaths)) {
			return "B";
		} else {
			return "C";
		}
	}

	private int getLowerLimit(boolean isMaths) {

		if (isMaths) {
			return 5;
		} else {
			return 0;
		}
	}

	private boolean isGradeA(int mark, boolean isMaths) {

		if (MIN_A_GRADE + getLowerLimit(isMaths) <= mark && mark <= MAX_A_GRADE) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isBGrade(int mark, boolean isMaths) {

		if (MIN_B_GRADE + getLowerLimit(isMaths) <= mark && mark < MIN_A_GRADE + getLowerLimit(isMaths)) {
			return true;
		} else {
			return false;
		}
	}

    /*  PROBLEM 3
     * You and your Friend are planning to enter a Subject Quiz.
     * However, there is a marks requirement that you should attain to qualify.
     * 
     * Return value can be YES, NO or MAYBE.
     * 
     * YES If either of you are very good at the subject(has 80 or more marks)
     * However, there is an exception that if either of you is not good in the subject(20 or less marks), it is NO.
     * In all other conditions, return MAYBE.
     * 
     * However, the definition for good and not good are 5 marks higher if the subject is Mathematics.
     * 
     * marks1 - your marks
     * marks2 - your friends marks
    */
        
    public String willQualifyForQuiz(int myMark, int friendMark, boolean isMaths) {

		if (isGoodAtSubject(myMark, isMaths) || isGoodAtSubject(friendMark, isMaths)) {
			return YES;
		} else if (isBadAtSubject(myMark, isMaths) || isBadAtSubject(friendMark, isMaths)) {
			return NO;
		} else {
			return MAYBE;
		}
    }

	private boolean isGoodAtSubject(int mark, boolean isMaths) {

		if (MAX_B_GRADE + getLowerLimit(isMaths) <= mark) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isBadAtSubject(int mark, boolean isMaths) {

		if (mark <= NOT_GOOD_MARK + getLowerLimit(isMaths)) {
			return true;
		} else {
			return false;
		}
	}
}