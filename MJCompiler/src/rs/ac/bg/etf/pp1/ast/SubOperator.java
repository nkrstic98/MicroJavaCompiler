// generated with ast extension for cup
// version 0.8
// 24/5/2021 9:35:34


package rs.ac.bg.etf.pp1.ast;

public class SubOperator extends Addop {

    public SubOperator () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SubOperator(\n");

        buffer.append(tab);
        buffer.append(") [SubOperator]");
        return buffer.toString();
    }
}
