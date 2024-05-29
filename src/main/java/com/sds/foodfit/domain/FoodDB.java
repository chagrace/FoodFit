package com.sds.foodfit.domain;

import lombok.Data;

@Data
<<<<<<< HEAD
public class FoodDB {	//식품영양성분DB 오픈API에서 가져오는 데이터를 담을 객체
	public int FOOD_CD;	// 넘버링
	public String DESC_KOR;	// 음식명 (식품이름)
	
	//중량 생략 100g 당으로 일괄 산출했음 (DAO 에서 계산필요)
	
	public int NUTR_CONT1;	// 열량(kcal) 
	public int NUTR_CONT2;	// 탄수화물(g)
	public int NUTR_CONT3;	// 단백질(g)
	public int NUTR_CONT4;	// 지방(g)
	public int NUTR_CONT6;	// 나트륨(mg) - 당뇨질환자 대상

=======
public class FoodDB { // 식품영양성분DB 오픈API에서 가져오는 데이터를 담을 객체
	public int foodIdx; // 넘버링
	public String foodName; // 음식이름 : DESC_KOR
	public int kcal; // 열량 : NUTR_CONT1
	public int carbohydrate; // 탄수화물 : NUTR_CONT2
	public int protain; // 단백질(g) : NUTR_CONT3
	public int fat; // 지방(g) : NUTR_CONT4
	public int sugar; // 당류(g) : NUTR_CONT5
	public int sodium; // 나트륨(mg) : NUTR_CONT6
>>>>>>> main

}
