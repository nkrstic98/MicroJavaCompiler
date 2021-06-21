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
		Obj objNumConst = Tab.find(numConst.getConstName());
		
		if(objNumConst == Tab.noObj) {
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
		Obj objNumConst = Tab.find(numConst.getConstName());
		
		if(objNumConst == Tab.noObj) {
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
		Obj objcharConst = Tab.find(charConst.getConstName());
		
		if(objcharConst == Tab.noObj) {
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
		Obj objcharConst = Tab.find(charConst.getConstName());
		
		if(objcharConst == Tab.noObj) {
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
		Obj objboolConst = Tab.find(boolConst.getConstName());
		
		if(objboolConst == Tab.noObj) {
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
		Obj objboolConst = Tab.find(boolConst.getConstName());
		
		if(objboolConst == Tab.noObj) {
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
		if(Tab.find(varDeclaration.getVarName()) != Tab.noObj) {
			report_error("Greska: Promenljiva " + varDeclaration.getVarName() + " je vec deklarisana", null);
		}
		else {
			report_info("Deklarisana je promenljiva " + varDeclaration.getVarName() + " na liniji " + varDeclLine, null);
			Obj varNode = Tab.insert(Obj.Var, varDeclaration.getVarName(), lastType);
		}
	}
	
	public void visit(VarDeclMore varDeclaration) {
		if(Tab.find(varDeclaration.getVarName()) != Tab.noObj) {
			report_error("Greska: Promenljiva " + varDeclaration.getVarName() + " je vec deklarisana", null);
		}
		else {
			report_info("Deklarisana je promenljiva " + varDeclaration.getVarName() + " na liniji " + varDeclLine, null);
			Obj varNode = Tab.insert(Obj.Var, varDeclaration.getVarName(), lastType);
		}
	}
	/*==========================================================================================================*/
	
	
	/*============================================OBILAZAK PROMENLJIVIH=========================================*/
	
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









