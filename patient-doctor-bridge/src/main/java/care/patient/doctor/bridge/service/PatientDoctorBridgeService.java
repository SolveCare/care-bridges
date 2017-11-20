package care.patient.doctor.bridge.service;

import care.patient.doctor.bridge.dto.DoctorDto;
import care.patient.doctor.bridge.dto.ScheduleSlotDto;

import java.util.List;

public interface PatientDoctorBridgeService {

    List<ScheduleSlotDto> getFreeScheduleSlots(String doctorId);

    List<ScheduleSlotDto> getPatientScheduleSlots(String doctorId, String patientId);

    ScheduleSlotDto createScheduleSlot(ScheduleSlotDto scheduleSlotDto);

    List<DoctorDto> getDoctors();

    boolean bookScheduleSlot(String slotId, String patientId);

}
