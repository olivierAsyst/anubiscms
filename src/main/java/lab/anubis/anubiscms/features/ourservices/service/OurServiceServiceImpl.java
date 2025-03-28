package lab.anubis.anubiscms.features.ourservices.service;

import lab.anubis.anubiscms.features.ourservices.dto.OurServiceDto;
import lab.anubis.anubiscms.features.ourservices.mapper.OurServiceMapper;
import lab.anubis.anubiscms.features.ourservices.model.OurService;
import lab.anubis.anubiscms.features.ourservices.repository.OurServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OurServiceServiceImpl implements OurServiceService {

    private final OurServiceRepository repository;
    private final OurServiceMapper mapper;

    public OurServiceServiceImpl(OurServiceRepository repository, OurServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private static Logger log = LoggerFactory.getLogger(OurServiceServiceImpl.class);

    @Override
    public OurServiceDto createService(OurServiceDto dto, String username) {
        OurService ourService = new OurService();
        try {
            ourService.setCreatedAt(LocalDate.now());
            ourService = repository.save(mapper.fromDto(dto));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return mapper.fromEntity(ourService);
    }

    @Override
    public List<OurServiceDto> createServices(List<OurServiceDto> dtoList, String username) {
        List<OurService> ourServices = new ArrayList<>();
        try {
            for (OurServiceDto dto: dtoList){
                OurService ourService = mapper.fromDto(dto);
                ourService.setCreatedAt(LocalDate.now());
                ourServices.add(ourService);
            }
            ourServices = repository.saveAll(dtoList.stream().map(mapper::fromDto).toList());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return ourServices.stream().map(mapper::fromEntity).toList();
    }

    @Override
    public Optional<OurServiceDto> getServiceById(Long idService) {
        return repository.findById(idService)
                .map(mapper::fromEntity);
    }

    @Override
    public Optional<OurServiceDto> getServiceByName(String serviceName) {
        return repository.findByName(serviceName)
                .map(mapper::fromEntity);
    }

    @Override
    public List<OurServiceDto> getAllServices() {
        return repository.findAll()
                .stream().map(mapper::fromEntity)
                .toList();
    }

    @Override
    public Optional<OurServiceDto> updateService(Long idService, OurServiceDto serviceDto, String username) {
        return repository.findById(idService).map( exist -> {
           exist.setName(serviceDto.name());
           exist.setContent(serviceDto.content());
           exist.setUpdatedAt(LocalDate.now());

           OurService ourService = repository.save(exist);
           return mapper.fromEntity(ourService);
        });
    }

    @Override
    public void deleteAService(Long idService, String username) {
        repository.deleteById(idService);
    }
}
