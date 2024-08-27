package com.example.demo.journalApp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.journalApp.controller.entity.*;
import java.util.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;




@RestController
@RequestMapping("/journal")
public class JournalEntryController {
	
	private Map<Long, JournalEntry> journalEntries = new HashMap<>();
	private static final Logger logger = LogManager.getLogger(JournalEntryController.class);
	@GetMapping
	public List<JournalEntry> getAll(){
		logger.info("kajshfkajsfhdkajhsfdkajshdfkjahsdfkajshdfkjashdfkjahsdfkh");
		return new ArrayList<>(journalEntries.values());
	}
	
	@PostMapping
	public boolean createEntry(@RequestBody JournalEntry myEntry) {
		journalEntries.put(myEntry.getId(), myEntry);
		return true;
	}
	
	@GetMapping("id/{myId}")
	public JournalEntry getJournalEntryById(@PathVariable Long myId) {
		return journalEntries.get(myId);
		
	}
	
	@DeleteMapping("id/{myId}")
	public JournalEntry deletePathEntryById(@PathVariable Long myId) {
		return journalEntries.remove(myId);
	}
	
	@PutMapping("id/{id}")
	public JournalEntry updateJournalById(@PathVariable Long id,@RequestBody JournalEntry myEntry) {
		return journalEntries.put(id, myEntry);
	}
}
