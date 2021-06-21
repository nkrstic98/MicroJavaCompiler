// generated with ast extension for cup
// version 0.8
// 21/5/2021 15:25:53


package rs.ac.bg.etf.pp1.ast;

public class DesAssign extends DesignatorStatement {

    private DesignatorAssignment DesignatorAssignment;

    public DesAssign (DesignatorAssignment DesignatorAssignment) {
        this.DesignatorAssignment=DesignatorAssignment;
        if(DesignatorAssignment!=null) DesignatorAssignment.setParent(this);
    }

    public DesignatorAssignment getDesignatorAssignment() {
        return DesignatorAssignment;
    }

    public void setDesignatorAssignment(DesignatorAssignment DesignatorAssignment) {
        this.DesignatorAssignment=DesignatorAssignment;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorAssignment!=null) DesignatorAssignment.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorAssignment!=null) DesignatorAssignment.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorAssignment!=null) DesignatorAssignment.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesAssign(\n");

        if(DesignatorAssignment!=null)
            buffer.append(DesignatorAssignment.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesAssign]");
        return buffer.toString();
    }
}
