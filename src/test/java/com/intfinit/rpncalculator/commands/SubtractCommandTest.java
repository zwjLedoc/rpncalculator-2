package com.intfinit.rpncalculator.commands;

import com.intfinit.rpncalculator.Model;
import com.intfinit.rpncalculator.RPNCalculatorException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SubtractCommandTest {

    private Model model;

    @Before
    public void setUp() {
        model = new Model();
    }

    @Test(expected = NullPointerException.class)
    public void executeWithNullModel() throws RPNCalculatorException {
        Command cmd = new SubtractCommand(null);
        cmd.execute();
    }

    @Test(expected = NullPointerException.class)
    public void undoWithNullModel() throws RPNCalculatorException {
        Command cmd = new SubtractCommand(null);
        cmd.undo();
    }

    @Test(expected = RPNCalculatorException.class)
    public void executeWithEmptyModel() throws RPNCalculatorException {
        Command cmd = new SubtractCommand(model);
        cmd.execute();
    }

    @Test(expected = RPNCalculatorException.class)
    public void undoWithEmptyModel() throws RPNCalculatorException {
        Command cmd = new SubtractCommand(model);
        cmd.undo();
    }

    @Test(expected = RPNCalculatorException.class)
    public void executeWithInsuficientOperands() throws RPNCalculatorException {
        model.push(new BigDecimal(1));
        Command cmd = new SubtractCommand(model);
        cmd.execute();
    }

    @Test
    public void executeSuccess() throws RPNCalculatorException {
        model.push(new BigDecimal(1));
        model.push(new BigDecimal(1));
        Command cmd = new SubtractCommand(model);
        cmd.execute();
        assertThat(model.toString()).isEqualTo("stack: 0");
    }

    @Test
    public void executeAndUndoSuccess() throws RPNCalculatorException {
        model.push(new BigDecimal(1));
        model.push(new BigDecimal(1));
        Command cmd = new SubtractCommand(model);
        cmd.execute();
        cmd.undo();
        assertThat(model.toString()).isEqualTo("stack: 1 1");
    }
}