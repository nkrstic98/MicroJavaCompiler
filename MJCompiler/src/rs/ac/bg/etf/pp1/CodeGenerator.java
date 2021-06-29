package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPc;
	
	public CodeGenerator() {
		initializePredeclaredMethods();
	}
	
	private void initializePredeclaredMethods() {
		Obj ord = Tab.find("ord");
		ord.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		Obj chr = Tab.find("chr");
		chr.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		Obj len = Tab.find("len");
		len.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public int getMainPc() {
		return mainPc;
	}
	
	private int getRelOp(Relop relop) {
		if(relop instanceof EqualsOperator)
			return Code.eq;
		else if(relop instanceof NotEqualsOperator)
			return Code.ne;
		else if(relop instanceof GreaterOperator)
			return Code.gt;
		else if(relop instanceof GreaterEqOperator)
			return Code.ge;
		else if(relop instanceof LessOperator)
			return Code.lt;
		else if(relop instanceof LessEqOperator)
			return Code.le;
		
		return 0;
	}
	

	/*=========================== Method Call ==========================*/
	public void visit(MethodTypeName methodTypeName) {
		methodTypeName.obj.setAdr(Code.pc);
		
		if(methodTypeName.getMethName().equals("main")) {
			mainPc = Code.pc;
		}
		
		int fpCnt = 0;
		for(Obj obj: methodTypeName.obj.getLocalSymbols())
			fpCnt += obj.getFpPos();
		
		Code.put(Code.enter);
		Code.put(fpCnt);
		Code.put(methodTypeName.obj.getLocalSymbols().size());
	}
	
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	/*===================================================================*/
	
	
	/*=========================== Statement =============================*/
	public void visit(RetStmt retStmt) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(RetNoValStmt retNoValStmt) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ReadStmt readStmt) {
		if(readStmt.getDesignator().obj.getType().equals(Tab.charType))
			Code.put(Code.bread);
		else
			Code.put(Code.read);
		Code.store(readStmt.getDesignator().obj);
	}
	
	public void visit(PrintStmt printStmt) {
		Code.loadConst(0);
		if(printStmt.getExpr().struct.equals(Tab.charType))
			Code.put(Code.bprint);
		else
			Code.put(Code.print);
		
	}
	
	public void visit(PrintStmtwithNumber printStmtwithNumber) {
		Code.loadConst(printStmtwithNumber.getN2());
		if(printStmtwithNumber.getExpr().struct.equals(Tab.charType))
			Code.put(Code.bprint);
		else
			Code.put(Code.print);
		
	}
	/*===================================================================*/
	
	/*=============================== IF ================================*/
	Stack<Integer> skipCondTerm = new Stack<Integer>();
	Stack<Integer> skipCondition = new Stack<Integer>();
	Stack<Integer> skipTrue = new Stack<Integer>();
	Stack<Integer> skipElse = new Stack<Integer>();
	
	public void visit(CondExpr condExpr) {
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		skipCondTerm.push(Code.pc - 2);
	}
	
	public void visit(CompareExpr compareExpr) {
		Code.putFalseJump(getRelOp(compareExpr.getRelop()), 0);
		skipCondTerm.push(Code.pc - 2);
	}
	
	public void visit(CondTerm condTerm) {
		Code.putJump(0);
		skipCondition.push(Code.pc - 2);
		
		while(!skipCondTerm.empty()) {
			Code.fixup(skipCondTerm.pop());
		}
	}
	
	public void visit(Condition condition) {
		Code.putJump(0);
		skipTrue.push(Code.pc - 2);
		
		while(!skipCondition.empty()) {
			Code.fixup(skipCondition.pop());
		}
	}
	
	public void visit(NoElseStatement noElseStatement) {
		Code.fixup(skipTrue.pop());
	}
	
	public void visit(ElseTerminal elseTerminal) {
		Code.putJump(0);
		skipElse.push(Code.pc - 2);
		
		Code.fixup(skipTrue.pop());
	}
	
	public void visit(ElseStatement elseStatement) {
		Code.fixup(skipElse.pop());
	}
	/*===================================================================*/
	
//	do {   } while (Condiiton);
	
	/*=========================== Do While ==============================*/
	Stack<Integer> do_begin = new Stack<Integer>();
	List<Stack<Integer>> breakDoWhile = new ArrayList<Stack<Integer>>();
	
	public void visit(DoStmt doStmt) {
		do_begin.push(Code.pc);
		breakDoWhile.add(0, new Stack<Integer>());
	}
	
	public void visit(DoWhileStmt stmt) {
		Code.putJump(do_begin.pop());
		
		Code.fixup(skipTrue.pop());
		
		while(!breakDoWhile.get(0).empty()) {
			Code.fixup(breakDoWhile.get(0).pop());
		}
		
		breakDoWhile.remove(0);
	}
	
	public void visit(ContinueStmt stmt) {
		Code.putJump(do_begin.peek());
	}
	
	public void visit(BreakStmt stmt) {
		Code.putJump(0);
		breakDoWhile.get(0).push(Code.pc - 2);
	}
	/*===================================================================*/
	
	
	/*============================= Switch ==============================*/
//	List<Map<Integer, Integer>> switchCaseList = new ArrayList<Map<Integer,Integer>>();
	
	Stack<Integer> yieldStack = new Stack<Integer>();
	boolean firstCase = false;
	int val;
	boolean yieldHappened = false;
	
	public void visit(SwitchExpretion expretion) {
		while(!yieldStack.empty()) {
			Code.fixup(yieldStack.pop());
		}
	}
	
	public void visit(SwitchExpr expr) {
		firstCase = true;
	}
	
	boolean fixed_up = false;
	public void visit(CaseCase caseStmt) {
//		Code.loadConst(caseStmt.getN1());
//		System.out.println("Visit case stmt" + caseStmt.getN1());
		
//		if(firstCase == true) {
//			Code.put(Code.dup);
//			//Code.put(Code.pop);
//			Code.loadConst(caseStmt.getN1());
//			Code.putFalseJump(Code.eq, 0);
//			firstCase = false;
//			val = Code.pc - 2;
//		}
//		else {
//			if(fixed_up == false) {
//				Code.fixup(val);
//			}
//			else {
//				fixed_up = false;
//			}
//			Code.put(Code.dup);
//			Code.loadConst(caseStmt.getN1());
//			if(yieldHappened) {
//				Code.putFalseJump(Code.eq, 0);
//				yieldHappened = false;
//			}
//			else {
//				Code.putJump(Code.pc + 4);
//				Code.put(Code.pop);
//				Code.put(Code.pop);
//				fixed_up = true;
//			}
//			val = Code.pc - 2;
//		}
		
		if(firstCase == true) {
			Code.put(Code.dup);
			//Code.put(Code.pop);
			Code.loadConst(caseStmt.getN1());
			Code.putFalseJump(Code.eq, 0);
			firstCase = false;
			val = Code.pc - 2;
		}
		else {
			Code.fixup(val);
			Code.put(Code.dup);
			//Code.put(Code.pop);
//			if(yieldHappened) {
				Code.loadConst(caseStmt.getN1());
				Code.putFalseJump(Code.eq, 0);
				val = Code.pc - 2;
//			}
//			yieldHappened = false;
		}
	}
	
	public void visit(CaseStmt stmt) {
		if(!yieldHappened) {
			Code.put(Code.dup);
			Code.putJump(Code.pc + 8);
		}
		yieldHappened = false;
	}
	
	public void visit(DefaultStmt stmt) {
		Code.loadConst(10);
		Code.fixup(val);
	}
	
	public void visit(YieldStmt yieldStmt) {
		yieldHappened = true;
		Code.putJump(0);
		yieldStack.push(Code.pc - 2);
	}
	/*===================================================================*/
	
	
	/*============================= Factor ==============================*/
	public void visit(Var var) {
		Code.load(var.getDesignator().obj);
		
		if(minusHappend.peek() == true) {
			Code.put(Code.neg);
			minusHappend.pop();
			minusHappend.push(false);  
		}
	}
	
	public void visit(NumConst NumConst) {
		Code.loadConst(NumConst.getNumConst());
		
		if(minusHappend.peek() == true) {
			Code.put(Code.neg);
			minusHappend.pop();
			minusHappend.push(false);  
		}
	}
	
	public void visit(FactExpr factExpr) {
		if(minusHappend.peek() == true) {
			Code.put(Code.neg);
			minusHappend.pop();
			minusHappend.push(false);
		}
	}
	
	public void visit(CharConst CharConst) {
		Code.loadConst(CharConst.getCharConst());
	}
	
	public void visit(BoolConst BoolConst) {
		Code.loadConst(BoolConst.getBoolConst().equalsIgnoreCase("true") ? 1 : 0);
	}
	
	public void visit(NewArray newArray) {
		Code.put(Code.newarray);
		if(newArray.getType().struct == Tab.charType) {
			Code.put(0);
		}
		else {
			Code.put(1);
		}
	}
	
	public void visit(FuncCall funcCall) {
		Obj func = funcCall.getDesignator().obj;
		int offset = func.getAdr() - Code.pc;
		
		Code.put(Code.call);
		Code.put2(offset);
		
		if(minusHappend.peek() == true) {
			Code.put(Code.neg);
			minusHappend.pop();
			minusHappend.push(false);  
		}
	}
	/*===================================================================*/
	
	
	/*========================== Designator =============================*/
	public void visit(DesignatorArrayName designatorArrayName) {
		Code.load(designatorArrayName.obj);
	}
	/*===================================================================*/
	
	
	/*=================== Designator Statement ==========================*/
	public void visit(DesAssign desAssign) {
		Code.store(desAssign.getDesignator().obj);
	}
	
	public void visit(VarInc varInc) {
		if(varInc.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);
		Code.load(varInc.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(varInc.getDesignator().obj);
	}
	
	public void visit(VarDec varDec) {
		if(varDec.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);
		Code.load(varDec.getDesignator().obj);
		Code.loadConst(-1);
		Code.put(Code.add);
		Code.store(varDec.getDesignator().obj);
	}
	
	public void visit(ProcedureCall procedureCall) {
		Obj procObj = procedureCall.getDesignator().obj;
		int offset = procObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		if(procedureCall.getDesignator().obj.getType() != Tab.noType) {
			Code.put(Code.pop);
		}
	}
	/*===================================================================*/
	
	
	/*========================= Addop Mulop =============================*/
	public void visit(AddExpr addExpr) {
		if(addExpr.getAddop() instanceof AddOperator ) {
			Code.put(Code.add);
		}
		else {
			Code.put(Code.sub);
		}
	}
	
	public void visit(MulopTerm term) {
		if(term.getMulop() instanceof MulOperator) {
			Code.put(Code.mul);
		}
		else if(term.getMulop() instanceof DivOperator) {
			Code.put(Code.div);
		}
		else {
			Code.put(Code.rem);
		}
	}
	/*===================================================================*/
	
	
	/*============================== Expr ===============================*/
	Stack<Boolean> minusHappend = new Stack<Boolean>();
	
	public void visit(Minus Minus) {
		minusHappend.push(true);
	}
	
	public void visit(NoMinus noMinus) {
		minusHappend.push(false);
	}
	
	public void visit(BasicExpr basicExpr) {
		minusHappend.pop();
	}
	/*===================================================================*/
	
	
	/*============================= GOTO ================================*/
	Map<String, Integer> labels = new HashMap<String, Integer>();
	Map<String, ArrayList<Integer>> patchAdr = new HashMap<String, ArrayList<Integer>>();
	
	public void visit(GotoStmt gotoStmt) {
		if(labels.get(gotoStmt.getLabelname()) != null) {
			Code.putJump(labels.get(gotoStmt.getLabelname()));
		}
		else {
			Code.putJump(0);
			
			if(patchAdr.containsKey(gotoStmt.getLabelname())) {
				patchAdr.get(gotoStmt.getLabelname()).add(Code.pc - 2);
			}
			else {
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(Code.pc - 2);
				patchAdr.put(gotoStmt.getLabelname(), list);
			}
		}
	}
	
	public void visit(Label label) {
		labels.put(label.getLabelname(), Code.pc);
		
		if(patchAdr.containsKey(label.getLabelname())) {
			while(!patchAdr.get(label.getLabelname()).isEmpty()) {
				Code.fixup(patchAdr.get(label.getLabelname()).remove(0));
			}
		}
	}
	/*===================================================================*/
}
