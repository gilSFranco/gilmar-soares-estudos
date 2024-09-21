package com.compasso.testes_automatizados;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testSum(){
        Calculator calculadora = new Calculator();
        assertThat(calculadora.sum(1, 1)).isEqualTo(2);
    }
}
