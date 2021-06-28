// generated with ast extension for cup
// version 0.8
// 28/5/2021 19:53:0


package rs.ac.bg.etf.pp1.ast;

public class Squares extends OptSquare {

    public Squares () {
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
        buffer.append("Squares(\n");

        buffer.append(tab);
        buffer.append(") [Squares]");
        return buffer.toString();
    }
}
