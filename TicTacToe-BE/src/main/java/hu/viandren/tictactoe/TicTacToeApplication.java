package hu.viandren.tictactoe;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan(basePackages = "hu.viandren")
public class TicTacToeApplication {

	private static final Logger LOGGER = LogManager.getLogger(TicTacToeApplication.class);

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(TicTacToeApplication.class);

		Map<String, Object> map = new HashMap<>();
		map.put("server.servlet.context-path", "/backend");
		map.put("server.port", "4000");
		app.setDefaultProperties(map);

		app.run();
	}

}
