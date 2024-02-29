package source;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class QuizServerApplication {
	
//	@Value("${server.port}")
    private static int serverPort = 7000;
	
	public static void main(String[] args) {
		SpringApplication.run(QuizServerApplication.class, args);
		System.out.println("Server started running at "+ serverPort);
	}

}
