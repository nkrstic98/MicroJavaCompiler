// generated with ast extension for cup
// version 0.8
// 20/5/2021 20:9:38


package rs.ac.bg.etf.pp1.ast;

public class FormParsDerived1 extends FormPars {

    public FormParsDerived1 () {
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
        buffer.append("FormParsDerived1(\n");

        buffer.append(tab);
        buffer.append(") [FormParsDerived1]");
        return buffer.toString();
    }
}
