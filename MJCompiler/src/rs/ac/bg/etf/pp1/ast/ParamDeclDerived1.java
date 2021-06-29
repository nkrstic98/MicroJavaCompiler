// generated with ast extension for cup
// version 0.8
// 29/5/2021 20:56:1


package rs.ac.bg.etf.pp1.ast;

public class ParamDeclDerived1 extends ParamDecl {

    public ParamDeclDerived1 () {
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
        buffer.append("ParamDeclDerived1(\n");

        buffer.append(tab);
        buffer.append(") [ParamDeclDerived1]");
        return buffer.toString();
    }
}
