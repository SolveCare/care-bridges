package care.patient.doctor.bridge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

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
