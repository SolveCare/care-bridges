package care.patient_doctor.bridge.service;

import care.patient_doctor.bridge.dto.ScheduleSlotDto;
import care.patient_doctor.bridge.dto.DoctorDto;

import java.util.List;

public interface PatientDoctorBridgeService {

    List<ScheduleSlotDto> getFreeScheduleSlots(String doctorId);

    List<ScheduleSlotDto> getPatientScheduleSlots(String doctorId, String patientId);

    List<DoctorDto> getDoctors();

    boolean bookScheduleSlot(String slotId, String patientId);

}
