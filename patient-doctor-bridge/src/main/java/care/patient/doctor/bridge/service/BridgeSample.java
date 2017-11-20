package care.patient.doctor.bridge.service;

import care.patient.doctor.bridge.dto.DoctorDto;
import care.patient.doctor.bridge.dto.ScheduleSlotDto;
import care.patient.doctor.bridge.entity.ThirdPartyScheduleSlot;
import care.patient.doctor.bridge.transformer.ThirdPartyToCareDoctorTransformer;
import care.patient.doctor.bridge.transformer.ThirdPartyToCareScheduleTransformer;
import care.patient.doctor.bridge.entity.ThirdPartyDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class BridgeSample implements PatientDoctorBridgeService {

    @Value("${third.party.url.doctor}")
    private String doctorEndpoint;

    @Value("${third.party.url.schedule}")
    private String scheduleEndpoint;

    private ThirdPartyToCareDoctorTransformer doctorTransformer;
    private ThirdPartyToCareScheduleTransformer scheduleTransformer;
    private HttpEntity httpEntity;
    private RestTemplate restTemplate;

    @Autowired
    public BridgeSample(ThirdPartyToCareDoctorTransformer doctorTransformer, ThirdPartyToCareScheduleTransformer scheduleTransformer, HttpEntity httpEntity, RestTemplate restTemplate) {
        this.doctorTransformer = doctorTransformer;
        this.scheduleTransformer = scheduleTransformer;
        this.httpEntity = httpEntity;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ScheduleSlotDto> getFreeScheduleSlots(String doctorId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(scheduleEndpoint)
                .queryParam("doctorId", doctorId)
                .build()
                .encode()
                .toUri();

        ResponseEntity<ThirdPartyScheduleSlot[]> response = restTemplate.exchange(
                uri, HttpMethod.GET, httpEntity, ThirdPartyScheduleSlot[].class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Unsuccessful third party endpoint call.");
        }

        return scheduleTransformer.transformList(Arrays.asList(response.getBody()));
    }

    @Override
    public List<ScheduleSlotDto> getPatientScheduleSlots(String doctorId, String patientId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(scheduleEndpoint)
                .queryParam("doctorId", doctorId)
                .queryParam("patientId", patientId)
                .build()
                .encode()
                .toUri();

        ResponseEntity<ThirdPartyScheduleSlot[]> response = restTemplate.exchange(
                uri, HttpMethod.GET, httpEntity, ThirdPartyScheduleSlot[].class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Unsuccessful third party endpoint call.");
        }

        return scheduleTransformer.transformList(Arrays.asList(response.getBody()));
    }

    @Override
    public ScheduleSlotDto createScheduleSlot(ScheduleSlotDto scheduleSlotDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(scheduleSlotDto, headers);

        ResponseEntity<ThirdPartyScheduleSlot> response = restTemplate.exchange(
                scheduleEndpoint, HttpMethod.POST, entity, ThirdPartyScheduleSlot.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Unsuccessful third party endpoint call.");
        }

        return scheduleTransformer.transform(response.getBody());
    }

    @Override
    public List<DoctorDto> getDoctors() {
        ResponseEntity<ThirdPartyDoctor[]> response = restTemplate.exchange(
                doctorEndpoint, HttpMethod.GET, httpEntity, ThirdPartyDoctor[].class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Unsuccessful third party endpoint call.");
        }

        return doctorTransformer.transformList(Arrays.asList(response.getBody()));
    }

    @Override
    public ScheduleSlotDto bookScheduleSlot(String slotId, String patientId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(scheduleEndpoint)
                .queryParam("scheduleId", slotId)
                .queryParam("patientId", patientId)
                .build()
                .encode()
                .toUri();

        ResponseEntity<ScheduleSlotDto> response = restTemplate.exchange(
                uri, HttpMethod.PUT, httpEntity, ScheduleSlotDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Unsuccessful third party endpoint call.");
        }

        return response.getBody();
    }

}
