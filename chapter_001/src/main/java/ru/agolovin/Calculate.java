package ru.agolovin;

public class Calculate{
	public double result;

	public double add(double first, double second) {
		result = first + second;
		return result;
	}

	public double substruct(double first, double second) {
		result = first - second;
		return result;
	}

	public double div(double first, double second) {
		result = first / second;
		return result;
	}

	public double multiple(double first, double second) {
		result = first * second;
		return result;
	}

}