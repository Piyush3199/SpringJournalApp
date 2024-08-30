package com.example.demo.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<JournalEntry> getAll(){
		return journalEntryService.getAll(); 
	}
	
	@PostMapping
	public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
		myEntry.setDate(LocalDateTime.now());
		journalEntryService.saveEntry(myEntry);
        return myEntry;
	}
	
	@GetMapping("id/{myId}")
	public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.findById(myId).orElse(null);
	}
	
	@DeleteMapping("id/{myId}")
	public boolean deletePathEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
		return true;
    }
	
	@PutMapping("id/{id}")
	public JournalEntry updateJournalById(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry) {
		JournalEntry old = journalEntryService.findById(id).orElse(null);

		if(old != null){
			old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
			old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
		}
		journalEntryService.saveEntry(old);
        return old;
    }
}
