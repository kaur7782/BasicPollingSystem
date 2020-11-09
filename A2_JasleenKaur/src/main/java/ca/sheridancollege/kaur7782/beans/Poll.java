package ca.sheridancollege.kaur7782.beans;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Poll {
	
	//fields
	private Long pollId;
	private int id;
	private String title;
	private String[] titles= {"Describe in ONE word", 
			"Any favourites?", 
			"Random Answer!", 
			"Would you rather...", 
			"Strangest things"};
	private String[] question;
	private String[] questions= {"In one word how would you describe your past week/month?",
			"What is your favourite ice cream?", 
			"Have you ever answered a poll question with a random answer?",
			"Would you rather be able to", 
			"What is the strangest thing you did while attending an online class?"};
	private String answer;
	private String[] answer1= {"Heavy", "Mint", "Yes", "See Future", "Brushed Teeth"};
	private String[] answer2= {"Fun", "Chocolate","No", "Read Minds", "Watched Netflix"};
	private String[] answer3= {"Intense", "Strawberry", "Ice Cream", "Teleport Anywhere", "Others but my lips are sealed ;)"};
	private int[] votes1={20, 3, 10, 15, 8};
	private int[] votes2={5, 25, 12, 8, 22};
	private int[] votes3={15, 12, 18, 17, 10};
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate date;
	

}
