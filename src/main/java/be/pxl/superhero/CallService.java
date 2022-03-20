package be.pxl.superhero;

import be.pxl.superhero.domain.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CallService {

	private final SmsService smsService;
	private final Map<Superhero, Integer> numberOfCalls = new HashMap<>();

	@Autowired
	public CallService(SmsService smsService) {
		this.smsService = smsService;
	}

	public void call(Superhero superhero, String message) {
		numberOfCalls.merge(superhero, 1, Integer::sum);
		smsService.sendSms(message, superhero.getPhoneNumber());
	}

	public int getNumberOfCalls(Superhero superhero) {
		if (!numberOfCalls.containsKey(superhero)) {
			return 0;
		}
		return numberOfCalls.get(superhero);
	}

}
