/* PollController.java
 * A2_JasleenKaur
 * author: Jasleen Kaur (991565618)
 */
package ca.sheridancollege.kaur7782.controllers;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.kaur7782.beans.Poll;
import ca.sheridancollege.kaur7782.database.DatabaseAccess;

@Controller
public class PollController {
	
	List<Poll> pollList=new CopyOnWriteArrayList<Poll>();
	
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("poll", new Poll());
		model.addAttribute("pollList", pollList);
		
		return "index";
	}// end of index method

	// selectPoll method gets user's choice of poll based on title and takes the user to the playPage
	@PostMapping("/selectPoll")
	public String selectPoll(Model model, @ModelAttribute Poll poll, @RequestParam String title) {
		
		//Get the pollList
		da.getPollList();
		
		//add pollchoice to the list
		pollList.add(poll);
		
		model.addAttribute("pollList", pollList);
		
		//
		poll.setId(getIndexOf(poll.getTitles(), poll.getTitle()));
		
		return "playPage";
	}// end of selectPoll method
	

	// playPoll method gets the user's answer for the poll and displays the result page
	@PostMapping("/playPoll")
	public String playPoll(Model model, @ModelAttribute Poll poll, @RequestParam String answer) {
		
		// setting id to -1; id is a temp variable I've used to access the values of the array
		poll.setId(-1);
		
		int temp;
		
		/* Check which element of answer1, answer2, answer3 matches with the user's selction and 
		 * and set the the value of id (temp variable) according to the element's index
		 */
		
		if(search(poll.getAnswer1(), poll.getAnswer())==true) {
			poll.setId(getIndexOf(poll.getAnswer1(), poll.getAnswer()));
			temp=poll.getVotes1()[poll.getId()];
			temp++;
		
		}
		else if(search(poll.getAnswer2(), poll.getAnswer())==true) {
			poll.setId(getIndexOf(poll.getAnswer2(), poll.getAnswer()));
			temp=poll.getVotes2()[poll.getId()];
			temp++;
			//Array.set(poll.getVotes2(),poll.getId(),temp);
		}
		else if(search(poll.getAnswer3(), poll.getAnswer())==true) {
			
			poll.setId(getIndexOf(poll.getAnswer3(), poll.getAnswer()));
			temp=poll.getVotes3()[poll.getId()];
			temp++;
		}
		
		//storing the value of id in a temp variable i
		int i=poll.getId();
		
		//get the list of poll 
		da.getPollListById(i);
		
		// add the poll in the pollList
		pollList.add(poll);
		
		model.addAttribute("pollList", pollList);

		return"results";
		
	}// end of playPoll method
	
	// getIndexOf method calculates the index of an element in an array
	public static int getIndexOf(String[] strings, String item) {
	    for (int i = 0; i < strings.length; i++) {
	        if (item.equals(strings[i])) 
	        return i;
	    }
	    return -1;
	}// end of getIndexOf method
	
	// search method return true if value is an element of an array, otherwise false
	public static boolean search(String[] array, String value) {
		for(String s: array){
			if(s.equals(value))
				return true;
		}
		return false;
	}// end of search method
}

