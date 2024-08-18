package unsw.calculator.model;

import unsw.calculator.model.tree.BinaryOperatorNode;
import unsw.calculator.model.tree.NumericNode;
import unsw.calculator.model.tree.TreeNode;

public class InFixPrintVisitor implements Visitor {

    @Override
    public void visitBinaryOperatorNode(BinaryOperatorNode node) {
        infixPrint(node);
    }

    @Override
    public void visitNumericNode(NumericNode node) {
        infixPrint(node);
    }

    
    public void infixPrint(TreeNode node)  {
        if (node instanceof NumericNode){
            System.out.print(node.getLabel());
            return;
        }
        System.out.print("(");

        if (node.getLeft() != null) {
            infixPrint(node.getLeft());
        }

        System.out.print(" " + node.getLabel() + " ");

        if (node.getRight() != null) {
            infixPrint(node.getRight());
        }
        
        System.out.print(")");
    }


}