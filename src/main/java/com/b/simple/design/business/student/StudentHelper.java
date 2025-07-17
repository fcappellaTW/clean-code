package com.b.simple.design.business.student;
public class StudentHelper {

	private static final int LOWER_LIMIT_FOR_A_GRADE_B = 51;
	private static final int UPPER_LIMIT_FOR_A_GRADE_B = 80;
	private static final int EXTRA_MARKS_FOR_MATHS = 10;

	private static final int LOWER_LIMIT_FOR_A_GRADE = 91;
	private static final int LOWER_LIMIT_FOR_B_GRADE = 51;
	private static final int EXTRA_MARKS_FOR_MATHS_GRADE = 5;

	private static final int LOWER_LIMIT_FOR_QUALIFICATION = 20;
	private static final int UPPER_LIMIT_FOR_QUALIFICATION = 80;
	private static final int EXTRA_MARKS_FOR_MATHS_QUALIFICATION = 5;

	public enum Grade {
		A, B, C;
	}

	public enum Qualification {
		YES, NO, MAYBE;
	}

	/* PROBLEM 1 */
	/*
	* You get a grade B if marks are between 51 and 80 (both inclusive). Except for Maths where the upper limit is increased by 10.
	*/
	public boolean isGradeB(int marks, boolean isMaths) {
		int extraLimit = isMaths ? EXTRA_MARKS_FOR_MATHS : 0;
		int upperLimit = UPPER_LIMIT_FOR_A_GRADE_B + extraLimit;

		return marks >= LOWER_LIMIT_FOR_A_GRADE_B && marks <= upperLimit;
	}

	/* PROBLEM 2 */
	/*
	You are awarded a grade based on your marks.
	Grade A = 91 to 100, Grade B = 51 to 90, Otherwise Grade C
	Except for Maths where marks to get a Grade are 5 higher than required for other subjects.
	*/

	public Grade getGrade(int mark, boolean isMaths) {
		int extraLimit = isMaths ? EXTRA_MARKS_FOR_MATHS_GRADE : 0;

		if (mark >= LOWER_LIMIT_FOR_A_GRADE + extraLimit)
			return Grade.A;

		if (mark >= LOWER_LIMIT_FOR_B_GRADE + extraLimit)
			return Grade.B;

		return Grade.C;
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

    public Qualification willQualifyForQuiz(int marks1, int marks2, boolean isMaths) {
		int extraLimit = isMaths ? EXTRA_MARKS_FOR_MATHS_QUALIFICATION : 0;
		int lowerLimit = LOWER_LIMIT_FOR_QUALIFICATION + extraLimit;
		int upperLimit = UPPER_LIMIT_FOR_QUALIFICATION + extraLimit;

		if (isNotGood(marks1, lowerLimit) || isNotGood(marks2, lowerLimit))
			return Qualification.NO;

		if (isGood(marks1, upperLimit) || isGood(marks2, upperLimit))
			return Qualification.YES;

		return Qualification.MAYBE;
    }

	private boolean isNotGood(int marks, int lowerLimit) {
		return marks <= lowerLimit;
	}

	private boolean isGood(int marks, int upperLimit) {
		return marks >= upperLimit;
	}

}