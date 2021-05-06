package radoslawwalat.currencyconverter.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import radoslawwalat.currencyconverter.dto.PostMappingDto;
import radoslawwalat.currencyconverter.model.GetData;
import radoslawwalat.currencyconverter.model.GetDataRepository;
import radoslawwalat.currencyconverter.model.PostData;
import radoslawwalat.currencyconverter.model.PostDataRepository;
import radoslawwalat.currencyconverter.service.ConvertService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/convert")
public class MainController {

    private final ConvertService convertService;
    private final GetDataRepository getDataRepository;
    private final PostDataRepository postDataRepository;

    public MainController(ConvertService convertService, GetDataRepository getDataRepository, PostDataRepository postDataRepository) {
        this.convertService = convertService;
        this.getDataRepository = getDataRepository;
        this.postDataRepository = postDataRepository;
    }

    @GetMapping()
    public Double exchangeGet(
            @RequestParam(name = "amount") Double amount,
            @RequestParam(name = "baseCurrency") String baseCurrency,
            @RequestParam(name = "targetCurrency") String targetCurrency) {

        Double result = convertService.exchange(amount, baseCurrency, targetCurrency);

        getDataRepository.save(new GetData(LocalDateTime.now(),amount,baseCurrency,targetCurrency, result));

        return result;
    }

    @PostMapping()
    public Double exchangePost(@RequestBody PostMappingDto postMappingDto) {

        Double result = convertService.exchange(postMappingDto.getAmount(), postMappingDto.getBaseCurrency(), postMappingDto.getTargetCurrency());

        postDataRepository.save(new PostData(LocalDateTime.now(), postMappingDto.getAmount(), postMappingDto.getBaseCurrency(), postMappingDto.getTargetCurrency(), result));

        return result;
    }

    @ExceptionHandler(NumberFormatException.class)
    public String numberFormatExceptionHandler() {
        return "Please provide a number as an amount";
    }

    @ExceptionHandler(IllegalStateException.class)
    public String illegalStateExceptionHandler(IllegalStateException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(RestClientException.class)
    public String restClientExceptionHandler(IllegalStateException ex) {
        return ex.getMessage();
    }
}
