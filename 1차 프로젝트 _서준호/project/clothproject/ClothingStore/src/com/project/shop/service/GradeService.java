package com.project.shop.service;

import java.util.Objects;

import com.project.shop.dao.GradeDAO;
import com.project.shop.exception.DataNotFoundException;
import com.project.shop.vo.GradeVO;

public class GradeService {
    private final GradeDAO dao = new GradeDAO();


    public void insertGrade(GradeVO gradeVO) throws Exception {
        dao.insertGrade(gradeVO);
    }


    public void deleteGradeByGradeNo(int gradeNo) throws Exception {

        if (Objects.isNull(getGradeByGradeNo(gradeNo))) {
            throw new DataNotFoundException("해당 권한이 없습니다.");
        }
        dao.deleteGradeByGradeNo(gradeNo);
    }


    public GradeVO getGradeByGradeNo(int gradeNo) throws Exception {
        return dao.getGradeByGradeNo(gradeNo);
    }


    public void modifyGradeDCByGradeNo(int gradeNo, int gradeDC) throws Exception {

        if (Objects.isNull(getGradeByGradeNo(gradeNo))) {
            throw new DataNotFoundException("해당 권한이 없습니다.");
        }
        dao.modifyGradeDCByGradeNo(gradeNo, gradeDC);
    }
}
