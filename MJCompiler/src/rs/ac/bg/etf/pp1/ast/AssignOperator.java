// generated with ast extension for cup
// version 0.8
// 20/5/2021 23:12:27


package rs.ac.bg.etf.pp1.ast;

public class AssignOperator extends Assignop {

    public AssignOperator () {
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
        buffer.append("AssignOperator(\n");

        buffer.append(tab);
        buffer.append(") [AssignOperator]");
        return buffer.toString();
    }
}
