package br.com.erudio.springproject;

import br.com.erudio.springproject.exceptions.UnsupportedMathOperationException;
import br.com.erudio.springproject.service.MathServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static br.com.erudio.springproject.service.MathServices.covertToDouble;

@RestController
public class MathController {

    private MathServices services = new MathServices();

    @RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!MathServices.isNumeric(numberOne) || !MathServices.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return services.sum(covertToDouble(numberOne), covertToDouble(numberTwo));
    }

    @RequestMapping(value="/sub/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double sub(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!MathServices.isNumeric(numberOne) || !MathServices.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return services.sub(covertToDouble(numberOne), covertToDouble(numberTwo));
    }

    @RequestMapping(value="/mul/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double mul(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!MathServices.isNumeric(numberOne) || !MathServices.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return services.mul(covertToDouble(numberOne), covertToDouble(numberTwo));
    }

    @RequestMapping(value="/div/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double div(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!MathServices.isNumeric(numberOne) || !MathServices.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return services.div(covertToDouble(numberOne), covertToDouble(numberTwo));
    }

    @RequestMapping(value="/med/{numberOne}/{numberTwo}", method=RequestMethod.GET)
    public Double med(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!MathServices.isNumeric(numberOne) || !MathServices.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return services.med(covertToDouble(numberOne), covertToDouble(numberTwo));
    }

    @RequestMapping(value="/quad/{numberOne}", method=RequestMethod.GET)
    public Double quad(@PathVariable("numberOne") String numberOne) throws Exception {
        if (!MathServices.isNumeric(numberOne)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return services.quad(covertToDouble(numberOne));
    }


}