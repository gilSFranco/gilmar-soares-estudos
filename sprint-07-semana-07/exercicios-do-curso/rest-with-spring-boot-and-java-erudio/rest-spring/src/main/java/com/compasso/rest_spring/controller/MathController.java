package com.compasso.rest_spring.controller;

import com.compasso.rest_spring.entitys.MathOperations;
import com.compasso.rest_spring.entitys.ValidatingParameters;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operacoes")
public class MathController {
    private MathOperations operacoes = new MathOperations();

    @GetMapping("/soma/{numberOne}/{numberTwo}")
    public Double soma(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        return operacoes.soma(
                ValidatingParameters.convertToDouble(numberOne),
                ValidatingParameters.convertToDouble(numberTwo)
        );
    }

    @GetMapping("/subtracao/{numberOne}/{numberTwo}")
    public Double subtracao(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        return operacoes.subtracao(
                ValidatingParameters.convertToDouble(numberOne),
                ValidatingParameters.convertToDouble(numberTwo)
        );
    }

    @GetMapping("/multiplicacao/{numberOne}/{numberTwo}")
    public Double multiplicacao(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        return operacoes.multiplicacao(
                ValidatingParameters.convertToDouble(numberOne),
                ValidatingParameters.convertToDouble(numberTwo)
        );
    }

    @GetMapping("/divisao/{numberOne}/{numberTwo}")
    public Double divisao(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        return operacoes.divisao(
                ValidatingParameters.convertToDouble(numberOne),
                ValidatingParameters.convertToDouble(numberTwo)
        );
    }

    @GetMapping("/media/{numberOne}/{numberTwo}")
    public Double media(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        return operacoes.media(
                ValidatingParameters.convertToDouble(numberOne),
                ValidatingParameters.convertToDouble(numberTwo)
        );
    }

    @GetMapping("/raiz-quadrada/{numberOne}")
    public Double raizQuadrada(
            @PathVariable(value = "numberOne") String numberOne
    ) {
        return operacoes.raizQuadrada(
                ValidatingParameters.convertToDouble(numberOne)
        );
    }
}
