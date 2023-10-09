package FlightManagementSystem.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FlightManagementSystem.Model.Feedback;
import FlightManagementSystem.Service.FeedbackService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/FeedBackServ")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/saveFeedback/{flightId}/{userId}")
	public ResponseEntity<Feedback> addFeedback(@Valid @RequestBody Feedback feedback,
					@PathVariable("flightId") Integer flightId, @PathVariable("userId") Integer userId) throws Exception{
		
		Feedback feedback2 = feedbackService.saveFeedBack(feedback,flightId,userId);
		
		return new ResponseEntity<Feedback>(feedback2,HttpStatus.ACCEPTED);
		
	}
	@DeleteMapping("DeleteFeedback/{flightId}")
	public void deleteFeedback(@PathVariable("flightId") Integer feedbackId) throws Exception {
		
		feedbackService.deleteFeedBackById(feedbackId);
		
	}
//	@GetMapping("/readOneFeedbackByFlight/{flightId}")
//	public ResponseEntity<Feedback> viewFeedback(@PathVariable("flightId") Integer flightId) throws Exception{
//		
//		Feedback feedback2 = feedbackService.getFeedbackById(flightId);
//		
//		return new ResponseEntity<Feedback>(feedback2,HttpStatus.ACCEPTED);
//		
//	}
//	
//	@GetMapping("/readOneFeedbackByUser/{userId}")
//	public ResponseEntity<Feedback> viewFeedbackByUser(@PathVariable("userId") Integer userId) throws Exception{
//		
//		Feedback feedback2 = feedbackService.getFeedbackByUserId(userId);
//		
//		return new ResponseEntity<Feedback>(feedback2,HttpStatus.ACCEPTED);
//		
//	}
	
	@GetMapping("/readAllFeedback")
	public ResponseEntity<List<Feedback>> viewFeedbackAll()  throws Exception{
		
		List<Feedback> feedback2 =  feedbackService.getFeedbackAll();
		
		return new ResponseEntity<List<Feedback>>(feedback2,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/readFeedbackByFeedbackId/{feedbackId}")
	public ResponseEntity<Feedback> viewFeedbackById(@PathVariable("feedbackId") Integer feedbackId){
		
		return new ResponseEntity<Feedback>(feedbackService.readFeedbackById(feedbackId), HttpStatus.OK);
	}
}
