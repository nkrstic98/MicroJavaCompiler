// generated with ast extension for cup
// version 0.8
// 21/5/2021 15:25:53


package rs.ac.bg.etf.pp1.ast;

public class UnmatchedDerived1 extends Unmatched {

    public UnmatchedDerived1 () {
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
        buffer.append("UnmatchedDerived1(\n");

        buffer.append(tab);
        buffer.append(") [UnmatchedDerived1]");
        return buffer.toString();
    }
}
