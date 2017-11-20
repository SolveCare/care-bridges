package care.patient.doctor.bridge.transformer;

import care.patient.doctor.bridge.dto.ScheduleSlotDto;
import care.patient.doctor.bridge.entity.ThirdPartyScheduleSlot;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartyToCareScheduleTransformer implements Transformer <ThirdPartyScheduleSlot, ScheduleSlotDto> {

    @Override
    public ScheduleSlotDto transform(ThirdPartyScheduleSlot thirdPartyScheduleSlot) {
        ScheduleSlotDto scheduleSlotDto = ScheduleSlotDto.builder().build();
        BeanUtils.copyProperties(thirdPartyScheduleSlot, scheduleSlotDto);

        return scheduleSlotDto;
    }

}
