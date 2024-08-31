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
import com.example.demo.journalApp.service.UserService;


import java.util.*;
import java.time.LocalDateTime;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;




@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
	
    @Autowired
    private JournalEntryService journalEntryService;
	
	@Autowired
	private UserService userService;

	/**
	 * @param userName
	 * @return
	 */
	@GetMapping("{userName}")
	public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
		try{
			User user = userService.findByUserName(userName);
			List<JournalEntry>list =  user.getJournalEntries();
			if(list != null && !list.isEmpty()){
				return new ResponseEntity<>(list,HttpStatus.OK);
			}
		}catch(Exception e){	
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}  
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 
	}
	
	@PostMapping("{userName}")
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
		try{
			journalEntryService.saveEntry(myEntry,userName);
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
	
	@DeleteMapping("id/{userName}/{myId}")
	public ResponseEntity<?> deletePathEntryById(@PathVariable ObjectId myId,@PathVariable String userName) {
        journalEntryService.deleteById(myId,userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@PutMapping("id/{userName}/{id}")
	public ResponseEntity<?> updateJournalById(
			@PathVariable ObjectId id,
			@RequestBody JournalEntry newEntry,
			@PathVariable String userName
		) {	
		JournalEntry old = journalEntryService.findById(id).orElse(null);

		if(old != null){
			old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
			old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
			journalEntryService.saveEntry(old);
			return new ResponseEntity<>(old, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
    }
}
