package unsw.calculator.model;

import java.util.Stack;

import unsw.calculator.model.tree.BinaryOperatorNode;
import unsw.calculator.model.tree.NumericNode;
import unsw.calculator.model.tree.TreeNode;

public class EvaluatorVisitor implements Visitor{

    private Stack<String> stack = new Stack<>();

    @Override
    public void visitBinaryOperatorNode(BinaryOperatorNode node) {
        postfixEvaluate(node);
    }

    @Override
    public void visitNumericNode(NumericNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitNumericNode'");
    } 

    public void postfixEvaluate(TreeNode node){
        if (node instanceof NumericNode){
            stack.push(node.getLabel());
        }

        if (node.getLeft() != null) {
            postfixEvaluate(node.getLeft());
        }


        if (node.getRight() != null) {
            postfixEvaluate(node.getRight());
        }

        
        String label = node.getLabel();
        stack.push(label);

        
        
    }

    // public int evaluate(){

    //     while(!stack.isEmpty()){

    //     }

    // }

}