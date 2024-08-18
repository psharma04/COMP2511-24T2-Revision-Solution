package unsw.calculator.view;

import unsw.calculator.model.EvaluatorVisitor;
import unsw.calculator.model.Parser;
import unsw.calculator.model.tree.TreeNode;

public class EvaluatorAdapter implements Evaluator {
    public int evaluate(String expression){
        System.out.println(expression);

        Parser p = new Parser(expression);
        TreeNode  root = p.parse();
 
        EvaluatorVisitor ev = new EvaluatorVisitor();

        root.accept(ev);

        return (int)ev.getValue();
 
    }
}