package com.example.demo.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.journalApp.entity.*;
import com.example.demo.journalApp.service.JournalEntryService;

import java.util.*;
import java.time.LocalDateTime;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;




@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
	
    @Autowired
    private JournalEntryService journalEntryService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<JournalEntry>list =  journalEntryService.getAll();
		if(list != null && !list.isEmpty()){
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);   
	}
	
	@PostMapping
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
		try{
			myEntry.setDate(LocalDateTime.now());
			journalEntryService.saveEntry(myEntry);
        	return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
        
	}
	
	@GetMapping("id/{myId}")
	public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> entry = journalEntryService.findById(myId);
		if(entry.isPresent()){
			return new ResponseEntity<>(entry.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@DeleteMapping("id/{myId}")
	public ResponseEntity<?> deletePathEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@PutMapping("id/{id}")
	public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry) {
		JournalEntry old = journalEntryService.findById(id).orElse(null);

		if(old != null){
			old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
			old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
			return new ResponseEntity<>(old, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
    }
}
