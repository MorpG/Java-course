package ru.agolovin;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculateTest{
	@Test
	public void whenUseAddThenReturnResultAdd() {
		Calculate calc = new Calculate();
		double result = calc.add(2, 4);
		assertThat(result, is(6.0));
	}

	@Test
	public void whenUseSubStructThenReturnResultSubstruct() {
		Calculate calc = new Calculate();
		double result = calc.substruct(2, 4);
		assertThat(result, is(-2.0));
	}

	@Test
	public void whenUseDivThenReturnResultDiv() {
		Calculate calc = new Calculate();
		double result = calc.div(6 , 2);
		assertThat(result, is(3.0));
	}

	@Test
	public void whenUseMultipleThenReturnResultMultiple() {
		Calculate calc = new Calculate();
		double result = calc.multiple(5 , 2);
		assertThat(result, is(10.0));
	}
}