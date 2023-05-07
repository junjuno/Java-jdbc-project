package com.project.shop.controller;

import com.project.shop.service.GradeService;
import com.project.shop.vo.GradeVO;

public class GradeController {


    private GradeService service = new GradeService();
    public static GradeController instance = new GradeController();

  
    public void insertGrade(GradeVO gradeVO) throws Exception {
        service.insertGrade(gradeVO);
    }


    public void deleteGradeByGradeNo(int gradeNo) throws Exception {
        service.deleteGradeByGradeNo(gradeNo);
    }


    public GradeVO getGradeByGradeNo(int gradeNo) throws Exception {
        return service.getGradeByGradeNo(gradeNo);
    }


    public void modifyGradeDCByGradeNo(int gradeNo, int gradeDC) throws Exception {
        service.modifyGradeDCByGradeNo(gradeNo, gradeDC);
    }
}
