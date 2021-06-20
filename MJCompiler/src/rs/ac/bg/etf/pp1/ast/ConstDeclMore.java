// generated with ast extension for cup
// version 0.8
// 20/5/2021 20:9:38


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclMore extends ConstDeclRest {

    private ConstDeclRest ConstDeclRest;
    private String constName;
    private ConstVal ConstVal;

    public ConstDeclMore (ConstDeclRest ConstDeclRest, String constName, ConstVal ConstVal) {
        this.ConstDeclRest=ConstDeclRest;
        if(ConstDeclRest!=null) ConstDeclRest.setParent(this);
        this.constName=constName;
        this.ConstVal=ConstVal;
        if(ConstVal!=null) ConstVal.setParent(this);
    }

    public ConstDeclRest getConstDeclRest() {
        return ConstDeclRest;
    }

    public void setConstDeclRest(ConstDeclRest ConstDeclRest) {
        this.ConstDeclRest=ConstDeclRest;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public ConstVal getConstVal() {
        return ConstVal;
    }

    public void setConstVal(ConstVal ConstVal) {
        this.ConstVal=ConstVal;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclRest!=null) ConstDeclRest.accept(visitor);
        if(ConstVal!=null) ConstVal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclRest!=null) ConstDeclRest.traverseTopDown(visitor);
        if(ConstVal!=null) ConstVal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclRest!=null) ConstDeclRest.traverseBottomUp(visitor);
        if(ConstVal!=null) ConstVal.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclMore(\n");

        if(ConstDeclRest!=null)
            buffer.append(ConstDeclRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(ConstVal!=null)
            buffer.append(ConstVal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclMore]");
        return buffer.toString();
    }
}
