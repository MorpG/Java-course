package ru.agolovin;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorTest{
	@Test
	public void whenUseAddThenReturnResultAdd() {
		Calculator calc = new Calculator();
		calc.add(5, 3);
		double result = calc.getResult();
		assertThat(result, is(8.0));
	}

	@Test
	public void whenUseSubStructThenReturnResultSubstruct() {
		Calculator calc = new Calculator();
		calc.substruct(2, 4);
		double result = calc.getResult();
		assertThat(result, is(-2.0));
	}

	@Test
	public void whenUseDivThenReturnResultDiv() {
		Calculator calc = new Calculator();
		calc.div(6 , 2);
		double result = calc.getResult();
		assertThat(result, is(3.0));
	}

	@Test
	public void whenUseMultipleThenReturnResultMultiple() {
		Calculator calc = new Calculator();
		calc.multiple(5 , 2);
		double result = calc.getResult();
		assertThat(result, is(10.0));
	}
}