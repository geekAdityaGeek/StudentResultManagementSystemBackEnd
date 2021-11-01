package com.management.student.studentresult.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarksVO {
    private String rollNo;
    private String subjectCode;
    private String subjectName;
    private int year;
    private int term;
    private int totalMarks;
    private int marksObtained;
    private String grade;
}
