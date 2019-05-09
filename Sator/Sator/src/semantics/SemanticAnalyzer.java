/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics;

import java.util.ArrayList;
import java.util.List;
import parser.grammar.Grammar;
import scanner.reader.Token;
import semantics.literals.ArrayLiteral;
import semantics.literals.Literal;
import semantics.literals.SimpleLiteral;
import semantics.table.Category;
import semantics.table.SemanticErrors;
import semantics.table.SymbolTable;
import semantics.table.Type;
import semantics.table.TypeTable;
import utils.Stack;

/**
 *
 * @author admin
 */
public class SemanticAnalyzer {
    
    private int row;
    private int col;
    
    private final SymbolTable globalTable = new SymbolTable();
    private final SymbolTable localTable = new SymbolTable();
    private final TypeTable typeTable = new TypeTable();
    private String lastIdentifier;
    
    private Type currentType;
    private Type attr;
    private List<Type> recordLiteral = new ArrayList<>();
    
    private Stack<ArrayLiteral> arrayStack = new Stack<>();
    
    public void analyze(int symbol, Token token){
        switch(symbol){
            case Grammar.CONS_TYPE:
                saveConstantType(token);
                break;
            case Grammar.CONS_DEF:
                defineConstant(token);
                break;
            case Grammar.CONS_LIT:
                saveConstantLiteral(token);
                break;
            case Grammar.TYPE_DEF:
                defineType(token);
                break;
            case Grammar.TYPE_NORM:
                assignTypeDefinition(token);
                break;
            case Grammar.REC_TYPE:
                newAttribute(token);
                break;
            case Grammar.REC_ID:
                setAttributeName(token);
                break;
            case Grammar.REC_END:
                insertRecord();
                break;
            case Grammar.VAR_TYPE:
                saveVariableType(token);
                break;
            case Grammar.VAR_DEF:
                defineVariable(token);
                break;
            case Grammar.VAR_LIT:
                saveVariableLiteral(token);
                break;
            case Grammar.REG_LIT:
                addRecordAttrLiteral(token);
                break;
            case Grammar.REG_LIT_END:
                saveRecordLiteral();
                break;
            case Grammar.ARR_DEF:
                defineArray();
                break;
            case Grammar.ARR_SIZE:
                addArrayDimension(token);
                break;
            case Grammar.ARR_TYPE:
                setBaseType(token);
                break;
            case Grammar.ARR_VAR:
                saveArrayVariable(token);
                break;
            case Grammar.ARR_LIT:
                newArrayLiteral(token);
                break;
            case Grammar.ARR_LIT_END:
                saveArrayLiteral();
                break;
                
        }
    }
    
    private void saveConstantType(Token token){
        currentType = typeTable.get(token.getLexeme());
        if(currentType == null){
            semanticError(1, token);
        }
    }
    private void defineConstant(Token token){
        if(globalTable.contains(token.getLexeme())){
            semanticError(2, token);
        }else{
            lastIdentifier = token.getLexeme();
            globalTable.insert(token.getLexeme(), Category.CONSTANT,currentType);
            
        }
    }
    private void saveConstantLiteral(Token token){
        if(currentType != null){
            if(typeMatchLiteral(currentType, token))
                globalTable.modify(lastIdentifier, token.getLexeme());
            else
                semanticErrorType(3, token);
            }
        currentType = null;
        lastIdentifier = "";
    }
    private void defineType(Token token){
        if(typeTable.exists(token.getLexeme()) || globalTable.contains(token.getLexeme())){
            semanticError(2, token);
        }else{
            currentType = new Type(Type.nextCode(), token.getLexeme());
        }
    }
    private void assignTypeDefinition(Token token){
        if(typeTable.exists(token.getLexeme())){
            currentType.setBaseType(typeTable.get(token.getLexeme()));
            typeTable.put(currentType.toString(), currentType);
            currentType = null;
        }else
            semanticError(1, token);
    }
    private void newAttribute(Token token){
        if(!typeTable.exists(token.getLexeme()) && !token.getLexeme().toLowerCase().equals(currentType.toString().toLowerCase())){
            semanticError(1, token);
        }else{
            attr = new Type();
            attr.setCode(Type.nextCode());
            attr.setBaseType(typeTable.get(token.getLexeme()));
            if(attr.getBaseType()==null)
                attr.setBaseType(currentType);
        }
    }
    private void setAttributeName(Token token){
        if(currentType.constainsAttribute(token.getLexeme())){
            semanticError(4, token);
        }else{
            attr.setName(token.getLexeme());
            currentType.addAtrribute(attr);
            attr = null;
        }
    }
    private void insertRecord(){
        typeTable.put(currentType.getName(), currentType);
        currentType = null;
    }
    private void saveVariableType(Token token){
        currentType = typeTable.get(token.getLexeme());
        if(currentType == null){
            semanticError(1, token);
        }
    }
    private void defineVariable(Token token){
        if(globalTable.contains(token.getLexeme())){
            semanticError(2, token);
        }else{
            lastIdentifier = token.getLexeme();
            globalTable.insert(token.getLexeme(), Category.VARIABLE,currentType);
            
        }
    }
    private void saveVariableLiteral(Token token){
        if(currentType != null){
            if(currentType.isRecord()){
                row = token.getIntegerProperty("row");
                col = token.getIntegerProperty("column");
            }else if(currentType.isArray()){
                if(!arrayStack.isEmpty())
                    arrayStack.push(new ArrayLiteral());
                else{
                    row = token.getIntegerProperty("row");
                    col = token.getIntegerProperty("column");
                }
            }else{
                if(typeMatchLiteral(currentType, token))
                    globalTable.modify(lastIdentifier, token.getLexeme());
                else
                    semanticErrorType(3, token);
                currentType = null;
            }
        }
        lastIdentifier = "";
    }
    private void addRecordAttrLiteral(Token token){
        if(currentType != null){
            if(currentType.isRecord()){
                Type t = typeTable.get(Type.typeName(token.getCode()));
                recordLiteral.add(t);
            }else if(currentType.isArray()){
                String name = Type.typeName(token.getCode());
                Type t = null;
                if(name !=null)
                    t = typeTable.get(name);
                if(t != null){
                    Literal literal = new SimpleLiteral(t, token.getLexeme());
                    arrayStack.peek().addLiteral(literal);
                }
            }
        }
    }
    private void saveRecordLiteral(){
        if(currentType != null){
            boolean matched = currentType.matchType(recordLiteral);
            if(!matched)
                semanticErrorType(3);
        }
        recordLiteral.clear();
    }
    private void defineArray(){
        currentType = null;
        currentType = new Type(Type.nextCode(), "");
    }
    private void addArrayDimension(Token token){
        
        if(currentType!=null){
            int dimension = Integer.parseInt(token.getLexeme());
            if(dimension<0){
                currentType.addDimension(0);
            }else
                currentType.addDimension(dimension);
        }
    }
    private void setBaseType(Token token){
        if(currentType!=null){
            if(typeTable.exists(token.getLexeme())){
                currentType.setBaseType(typeTable.get(token.getLexeme()));
            }else
                semanticError(1, token);
        }
    }
    private void saveArrayVariable(Token token){
        if(currentType != null){
            if(globalTable.contains(token.getLexeme()) || typeTable.exists(token.getLexeme()))
                semanticError(2, token);
            else{
                typeTable.put(currentType.getName(), currentType);
                lastIdentifier = token.getLexeme();
                globalTable.insert(lastIdentifier, Category.VARIABLE, currentType);
            }
        }
    }
    private void newArrayLiteral(Token token){
        arrayStack.push(new ArrayLiteral());
    }
    private void saveArrayLiteral(){
        if(currentType != null && !arrayStack.isEmpty()){
            Literal literal = arrayStack.pop();
            if(arrayStack.isEmpty()){
                if(currentType != null){
                    if(!literal.match(currentType.getBaseType()) || !literal.matchDimension(currentType))
                        semanticErrorLiteral(6);
                    currentType = null;
                    lastIdentifier="";
                }
            }else{
                arrayStack.peek().addLiteral(literal);
            }
        }
    }
    private void semanticError(int code, Token token){
        System.err.println(SemanticErrors.getError(code, token.getIntegerProperty("row"), token.getIntegerProperty("column"), token.getLexeme()));
    }
    private void semanticErrorType(int code, Token token){
        Type expected = currentType;
        Type received = typeTable.get(Type.typeName(token.getCode()));
        System.out.println(SemanticErrors.getError(code, token.getIntegerProperty("row"), token.getIntegerProperty("column"), received.toString(),expected.toString()));
    }
    private void semanticErrorType(int code){
        Type expected = currentType;
        String str = "<-";
        int i=0;
        for(;i<recordLiteral.size()-1;i++){
            str += recordLiteral.get(i).toString() + ",";
        }
        str += recordLiteral.get(i) +  "->";
        System.err.println(SemanticErrors.getError(code,row,col,str,expected.toString()));
    }
    private void semanticErrorLiteral(int code){
        System.err.println(SemanticErrors.getError(code,row,col,currentType.toString()));
    }
    private boolean typeMatchLiteral(Type type, Token token){
        Type tmp = type;
        while(tmp.getCode()>=7){
            tmp = tmp.getBaseType();
        }
        if(tmp.getCode()<7)
            return Type.TYPE_TO_TOKEN[tmp.getCode()] == token.getCode();
        return false;
    }
}
