package ca.sheridancollege.kaur7782.database;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.kaur7782.beans.Poll;


@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	// getPollList method returns the pollList
	public List<Poll> getPollList() {
		
		// to use for parameters
		MapSqlParameterSource namedParameters=new MapSqlParameterSource();
		
		//query in form of String
		String query="Select * from poll";
		
		//get the list of polls
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Poll>(Poll.class));
	
		
	}// end of getPollList method

	// getPollListById method returns the pollList according to id
	public List<Poll> getPollListById(int id){
		
		// to use for parameters
		MapSqlParameterSource namedParameters=new MapSqlParameterSource();
		
		//query in form of String
		String query="Select pollId, title, question, answer1, answer2, answer3, votes1, votes2, votes3 from poll where pollId= :pollId";
		
		//replace the value of pollId with id
		namedParameters.addValue("pollId", id);
		
		//get poll
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Poll>(Poll.class));
		
	}//end of getPollListById method

}//end of DatabaseAccess class
