package ch.rafi.weather_oracle;

import ch.rafi.weather_oracle.server.WeatherOracle;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author rbu
 */
@SpringBootApplication
public class WeatherOracleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherOracleApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialize(){
		WeatherOracle.initialize();
		return (args) -> System.out.println("Oracle initialized");
	}
}
