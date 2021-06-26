package rs.ac.bg.etf.pp1;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	Obj currentMethod = null;
	
	Struct lastType = null;
	int varDeclLine;
	
	boolean returnFound = false;
	
	boolean errorDetected = false;
	
	Logger log = Logger.getLogger(getClass());
	
	Struct boolType = new Struct(Struct.Bool);
	
	int nVars = 0;
	
	/*======= Uslovi za break i continue =========*/
	boolean in_loop = false;
	boolean in_switch = false;
	/*============================================*/
	
	/*============== Uslovi za switch ============*/
	boolean default_found = false;
	boolean yield_found = false;
	List<Integer> caseValues = new ArrayList<Integer>();
	Struct switch_ret_val;
	/*============================================*/
	
	/*=========== Pozivanje metoda ===============*/
	List<Obj> calledMethodsList = new ArrayList<Obj>();
	List<List<Struct>> actualParams = new ArrayList<List<Struct>>();
	boolean function_called = false;
	/*============================================*/
	
	public SemanticAnalyzer() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
		
		for (Obj o : Tab.find("chr").getLocalSymbols()) {
			o.setFpPos(1);
		}
		
		for (Obj o : Tab.find("ord").getLocalSymbols()) {
			o.setFpPos(1);
		}
		
		for (Obj o : Tab.find("len").getLocalSymbols()) {
			o.setFpPos(1);
		}
	}

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}
	
	public void visit(Program program) {
		Obj mainMethod = Tab.find("main");
		
		if(mainMethod == Tab.noObj) {
			report_error("Greska: ne postoji main funkcija u programu", null);
		}
		else if(mainMethod.getType() != Tab.noType) {
			report_error("Greska : main funkcija mora biti void tipa!", null);
		}
		else {
			int fpCnt = 0;
			
			for(Obj obj : mainMethod.getLocalSymbols()) {
				fpCnt += obj.getFpPos();
			}
			
			if(fpCnt > 0) {
				report_error("Greska : main funkcija ne sme da ima parametre!", null);
			}
		}
		
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
		
		//provera main funkcije
	}

	/*============================================OBILAZAK KONSTANTI============================================*/
	public void visit(SingleNumberConst numConst) {
		Obj objNumConst = Tab.currentScope.findSymbol(numConst.getConstName());
		
		if(objNumConst == null) {
			Obj newNumConst = Tab.insert(Obj.Con, numConst.getConstName(), lastType);
			newNumConst.setAdr(numConst.getConstVal());
			report_info("Deklarisana je nova konstanta " + newNumConst.getName() + " na liniji " + numConst.getLine(), null);
		}
		else {
			report_error("Greska: identifikator " + numConst.getConstName() + " je vec deklarisan!", null);
		}
		
		if(lastType != Tab.intType) {
			report_error("Greska: konstanta " + numConst.getConstName() + " nije odgovarajuceg tipa", numConst);
		}
	}
	
	public void visit(MultiNumberConst numConst) {
		Obj objNumConst = Tab.currentScope.findSymbol(numConst.getConstName());
		
		if(objNumConst == null) {
			Obj newNumConst = Tab.insert(Obj.Con, numConst.getConstName(), lastType);
			newNumConst.setAdr(numConst.getConstVal());
			report_info("Deklarisana je nova konstanta " + newNumConst.getName() + " na liniji " + numConst.getLine(), null);
		}
		else {
			report_error("Greska: identifikator " + numConst.getConstName() + " je vec deklarisan!", null);
		}
		
		if(lastType != Tab.intType) {
			report_error("Greska: konstanta " + numConst.getConstName() + " nije odgovarajuceg tipa", numConst);
		}
	}
	
	public void visit(SingleCharConst charConst) {
		Obj objcharConst = Tab.currentScope.findSymbol(charConst.getConstName());
		
		if(objcharConst == null) {
			Obj newcharConst = Tab.insert(Obj.Con, charConst.getConstName(), lastType);
			newcharConst.setAdr(charConst.getConstVal());
			report_info("Deklarisana je nova konstanta " + newcharConst.getName() + " na liniji " + charConst.getLine(), null);
		}
		else {
			report_error("Greska: identifikator " + charConst.getConstName() + " je vec deklarisan!", null);
		}
		
		if(lastType != Tab.charType) {
			report_error("Greska: konstanta " + charConst.getConstName() + " nije odgovarajuceg tipa", charConst);
		}
	}
	
	public void visit(MultiCharConst charConst) {
		Obj objcharConst = Tab.currentScope.findSymbol(charConst.getConstName());
		
		if(objcharConst == null) {
			Obj newcharConst = Tab.insert(Obj.Con, charConst.getConstName(), lastType);
			newcharConst.setAdr(charConst.getConstVal());
			report_info("Deklarisana je nova konstanta " + newcharConst.getName() + " na liniji " + charConst.getLine(), null);
		}
		else {
			report_error("Greska: identifikator " + charConst.getConstName() + " je vec deklarisan!", null);
		}
		
		if(lastType != Tab.charType) {
			report_error("Greska: konstanta " + charConst.getConstName() + " nije odgovarajuceg int tipa", charConst);
		}
	}
	
	public void visit(SingleBoolConst boolConst) {
		Obj objboolConst = Tab.currentScope.findSymbol(boolConst.getConstName());
		
		if(objboolConst == null) {
			Obj newboolConst = Tab.insert(Obj.Con, boolConst.getConstName(), lastType);
			newboolConst.setAdr(boolConst.getConstVal().equals("true") ? 1 : 0);
			report_info("Deklarisana je nova konstanta " + newboolConst.getName() + " na liniji " + boolConst.getLine(), null);
		}
		else {
			report_error("Greska: identifikator " + boolConst.getConstName() + " je vec deklarisan!", null);
		}
		
		if(lastType != boolType) {
			report_error("Greska: konstanta " + boolConst.getConstName() + " nije odgovarajuceg tipa", boolConst);
		}
	}
	
	public void visit(MultiBoolConst boolConst) {
		Obj objboolConst = Tab.currentScope.findSymbol(boolConst.getConstName());
		
		if(objboolConst == null) {
			Obj newboolConst = Tab.insert(Obj.Con, boolConst.getConstName(), lastType);
			newboolConst.setAdr(boolConst.getConstVal().equals("true") ? 1 : 0);
			report_info("Deklarisana je nova konstanta " + newboolConst.getName() + " na liniji " + boolConst.getLine(), null);
		}
		else {
			report_error("Greska: identifikator " + boolConst.getConstName() + " je vec deklarisan!", null);
		}
		
		if(lastType != boolType) {
			report_error("Greska: konstanta " + boolConst.getConstName() + " nije odgovarajuceg int tipa", boolConst);
		}
	}
	/*==========================================================================================================*/
	
	
	/*============================================OBILAZAK PROMENLJIVIH=========================================*/
	public void visit(VarDeclaration varDeclaration) {
		if(Tab.currentScope.findSymbol(varDeclaration.getVarName()) != null) {
			report_error("Greska: Promenljiva " + varDeclaration.getVarName() + " je vec deklarisana", null);
		}
		else {
			report_info("Deklarisana je promenljiva " + varDeclaration.getVarName() + " na liniji " + varDeclLine, null);
			Obj varNode = Tab.insert(Obj.Var, varDeclaration.getVarName(), lastType);
		}
	}
	
	public void visit(VarDeclarationArray varDeclaration) {
		if(Tab.currentScope.findSymbol(varDeclaration.getVarName()) != null) {
			report_error("Greska: Promenljiva " + varDeclaration.getVarName() + " je vec deklarisana", null);
		}
		else {
			report_info("Deklarisana je promenljiva " + varDeclaration.getVarName() + " na liniji " + varDeclLine, null);
			Obj varNode = Tab.insert(Obj.Var, varDeclaration.getVarName(), new Struct(Struct.Array, lastType));
		}
	}
	
	public void visit(VarDeclMore varDeclaration) {
		if(Tab.currentScope.findSymbol(varDeclaration.getVarName()) != null) {
			report_error("Greska: Promenljiva " + varDeclaration.getVarName() + " je vec deklarisana", null);
		}
		else {
			report_info("Deklarisana je promenljiva " + varDeclaration.getVarName() + " na liniji " + varDeclLine, null);
			Obj varNode = Tab.insert(Obj.Var, varDeclaration.getVarName(), lastType);
		}
	}
	
	public void visit(VarDeclArrayMore varDeclaration) {
		if(Tab.currentScope.findSymbol(varDeclaration.getVarName()) != null) {
			report_error("Greska: Promenljiva " + varDeclaration.getVarName() + " je vec deklarisana", null);
		}
		else {
			report_info("Deklarisana je promenljiva " + varDeclaration.getVarName() + " na liniji " + varDeclLine, null);
			Obj varNode = Tab.insert(Obj.Var, varDeclaration.getVarName(), new Struct(Struct.Array, lastType));
		}
	}
	/*==========================================================================================================*/
	
	
	/*============================================OBILAZAK METODA===============================================*/
	public void visit(MethodTypeName methodTypeName) {
		if(Tab.currentScope.findSymbol(methodTypeName.getMethName()) != null) {
			report_error("Greska: Metoda sa imenom " + methodTypeName.getMethName() + " je vec deklarisana", null);
		}
		else {
			report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
		}
		
		currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), methodTypeName.getMethodType().struct);
		methodTypeName.obj = currentMethod;
		Tab.openScope();
	}
	
	public void visit(MethodDecl methodDecl) {
		if(!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Greska : funkcija " + currentMethod.getName() + " nema return iskaz ", methodDecl);
		}
		
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		returnFound = false;
		currentMethod = null;
	}
	
	public void visit(MethodRetType retType) {
		retType.struct = retType.getType().struct;
	}
	
	public void visit(VoidRetType retType) {
		retType.struct = Tab.noType;
	}
	
	public void visit(FormParam formParam) {
		Obj param = Tab.currentScope.findSymbol(formParam.getParamName());
		if(param != null) {
			report_error("Greska: Promenljiva sa imenom " + formParam.getParamName() + " je vec deklarisana", formParam);
		}
		else {
			report_info("Deklarisan je formalni parametar " + formParam.getParamName() + " metode " + currentMethod.getName(), formParam);
			Obj newParam = Tab.insert(Obj.Var, formParam.getParamName(), formParam.getType().struct);
			newParam.setFpPos(1);
		}
	}
	
	public void visit(FormParamArray formParam) {
		Obj param = Tab.currentScope.findSymbol(formParam.getParamName());
		if(param != null) {
			report_error("Greska: Promenljiva sa imenom " + formParam.getParamName() + " je vec deklarisana", formParam);
		}
		else {
			report_info("Deklarisan je formalni parametar " + formParam.getParamName() + " metode " + currentMethod.getName(), formParam);
			Obj newParam = Tab.insert(Obj.Var, formParam.getParamName(), new Struct(Struct.Array, formParam.getType().struct));
			newParam.setFpPos(1);
		}
	}
	
	/*==========================================================================================================*/
	
	/*======================================= OBILAZAK DesignatorStatement =====================================*/
	public void visit(DesAssign assign) {
		if(!(assign.getDesignator().obj.getKind() == Obj.Elem || assign.getDesignator().obj.getKind() == Obj.Var)) {
			report_error("Greska na liniji " + assign.getLine() + " : simbol mora biti promenljiva, ili element niza", null);
		}
		else if(!assign.getExpr().struct.assignableTo(assign.getDesignator().obj.getType())) {
			report_error("Greska na liniji " + assign.getLine() + " : tipovi u dodeli vrednosti nisu kompatibilni", null);
		}
		else {
			report_info("Promenljivoj se dodeljuje vrednost ", assign);
		}
	}

	public void visit(ProcedureCall procedureCall) {
		Obj proc = procedureCall.getDesignator().obj;
		if(Obj.Meth == proc.getKind()) {
			report_info("Pronadjen poziv funkcije " + proc.getName() + " na liniji " + procedureCall.getLine(), null);
			procedureCall.struct = proc.getType();
			
			int fpPos = 0;
			for(Obj o : proc.getLocalSymbols()) {
				fpPos += o.getFpPos();
			}
			
//			if(proc.getName().equals("chr") || proc.getName().equals("ord") || proc.getName().equals("len")) {
//				fpPos++;
//			}
			
			if(this.actualParams.get(0).size() != fpPos) {
				report_error("Greska : broj prosledjenih parametara u pozivu funkcije " + proc.getName() + " se ne slaze sa brojem formalnih parametara ", procedureCall);
			}
			int i = 0;
			for(Obj o : proc.getLocalSymbols()) {
				if(o.getFpPos() == 0) continue;
				if(this.actualParams.get(0).get(i++) != o.getType()) {
					report_error("Greska : prosledjeni parametri funkcije " + this.calledMethodsList.get(0).getName() + " se ne slazu sa formalnim parametrima funkcije ", procedureCall);
					break;
				}
			}
			
			this.calledMethodsList.remove(0);
			this.actualParams.remove(0);
		}
		else {
			report_error("Greska na liniji " + procedureCall.getLine() + " : ime " + proc.getName() + " nije funkcija!", null);
			procedureCall.struct = Tab.noType;
		}
	}
	
	public void visit(VarInc varInc) {
		if(varInc.getDesignator().obj.getKind() != Obj.Elem && varInc.getDesignator().obj.getType().getKind() != Obj.Var) {
			report_error("Greska na liniji " + varInc.getLine() + " : simbol nije promenljiva ili element niza!", null);
		}
		else if(varInc.getDesignator().obj.getType() != Tab.intType) {
			report_error("Greska na liniji " + varInc.getLine() + " promenljiva mora biti tipa int", null);
		}
		else {
			report_info("Promenljiva " + varInc.getDesignator().obj.getName() + " je inkrementirana ", varInc);
		}
	}
	
	public void visit(VarDec varDec) {
		if(varDec.getDesignator().obj.getKind() != Obj.Elem && varDec.getDesignator().obj.getType().getKind() != Obj.Var) {
			report_error("Greska na liniji " + varDec.getLine() + " : simbol nije promenljiva ili element niza!", null);
		}
		else if(varDec.getDesignator().obj.getType() != Tab.intType) {
			report_error("Greska na liniji " + varDec.getLine() + " promenljiva mora biti tipa int", null);
		}
		else {
			report_info("Promenljiva " + varDec.getDesignator().obj.getName() + " je inkrementirana ", varDec);
		}
	}
	/*==========================================================================================================*/
	
	/*============================================OBILAZAK Designatora==========================================*/
	public void visit(DesBasic designator) {
		Obj obj = Tab.find(designator.getVarName());
		
		if(obj == Tab.noObj) {
			report_error("Greska na liniji " + designator.getLine() + " : ime " + designator.getVarName() + " nije deklarisano!", null);
		}
		else if (obj.getKind() == Obj.Meth) {
			this.calledMethodsList.add(0, obj);
			this.actualParams.add(0, new ArrayList<Struct>());
		}
		
		designator.obj = obj;
	}
	
	public void visit(DesArray desArray) {
		if(desArray.getDesignatorArrayName().obj == Tab.noObj) {
			desArray.obj = Tab.noObj;
			return;
		}
		else if(desArray.getDesignatorArrayName().obj.getKind() == Obj.Meth) {
			this.calledMethodsList.add(0, desArray.getDesignatorArrayName().obj);
			this.actualParams.add(0, new ArrayList<Struct>());
		}
		
		if(desArray.getExpr().struct != Tab.intType) {
			report_error("Greska : Indeks niza mora biti celobrojna vrednost! ", desArray);
			desArray.obj = Tab.noObj;
		}
		else {
			desArray.obj = new Obj(Obj.Elem, "", desArray.getDesignatorArrayName().obj.getType().getElemType());
		}
	}
	
	public void visit(DesignatorArrayName arrayName) {
		Obj arrayType = Tab.find(arrayName.getVarName());
		
		if(arrayType == Tab.noObj) {
			report_error("Greska : nije deklarian niz ", arrayName);
			arrayName.obj=  Tab.noObj;
		}
		else if(Struct.Array != arrayType.getType().getKind()) {
			report_error("Tip simbola mora biti nizovnog tipa!", null);
			arrayName.obj = Tab.noObj;
		}
		else {
			arrayName.obj = arrayType;
		}
	}
	/*==========================================================================================================*/
	

	/*==========================================OBILAZAK Factora================================================*/
	
	public void visit(Var var) {
		var.struct = var.getDesignator().obj.getType();
	}
	
	public void visit(FuncCall funcCall) {
		Obj func = funcCall.getDesignator().obj;
		if(Obj.Meth == func.getKind()) {
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
			funcCall.struct = func.getType();
			
			int fpPos = 0;
			for(Obj o : func.getLocalSymbols()) {
				fpPos += o.getFpPos();
			}
			
//			if(func.getName().equals("chr") || func.getName().equals("ord") || func.getName().equals("len")) {
//				fpPos++;
//			}
			
			if(this.actualParams.get(0).size() != fpPos) {
				report_error("Greska : broj prosledjenih parametara u pozivu funkcije " + func.getName() + " se ne slaze sa brojem formalnih parametara ", funcCall);
			}
			
			int i = 0;
			for(Obj o : func.getLocalSymbols()) {
				if(o.getFpPos() == 0) continue;
				if(this.actualParams.get(0).get(i++) != o.getType()) {
					report_error("Greska : prosledjeni parametri funkcije " + this.calledMethodsList.get(0).getName() + " se ne slazu sa formalnim parametrima funkcije ", funcCall);
					break;
				}
			}
			
			this.calledMethodsList.remove(0);
			this.actualParams.remove(0);
		}
		else {
			report_error("Greska na liniji " + funcCall.getLine() + " : ime " + func.getName() + " nije funkcija!", null);
			funcCall.struct = Tab.noType;
		}
	}
	
	public void visit(NumConst numConst) {
		numConst.struct = Tab.intType;
	}
	
	public void visit(CharConst charConst) {
		charConst.struct = Tab.charType;
	}
	
	public void visit(BoolConst boolConst) {
		boolConst.struct = boolType;
	}
	
	public void visit(NewOp newOp) {
		if(newOp.getType().struct.getKind() != Struct.Class) {
			report_error("Greska na liniji " + newOp.getLine() + ": tip promenljive uz operator new mora biti klasnog tipa", null);
		}
		newOp.struct = new Struct(Struct.Class, newOp.getType().struct);
	}
	
	public void visit(NewArray newArray) {
		if(newArray.getExpr().struct != Tab.intType) {
			report_error("Greska na liniji " + newArray.getLine() + " : duzina niza mora biti celobrojna vrednost", null);
		}
		else {
			report_info("Formiran novi niz ", newArray);
		}
		newArray.struct = new Struct(Struct.Array, newArray.getType().struct);
	}
	
	public void visit(FactExpr expr) {
		expr.struct=  expr.getExpr().struct;
	}
	/*==========================================================================================================*/
	

	/*===========================================OBILAZAK Terma=================================================*/
	public void visit(FactorTerm term) {
		term.struct = term.getFactor().struct;
	}
	
	public void visit(MulopTerm mulopTerm) {
		Struct term = mulopTerm.getTerm().struct;
		Struct factor = mulopTerm.getFactor().struct;
		
		if(term.equals(factor) && term == Tab.intType) {
			mulopTerm.struct = factor;
		}
		else {
			report_error("Greska na liniji " + mulopTerm.getLine() + " : nekompatibilni tipovi u izrazu", null);
			mulopTerm.struct = Tab.noType;
		}
	}
	/*==========================================================================================================*/
	
	/*========================================== OBILAZAK Expr1 ================================================*/
	public void visit(AddExpr addExpr) {
		Struct term = addExpr.getExpr1().struct;
		Struct t = addExpr.getTerm().struct;
		
		if(term.equals(t) && term == Tab.intType) {
			addExpr.struct = term;
		} else {
			report_error("Greska na liniji " + addExpr.getLine() + " : nekompatibilni tipovi u izrazu za sabiranje!", null);
			addExpr.struct = Tab.noType;
		}
	}
	
	public void visit(TermExpr termExpr) {
		termExpr.struct = termExpr.getTerm().struct;
	}
	/*==========================================================================================================*/
	
	/*========================================= OBILAZAK ActPars ===============================================*/
	public void visit(SingleParam param) {
		this.actualParams.get(0).add(param.getExpr().struct);
	}
	
	public void visit(MultipleParam param) {
		this.actualParams.get(0).add(param.getExpr().struct);
	}
	/*==========================================================================================================*/
	
	
	/*========================================== OBILAZAK Expr ==============================================*/
	public void visit(BasicExpr basicExpr) {
		basicExpr.struct = basicExpr.getExpr1().struct;
	}
	
	public void visit(SwitchExpretion expr) {
		if(expr.getExpr().struct != Tab.intType) {
			report_error("Greska : opcije switch naredbe moraju biti int tipa ", expr);
			expr.struct = Tab.noType;
		}
		else {
			expr.struct = this.switch_ret_val;
		}
		
		if(!default_found) {
			report_error("Greska: nije pronadjena default labela u switch naredbi ", expr);
		}
		
		if(!yield_found) {
			report_error("Greska: ne postoji yield naredba u default labeli u switch-u ", expr);
		}
		
		default_found = false;
		yield_found = false;
		in_switch = false;
		caseValues.clear();
	}
	
	public void visit(DefaultStmt stmt) {
		default_found = true;
	}
	
	public void visit(SwitchExpr expr) {
		this.in_switch = true;
		this.caseValues.clear();
	}
	
	public void visit(CaseStmt caseStmt) {
		if(this.caseValues.contains(caseStmt.getN1())) {
			report_error("Greska: pronadjeno vise case-ova sa istom vrednoscu " + caseStmt.getN1() + " ", caseStmt);
		}
		else {
			this.caseValues.add(caseStmt.getN1());
		}
	}
	/*==========================================================================================================*/
	
	/*======================================= OBILAZAK Statementa ==============================================*/
	public void visit(PrintStmt stmt) {
		if(!(stmt.getExpr().struct.equals(Tab.intType) || stmt.getExpr().struct.equals(Tab.charType) || stmt.getExpr().struct.equals(boolType))) {
			report_error("Greska : parametar print funkcije nije validan tip (int, char, bool)", null);
		}
	}
	
	public void visit(PrintStmtwithNumber stmt) {
		if(!(stmt.getExpr().struct.equals(Tab.intType) || stmt.getExpr().struct.equals(Tab.charType) || stmt.getExpr().struct.equals(boolType))) {
			report_error("Greska : parametar print funkcije nije validan tip (int, char, bool)", null);
		}
	}
	
	
	public void visit(ReadStmt stmt) {
		if(stmt.getDesignator().obj.getKind() != Obj.Elem && stmt.getDesignator().obj.getKind() != Obj.Var) {
			report_error("Greska na liniji " + stmt.getLine() + " : simbol nije promenljiva ili element niza!", null);	
		}
		else if(!(stmt.getDesignator().obj.getType() == Tab.intType || stmt.getDesignator().obj.getType() == Tab.charType || stmt.getDesignator().obj.getType() == boolType)) {
			report_error("Greska : parametar read funkcije nije validan tip (int, char, bool)", null);
		}
	}
	
	public void visit(RetStmt stmt) {
		this.returnFound = true;
		if(!currentMethod.getType().compatibleWith(stmt.getExpr().struct)) {
			report_error("Greska : povratna vrednost metode " + currentMethod.getName() + " se ne poklapa sa tipom u return iskazu ", stmt);
		}
	}
	
	public void visit(RetNoValStmt stmt) {
		returnFound = false;
		if(!currentMethod.getType().compatibleWith(Tab.noType)) {
			report_error("Greska : metoda " + currentMethod.getName() + " mora da ima povratni parametar ", stmt);
		}
	}
	
	public void visit(DoStmt stmt) {
		in_loop = true;
	}
	
	public void visit(ContinueStmt stmt) {
		if(!in_loop) {
			report_error("Greska : continue naredba je dozvoljena iskljucivo unutar petlji ", null);
		}
	}
	
	public void visit(BreakStmt stmt) {
		if(!in_loop) {
			report_error("Greska : break naredba je dozvoljena samo unutar petlji  ", null);
		}
	}
	
	public void visit(DoWhileStmt stmt) {
		in_loop = false;
	}
	
	public void visit(YieldStatement stmt) {
		if(default_found) {
			yield_found = true;
		}
	}
	
	public void visit(YieldStmt stmt) {
		if(!in_switch) {
			report_error("Greska : yield naredba je dozvoljena samo unutar switch naredbe ", stmt);
			stmt.struct = Tab.noType;
		}
		else {
			stmt.struct = stmt.getExpr().struct;
		}
		
		switch_ret_val = stmt.struct;
	}
	/*==========================================================================================================*/
	
	/*========================================= OBILAZAK Condition =============================================*/
	public void visit(Condition condition) {
		condition.struct = condition.getCondTermList().struct;
	}
	
	public void visit(OrCond cond) {
		Struct cond1Type = cond.getCondTermList().struct;
		Struct cond2Type = cond.getCondTerm().struct;
		
		if(cond1Type.getKind() == boolType.getKind() && cond2Type.getKind() == boolType.getKind()) {
			report_info("Ispitan uslov u if naredbi ", cond);
			cond.struct = boolType;
		}
		else {
			cond.struct = Tab.noType;
			report_error("Greska na liniji " + cond.getLine() + " : izraz u if uslovu nije bool tipa", null);
		}
	}
	
	public void visit(SimpleCond cond) {
		if(cond.getCondTerm().struct.getKind() != boolType.getKind()) {
			report_error("Greska na liniji " + cond.getLine() + " : Uslov u if naredbi nije bool tipa", null);
			cond.struct = Tab.noType;
		}
		else {
			report_info("Ispitan je uslov u if naredbi ", cond);
			cond.struct = boolType;
		}
	}
	
	public void visit(CondTerm condTerm) {
		condTerm.struct = condTerm.getCondFactList().struct;
	}
	
	public void visit(AndCond cond) {
		Struct cond1 = cond.getCondFactList().struct;
		Struct cond2 = cond.getCondFact().struct;
		
		if(!(cond1.getKind() == boolType.getKind() && cond2.getKind() == boolType.getKind())) {
			report_error("Greska : tipovi uslova nisu ispravnog bool tipa ", cond);
			cond.struct = Tab.noType;
		}
		else {
			cond.struct = boolType;
		}
	}
	
	public void visit(SimpleCondTerm condTerm) {
		condTerm.struct = condTerm.getCondFact().struct;
	}

	public void visit(CondExpr condExpr) {
		condExpr.struct = condExpr.getExpr().struct;
	}
	
	public void visit(CompareExpr expr) {
		Struct expr1 = expr.getExpr().struct;
		Struct expr2 = expr.getExpr1().struct;
		
		if(!expr1.compatibleWith(expr2)) {
			report_error("Greska: nisu komparabilni tipovi ", expr);
			expr.struct = Tab.noType;
		}
		else {
			expr.struct = boolType;
		}
	}
	/*==========================================================================================================*/
	
	
	public void visit(Type type) {
		//provera da li se radi o tipu
		Obj typeNode = Tab.find(type.getType());
		if(typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getType() + " u tabeli simbola!", null);
			type.struct=  Tab.noType;
		}
		else {
			if(Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
				lastType = type.struct;
				varDeclLine = type.getLine();
//				report_info("Deklarisan novi tip " + type.getType() + " u tabeli simbola", null);
			}
			else { 
				report_error("Greska: Ime " + type.getType() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
	}
	
	public boolean passed(){
    	return !errorDetected;
    }
}









