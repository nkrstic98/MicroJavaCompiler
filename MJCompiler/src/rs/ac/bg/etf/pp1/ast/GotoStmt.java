// generated with ast extension for cup
// version 0.8
// 29/5/2021 20:56:1


package rs.ac.bg.etf.pp1.ast;

public class GotoStmt extends Statement {

    private String labelname;

    public GotoStmt (String labelname) {
        this.labelname=labelname;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname=labelname;
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
        buffer.append("GotoStmt(\n");

        buffer.append(" "+tab+labelname);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GotoStmt]");
        return buffer.toString();
    }
}
