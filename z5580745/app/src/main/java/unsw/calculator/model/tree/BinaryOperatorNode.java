package unsw.calculator.model.tree;

/*
 * Tree node that contains two children
 */
public abstract class BinaryOperatorNode implements TreeNode  {

    private TreeNode left, right;

    public BinaryOperatorNode() {
        this.left = null;
        this.right = null;
    }

    public BinaryOperatorNode(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

    public TreeNode getLeft() {
        return this.left;
    }

    public TreeNode getRight()  {
        return this.right;
    }

    /**
     * Apply this operator (+,-,*,/ etc.) to the given operands
     */
    public abstract int compute(int a, int b);
    
    /**
     * Returns a textual representation of the node.
     */
    public abstract String getLabel();

}