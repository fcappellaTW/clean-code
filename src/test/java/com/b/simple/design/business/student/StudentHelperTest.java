package com.b.simple.design.business.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.b.simple.design.business.student.StudentHelper.Grade;

import com.b.simple.design.business.student.StudentHelper.Qualification;

public class StudentHelperTest {

	StudentHelper helper = new StudentHelper();

	@Test
	public void testIsGradeB() {
		assertEquals(false,helper.isGradeB(30, false));
		assertEquals(false,helper.isGradeB(50, false));
		assertEquals(true,helper.isGradeB(51, false));
		assertEquals(true,helper.isGradeB(80, false));
		assertEquals(false,helper.isGradeB(81, false));

		assertEquals(false,helper.isGradeB(30, true));
		assertEquals(false,helper.isGradeB(50, true));
		assertEquals(true,helper.isGradeB(51, true));
		assertEquals(true,helper.isGradeB(80, true));
		assertEquals(true,helper.isGradeB(81, true));
		assertEquals(true,helper.isGradeB(89, true));
		assertEquals(true,helper.isGradeB(90, true));
		assertEquals(false,helper.isGradeB(91, true));

	}

	@Test
	public void testGetGrade() {

		assertEquals(Grade.A,helper.getGrade(99, false));
		assertEquals(Grade.A,helper.getGrade(91, false));
		assertEquals(Grade.B,helper.getGrade(85, false));
		assertEquals(Grade.B,helper.getGrade(51, false));
		assertEquals(Grade.C,helper.getGrade(50, false));
		assertEquals(Grade.C,helper.getGrade(45, false));

		assertEquals(Grade.A,helper.getGrade(99, true));
		assertEquals(Grade.A,helper.getGrade(96, true));
		assertEquals(Grade.B,helper.getGrade(89, true));
		assertEquals(Grade.B,helper.getGrade(56, true));
		assertEquals(Grade.C,helper.getGrade(55, true));
		assertEquals(Grade.C,helper.getGrade(50, true));
		assertEquals(Grade.C,helper.getGrade(45, true));

		//Will fail due to wrong implementation
		//assertEquals("B",helper.getGrade(95, true));
		//assertEquals("B",helper.getGrade(90, false));
	}

	@Test
	public void testWillQualifyForQuiz() {
		assertEquals(Qualification.NO,helper.willQualifyForQuiz(15, 25, false));
		assertEquals(Qualification.NO,helper.willQualifyForQuiz(20, 20, false));
		assertEquals(Qualification.MAYBE,helper.willQualifyForQuiz(21, 21, false));
		assertEquals(Qualification.MAYBE,helper.willQualifyForQuiz(25, 25, false));
		assertEquals(Qualification.MAYBE,helper.willQualifyForQuiz(79, 79, false));
		assertEquals(Qualification.YES,helper.willQualifyForQuiz(80, 30, false));
		assertEquals(Qualification.YES,helper.willQualifyForQuiz(85, 30, false));
		assertEquals(Qualification.YES,helper.willQualifyForQuiz(30, 90, false));

		assertEquals(Qualification.NO,helper.willQualifyForQuiz(15, 25, true));
		assertEquals(Qualification.NO,helper.willQualifyForQuiz(20, 20, true));
		assertEquals(Qualification.NO,helper.willQualifyForQuiz(21, 21, true));
		assertEquals(Qualification.NO,helper.willQualifyForQuiz(25, 25, true));
		assertEquals(Qualification.MAYBE,helper.willQualifyForQuiz(27, 34, true));
		assertEquals(Qualification.MAYBE,helper.willQualifyForQuiz(79, 79, true));
		assertEquals(Qualification.MAYBE,helper.willQualifyForQuiz(80, 30, true));
		assertEquals(Qualification.MAYBE,helper.willQualifyForQuiz(80, 84, true));
		assertEquals(Qualification.YES,helper.willQualifyForQuiz(85, 30, true));
		assertEquals(Qualification.YES,helper.willQualifyForQuiz(30, 90, true));

	}

}
