package com.example.demo.journalApp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Document(collection = "journal_entries")
@Getter
@Setter
@NoArgsConstructor
//Can use @Data to avoid indivisual getters and setters
public class JournalEntry {
	
	@Id // Created a primary key
	private ObjectId id;
	@NonNull
	private String title;
	private String content;
	private LocalDateTime date;
	
	
}
