package unsw.calculator.model;

import unsw.calculator.model.tree.BinaryOperatorNode;
import unsw.calculator.model.tree.NumericNode;

public class EvaluatorVisitor implements Visitor {
    private double result = 0.0;

    public void visitBinaryOperatorNode(BinaryOperatorNode node) {
        node.getLeft().accept_recurse(this);
        double leftValue = result;

        node.getRight().accept_recurse(this);
        double rightValue = result;

        switch (node.getLabel()) {
            case "+":
                result = leftValue + rightValue;
                break;
            case "-":
                result = leftValue - rightValue;
                break;
            case "*":
                result = leftValue * rightValue;
                break;
            case "/":
                result = leftValue / rightValue;
                break;
            default:
                throw new AssertionError();
        }

    }

    public void visitNumericNode(NumericNode node) {

        result = node.getValue();
    }

    public double getValue(){
        return result;  
    }

    public void printResult() {
        System.out.println(result);
    }
}