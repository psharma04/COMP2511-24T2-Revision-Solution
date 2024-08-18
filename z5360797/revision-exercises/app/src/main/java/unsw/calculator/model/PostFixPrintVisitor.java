package unsw.calculator.model;

import unsw.calculator.model.tree.BinaryOperatorNode;
import unsw.calculator.model.tree.NumericNode;

public class PostFixPrintVisitor implements Visitor {

    @Override
    public void visitBinaryOperatorNode(BinaryOperatorNode node) {
        node.acceptLeft(this);
        node.acceptRight(this);
        System.out.print(node.getLabel());
        System.out.print(" ");
    }

    @Override
    public void visitNumericNode(NumericNode node) {
        System.out.print(node.getLabel());
        System.out.print(" ");
    }

}
