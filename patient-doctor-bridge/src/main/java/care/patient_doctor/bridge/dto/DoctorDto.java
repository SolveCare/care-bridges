package care.patient_doctor.bridge.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorDto {

    private String id;

    private String firstName;

    private String lastName;

}
