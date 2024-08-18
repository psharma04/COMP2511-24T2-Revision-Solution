package unsw.calculator.model;

import unsw.calculator.model.tree.BinaryOperatorNode;
import unsw.calculator.model.tree.NumericNode;
import unsw.calculator.model.tree.TreeNode;

public class PostFixPrintVisitor implements Visitor{

    @Override
    public void visitBinaryOperatorNode(BinaryOperatorNode node) {
        postfixPrint(node);
    }

    @Override
    public void visitNumericNode(NumericNode node) {
        postfixPrint(node);
    }
    
    
    public void postfixPrint(TreeNode node){
        if (node instanceof NumericNode){
            System.out.print(node.getLabel());
            return;
        }
        System.out.print("(");

        if (node.getLeft() != null) {
            postfixPrint(node.getLeft());
        }

        

        if (node.getRight() != null) {
            postfixPrint(node.getRight());
        }

        System.out.print(" " + node.getLabel() + " ");
        
        System.out.print(")");
    }

}