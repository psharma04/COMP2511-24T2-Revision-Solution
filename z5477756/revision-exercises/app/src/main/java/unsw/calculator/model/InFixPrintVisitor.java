package unsw.calculator.model;

import unsw.calculator.model.tree.BinaryOperatorNode;
import unsw.calculator.model.tree.NumericNode;

public class InFixPrintVisitor implements Visitor {
    String result = "";

    public void visitBinaryOperatorNode(BinaryOperatorNode node) {
        result = result + "(";
        node.getLeft().accept_recurse(this);
        result = result + " ";
        result = result + node.getLabel();
        result = result + " ";
        node.getRight().accept_recurse(this); 
        result = result + ")";

    }

    public void visitNumericNode(NumericNode node) {

        result = result + node.getLabel();
    }

    public void printResult(){
        System.out.println(result);
    }
}