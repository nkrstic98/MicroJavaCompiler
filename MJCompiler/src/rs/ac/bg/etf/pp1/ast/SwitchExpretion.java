// generated with ast extension for cup
// version 0.8
// 29/5/2021 0:22:17


package rs.ac.bg.etf.pp1.ast;

public class SwitchExpretion extends Expr {

    private SwitchExpr SwitchExpr;
    private Expr Expr;
    private CaseStatements CaseStatements;

    public SwitchExpretion (SwitchExpr SwitchExpr, Expr Expr, CaseStatements CaseStatements) {
        this.SwitchExpr=SwitchExpr;
        if(SwitchExpr!=null) SwitchExpr.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.CaseStatements=CaseStatements;
        if(CaseStatements!=null) CaseStatements.setParent(this);
    }

    public SwitchExpr getSwitchExpr() {
        return SwitchExpr;
    }

    public void setSwitchExpr(SwitchExpr SwitchExpr) {
        this.SwitchExpr=SwitchExpr;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public CaseStatements getCaseStatements() {
        return CaseStatements;
    }

    public void setCaseStatements(CaseStatements CaseStatements) {
        this.CaseStatements=CaseStatements;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SwitchExpr!=null) SwitchExpr.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(CaseStatements!=null) CaseStatements.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchExpr!=null) SwitchExpr.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(CaseStatements!=null) CaseStatements.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchExpr!=null) SwitchExpr.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(CaseStatements!=null) CaseStatements.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchExpretion(\n");

        if(SwitchExpr!=null)
            buffer.append(SwitchExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseStatements!=null)
            buffer.append(CaseStatements.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchExpretion]");
        return buffer.toString();
    }
}
