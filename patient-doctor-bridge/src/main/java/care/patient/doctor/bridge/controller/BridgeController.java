package care.patient.doctor.bridge.controller;

import care.patient.doctor.bridge.dto.DoctorDto;
import care.patient.doctor.bridge.dto.ScheduleSlotDto;
import care.patient.doctor.bridge.service.PatientDoctorBridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bridge/patient-doctor")
public class BridgeController {

    private PatientDoctorBridgeService patientDoctorBridgeService;

    @Autowired
    public BridgeController(PatientDoctorBridgeService patientDoctorBridgeService) {
        this.patientDoctorBridgeService = patientDoctorBridgeService;
    }

    @GetMapping("/schedule/patient")
    public List<ScheduleSlotDto> getSchedule(@RequestParam String patientId,
                                             @RequestParam(required = false) String doctorId) {
        return patientDoctorBridgeService.getPatientScheduleSlots(patientId, doctorId);
    }

    @GetMapping("/schedule/doctor")
    public List<ScheduleSlotDto> getSchedule(@RequestParam String doctorId) {
        return patientDoctorBridgeService.getFreeScheduleSlots(doctorId);
    }


    @PatchMapping("/schedule")
    public ScheduleSlotDto bookScheduleSlot(@RequestParam String scheduleId,
                                            @RequestParam String patientId) {
        return patientDoctorBridgeService.bookScheduleSlot(scheduleId, patientId);
    }

    @PostMapping("/schedule")
    public ScheduleSlotDto createScheduleSlot(@RequestBody ScheduleSlotDto scheduleSlotDto) {
        return patientDoctorBridgeService.createScheduleSlot(scheduleSlotDto);
    }

    @GetMapping("/doctors")
    public List<DoctorDto> getDoctors() {
        return patientDoctorBridgeService.getDoctors();
    }
}
