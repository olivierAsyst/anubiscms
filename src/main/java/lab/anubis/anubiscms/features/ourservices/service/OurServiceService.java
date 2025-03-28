package lab.anubis.anubiscms.features.ourservices.service;

import lab.anubis.anubiscms.features.ourservices.dto.OurServiceDto;

import java.util.List;
import java.util.Optional;

public interface OurServiceService {
    OurServiceDto createService(OurServiceDto dto, String username);
    List<OurServiceDto> createServices(List<OurServiceDto> dtoList, String username);
    Optional<OurServiceDto> getServiceById(Long idService);
    Optional<OurServiceDto> getServiceByName(String serviceName);
    List<OurServiceDto> getAllServices();
    Optional<OurServiceDto> updateService(Long idArticle, OurServiceDto serviceDto, String username);
    void deleteAService(Long idService, String username);
}
