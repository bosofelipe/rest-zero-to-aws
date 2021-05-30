package com.boso.calculatorapi.controller;

import static com.boso.calculatorapi.converter.NumberConverter.isNumeric;
import static com.boso.calculatorapi.converter.NumberConverter.toDouble;

import com.boso.calculatorapi.exception.UnsupportedMathOperationException;
import com.boso.calculatorapi.math.MathOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

  private final MathOperation operation = new MathOperation();

  @RequestMapping(method = RequestMethod.GET, value = "/sum/{numberOne}/{numberTwo}")
  public Double sum(@PathVariable("numberOne") String numberOne,
      @PathVariable("numberTwo") String numberTwo) throws Exception {
    if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
      throw new UnsupportedMathOperationException("Only numeric value (0-9) is supported!");
    }
    return operation.sum(toDouble(numberOne), toDouble(numberTwo));
  }

  @RequestMapping(method = RequestMethod.GET, value = "/subtraction/{numberOne}/{numberTwo}")
  public Double subtraction(@PathVariable("numberOne") String numberOne,
      @PathVariable("numberTwo") String numberTwo) throws Exception {
    if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
      throw new UnsupportedMathOperationException("Only numeric value (0-9) is supported!");
    }
    return operation.subtraction(toDouble(numberOne), toDouble(numberTwo));
  }

  @RequestMapping(method = RequestMethod.GET, value = "/multiplication/{numberOne}/{numberTwo}")
  public Double multiplication(@PathVariable("numberOne") String numberOne,
      @PathVariable("numberTwo") String numberTwo) throws Exception {
    if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
      throw new UnsupportedMathOperationException("Only numeric value (0-9) is supported!");
    }
    return operation.multiplication(toDouble(numberOne), toDouble(numberTwo));
  }

  @RequestMapping(method = RequestMethod.GET, value = "/division/{numberOne}/{numberTwo}")
  public Double division(@PathVariable("numberOne") String numberOne,
      @PathVariable("numberTwo") String numberTwo) throws Exception {
    if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
      throw new UnsupportedMathOperationException("Only numeric value (0-9) is supported!");
    }
    return operation.division(toDouble(numberOne), toDouble(numberTwo));
  }

  @RequestMapping(method = RequestMethod.GET, value = "/average/{numberOne}/{numberTwo}")
  public Double average(@PathVariable("numberOne") String numberOne,
      @PathVariable("numberTwo") String numberTwo) throws Exception {
    if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
      throw new UnsupportedMathOperationException("Only numeric value (0-9) is supported!");
    }
    return operation.average(toDouble(numberOne), toDouble(numberTwo));
  }

  @RequestMapping(method = RequestMethod.GET, value = "/squareRoot/{number}")
  public Double squareRoot(@PathVariable("number") String number) throws Exception {
    if (!isNumeric(number)) {
      throw new UnsupportedMathOperationException("Only numeric value (0-9) is supported!");
    }
    return operation.squareRoot(toDouble(number));
  }
}
