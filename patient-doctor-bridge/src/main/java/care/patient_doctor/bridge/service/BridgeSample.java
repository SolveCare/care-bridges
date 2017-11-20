package care.patient_doctor.bridge.service;

import care.patient_doctor.bridge.dto.DoctorDto;
import care.patient_doctor.bridge.dto.ScheduleSlotDto;
import care.patient_doctor.bridge.transformer.ThirdPartyToCareDoctorTransformer;
import care.patient_doctor.bridge.transformer.ThirdPartyToCareScheduleTransformer;
import care.patient_doctor.bridge.transformer.third.party.entity.ThirdPartyDoctor;
import care.patient_doctor.bridge.transformer.third.party.entity.ThirdPartyScheduleSlot;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BridgeSample implements PatientDoctorBridgeService {

    @Value("${third.party.url.doctor}")
    private String doctorEndpoint;

    @Value("${third.party.url.schedule}")
    private String scheduleEndpoint;

    @Value("${third.party.url.book}")
    private String bookEndpoint;

    private ThirdPartyToCareDoctorTransformer doctorTransformer;
    private ThirdPartyToCareScheduleTransformer scheduleTransformer;

    @Autowired
    public BridgeSample(ThirdPartyToCareDoctorTransformer doctorTransformer, ThirdPartyToCareScheduleTransformer scheduleTransformer) {
        this.doctorTransformer = doctorTransformer;
        this.scheduleTransformer = scheduleTransformer;
    }

    @Override
    public List<ScheduleSlotDto> getFreeScheduleSlots(String doctorId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);

        URI uri = UriComponentsBuilder.fromHttpUrl(scheduleEndpoint)
                .queryParam("doctorId", doctorId)
                .build()
                .encode()
                .toUri();

        ResponseEntity<ThirdPartyScheduleSlot[]> response = new RestTemplate().exchange(
                uri, HttpMethod.GET, entity, ThirdPartyScheduleSlot[].class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Unsuccessful third party endpoint call.");
        }

        return scheduleTransformer.transformList(Arrays.asList(response.getBody()));
    }

    @Override
    public List<ScheduleSlotDto> getPatientScheduleSlots(String doctorId, String patientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);

        URI uri = UriComponentsBuilder.fromHttpUrl(scheduleEndpoint)
                .queryParam("doctorId", doctorId)
                .queryParam("patientId", patientId)
                .build()
                .encode()
                .toUri();

        ResponseEntity<ThirdPartyScheduleSlot[]> response = new RestTemplate().exchange(
                uri, HttpMethod.GET, entity, ThirdPartyScheduleSlot[].class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Unsuccessful third party endpoint call.");
        }

        return scheduleTransformer.transformList(Arrays.asList(response.getBody()));
    }

    @Override
    public List<DoctorDto> getDoctors() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<ThirdPartyDoctor[]> response = new RestTemplate().exchange(
                doctorEndpoint, HttpMethod.GET, entity, ThirdPartyDoctor[].class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Unsuccessful third party endpoint call.");
        }

        return doctorTransformer.transformList(Arrays.asList(response.getBody()));
    }

    @Override
    public boolean bookScheduleSlot(String slotId, String patientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);

        URI uri = UriComponentsBuilder.fromHttpUrl(bookEndpoint)
                .queryParam("scheduleId", slotId)
                .queryParam("patientId", patientId)
                .build()
                .encode()
                .toUri();

        ResponseEntity<Boolean> response = new RestTemplate().exchange(
                uri, HttpMethod.PUT, entity, Boolean.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Unsuccessful third party endpoint call.");
        }

        return response.getBody();
    }

}
