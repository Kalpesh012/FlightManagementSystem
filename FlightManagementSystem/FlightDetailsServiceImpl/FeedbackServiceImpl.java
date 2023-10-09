package FlightManagementSystem.FlightDetailsServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FlightManagementSystem.Model.Feedback;
import FlightManagementSystem.Model.FlightDetails;
import FlightManagementSystem.Model.User;
import FlightManagementSystem.Repository.FeedbackRepository;
import FlightManagementSystem.Repository.FlightDetailsRepository;
import FlightManagementSystem.Repository.UserRepository;
import FlightManagementSystem.Service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private FlightDetailsRepository  flightRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Feedback saveFeedBack(Feedback feedback, Integer flightId, Integer userId) throws Exception{
		Optional<FlightDetails> flightObj = flightRepository.findById(flightId);
		if(flightObj.isEmpty()) {
			throw new Exception("Flight is not present with the Id: " + flightId);
		}
		Optional<User> userObj = userRepository.findById(userId);
		
		feedback.setFlight(flightObj.get());
		feedback.setUser(userObj.get());
		Feedback savedFeedback = feedbackRepository.save(feedback);
		
		return savedFeedback;
	}

	@Override
	public void deleteFeedBackById(Integer feedbackId) {
		feedbackRepository.deleteById(feedbackId);
	}

//	@Override
//	public Feedback getFeedbackById(Integer flightId) throws Exception {
//		Optional<Feedback> fedOptional = feedbackRepository.findById(flightId);
//		if (fedOptional.isPresent()) {
//			return fedOptional.get();
//		}
//		throw new Exception("No feedback found!");
//	}

	@Override
	public List<Feedback> getFeedbackAll() throws Exception {
		Optional<List<Feedback>> fedOptional = Optional.of(feedbackRepository.findAll());
		if (fedOptional.isPresent()) {
			return fedOptional.get();
		}
		throw new Exception("No feedbacks found!");
	}

	@Override
	public Feedback readFeedbackById(Integer feedbackId) {
		Optional<Feedback> feedbackObj = feedbackRepository.findById(feedbackId);
		if(feedbackObj.isPresent()) {
			return feedbackObj.get();
		}
		return null;
	}

//	@Override
//	public Feedback getFeedbackByUserId(Integer userId) throws Exception {
//		
//		Optional<Feedback> fedOptional = feedbackRepository.findById(userId);
//		if (fedOptional.isPresent()) {
//			return fedOptional.get();
//		}
//		throw new Exception("No feedback found!");
//	}
}
