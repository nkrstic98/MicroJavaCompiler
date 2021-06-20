// generated with ast extension for cup
// version 0.8
// 20/5/2021 23:12:27


package rs.ac.bg.etf.pp1.ast;

public class OptFormParams extends OptFormPars {

    private FormParamDecl FormParamDecl;
    private FormPars FormPars;

    public OptFormParams (FormParamDecl FormParamDecl, FormPars FormPars) {
        this.FormParamDecl=FormParamDecl;
        if(FormParamDecl!=null) FormParamDecl.setParent(this);
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
    }

    public FormParamDecl getFormParamDecl() {
        return FormParamDecl;
    }

    public void setFormParamDecl(FormParamDecl FormParamDecl) {
        this.FormParamDecl=FormParamDecl;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParamDecl!=null) FormParamDecl.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParamDecl!=null) FormParamDecl.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParamDecl!=null) FormParamDecl.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptFormParams(\n");

        if(FormParamDecl!=null)
            buffer.append(FormParamDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptFormParams]");
        return buffer.toString();
    }
}
