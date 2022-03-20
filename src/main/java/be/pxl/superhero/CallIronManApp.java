package be.pxl.superhero;

import be.pxl.superhero.domain.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class CallIronManApp {

	@Autowired
	private CallService callService;

	@Value("${ironman.phonenumber}")
	private String phoneNumber;

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(CallIronManApp.class);
		CallIronManApp app = context.getBean(CallIronManApp.class);
		app.doCall();
	}


	private void doCall() {
		Superhero ironMan = new Superhero("Tony", "Stark", "Iron Man");
		ironMan.setPhoneNumber(phoneNumber);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Give your message for Iron Man: ");
		String message = scanner.nextLine();
		callService.call(ironMan, message);
	}
}
