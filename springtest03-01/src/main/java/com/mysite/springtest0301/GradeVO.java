package com.mysite.springtest0301;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeVO {
	
	private String id;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private double avg;
	private String grade;

	public void processGrade() {
		tot = kor + eng + math;
		avg = tot / 3;
		switch ((int) this.avg / 10) {
		case 10:
		case 9:
			grade = "수";
			break;
		case 8:
			grade = "우";
			break;
		case 7:
			grade = "미";
			break;
		case 6:
			grade = "양";
			break;
		default:
			grade = "가";
			break;

		}
	}
}
