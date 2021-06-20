package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import com.sun.javafx.geom.transform.BaseTransform.Degree;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	int varDeclCount = 0;
	
	Obj currentMethod = null;
	
	Struct globalVarType = null;
	int varDeclLine;
	
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
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
	
	public void visit(VarDeclaration varDeclaration) {
		varDeclCount++;
		report_info("Deklarisana promenljiva " + varDeclaration.getVarName(), varDeclaration);
		
		Obj varNode = Tab.insert(Obj.Var, varDeclaration.getVarName(), varDeclaration.getType().struct);
	}
	
	public void visit(VarDeclMore varDeclMore) {
		varDeclCount++;
		report_info("Deklarisana promenljiva " + varDeclMore.getVarName() + " na liniji " + varDeclLine, null);
		
		Obj varNode = Tab.insert(Obj.Var, varDeclMore.getVarName(), globalVarType);
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
				globalVarType = type.struct;
				varDeclLine = type.getLine();
//				report_info("Deklarisan novi tip " + type.getType() + " u tabeli simbola", null);
			}
			else { 
				report_error("Greska: Ime " + type.getType() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
	}
	
	public void visit(MethodTypeName methodTypeName) {
		currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), methodTypeName.getMethodType().struct);
		methodTypeName.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
	}
	
	public void visit(MethodDecl methodDecl) {
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		currentMethod = null;
	}
	
	public void visit(DesBasic desBasic) {
		Obj obj = Tab.find(desBasic.getVarName());
		
		if(obj == Tab.noObj) {
			report_error("Greska na liniji " + desBasic.getLine() + " : ime " + desBasic.getVarName() + " nije deklarisano!", null);
		}
		desBasic.obj = obj;
	}
	
	public void visit(FuncCall funcCall) {
		Obj func = funcCall.getDesignator().obj;
		if(Obj.Meth == func.getKind()) {
			report_info("Pronadjen poziv funkcije " + func.getName() + "  na liniji " + funcCall.getLine(), null);
			funcCall.struct = func.getType();
		}
		else {
			report_error("Greska na liniji " + funcCall.getLine() + " : ime " + func.getName() + " nije funkcija!", null);
			funcCall.struct = Tab.noType;
		}
	}
}









