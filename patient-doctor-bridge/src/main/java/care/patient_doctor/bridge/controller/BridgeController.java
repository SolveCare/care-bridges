package care.patient_doctor.bridge.controller;

import care.patient_doctor.bridge.dto.DoctorDto;
import care.patient_doctor.bridge.dto.ScheduleSlotDto;
import care.patient_doctor.bridge.service.PatientDoctorBridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bridge/patient-doctor", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BridgeController {

    private PatientDoctorBridgeService patientDoctorBridgeService;

    @Autowired
    public BridgeController(PatientDoctorBridgeService patientDoctorBridgeService) {
        this.patientDoctorBridgeService = patientDoctorBridgeService;
    }

    @GetMapping("/schedule")
    public List<ScheduleSlotDto> getSchedule(@RequestParam String doctorId,
                                             @RequestParam(required = false) String patientId) {
        if (StringUtils.isEmpty(patientId)) {
            return patientDoctorBridgeService.getFreeScheduleSlots(doctorId);
        } else {
            return patientDoctorBridgeService.getPatientScheduleSlots(doctorId, patientId);
        }
    }

    @PutMapping("/schedule/book")
    public boolean bookScheduleSlot(@RequestParam String scheduleId,
                                    @RequestParam String patientId) {
        return patientDoctorBridgeService.bookScheduleSlot(scheduleId, patientId);
    }

    @GetMapping("/doctors")
    public List<DoctorDto> getDoctors() {
        return patientDoctorBridgeService.getDoctors();
    }
}
