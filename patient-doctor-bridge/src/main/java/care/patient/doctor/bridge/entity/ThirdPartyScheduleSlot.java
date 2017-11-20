package care.patient.doctor.bridge.entity;

import lombok.Data;

@Data
public class ThirdPartyScheduleSlot {

    private String id;
    private String doctorId;
    private String patientId;
    private Long timeStart;
    private Long timeFinish;

}
