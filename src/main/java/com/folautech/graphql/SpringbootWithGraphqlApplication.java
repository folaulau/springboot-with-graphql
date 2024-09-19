package com.folautech.graphql;

import com.folautech.graphql.dataloader.ChatDataLoaderService;
import com.folautech.graphql.dataloader.UserDataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class SpringbootWithGraphqlApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWithGraphqlApplication.class, args);
	}


	@Autowired
	private UserDataLoaderService userDataLoaderService;

	@Autowired
	private ChatDataLoaderService chatDataLoaderService;


	@Order(Integer.MAX_VALUE)
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			// Display Environmental Useful Variables
			try {
				System.out.println("\n");
				Environment env = ctx.getEnvironment();
				TimeZone timeZone = TimeZone.getDefault();

				System.out
						.println("************************ Sprinboot with GraphQL ***********************************");
				System.out.println("** Active Profile: " + Arrays.toString(env.getActiveProfiles()));
				System.out.println("** Port: " + env.getProperty("server.port"));
				System.out.println("** Timezone: " + timeZone.getID());
				System.out.println("** TimeStamp: " + new Date().toInstant().toString());

				System.out.println("** Internal Url: http://localhost:"
						+ env.getProperty("server.port"));

				System.out.println("** Internal Swagger: http://localhost:"
						+ env.getProperty("server.port") + "/swagger-ui.html");
				System.out.println("** External Swagger: http://"
						+ InetAddress.getLocalHost().getHostAddress() + ":" + env.getProperty("server.port")+ "/swagger-ui.html");
				System.out
						.println("**********************************************************************");

				userDataLoaderService.load();

				chatDataLoaderService.load();

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Exception, commandlineRunner -> " + e.getMessage());
			}
			System.out.println("\n");
		};
	}

	@Override
	public void run(String... args) throws Exception {

		if (args == null || args.length == 0) {
			return;
		}

		System.out.println("************************* CommandLineRunner **************************");
		for (String arg : args) {
			System.out.println("arg: " + arg);
		}


		System.out.println("**********************************************************************");
	}
}
