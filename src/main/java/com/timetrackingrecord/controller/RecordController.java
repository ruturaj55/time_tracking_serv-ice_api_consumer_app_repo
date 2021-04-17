package com.timetrackingrecord.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.timetrackingrecord.model.Record;
import com.timetrackingrecord.service.EmployeeRecordServiceProvider;

@Controller
public class RecordController {
	
	@Autowired
	EmployeeRecordServiceProvider employeeRecordServiceProvider;

	/**
	 * its a get all records function and displays all record on home page
	 * 
	 * Its get all request
	 * 
	 * method call on "/home" action request
	 * 
	 * return all records from services provider
	 */

	@GetMapping("/home")
	public String getEmployeeData(Model model) throws JsonMappingException, JsonProcessingException {
		List<Record> recList = employeeRecordServiceProvider.getAllEmployees();
		model.addAttribute("recList", recList);
		return "home";
	}

	/**
	 * its a get record with email id input
	 * 
	 * method call on "/searchbyemail" action request
	 * 
	 * return records search by email form
	 */
	@GetMapping("/searchbyemail")
	public String searchByEmailForm(Model model) {
		Record record = new Record();
		model.addAttribute("record", record);
		return "searchbyemail";
	}

	/**
	 * its a record search operation with email id input
	 * 
	 * its get record by email request to external service
	 * 
	 * method call on "/searchbyemaildata" action request
	 * 
	 * return available records list with email search
	 * 
	 * @throws JsonProcessingException
	 */
	@GetMapping("/searchbyemaildata")
	private String getEmployeeByEmail(@ModelAttribute("record") Record rec, Model model)
			throws JsonProcessingException {
		String email = rec.getEmail();
		List<Record> recList = employeeRecordServiceProvider.getRecordByEmail(email);
		if (null != recList.get(0)) {
			model.addAttribute("recList", recList);
			return "employeebyemail";
		}
		model.addAttribute("email", rec.getEmail());
		return "record_not_found";
	}

	/**
	 * its a new record form page takes input data
	 * 
	 * method call on "/newRecordForm" action request
	 * 
	 * return new record form page
	 */
	@GetMapping("/newrecord")
	public String getRecordForm(Model model) {
		Record record = new Record();
		model.addAttribute("record", record);

		return "new_record";
	}

	/**
	 * its a new record save operation
	 * its create record request (post request)
	 * method call on "/saveRecord" action request
	 * 
	 */
	@PostMapping("/saveRecord")
	public String createRecord(@ModelAttribute("record") Record record ,Model model) throws Exception {
			List<Record> recList = new ArrayList<>();
			Record resRecord =new Record();
			resRecord=employeeRecordServiceProvider.createRecord(record);
			recList.add(resRecord);
			if (!recList.isEmpty()) {
				model.addAttribute("recList", recList);
				return "record_created";

			}else {
				return "error";
			}
	}
}
