package com.sds.foodfit.model.member;

import org.apache.ibatis.annotations.Mapper;

import com.sds.foodfit.domain.MemberDetail;

@Mapper
public interface MemberDetailDAO {

	public int insert(MemberDetail memberDetail); // 등록

	public MemberDetail selectByMemberIdx(int memberIdx); // member_idx 해당하는 회원정보 가져오기
}
