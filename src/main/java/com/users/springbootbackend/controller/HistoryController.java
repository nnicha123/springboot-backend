package com.users.springbootbackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.springbootbackend.exception.ResourceNotFoundException;
import com.users.springbootbackend.model.Employee;
import com.users.springbootbackend.model.History;

import com.users.springbootbackend.repository.HistoryRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class HistoryController {
	 
	@Autowired
	private HistoryRepository historyRepository;
	
	@GetMapping("/histories")
	public List<History> getAllHistories(){
		return historyRepository.findAll();
	}
	
	@PostMapping("/histories")
	public History createHistory(@RequestBody History history) {
		return historyRepository.save(history);
	}
	
	@GetMapping("/histories/{id}")
	public ResponseEntity<History> getHistoryById(@PathVariable Long id){
		History history = historyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("History does not exist with id: "+id));
		return ResponseEntity.ok(history);
	}
	
	@PutMapping("/histories/{id}")
	public ResponseEntity<History> updateHistory(@PathVariable Long id, @RequestBody History historyDetails){
		History history = historyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("History does not exist with id: "+id));
		history.setFirstName(historyDetails.getFirstName());
		history.setLastName(historyDetails.getLastName());
		history.setEmailId(historyDetails.getEmailId());
		history.setDate(historyDetails.getDate());
		history.setAction(historyDetails.getAction());
		
		History updatedHistory = historyRepository.save(history);
		return ResponseEntity.ok(updatedHistory);
	}
	
	@DeleteMapping("/histories/{id}")
	public  ResponseEntity<Map<String, Boolean>> deleteHistory(@PathVariable Long id){
		History history = historyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("History does not exist with id: "+id));
		historyRepository.delete(history);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}

}
