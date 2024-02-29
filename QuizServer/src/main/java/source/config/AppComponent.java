package source.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppComponent {
	
	@Value("${server.port}")
	private String appPort;

	public String getAppPort() {
		return appPort;
	}	
	
}
