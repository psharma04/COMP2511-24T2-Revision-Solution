package unsw.calculator.model;

import unsw.calculator.model.tree.BinaryOperatorNode;
import unsw.calculator.model.tree.NumericNode;

public class EvaluatorVisitor implements Visitor {
    int result = 0;

    @Override
    public void visitBinaryOperatorNode(BinaryOperatorNode node) {
        node.acceptLeft(this);
        int left = result;
        node.acceptRight(this);
        int right = result;
        result = node.compute(left, right);
    }

    @Override
    public void visitNumericNode(NumericNode node) {
        result = node.getValue();
    }

    public int getValue() {
        return result;
    }

}
