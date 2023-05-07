package com.project.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberVO {
    private int memberNo;
    private String memberId;
    private String memberPw;
    private String name;
    private String address;
    private String phone;
    private int gradeNo = 0;
    private int mileage = 0; 
}
