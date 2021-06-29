// generated with ast extension for cup
// version 0.8
// 29/5/2021 0:22:17


package rs.ac.bg.etf.pp1.ast;

public class CaseCase implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Integer N1;

    public CaseCase (Integer N1) {
        this.N1=N1;
    }

    public Integer getN1() {
        return N1;
    }

    public void setN1(Integer N1) {
        this.N1=N1;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
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
        buffer.append("CaseCase(\n");

        buffer.append(" "+tab+N1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseCase]");
        return buffer.toString();
    }
}
