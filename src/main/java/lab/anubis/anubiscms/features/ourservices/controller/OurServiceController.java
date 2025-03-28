package lab.anubis.anubiscms.features.ourservices.controller;

import lab.anubis.anubiscms.features.ourservices.model.OurService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/our-service")
public class OurServiceController {

    private final OurService ourService;

    public OurServiceController(OurService ourService) {
        this.ourService = ourService;
    }


}
