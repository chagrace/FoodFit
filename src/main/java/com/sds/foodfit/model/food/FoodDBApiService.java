package com.sds.foodfit.model.food;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sds.foodfit.domain.FoodDB;
import com.sds.foodfit.exception.ApiLoadException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FoodDBApiService {

	@Autowired
	private String key;

	private final String urlFrame = "https://openapi.foodsafetykorea.go.kr/api/" + key + "/I2790/json/";
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	public FoodDBApiService(RestTemplate restTemplate, ObjectMapper objectMapper) {
		// 맵핑에 쓸 템플릿과 맵퍼, 직접 의존성 주입
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	public List<FoodDB> getFoodDB() {
		List<FoodDB> foodList = new ArrayList<>();
		boolean exploreDB = true;
		int startIndex = 1;
		int endIndex = 5;

		while (exploreDB) {
			String callUrl = urlFrame + startIndex + "/" + endIndex;
			String responseJson = restTemplate.getForObject(callUrl, String.class);

			try {
				JsonNode root = objectMapper.readTree(responseJson);
				JsonNode dataNode = root.get("data");
				if (dataNode != null && dataNode.isArray()) {
					for (JsonNode item : dataNode) {
						FoodDB food = new FoodDB();
						food.setFOOD_CD(item.get("FOOD_CD").asInt());			// 코드
						food.setDESC_KOR(item.get("DESC_KOR").asText());		// 음식명
						food.setNUTR_CONT1(item.get("NUTR_CONT1").asInt());		// 열량
						food.setNUTR_CONT2(item.get("NUTR_CONT2").asInt());		// 탄수화물
						food.setNUTR_CONT3(item.get("NUTR_CONT3").asInt());		// 단백질
						food.setNUTR_CONT4(item.get("NUTR_CONT4").asInt());		// 지방
						food.setNUTR_CONT6(item.get("NUTR_CONT6").asInt());		// 나트륨
						foodList.add(food);
						log.debug("5개만 불렀을때?"+foodList);
					}
					// 다음 페이지가 있는지 확인
					exploreDB = root.get("exploreDB").asBoolean();
					endIndex++;
				} else {
					exploreDB = false; // 데이터가 없으면 더 이상 요청하지 않음
				}

			} catch (JsonProcessingException e) {
				exploreDB = false;
				throw new ApiLoadException("API 데이터 읽어오기 실패 ;" + e);
			}
		}

		return foodList;
	}
}
