// generated with ast extension for cup
// version 0.8
// 20/5/2021 20:9:38


package rs.ac.bg.etf.pp1.ast;

public class RetStmt extends Matched {

    private ReturnValue ReturnValue;

    public RetStmt (ReturnValue ReturnValue) {
        this.ReturnValue=ReturnValue;
        if(ReturnValue!=null) ReturnValue.setParent(this);
    }

    public ReturnValue getReturnValue() {
        return ReturnValue;
    }

    public void setReturnValue(ReturnValue ReturnValue) {
        this.ReturnValue=ReturnValue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ReturnValue!=null) ReturnValue.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReturnValue!=null) ReturnValue.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReturnValue!=null) ReturnValue.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RetStmt(\n");

        if(ReturnValue!=null)
            buffer.append(ReturnValue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RetStmt]");
        return buffer.toString();
    }
}
