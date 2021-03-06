package com.intfinit.rpncalculator.commands;

import com.intfinit.rpncalculator.Model;
import com.intfinit.rpncalculator.RPNCalculatorException;

import java.math.BigDecimal;

public abstract class MathOperationCommand extends AbstractCommand {

    MathOperationCommand(Model model, int numOfOperands) {
        super(model);
        setOperands(new BigDecimal[numOfOperands]);
    }

    protected void init() throws RPNCalculatorException {
        BigDecimal[] currentOperands = getOperands();
        if (getNumberOfOperands() > getModel().size()) {
            throw new RPNCalculatorException("insufficient parameters.");
        }

        for (int operandCount = currentOperands.length; operandCount > 0; ) {
            currentOperands[--operandCount] = getModel().pop();
        }
    }

    private int getNumberOfOperands() {
        int size = 0;
        BigDecimal[] operands = getOperands();
        if (operands != null) {
            size = operands.length;
        }

        return size;
    }

    @Override
    public void undo() throws RPNCalculatorException {
        getModel().pop();
        BigDecimal[] currentOperands = getOperands();
        for (BigDecimal currentOperand : currentOperands) {
            getModel().push(currentOperand);
        }
    }
}
