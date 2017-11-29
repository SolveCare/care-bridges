package care.patient.doctor.bridge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

@Configuration
public class RestConfig {

    @Bean("restTemplate")
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);

        return restTemplate;
    }
}
