package lab.anubis.anubiscms.features.ourservices.mapper;

import lab.anubis.anubiscms.features.ourservices.dto.OurServiceDto;
import lab.anubis.anubiscms.features.ourservices.model.OurService;
import org.springframework.stereotype.Service;

@Service
public class OurServiceMapper {

    public OurServiceDto fromEntity(OurService ourService){
        if (ourService == null) return null;
        return new OurServiceDto(
                ourService.getId(),
                ourService.getName(),
                ourService.getContent()
        );
    }

    public OurService fromDto(OurServiceDto dto){
        if (dto == null) return null;
        return new OurService(
                dto.id(),
                dto.name(),
                dto.content()
        );
    }

}
