package care.patient_doctor.bridge.transformer.third.party.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdPartyDoctor {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;

}
