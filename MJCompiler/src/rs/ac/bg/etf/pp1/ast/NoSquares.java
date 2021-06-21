// generated with ast extension for cup
// version 0.8
// 21/5/2021 20:28:6


package rs.ac.bg.etf.pp1.ast;

public class NoSquares extends OptSquare {

    public NoSquares () {
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
        buffer.append("NoSquares(\n");

        buffer.append(tab);
        buffer.append(") [NoSquares]");
        return buffer.toString();
    }
}
