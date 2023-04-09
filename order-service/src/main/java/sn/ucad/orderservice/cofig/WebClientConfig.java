package sn.ucad.orderservice.cofig;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;


@Configuration
@Builder
public class WebClientConfig {

    @Value("${inventory.service.URL}")
    private String inventoryURL;


    /**
     * Provides webClientBuilder.
     */
    @Bean
    @LoadBalanced
    public WebClient getWebClientBuilder() throws SSLException {
        // Truts all X.509 certificates without any verification
        SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).baseUrl(inventoryURL).build();
    }
}
