package com.management.student.studentresult.controller;

import com.management.student.studentresult.service.PdfResultDownloadService;
import com.management.student.studentresult.vo.QueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.Query;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private PdfResultDownloadService fileDownloadService;

    @GetMapping("/result/pdf")
    public void downloadResultPdf(HttpServletResponse response) throws IOException, ParseException {
        QueryVO queryVO = new QueryVO();
        queryVO.setRollNumber("MT2020093");
        fileDownloadService.exportFile(response, queryVO);
    }

}
