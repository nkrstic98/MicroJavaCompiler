// generated with ast extension for cup
// version 0.8
// 29/5/2021 20:56:1


package rs.ac.bg.etf.pp1.ast;

public class CaseStmt implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private CaseCase CaseCase;
    private CaseDdot CaseDdot;
    private StmtList StmtList;

    public CaseStmt (CaseCase CaseCase, CaseDdot CaseDdot, StmtList StmtList) {
        this.CaseCase=CaseCase;
        if(CaseCase!=null) CaseCase.setParent(this);
        this.CaseDdot=CaseDdot;
        if(CaseDdot!=null) CaseDdot.setParent(this);
        this.StmtList=StmtList;
        if(StmtList!=null) StmtList.setParent(this);
    }

    public CaseCase getCaseCase() {
        return CaseCase;
    }

    public void setCaseCase(CaseCase CaseCase) {
        this.CaseCase=CaseCase;
    }

    public CaseDdot getCaseDdot() {
        return CaseDdot;
    }

    public void setCaseDdot(CaseDdot CaseDdot) {
        this.CaseDdot=CaseDdot;
    }

    public StmtList getStmtList() {
        return StmtList;
    }

    public void setStmtList(StmtList StmtList) {
        this.StmtList=StmtList;
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
        if(CaseCase!=null) CaseCase.accept(visitor);
        if(CaseDdot!=null) CaseDdot.accept(visitor);
        if(StmtList!=null) StmtList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseCase!=null) CaseCase.traverseTopDown(visitor);
        if(CaseDdot!=null) CaseDdot.traverseTopDown(visitor);
        if(StmtList!=null) StmtList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseCase!=null) CaseCase.traverseBottomUp(visitor);
        if(CaseDdot!=null) CaseDdot.traverseBottomUp(visitor);
        if(StmtList!=null) StmtList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CaseStmt(\n");

        if(CaseCase!=null)
            buffer.append(CaseCase.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseDdot!=null)
            buffer.append(CaseDdot.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StmtList!=null)
            buffer.append(StmtList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseStmt]");
        return buffer.toString();
    }
}
