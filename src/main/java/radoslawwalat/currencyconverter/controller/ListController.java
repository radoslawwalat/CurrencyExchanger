package radoslawwalat.currencyconverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radoslawwalat.currencyconverter.dto.RateDto;
import radoslawwalat.currencyconverter.model.ListData;
import radoslawwalat.currencyconverter.model.ListDataRepository;
import radoslawwalat.currencyconverter.service.ConvertService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/list")
public class ListController {

    private final ConvertService convertService;
    private final ListDataRepository listDataRepository;

    public ListController(ConvertService convertService, ListDataRepository listDataRepository) {
        this.convertService = convertService;
        this.listDataRepository = listDataRepository;
    }

    @GetMapping()
    public List<RateDto> getListOfRates(){

        // For future reference any kind of data could be stored in database by editing it here
        listDataRepository.save(new ListData(LocalDateTime.now()));

        return convertService.printListOfRates();
    }
}
