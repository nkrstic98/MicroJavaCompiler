// generated with ast extension for cup
// version 0.8
// 20/5/2021 20:9:38


package rs.ac.bg.etf.pp1.ast;

public class ClassParam extends ParamDecl {

    private ParamDecl ParamDecl;
    private ClassDecl ClassDecl;

    public ClassParam (ParamDecl ParamDecl, ClassDecl ClassDecl) {
        this.ParamDecl=ParamDecl;
        if(ParamDecl!=null) ParamDecl.setParent(this);
        this.ClassDecl=ClassDecl;
        if(ClassDecl!=null) ClassDecl.setParent(this);
    }

    public ParamDecl getParamDecl() {
        return ParamDecl;
    }

    public void setParamDecl(ParamDecl ParamDecl) {
        this.ParamDecl=ParamDecl;
    }

    public ClassDecl getClassDecl() {
        return ClassDecl;
    }

    public void setClassDecl(ClassDecl ClassDecl) {
        this.ClassDecl=ClassDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ParamDecl!=null) ParamDecl.accept(visitor);
        if(ClassDecl!=null) ClassDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ParamDecl!=null) ParamDecl.traverseTopDown(visitor);
        if(ClassDecl!=null) ClassDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ParamDecl!=null) ParamDecl.traverseBottomUp(visitor);
        if(ClassDecl!=null) ClassDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassParam(\n");

        if(ParamDecl!=null)
            buffer.append(ParamDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDecl!=null)
            buffer.append(ClassDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassParam]");
        return buffer.toString();
    }
}
