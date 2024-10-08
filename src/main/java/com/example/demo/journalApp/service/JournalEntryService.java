package com.example.demo.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.journalApp.entity.JournalEntry;
import com.example.demo.journalApp.entity.User;
import com.example.demo.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.*;


@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    //Used annotation to make the method transactional, so that if any exception occurs, the transaction is rolled back
    public void saveEntry(JournalEntry journalEntry,String userName){
        User user = userService.findByUserName(userName);
        //Saving in journalEntries
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry savedEnrty = journalEntryRepository.save(journalEntry);
        //Saving in journalEntries of user
        user.getJournalEntries().add(savedEnrty);
        userService.saveEntry(user);
    }
    
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry); 
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id,String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);

    }
}
 