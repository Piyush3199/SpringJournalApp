package com.example.demo.journalApp.entity;

import java.sql.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Document(collection = "journal_entries")
@Getter
@Setter
//Can use @Data to avoid indivisual getters and setters
public class JournalEntry {
	
	@Id // Created a primary key
	private ObjectId id;
	private String title;
	private String content;
	private LocalDateTime date;
	
	
}
