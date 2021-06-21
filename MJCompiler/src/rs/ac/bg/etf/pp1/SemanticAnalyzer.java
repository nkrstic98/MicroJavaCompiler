package rs.ac.bg.etf.pp1;

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
	
	public SemanticAnalyzer() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
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
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
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
		}
	}
	
	/*==========================================================================================================*/
	
	/*============================================OBILAZAK Designatora==========================================*/
	public void visit(DesBasic designator) {
		Obj obj = Tab.find(designator.getVarName());
		
		if(obj == Tab.noObj) {
			report_error("Greska na liniji " + designator.getLine() + " : ime " + designator.getVarName() + " nije deklarisano!", null);
		}
		
		designator.obj = obj;
	}
	
	public void visit(DesArray desArray) {
		Obj arrayType = Tab.find(desArray.getDesignator().obj.getName());
		desArray.obj = new Obj(Obj.Elem, "", arrayType.getType().getElemType());
		
		if(Struct.Array != arrayType.getType().getKind()) {
			report_error("Tip simbola mora biti nizovnog tipa!", null);
			desArray.obj = Tab.noObj;
		}
		
		if(desArray.getExpr().struct != Tab.intType) {
			report_error("Greska : Indeks niza mora biti celobrojna vrednost! ", desArray);
			desArray.obj = Tab.noObj;
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
	/*==========================================================================================================*/
	

	/*===========================================OBILAZAK Terma=================================================*/
	public void visit(FactorTerm term) {
		term.struct = term.getFactor().struct;
	}
	
	public void visit(MulopTerm mulopTerm) {
		Struct term = mulopTerm.getTerm().struct;
		Struct factor = mulopTerm.getFactor().struct;
		
		if(term.equals(factor) && term == Tab.intType) {
			mulopTerm.struct = term;
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
	
	
	public void visit(ProcedureCall procedureCall) {
		Obj proc = procedureCall.getDesignator().obj;
		if(Obj.Meth == proc.getKind()) {
			report_info("Pronadjen poziv funkcije " + proc.getName() + " na liniji " + procedureCall.getLine(), null);
			procedureCall.struct = proc.getType();
		}
		else {
			report_error("Greska na liniji " + procedureCall.getLine() + " : ime " + proc.getName() + " nije funkcija!", null);
			procedureCall.struct = Tab.noType;
		}
	}
	
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
}









