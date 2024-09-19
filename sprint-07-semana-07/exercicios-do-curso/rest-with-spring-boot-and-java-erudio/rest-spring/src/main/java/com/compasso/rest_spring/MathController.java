package com.compasso.rest_spring;

import com.compasso.rest_spring.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/operacoes")
public class MathController {
    private static AtomicLong counter = new AtomicLong();

    @GetMapping("/soma/{numberOne}/{numberTwo}")
    public Double soma(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @GetMapping("/subtracao/{numberOne}/{numberTwo}")
    public Double subtracao(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @GetMapping("/multiplicacao/{numberOne}/{numberTwo}")
    public Double multiplicacao(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @GetMapping("/divisao/{numberOne}/{numberTwo}")
    public Double divisao(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @GetMapping("/media/{numberOne}/{numberTwo}")
    public Double media(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    @GetMapping("/raiz-quadrada/{numberOne}")
    public Double raizQuadrada(
            @PathVariable(value = "numberOne") String numberOne
    ) {
        if(!isNumeric(numberOne)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return Math.sqrt(convertToDouble(numberOne));
    }

    private Double convertToDouble(String number) {
        if(number == null) return 0D;
        String strNumber = number.replace(",", ".");

        if(isNumeric(strNumber)) return Double.parseDouble(strNumber);

        return 0D;
    }

    private boolean isNumeric(String number) {
        if(number == null) return false;
        String strNumber = number.replace(",", ".");

        return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
