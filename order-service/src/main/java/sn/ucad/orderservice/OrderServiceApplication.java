package sn.ucad.orderservice;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApplication  implements CommandLineRunner {

/*	@Value("${inventory.service.URL}")
	private String inventoryURL;*/

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}


	/**
	 * Provides webClientBuilder.
	 *//*
	@Bean
	public WebClient getWebClientBuilder() throws SSLException {
		// Truts all X.509 certificates without any verification
		SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).baseUrl(inventoryURL).build();
	}
*/
	@Override
	public void run(String... args) throws Exception {

	}
}
