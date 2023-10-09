package FlightManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.twilio.Twilio;

import FlightManagementSystem.Model.TwilioConfig;
import jakarta.annotation.PostConstruct;

/*
 * This is the working class for API.
 * @SpringBootApplication is used to call functions like bean creation, component-scan etc 
 * which are called automatically by spring. 
 */
@SpringBootApplication
@EnableConfigurationProperties
public class FlightDetailsApplication {

	@Autowired
	private TwilioConfig twilioConfig;

	@PostConstruct
	public void setup() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}
	public static void main(String[] args) {
		SpringApplication.run(FlightDetailsApplication.class, args);
	}

}
