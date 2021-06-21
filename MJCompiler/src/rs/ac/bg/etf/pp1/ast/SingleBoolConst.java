// generated with ast extension for cup
// version 0.8
// 21/5/2021 15:25:53


package rs.ac.bg.etf.pp1.ast;

public class SingleBoolConst extends ConstAssign {

    private String constName;
    private String constVal;

    public SingleBoolConst (String constName, String constVal) {
        this.constName=constName;
        this.constVal=constVal;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public String getConstVal() {
        return constVal;
    }

    public void setConstVal(String constVal) {
        this.constVal=constVal;
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
        buffer.append("SingleBoolConst(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        buffer.append(" "+tab+constVal);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleBoolConst]");
        return buffer.toString();
    }
}
