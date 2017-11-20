package care.patient.doctor.bridge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean(name = "httpEntity")
    public HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity(headers);
    }

    @Bean(name = "restTemplate")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
