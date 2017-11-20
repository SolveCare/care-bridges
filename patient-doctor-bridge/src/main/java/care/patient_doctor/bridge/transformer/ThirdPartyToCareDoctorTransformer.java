package care.patient_doctor.bridge.transformer;

import care.patient_doctor.bridge.dto.DoctorDto;
import care.patient_doctor.bridge.transformer.third.party.entity.ThirdPartyDoctor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartyToCareDoctorTransformer implements Transformer <ThirdPartyDoctor, DoctorDto> {

    @Override
    public DoctorDto transform(ThirdPartyDoctor thirdPartyDoctor) {
        DoctorDto doctorDto = DoctorDto.builder().build();
        BeanUtils.copyProperties(thirdPartyDoctor, doctorDto);

        return doctorDto;
    }


}
