package com.timetrackingrecord.controller;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timetrackingrecord.model.Record;

@RestController

public class RecordRestController {

	
	@Autowired
	RestTemplate restTemplate;

	public RecordRestController(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	/**
	 * its a get all record operation
	 * 
	 * method call on "/searchbyemaildata" action request
	 * 
	 * return available record list with email search
	 * 
	 * @throws JsonProcessingException
	 */
	public List<Record> getAllEmployees() throws JsonMappingException, JsonProcessingException {
		String GET_EMPLOYEES = "http://localhost:8080/records";
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Record> entity = new HttpEntity<Record>(header);
		Object forObject = restTemplate.getForObject(GET_EMPLOYEES, Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(forObject);
		System.out.println(jsonStr);
		Record[] Record2 = objectMapper.readValue(jsonStr, Record[].class);
		List<Record> recordList = new ArrayList<Record>(Arrays.asList(Record2));
		return recordList;

	}

	/**
	 * its a record search operation with email id input
	 * 
	 * method call on "/searchbyemaildata" action request
	 * 
	 * return available record list with email search
	 * 
	 * @throws JsonProcessingException
	 */
	public List<Record> getRecordByEmail(String email) throws JsonProcessingException {
		String GET_EMPLOYEE_EMAIL = "http://localhost:8080/records?" + "email=" + email;
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Record> entity = new HttpEntity<Record>(header);
		Object forObject = restTemplate.getForObject(GET_EMPLOYEE_EMAIL, Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(forObject);
		System.out.println(jsonStr);
		Record[] emp2 = objectMapper.readValue(jsonStr, Record[].class);
		List<Record> empList = new ArrayList<Record>(Arrays.asList(emp2));
		return empList;
	}

//	@PostMapping
//	public void saveRecord(@RequestBody Record records) throws Exception {
//		String POST_EMPLOYEE_RECORD = "http://localhost:8080/records";
//		HttpHeaders header = new HttpHeaders();
//		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		HttpEntity<Record> entity=new HttpEntity<Record>(records,header);
//		try {
//			ResponseEntity<String> postForEntity = restTemplate.postForEntity(POST_EMPLOYEE_RECORD, records, String.class);
//			System.out.println(postForEntity.getBody());
//			ResponseEntity<String> exchange = restTemplate.exchange(POST_EMPLOYEE_RECORD, HttpMethod.POST, entity, String.class);
//			System.out.println(exchange.getBody());
//			ResponseEntity<Record> responseEntity=restTemplate.postForEntity(POST_EMPLOYEE_RECORD, entity, Record.class);
//			System.out.println(responseEntity.getBody());
//		} catch (Exception e) {
//			throw new Exception("Record Not saved");
//		}
//		
//
//	}

	/**
	 * its a record save call with given inpu put
	 * 
	 * method call on "/searchbyemaildata" action request
	 * 
	 * return available record list with email search
	 * 
	 * @throws JsonProcessingException
	 */
	public void saveRecord(@RequestBody Record records) throws Exception {
		URL url = new URL("http://localhost:8080/records");
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("POST");
		http.setDoOutput(true);
		http.setRequestProperty("Content-Type", "application/json");
		String data = getRequestData(records);
		// String data = "email=john.doe@gmail.com&start=28.11.2016 08:00&end=28.11.2016
		// 09:00";

		byte[] out = data.getBytes(StandardCharsets.UTF_8);

		OutputStream stream = http.getOutputStream();
		stream.write(out);
		System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
		http.disconnect();

	}

	/**
	 * 
	 * return formated request data for post method operation
	 * 
	 */
	private String getRequestData(Record record) {
		String requestData = "";
		char c = '"';
		StringBuilder sb = new StringBuilder(requestData);
		sb.append(c + "email=" + record.getEmail()).append("&start=" + record.getStart())
				.append("&end=" + record.getEnd() + c);
		return sb.toString();
	}

}
