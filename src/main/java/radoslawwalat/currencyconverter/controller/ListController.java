package radoslawwalat.currencyconverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radoslawwalat.currencyconverter.dto.RateDto;
import radoslawwalat.currencyconverter.service.ConvertService;

import java.util.List;

@RestController
@RequestMapping("/list")
public class ListController {

    private final ConvertService convertService;

    public ListController(ConvertService convertService) {
        this.convertService = convertService;
    }

    @GetMapping()
    public List<RateDto> getListOfRates(){
        return convertService.printListOfRates();
    }
}
