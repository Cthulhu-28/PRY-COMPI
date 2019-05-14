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
import semantics.identifiers.Category;
import semantics.identifiers.Identifier;
import semantics.identifiers.Parameter;
import semantics.table.SymbolTable;
import semantics.identifiers.Type;
import semantics.table.TypeTable;
import utils.Stack;

/**
 *
 * @author admin
 */
public class SemanticAnalyzer {
    
    private int row;
    private int col;
    
    
    private boolean isReferenced=false;
    
    private final SymbolTable globalTable = new SymbolTable();
    private final SymbolTable localTable = new SymbolTable();
    private final TypeTable typeTable = new TypeTable();
    private String lastIdentifier;
    
    private Type currentType;
    private Type attr;
    private List<Type> recordLiteral = new ArrayList<>();
    
    private Identifier currentIdentifier;
    
    private final Stack<ArrayLiteral> arrayStack = new Stack<>();
    private final Stack<Type> parallelStack = new Stack<>();
    private final Stack<Identifier> invocations = new Stack<>();
    private final Stack<List<Type>> parameterOrder = new Stack<>();
    
    private boolean methodBody = false;
    
    private final Stack<Boolean> breakStack = new Stack<>();
    private final Stack<Boolean> continueStack = new Stack<>();
    private boolean returnFlag = false;
    private boolean canUseReturn = false;
    private final Stack<Boolean> revelloStack = new Stack<>();
    
    private Stack<Token> positions = new Stack<>();
    
    private boolean isAttribute = false;
    
    public void analyze(int symbol, Token token){
        switch(symbol){
            case Grammar.RESET:
                reset();
                break;
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
            case Grammar.PROT_FUNC:
                defineMethod(Category.FUNCTION, token);
                break;
            case Grammar.PROT_PROC:
                defineMethod(Category.PROCEDURE, token);
                break;
            case Grammar.REF_PARAM:
                isReferenced=true;
                break;
            case Grammar.PARAM_DEF:
                defineParameter(token);
                break;
            case Grammar.PARAM_TYPE:
                saveParameterType(token);
                break;
            case Grammar.FUNC_RET:
                saveReturnType(token);
                break;
            case Grammar.ID_STACK:
                findSymbol(token);
                break;
            case Grammar.ID_STACK_PUT:
                putIdentifierOnStack(token);
                break;
            case Grammar.CHECK_PROC:
                checkProc(token);
                break;
            case Grammar.CHECK_PARAM:
                checkParameters(token);
                break;
            case Grammar.POP_PARAM:
                popParameter(token);
                break;
            case Grammar.ATTR_NEXT:
                accessAttribute(token);
                break;
            case Grammar.LIT_STACK:
                readLiteralType(token);
                break;
            case Grammar.LIT_STACK_PUT:
                putLiteralOnStack();
                break;
            case Grammar.SAVE_POS:
                savePosition(token);
                break;
            case Grammar.POP_NUMERUS:
                popNumerus(token);
                break;
            case Grammar.POP_IMAGO:
                popImago(token);
                break;
            case Grammar.POP_CATENA:
                popCatena(token);
                break;
            case Grammar.POP_FRACTIO:
                popFractio(token);
                break;
            case Grammar.POP_LIBER:
                popLiber(token);
                break;
            case Grammar.POP_DUALIS:
                popDualis(token);
                break;
            case Grammar.POP_GREGORIUS:
                popGregorius(token);
                break;
            case Grammar.PUSH_NUMERUS:
                pushNumerus();
                break;
            case Grammar.PUSH_IMAGO:
                pushImago();
                break;
            case Grammar.PUSH_CATENA:
                pushCatena();
                break;
            case Grammar.PUSH_FRACTIO:
                pushFractio();
                break;
            case Grammar.PUSH_LIBER:
                pushLiber();
                break;
            case Grammar.PUSH_DUALIS:
                pushDualis();
                break;
            case Grammar.PUSH_GREGORIUS:
                pushGregorius();
                break;
            case Grammar.BODY_PROC:
                methodBody = true;
                break;
            case Grammar.FUNC_FLAGS_ON:
                beginFunction(token);
                break;
            case Grammar.CHECK_RETURN:
                checkReturn(token);
                break;
        }
    }
    private void savePosition(Token token){
        if(positions.isEmpty())
            positions.push(token);
        else{
            int _row = token.getIntegerProperty("row");
            int _column = token.getIntegerProperty("column");
            if(_row != token.getIntegerProperty("row") || _column != token.getIntegerProperty("column"))
                positions.push(token);
        }
    }
    private void reset(){
        currentIdentifier = null;
        currentType = null;
        isAttribute = false;
        isReferenced = false;
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
            if(typeMatchLiteral(currentType, token)){
                Literal literal = new SimpleLiteral(currentType, token.getLexeme());
                globalTable.modify(lastIdentifier, literal);
            }
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
                if(typeMatchLiteral(currentType, token)){
                    Literal literal = new SimpleLiteral(currentType, token.getLexeme());
                    globalTable.modify(lastIdentifier,literal);
                }
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
        }else if(currentType==null && !arrayStack.isEmpty()){
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
    private void defineMethod(Category category, Token token){
        if(!methodBody && (globalTable.contains(token.getLexeme()) || typeTable.exists(token.getLexeme()))){
            semanticError(2, token);
        }else{
            if(methodBody){
                currentIdentifier = new Identifier();
                currentIdentifier.setCategory(category);
                currentIdentifier.setName(token.getLexeme());
            }else{
                lastIdentifier = token.getLexeme();
                globalTable.insert(token.getLexeme(), category);        
            }
        }
        isReferenced = false;
    }
    private void saveParameterType(Token token){
        currentType = typeTable.get(token.getLexeme());
        if(currentType == null){
            semanticError(1, token);
        }
    }
    private void defineParameter(Token token){
        if(methodBody){
            currentIdentifier.getParameters().add(new Parameter(token.getLexeme(), currentType, isReferenced));
        }else if(globalTable.get(lastIdentifier).containsParameter(token.getLexeme())){
            globalTable.putParameter(lastIdentifier, token.getLexeme()+"-err", isReferenced, currentType);
            semanticError(7, token);
        }else{
            globalTable.putParameter(lastIdentifier, token.getLexeme(), isReferenced, currentType);
        }
    }
    private void saveReturnType(Token token){
        if(typeTable.exists(token.getLexeme())){
            if(methodBody)
                currentIdentifier.setType(typeTable.get(token.getLexeme()));
            else
                globalTable.modify(lastIdentifier, typeTable.get(token.getLexeme()));
        }else
            semanticError(1, token);
    }
    private void beginFunction(Token token){
        canUseReturn = true;
        if(!globalTable.get(currentIdentifier.getName()).equals(currentIdentifier)){
            semanticError(13, getPosition(token), currentIdentifier.getName());
        }
        currentIdentifier = globalTable.get(currentIdentifier.getName());
    }
    private void findSymbol(Token token){
        currentType = null;
        if(globalTable.contains(token.getLexeme())){
            invocations.push(globalTable.get(token.getLexeme()));
            parameterOrder.push(new ArrayList<>());
            positions.push(token);
        }else{
            semanticError(8, token);
        }
    }
    private void putIdentifierOnStack(Token token){
        if(currentType != null){
           parallelStack.push(currentType);
        }else if(!invocations.isEmpty() && !isAttribute){
            Identifier identifier = invocations.pop();
            if(identifier.getCategory()==Category.PROCEDURE){
                //semanticError(12, getPosition(token),identifier.getName());
                parallelStack.push(new Type(-2, identifier.getName()));
            }else
                parallelStack.push(identifier.getType());
        }else
            parallelStack.push(typeTable.get("$error"));
        isAttribute = false;
        currentType = null;
        currentIdentifier = null;
    }
    private void accessAttribute(Token token){
        isAttribute = true;
        if(currentType != null){
            Type oldType = currentType;
            currentType = currentType.getBaseType().getAttribute(token.getLexeme());
            if(currentType==null){               
                semanticError(9, token, oldType.getName(), token.getLexeme());
            }
        }else if(!invocations.isEmpty()){
            Identifier identifier = invocations.pop();
            currentType = identifier.getType().getAttribute(token.getLexeme());
            if(currentType==null){
                semanticError(9, token, identifier.getName(), token.getLexeme());
            }
        }
    }
    private void checkProc(Token token){
        if(!invocations.isEmpty()){
            Identifier identifier = invocations.peek();
            if(!identifier.isMethod()){
                semanticError(10, getPosition(token), identifier.getName());
                identifier.setParameterNumber(-1);
            }
        }
    }
    private void checkReturn(Token token){
        if(!canUseReturn)
            semanticError(14, getPosition(token), "");
    }
    private void popParameter(Token token){
        if(!invocations.isEmpty()){
            Identifier identifier = invocations.peek();
            if(identifier.isMethod()){
                Type t = parallelStack.peek();
               parameterOrder.peek().add(parallelStack.pop());
            }else
                parallelStack.pop();
        }
    }
    private void checkParameters(Token token){
        if(!invocations.isEmpty()){
            Identifier identifier = invocations.peek();
            if(identifier.isMethod()){
                List<Type> order = parameterOrder.pop();
                boolean matched = order.size() == identifier.getParameters().size();
                for(int i=0;i<order.size()&&matched;i++)
                    matched = matched && Type.isCompatible(order.get(i).getCode(), identifier.getParameters().get(i).getType().getCode());
                if(!matched){
                    String str = order.toString().replace("[", "(").replace("]", ")");
                    str = identifier.getName()+str;
                    semanticError(11, getPosition(token), str,identifier.toString());
                }
            }
        }
    }
    private void readLiteralType(Token token){
        currentType = typeTable.get(Type.typeName(token.getCode()));
    }
    private void putLiteralOnStack(){
        if(!arrayStack.isEmpty())
            parallelStack.push(arrayStack.pop().getType());
        else
            parallelStack.push(currentType);
        currentType = null;
    }
    private void popNumerus(Token token){
        Type expected = typeTable.get("numerus");
        Type received = parallelStack.pop();
        received = fixType(received, expected);
        if(!Type.isCompatible(received.getCode(), expected.getCode())){
            semanticError(3, getPosition(token),received.toString(),expected.toString());
        }
    }
    private void popImago(Token token){
        Type expected = typeTable.get("imago");
        Type received = parallelStack.pop();
        received = fixType(received, expected);
        if(!Type.isCompatible(received.getCode(), expected.getCode())){
            semanticError(3, getPosition(token),received.toString(),expected.toString());
        }
    }
    private void popCatena(Token token){
        Type expected = typeTable.get("catena");
        Type received = parallelStack.pop();
        received = fixType(received, expected);
        if(!Type.isCompatible(received.getCode(), expected.getCode())){
            semanticError(3, getPosition(token),received.toString(),expected.toString());
        }
    }
    private void popFractio(Token token){
        Type expected = typeTable.get("fractio");
        Type received = parallelStack.pop();
        received = fixType(received, expected);
        if(!Type.isCompatible(received.getCode(), expected.getCode())){
            semanticError(3, getPosition(token),received.toString(),expected.toString());
        }
    }
    private void popLiber(Token token){
        Type expected = typeTable.get("liber");
        Type received = parallelStack.pop();
        received = fixType(received, expected);
        if(!Type.isCompatible(received.getCode(), expected.getCode())){
            semanticError(3, getPosition(token),received.toString(),expected.toString());
        }
    }
    private void popDualis(Token token){
        Type expected = typeTable.get("dualis");
        Type received = parallelStack.pop();
        received = fixType(received, expected);
        if(!Type.isCompatible(received.getCode(), expected.getCode())){
            semanticError(3, getPosition(token),received.toString(),expected.toString());
        }
    }
    private void popGregorius(Token token){
        Type expected = typeTable.get("gregorius");
        Type received = parallelStack.pop();
        received = fixType(received, expected);
        if(!Type.isCompatible(received.getCode(), expected.getCode())){
            semanticError(3, getPosition(token),received.toString(),expected.toString());
        }
    }
    private void pushNumerus(){
        System.out.println("dfdfdf");
        parallelStack.push(typeTable.get("numerus"));
    }
    private void pushImago(){
        parallelStack.push(typeTable.get("imago"));
    }
    private void pushCatena(){
        parallelStack.push(typeTable.get("catena"));
    }
    private void pushFractio(){
        parallelStack.push(typeTable.get("fractio"));
    }
    private void pushLiber(){
        parallelStack.push(typeTable.get("liber"));
    }
    private void pushDualis(){
        parallelStack.push(typeTable.get("dualis"));
    }
    private void pushGregorius(){
        parallelStack.push(typeTable.get("gregorius"));
    }
    private Token getPosition(Token token){
//        if(!positions.isEmpty())
//            return positions.pop();
        return token;
    }
    private Type fixType(Type type, Type expected){
        Type t = type.getBaseTypeDepth();
        if(t == null)
            t = type;
        return t.getCode() == -1 ? expected : t;
    }
    private void semanticError(int code, Token token){
        System.err.println(SemanticErrors.getError(code, token.getIntegerProperty("row"), token.getIntegerProperty("column"), token.getLexeme()));
    }
    private void semanticError(int code, Token token, String... tokens){
        System.err.println(SemanticErrors.getError(code, token.getIntegerProperty("row"), token.getIntegerProperty("column"), tokens));
    }
    private void semanticErrorType(int code, Token token){
        Type expected = currentType;
        Type received = typeTable.get(Type.typeName(token.getCode()));
        System.err.println(SemanticErrors.getError(code, token.getIntegerProperty("row"), token.getIntegerProperty("column"), received.toString(),expected.toString()));
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
