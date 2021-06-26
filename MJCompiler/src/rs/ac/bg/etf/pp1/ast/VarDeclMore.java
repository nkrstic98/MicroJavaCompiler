// generated with ast extension for cup
// version 0.8
// 26/5/2021 20:36:52


package rs.ac.bg.etf.pp1.ast;

public class VarDeclMore extends VarDeclRest {

    private VarDeclRest VarDeclRest;
    private String varName;

    public VarDeclMore (VarDeclRest VarDeclRest, String varName) {
        this.VarDeclRest=VarDeclRest;
        if(VarDeclRest!=null) VarDeclRest.setParent(this);
        this.varName=varName;
    }

    public VarDeclRest getVarDeclRest() {
        return VarDeclRest;
    }

    public void setVarDeclRest(VarDeclRest VarDeclRest) {
        this.VarDeclRest=VarDeclRest;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclRest!=null) VarDeclRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclRest!=null) VarDeclRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclRest!=null) VarDeclRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclMore(\n");

        if(VarDeclRest!=null)
            buffer.append(VarDeclRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclMore]");
        return buffer.toString();
    }
}
