package unsw.calculator.view;

import unsw.calculator.model.EvaluatorVisitor;
import unsw.calculator.model.Parser;
import unsw.calculator.model.tree.TreeNode;

public class EvaluatorAdapter implements Evaluator {

    @Override
    public int evaluate(String expression) {
        Parser parser = new Parser(expression);
        TreeNode node = parser.parse();
        EvaluatorVisitor visitor = new EvaluatorVisitor();
        node.accept(visitor);
        return visitor.getValue();
    }

}
