package com.example.demo.journalApp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Document(collection = "users")
@Getter
@Setter
//Can use @Data to avoid indivisual getters and setters
public class User {
	
	@Id // Created a primary key
	private ObjectId id;
    @Indexed(unique = true)
    @NonNull
	private String userName;
    @NonNull
	private String password;
    
	//Creatign reference to journal entries
    @DBRef
	private List<JournalEntry> journalEntries = new ArrayList<>();
}
