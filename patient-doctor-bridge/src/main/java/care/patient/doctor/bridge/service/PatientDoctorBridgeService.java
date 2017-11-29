package care.patient.doctor.bridge.service;

import care.patient.doctor.bridge.dto.DoctorDto;
import care.patient.doctor.bridge.dto.ScheduleSlotDto;

import java.util.List;

public interface PatientDoctorBridgeService {

    List<ScheduleSlotDto> getFreeScheduleSlots(String doctorId);

    List<ScheduleSlotDto> getPatientScheduleSlots(String patientId, String doctorId);

    ScheduleSlotDto createScheduleSlot(ScheduleSlotDto scheduleSlotDto);

    List<DoctorDto> getDoctors();

    ScheduleSlotDto bookScheduleSlot(String slotId, String patientId);

}
