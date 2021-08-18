package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.BoardDto;
import com.example.demo.dao.Dao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson; 
@RestController
public class Controller {
	@Autowired
	private Dao dao;

	@GetMapping(path = "/helloWorld")
	public String helloWorld() {
		return dao.selectName();
	}
	@GetMapping(path = "/getBoardData")
	public JSONArray getBoardData(HttpServletRequest req ,Model model) {
		List<Map<String, Object>> boardList = dao.getBoardList();
		JSONArray jsonArray = new JSONArray();

		for (Map<String, Object> map : boardList) {
			jsonArray.add(convertMapToJson(map));
		}
		return jsonArray;

	}
	@SuppressWarnings({ "unchecked" })

	public static JSONObject convertMapToJson(Map<String, Object> map) {

		JSONObject json = new JSONObject();

		for (Map.Entry<String, Object> entry : map.entrySet()) {

			String key = entry.getKey();

			Object value = entry.getValue();

			// json.addProperty(key, value);

			json.put(key, value);

		}

		return json;

	}
	
	
}
