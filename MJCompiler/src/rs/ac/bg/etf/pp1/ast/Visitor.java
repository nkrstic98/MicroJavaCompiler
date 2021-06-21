// generated with ast extension for cup
// version 0.8
// 21/5/2021 10:0:40


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Unmatched Unmatched);
    public void visit(Mulop Mulop);
    public void visit(Matched Matched);
    public void visit(Relop Relop);
    public void visit(Assignop Assignop);
    public void visit(MethodType MethodType);
    public void visit(OptFormPars OptFormPars);
    public void visit(OptSquare OptSquare);
    public void visit(FormParamDecl FormParamDecl);
    public void visit(Addop Addop);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(Designator Designator);
    public void visit(ConstDeclRest ConstDeclRest);
    public void visit(Term Term);
    public void visit(Condition Condition);
    public void visit(DesignatorAssignment DesignatorAssignment);
    public void visit(CaseList CaseList);
    public void visit(StmtList StmtList);
    public void visit(VarDeclRest VarDeclRest);
    public void visit(VarDeclList VarDeclList);
    public void visit(ConstVal ConstVal);
    public void visit(Expr Expr);
    public void visit(Expr1 Expr1);
    public void visit(ActPars ActPars);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(ActualPars ActualPars);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(ClassDecl ClassDecl);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(OptionalMinus OptionalMinus);
    public void visit(ParamDecl ParamDecl);
    public void visit(ReturnValue ReturnValue);
    public void visit(FormPars FormPars);
    public void visit(ClassMethods ClassMethods);
    public void visit(PrintExpr PrintExpr);
    public void visit(ModOperator ModOperator);
    public void visit(DivOperator DivOperator);
    public void visit(MulOperator MulOperator);
    public void visit(SubOperator SubOperator);
    public void visit(AddOperator AddOperator);
    public void visit(LessEqOperator LessEqOperator);
    public void visit(LessOperator LessOperator);
    public void visit(GreaterEqOperator GreaterEqOperator);
    public void visit(GreaterOperator GreaterOperator);
    public void visit(NotEqualsOperator NotEqualsOperator);
    public void visit(EqualsOperator EqualsOperator);
    public void visit(AssignOperator AssignOperator);
    public void visit(DesObject DesObject);
    public void visit(DesArray DesArray);
    public void visit(DesBasic DesBasic);
    public void visit(NoActuals NoActuals);
    public void visit(Actuals Actuals);
    public void visit(FactExpr FactExpr);
    public void visit(NewArray NewArray);
    public void visit(NewOp NewOp);
    public void visit(BoolConst BoolConst);
    public void visit(CharConst CharConst);
    public void visit(NumConst NumConst);
    public void visit(FuncCall FuncCall);
    public void visit(Var Var);
    public void visit(FactorTerm FactorTerm);
    public void visit(MulopTerm MulopTerm);
    public void visit(NoMinus NoMinus);
    public void visit(Minus Minus);
    public void visit(TermExpr TermExpr);
    public void visit(AddExpr AddExpr);
    public void visit(NoCase NoCase);
    public void visit(MultiCaseList MultiCaseList);
    public void visit(CaseStmt CaseStmt);
    public void visit(SwitchExpretion SwitchExpretion);
    public void visit(BasicExpr BasicExpr);
    public void visit(TernarOperator TernarOperator);
    public void visit(CompareExpr CompareExpr);
    public void visit(CondExpr CondExpr);
    public void visit(SimpleCondTerm SimpleCondTerm);
    public void visit(AndCond AndCond);
    public void visit(SimpleCond SimpleCond);
    public void visit(OrCond OrCond);
    public void visit(SingleParam SingleParam);
    public void visit(MultipleParam MultipleParam);
    public void visit(AssignError AssignError);
    public void visit(Assign Assign);
    public void visit(VarDec VarDec);
    public void visit(VarInc VarInc);
    public void visit(ProcedureCall ProcedureCall);
    public void visit(DesAssign DesAssign);
    public void visit(SinglePrintExpr SinglePrintExpr);
    public void visit(MultiPrintExpr MultiPrintExpr);
    public void visit(NoRetVal NoRetVal);
    public void visit(RetVal RetVal);
    public void visit(NoStmt NoStmt);
    public void visit(MultiStmtList MultiStmtList);
    public void visit(BasicStmt BasicStmt);
    public void visit(PrintStmt PrintStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(RetStmt RetStmt);
    public void visit(ContinueStmt ContinueStmt);
    public void visit(BreakStmt BreakStmt);
    public void visit(YieldStmt YieldStmt);
    public void visit(DoWhileStmt DoWhileStmt);
    public void visit(MatchedIfElse MatchedIfElse);
    public void visit(DesStmt DesStmt);
    public void visit(UnmatchedDerived1 UnmatchedDerived1);
    public void visit(UnmatchedIfElse UnmatchedIfElse);
    public void visit(UnmatchedIf UnmatchedIf);
    public void visit(UnmatchedStmt UnmatchedStmt);
    public void visit(MatchedStmt MatchedStmt);
    public void visit(Type Type);
    public void visit(FormParamDeclDerived1 FormParamDeclDerived1);
    public void visit(FormParam FormParam);
    public void visit(FormParsDerived1 FormParsDerived1);
    public void visit(NoFormParams NoFormParams);
    public void visit(FormParams FormParams);
    public void visit(NoFormPars NoFormPars);
    public void visit(OptFormParams OptFormParams);
    public void visit(VoidRetType VoidRetType);
    public void visit(MethodRetType MethodRetType);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDeclarations NoMethodDeclarations);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(NoClassMethods NoClassMethods);
    public void visit(ClassMethodList ClassMethodList);
    public void visit(ExtendedClass ExtendedClass);
    public void visit(BaseClassDecl BaseClassDecl);
    public void visit(NoVarDecl NoVarDecl);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(NoSquares NoSquares);
    public void visit(Squares Squares);
    public void visit(NoVarDeclMore NoVarDeclMore);
    public void visit(VarDeclMore VarDeclMore);
    public void visit(VarDeclDerived2 VarDeclDerived2);
    public void visit(VarDeclDerived1 VarDeclDerived1);
    public void visit(VarDeclaration VarDeclaration);
    public void visit(BoolConstVal BoolConstVal);
    public void visit(CharConstVal CharConstVal);
    public void visit(NumConstVal NumConstVal);
    public void visit(NoConstDeclMore NoConstDeclMore);
    public void visit(ConstDeclMore ConstDeclMore);
    public void visit(ConstDecl ConstDecl);
    public void visit(ProgName ProgName);
    public void visit(ParamDeclDerived1 ParamDeclDerived1);
    public void visit(ClassParam ClassParam);
    public void visit(VarParam VarParam);
    public void visit(ConstParam ConstParam);
    public void visit(Program Program);

}
