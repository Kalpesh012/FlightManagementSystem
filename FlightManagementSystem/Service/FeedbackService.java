package FlightManagementSystem.Service;

import java.util.List;

import FlightManagementSystem.Model.Feedback;

public interface FeedbackService {

	Feedback saveFeedBack(Feedback feedBack,Integer flightId, Integer userId) throws Exception;
	
	void deleteFeedBackById(Integer feedbackId) throws Exception;

//	Feedback getFeedbackById(Integer flightId) throws Exception;

	List<Feedback> getFeedbackAll() throws Exception;
	
	Feedback readFeedbackById(Integer feedbackId);
	
//	Feedback getFeedbackByUserId(Integer userId) throws Exception;
}
