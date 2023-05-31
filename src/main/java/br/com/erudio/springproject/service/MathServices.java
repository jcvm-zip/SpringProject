package br.com.erudio.springproject.service;

import br.com.erudio.springproject.exceptions.UnsupportedMathOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class MathServices {

    public Double sum(Double numberOne, Double numberTwo){
        return numberOne + numberTwo;
    }
    public Double sub(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }
    public Double mul(Double numberOne, Double numberTwo){
        return numberOne * numberTwo;
    }
    public Double div(Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }
    public Double med(Double numberOne, Double numberTwo) {
        return (numberOne + numberTwo) / 2;
    }
    public Double quad(Double numberOne){
        return numberOne * numberOne;
    }



    public static Double covertToDouble(String strNumber) {
        if (strNumber == null) return 0d;
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 1.0d;
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
