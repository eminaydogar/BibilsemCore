package com.project;


import com.project.dto.IDto;
import com.project.dto.QuestionAnswerDto;
import com.project.dto.QuestionDto;
import com.project.utility.ObjectUtilty;

public class MainTest {

	public static void main(String[] args) {
		QuestionAnswerDto dto = new QuestionAnswerDto();
		dto.setId(2L);
		dto.setQuestion(new QuestionDto());
		IDto ref = dto;
		QuestionAnswerDto d2 = (QuestionAnswerDto) ref;
		System.out.println(d2);
            //DTOUtilty.JSONValidation(dto);

	}

}
