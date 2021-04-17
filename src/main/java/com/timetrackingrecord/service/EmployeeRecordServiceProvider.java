package com.timetrackingrecord.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timetrackingrecord.model.Record;

@RestController
public class EmployeeRecordServiceProvider {

	@Autowired
	RestTemplate restTemplate;

	public EmployeeRecordServiceProvider(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/**
	 * its a get all record (get all records call)
	 * 
	 * method call on "/searchbyemaildata" action request
	 * 
	 * Service provider url- $ curl "http://192.168.99.100:8080/records?offset=0&length=10"
	 * 
	 * return available record list with email search
	 * 
	 * @throws JsonProcessingException
	 */

	public List<Record> getAllEmployees() throws JsonMappingException, JsonProcessingException {
		String GET_ALL_RECORDS_URL = "http://localhost:8080/records";
		// String GET_ALL_RECORDS_URL = "http://192.168.99.100:8080/records";

		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		// HttpEntity<Record> entity = new HttpEntity<Record>(header);
		Object forObject = restTemplate.getForObject(GET_ALL_RECORDS_URL, Object.class);
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
	 * Service provider url- $ curl "http://192.168.99.100:8080/records?email=aliriza.saral@gmail.com&length=10"
	 * 
	 * return available record list with email search
	 * 
	 * @throws JsonProcessingException
	 */
	public List<Record> getRecordByEmail(String email) throws JsonProcessingException {
		String GET_RECORD_BY_EMAIL = "http://localhost:8080/records?" + "email=" + email;
		// String GET_RECORD_BY_EMAIL = "http://192.168.99.100/records?" + "email=" +
		// email;
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		// HttpEntity<Record> entity = new HttpEntity<Record>(header);
		Object forObject = restTemplate.getForObject(GET_RECORD_BY_EMAIL, Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(forObject);
		System.out.println(jsonStr);
		Record[] emp2 = objectMapper.readValue(jsonStr, Record[].class);
		List<Record> empList = new ArrayList<Record>(Arrays.asList(emp2));
		return empList;

	}

	/**
	 * its create new record call with given input data
	 * 
	 * method call on "/saveRecord" action request
	 * 
	 * Service provider url- $ curl -X POST --data "email=john.doe@gmail.com" --data "start=28.11.2016 08:00" --data "end=28.11.2016 09:00" "http://192.168.99.100:8080/records"
	 * 
	 * return successfully created record
	 * 
	 */

	public Record createRecord(Record records) {
		String POST_RECORD_URL = "http://localhost:8080/records";
		// String POST_RECORD_URL = "http://192.168.99.100:8080/records";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("start", records.getStart());
		map.add("end", records.getEnd());
		map.add("email", records.getEmail());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<Record> response = restTemplate.postForEntity(POST_RECORD_URL, request, Record.class);

		System.out.println(response.getBody());

		return records;

	}

}
