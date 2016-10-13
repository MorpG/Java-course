package ru.agolovin;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculateTest{
	@Test
	public void whenUseAddThenReturnResultAdd() {
		Calculate calc = new Calculate();
		calc.add(5, 3);
		double result = calc.getResult();
		assertThat(result, is(8.0));
	}

	@Test
	public void whenUseSubStructThenReturnResultSubstruct() {
		Calculate calc = new Calculate();
		calc.substruct(2, 4);
		double result = calc.getResult();
		assertThat(result, is(-2.0));
	}

	@Test
	public void whenUseDivThenReturnResultDiv() {
		Calculate calc = new Calculate();
		calc.div(6 , 2);
		double result = calc.getResult();
		assertThat(result, is(3.0));
	}

	@Test
	public void whenUseMultipleThenReturnResultMultiple() {
		Calculate calc = new Calculate();
		calc.multiple(5 , 2);
		double result = calc.getResult();
		assertThat(result, is(10.0));
	}
}