package care.patient_doctor.bridge.transformer;

import care.patient_doctor.bridge.dto.ScheduleSlotDto;
import care.patient_doctor.bridge.transformer.third.party.entity.ThirdPartyScheduleSlot;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ThirdPartyToCareScheduleTransformer implements Transformer <ThirdPartyScheduleSlot, ScheduleSlotDto> {

    @Override
    public ScheduleSlotDto transform(ThirdPartyScheduleSlot thirdPartyScheduleSlot) {
        ScheduleSlotDto scheduleSlotDto = ScheduleSlotDto.builder().build();
        BeanUtils.copyProperties(thirdPartyScheduleSlot, scheduleSlotDto);

        return scheduleSlotDto;
    }

}
