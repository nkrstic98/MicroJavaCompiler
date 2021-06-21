// generated with ast extension for cup
// version 0.8
// 21/5/2021 10:0:40


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private String constName;
    private ConstVal ConstVal;
    private ConstDeclRest ConstDeclRest;

    public ConstDecl (Type Type, String constName, ConstVal ConstVal, ConstDeclRest ConstDeclRest) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.constName=constName;
        this.ConstVal=ConstVal;
        if(ConstVal!=null) ConstVal.setParent(this);
        this.ConstDeclRest=ConstDeclRest;
        if(ConstDeclRest!=null) ConstDeclRest.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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

    public ConstDeclRest getConstDeclRest() {
        return ConstDeclRest;
    }

    public void setConstDeclRest(ConstDeclRest ConstDeclRest) {
        this.ConstDeclRest=ConstDeclRest;
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
        if(Type!=null) Type.accept(visitor);
        if(ConstVal!=null) ConstVal.accept(visitor);
        if(ConstDeclRest!=null) ConstDeclRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstVal!=null) ConstVal.traverseTopDown(visitor);
        if(ConstDeclRest!=null) ConstDeclRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstVal!=null) ConstVal.traverseBottomUp(visitor);
        if(ConstDeclRest!=null) ConstDeclRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
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

        if(ConstDeclRest!=null)
            buffer.append(ConstDeclRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
