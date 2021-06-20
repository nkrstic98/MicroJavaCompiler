// generated with ast extension for cup
// version 0.8
// 20/5/2021 20:9:38


package rs.ac.bg.etf.pp1.ast;

public class SwitchExpretion extends Expr {

    private Expr Expr;
    private CaseStmt CaseStmt;

    public SwitchExpretion (Expr Expr, CaseStmt CaseStmt) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.CaseStmt=CaseStmt;
        if(CaseStmt!=null) CaseStmt.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public CaseStmt getCaseStmt() {
        return CaseStmt;
    }

    public void setCaseStmt(CaseStmt CaseStmt) {
        this.CaseStmt=CaseStmt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(CaseStmt!=null) CaseStmt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(CaseStmt!=null) CaseStmt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(CaseStmt!=null) CaseStmt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchExpretion(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseStmt!=null)
            buffer.append(CaseStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchExpretion]");
        return buffer.toString();
    }
}
