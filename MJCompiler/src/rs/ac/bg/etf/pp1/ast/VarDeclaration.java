// generated with ast extension for cup
// version 0.8
// 20/5/2021 20:9:38


package rs.ac.bg.etf.pp1.ast;

public class VarDeclaration extends VarDecl {

    private Type Type;
    private String varName;
    private OptSquare OptSquare;
    private VarDeclRest VarDeclRest;

    public VarDeclaration (Type Type, String varName, OptSquare OptSquare, VarDeclRest VarDeclRest) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varName=varName;
        this.OptSquare=OptSquare;
        if(OptSquare!=null) OptSquare.setParent(this);
        this.VarDeclRest=VarDeclRest;
        if(VarDeclRest!=null) VarDeclRest.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public OptSquare getOptSquare() {
        return OptSquare;
    }

    public void setOptSquare(OptSquare OptSquare) {
        this.OptSquare=OptSquare;
    }

    public VarDeclRest getVarDeclRest() {
        return VarDeclRest;
    }

    public void setVarDeclRest(VarDeclRest VarDeclRest) {
        this.VarDeclRest=VarDeclRest;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(OptSquare!=null) OptSquare.accept(visitor);
        if(VarDeclRest!=null) VarDeclRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(OptSquare!=null) OptSquare.traverseTopDown(visitor);
        if(VarDeclRest!=null) VarDeclRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(OptSquare!=null) OptSquare.traverseBottomUp(visitor);
        if(VarDeclRest!=null) VarDeclRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclaration(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(OptSquare!=null)
            buffer.append(OptSquare.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclRest!=null)
            buffer.append(VarDeclRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclaration]");
        return buffer.toString();
    }
}
