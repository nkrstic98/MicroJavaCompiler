// generated with ast extension for cup
// version 0.8
// 21/5/2021 10:0:40


package rs.ac.bg.etf.pp1.ast;

public class LessEqOperator extends Relop {

    public LessEqOperator () {
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
        buffer.append("LessEqOperator(\n");

        buffer.append(tab);
        buffer.append(") [LessEqOperator]");
        return buffer.toString();
    }
}