package com.steps;


import static org.junit.Assert.assertNotNull;

import com.sapient.impl.Calculator;


import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CalculatorSteps {

private Calculator calculater;

@Before
public void setUp() {
	calculater = new Calculator();
}

@Given("^i have a calculator$")
public void i_have_a_calculator() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	//assert (calculater) != null;
	assertNotNull(calculater);
}

@When("^I add (\\d+) and (\\d+)$")
public void i_add_and(int arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	calculater.add(arg1, arg2);
}

@When("^I subtract (\\d+) and (\\d+)$")
public void i_subtract_and(int arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    calculater.subtract(arg1, arg2);
}

@When("^I multiply Input(\\d+) <val(\\d+)> and Input(\\d+) <val(\\d+)>$")
public void i_multiply_Input_val_and_Input_val(int arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	calculater.Multiply(arg1, arg2);
}

@Then("^the result should be (\\d+)$")
public void the_result_should_be(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	calculater.getResult();
}
}