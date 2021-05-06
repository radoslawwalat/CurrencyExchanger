package radoslawwalat.currencyconverter.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import radoslawwalat.currencyconverter.dto.PostMappingDto;
import radoslawwalat.currencyconverter.service.ConvertService;

@RestController
@RequestMapping("/convert")
public class MainController {

    private final ConvertService convertService;

    public MainController(ConvertService convertService) {
        this.convertService = convertService;
    }

    @GetMapping()
    public Double exchangeGet(
            @RequestParam(name = "amount") Double amount,
            @RequestParam(name = "baseCurrency") String baseCurrency,
            @RequestParam(name = "targetCurrency") String targetCurrency) {
        return convertService.exchange(amount, baseCurrency, targetCurrency);
    }

    @PostMapping()
    public Double exchangePost(@RequestBody PostMappingDto postMappingDto) {
        return convertService.exchange(postMappingDto.getAmount(), postMappingDto.getBaseCurrency(), postMappingDto.getTargetCurrency());
    }

    @ExceptionHandler(NumberFormatException.class)
    public String numberFormatExceptionHandler() {
        return "Given amount must be a number";
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
