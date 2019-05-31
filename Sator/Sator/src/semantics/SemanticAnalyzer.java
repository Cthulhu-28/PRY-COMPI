/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics;

import generator.CodeGenerator;
import java.io.FileNotFoundException;
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
import semantics.literals.RecordLiteral;
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
    private SymbolTable localTable = new SymbolTable();
    private final TypeTable typeTable = new TypeTable();
    private String lastIdentifier;
    
    private Type currentType;
    private Type attr;
    private final List<Type> recordLiteral = new ArrayList<>();
    
    private Identifier currentIdentifier;
    private Identifier currentMethod;
    private Identifier genratorIdentifier;
    private Literal currentLiteral;
    
    private final Stack<ArrayLiteral> arrayStack = new Stack<>();
    private final Stack<RecordLiteral> recordStack = new Stack<>();
    private final Stack<Type> parallelStack = new Stack<>();
    private final Stack<Identifier> invocations = new Stack<>();
    private final Stack<List<Type>> parameterOrder = new Stack<>();
    private final Stack<Integer> accessCount = new Stack<>();
    
    private boolean methodBody = false;
    
    private final Stack<Type> siglaTypes = new Stack<>();
    
    private final Stack<Boolean> breakStack = new Stack<>();
    private final Stack<Boolean> continueStack = new Stack<>();
    private final Stack<Boolean> defaultStack = new Stack<>();
    private boolean returnFlag = false;
    private boolean canUseReturn = false;
    private final Stack<Boolean> revelloStack = new Stack<>();
    
    private final Stack<Token> positions = new Stack<>();
    //private final Stack<Identifier> last = new Stack<>();
    private Identifier last = null;
    
    private boolean isAttribute = false;
    private boolean useOperator=false;
    
    private CodeGenerator generator;
    
    public SemanticAnalyzer(){
        for(Identifier i : Identifier.IO()){
            globalTable.put(i.getName(), i);
        }
    }
    
    public void analyze(int symbol, Token token) throws FileNotFoundException{
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
            case Grammar.REG_LIT_OPEN:
                newRecordLiteral(token);
                break;
            case Grammar.REG_LIT:
                addRecordAttrLiteral(token);
                break;
            case Grammar.REG_LIT_END:
                saveRecordLiteral(token);
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
            case Grammar.ADD_ACCESS:
                addCount();
                break;
            case Grammar.CHECK_ARRAY:
                checkArray(token);
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
            case Grammar.FUNC_FLAGS_OFF:
                endFunction(token);
                break;
            case Grammar.PROC_FLAGS_ON:
                beginProcedure(token);
                break;
            case Grammar.PROC_FLAGS_OFF:
                endProcedure(token);
                break;
            case Grammar.CHECK_RETURN:
                checkReturn(token);
                break;
            case Grammar.POP_RETURN:
                popReturn(token);
                break;
            case Grammar.PUSH_DEFAULT:
                pushDefault(token);
                break;
            case Grammar.POP_DEFAULT:
                popDefault(token);
                break;
            case Grammar.CHECK_DEFAULT:
                checkDefault(token);
                break;
            case Grammar.CHECK_BREAK:
                checkBreak(token);
                break;
            case Grammar.LOOP_FLAGS_ON:
                loopFlagsOn(token);
                break;
            case Grammar.LOOP_FLAGS_OFF:
                loopFlagsOff(token);
                break;
            case Grammar.TRY_FLAGS_ON:
                tryFlagsOn(token);
                break;
            case Grammar.TRY_GLAGS_OFF:
                tryFlagsOff(token);
                break;
            case Grammar.CHECK_FOR_ID:
                checkForId(token);
                break;
            case Grammar.CHECK_CONTINUE:
                checkContinue(token);
                break;
            case Grammar.CHECK_REVELLO:
                checkRevello(token);
                break;
            case Grammar.POP_ARRAY:
                popArray(token);
                break;
            case Grammar.PUSH_ID_SIGLA:
                pushIdSigla(token);
                break;
            case Grammar.POP_ID_SIGLA:
                popIdSilgla();
                break;
            case Grammar.POP_TYPE:
                popType(token);
                break;
            case Grammar.CHECK_IS_MEMORY:
                checkIsMemory(token);
                break;
            case Grammar.CG_INIT:
                generator = new CodeGenerator(token.getLexeme());
                break;
            case Grammar.CG_CLOSE:
                generator.close();
                break;
            case Grammar.CG_DATA_SEGMENT:
                generator.createDataSegment();
                break;
            case Grammar.CG_WRITE_VAR:
                generator.addVariable(currentIdentifier);
                break;
            case Grammar.CG_CODE_SEG:
                generator.createCodeSegment();
                break;
            case Grammar.CG_WRITE_LIT:
                generator.putLiteral(currentLiteral);
                break;
            case Grammar.CG_WRITE_STACK:
                if(token.getCode()!=27)
                    generator.writePush(genratorIdentifier);
                break;
            case Grammar.CG_INC:
                generator.incrementum();
                break;
            case Grammar.CG_DEC:
                generator.decrementum();
                break;
            case Grammar.CG_NON:
                generator.non();
                break;
            case Grammar.CG_UPPER:
                generator.upper();
                break;
            case Grammar.CG_LOWER:
                generator.lower();
                break;
            case Grammar.CG_ALPHA:
                generator.isAlpha();
                break;
            case Grammar.CG_DIGIT:
                generator.isDigit();
                break;
            case Grammar.CG_LENGTH:
                generator.length();
                break;
            case Grammar.CG_FLOOR:
                generator.floor();
                break;
            case Grammar.CG_ROUND:
                generator.round();
                break;
            case Grammar.CG_ANNUS:
                generator.annus();
                break;
            case Grammar.CG_MENSIS:
                generator.mensis();
                break;
            case Grammar.CG_DIES:
                generator.dies();
                break;
            case Grammar.CG_NUNCS:
                generator.nunc();
                break;
            case Grammar.CG_DAY:
                generator.day();
                break;
            case Grammar.CG_WEEK:
                generator.week();
                break;
            case Grammar.CG_LEAP:
                generator.isLeap();
                break;
            case Grammar.CG_CONCAT:
                generator.concat();
                break;
            case Grammar.CG_CONTAINS:
                generator.concat();
                break;
            case Grammar.CG_MULT:
                generator.mul();
                break;
            case Grammar.CG_DIV:
                generator.div();
                break;
            case Grammar.CG_MOD:
                generator.modulus();
                break;
            case Grammar.CG_FRAC_MUL:
                generator.fracMul();
                break;
            case Grammar.GG_FRAC_DIV:
                generator.fracDiv();
                break;
            case Grammar.CG_PLUS:
                generator.plus();
                break;
            case Grammar.CG_SUB:
                generator.subtract();
                break;
            case Grammar.CG_FRAC_PLUS:
                generator.fracPlus();
                break;
            case Grammar.CG_FRAC_MINUS:
                generator.fracSubtract();
                break;
            case Grammar.CG_DATE_ADD:
                generator.dateAdd();
                break;
            case Grammar.CG_DATE_MINU:
                generator.dateMinus();
                break;
            case Grammar.CG_LESS:
                generator.less();
                break;
            case Grammar.CG_EQ:
                generator.equal();
                break;
            case Grammar.CG_DIFF:
                generator.different();
                break;
            case Grammar.CG_AND:
                generator.and();
                break;
            case Grammar.CG_XOR:
                generator.xor();
                break;
            case Grammar.CG_OR:
                generator.or();
                break;
            case Grammar.CG_ASSIGN:
                generator.assignValue(last);
                last=null;
                break;
        }
    }
    private void checkIsMemory(Token token){
        if(useOperator){
            semanticError(22, token);
        }
        else if(last == null){
            semanticError(22, token);
        }else if(last.getCategory()==Category.CONSTANT){
            semanticError(23, token);
        }else if(last.getCategory()!=Category.VARIABLE){
            semanticError(22, token);
        }
        useOperator = false;
        //last = null;
    }
    private boolean contains(String token){
        if(methodBody){
            if(localTable.contains(token))
                return true;
            else if(currentMethod.containsParameter(token))
                return true;
        }
        return globalTable.contains(token) || typeTable.exists(token);
    }
    private boolean containsSymbol(String token){
        if(methodBody){
            if(localTable.contains(token))
                return true;
            else if(currentMethod.containsParameter(token))
                return true;
        }
        return globalTable.contains(token);
    }
    private SymbolTable selectTable(){
        if(methodBody)
            return localTable;
        return globalTable;
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
        useOperator = false;
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
            currentIdentifier = new Identifier();
            currentIdentifier.setName(token.getLexeme());
            currentIdentifier.setType(currentType);
            globalTable.insert(token.getLexeme(), Category.CONSTANT,currentType);  
        }
    }
    private void saveConstantLiteral(Token token){
        if(currentType != null){
            if(typeMatchLiteral(currentType, token)){
                Literal literal = new SimpleLiteral(currentType, token.getLexeme());
                currentIdentifier.setInitialValue(literal);
                globalTable.modify(lastIdentifier, literal);
            }
            else
                semanticErrorType(3, token);
            }
        currentType = null;
        //lastIdentifier = "";
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
        //if(globalTable.contains(token.getLexeme())){
        if(contains(token.getLexeme())){
            semanticError(2, token);
        }else{
            lastIdentifier = token.getLexeme();
            selectTable().insert(token.getLexeme(), Category.VARIABLE,currentType);
            currentIdentifier = selectTable().get(lastIdentifier);
            
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
                    selectTable().modify(lastIdentifier,literal);
                    currentIdentifier = selectTable().get(lastIdentifier);
                }
                else
                    semanticErrorType(3, token);
                currentType = null;
            }
        }
        if(currentType==null){
            lastIdentifier="";
            return;
        }
        if(!currentType.isArray())
            lastIdentifier = "";
    }
    private void addRecordAttrLiteral(Token token){
        if(currentType != null){
            if(currentType.isRecord()){
                //Type t = typeTable.get(Type.typeName(token.getCode()));
                //recordLiteral.add(t);
                String name = Type.typeName(token.getCode());
                Type t = null;
                if(name !=null)
                    t = typeTable.get(name);
                if(t != null){
                    Literal literal = new SimpleLiteral(t, token.getLexeme());
                    recordStack.peek().addLiteral(literal);
                }
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
        }else if(currentType==null && !recordStack.isEmpty()){
            String name = Type.typeName(token.getCode());
                Type t = null;
                if(name !=null)
                    t = typeTable.get(name);
                if(t != null){
                    Literal literal = new SimpleLiteral(t, token.getLexeme());
                    recordStack.peek().addLiteral(literal);
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
            //if(globalTable.contains(token.getLexeme()) || typeTable.exists(token.getLexeme()))
            if(contains(token.getLexeme()))
                semanticError(2, token);
            else{
                typeTable.put(currentType.getName(), currentType);
                lastIdentifier = token.getLexeme();
                selectTable().insert(lastIdentifier, Category.VARIABLE, currentType);
                currentIdentifier = selectTable().get(lastIdentifier);
            }
        }
    }
    private void newArrayLiteral(Token token){
        arrayStack.push(new ArrayLiteral());
    }
    private void newRecordLiteral(Token token){
        recordStack.push(new RecordLiteral());
    }
    private void saveArrayLiteral(){
        if(currentType != null && !arrayStack.isEmpty()){
            Literal literal = arrayStack.pop();
            if(arrayStack.isEmpty()){
                if(currentType != null){
                    if(!literal.match(currentType.getBaseType()) || !literal.matchDimension(currentType))
                        semanticErrorLiteral(6);
                    currentType = null;
                    selectTable().modify(lastIdentifier,literal);
                    currentIdentifier = selectTable().get(lastIdentifier);
                    //generator.addVariable(currentIdentifier);
                    lastIdentifier="";
                }
            }else{
                arrayStack.peek().addLiteral(literal);
            }
        }else if(!arrayStack.isEmpty()){
            ArrayLiteral literal = arrayStack.pop();
            if(arrayStack.isEmpty()){
                arrayStack.push(literal);
            }else{
                arrayStack.peek().addLiteral(literal);
            }
        }
    }
    private void saveRecordLiteral(Token token){
        if(currentType != null && !recordStack.isEmpty()){
            Literal literal = recordStack.pop();
            if(recordStack.isEmpty()){
                if(currentType != null){
                   if(!literal.match(currentType)){
                        semanticError(3, token, literal.getType().toString(),currentType.toString());
                   }
                    currentType = null;
                    lastIdentifier="";
                }
            }else{
                recordStack.peek().addLiteral(literal);
            }
        }else if(!recordStack.isEmpty()){
            RecordLiteral literal = recordStack.pop();
            if(recordStack.isEmpty()){
                Type t = literal.getType();
                recordStack.push(literal);
            }else{
                recordStack.peek().addLiteral(literal);
            }
        }
    }
    private void defineMethod(Category category, Token token){
        if(!methodBody && (globalTable.contains(token.getLexeme()) || typeTable.exists(token.getLexeme()))){
            semanticError(2, token);
        }else{
            if(methodBody){
                currentMethod = new Identifier();
                currentMethod.setCategory(category);
                currentMethod.setName(token.getLexeme());
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
            currentMethod.getParameters().add(new Parameter(token.getLexeme(), currentType, isReferenced));
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
                currentMethod.setType(typeTable.get(token.getLexeme()));
            else
                globalTable.modify(lastIdentifier, typeTable.get(token.getLexeme()));
        }else
            semanticError(1, token);
    }
    private void beginFunction(Token token){
        canUseReturn = true;
        if(!globalTable.get(currentMethod.getName()).equals(currentMethod)){
            semanticError(13, getPosition(token), currentMethod.getName());
        }
        currentMethod = globalTable.get(currentMethod.getName());
    }
    private void endFunction(Token token){
        if(!returnFlag){
            semanticError(15, getPosition(token), currentIdentifier.getName());
        }
        methodBody = false;
        currentMethod = null;
        returnFlag = false;
        canUseReturn = false;
        localTable = new SymbolTable();
        System.gc();
    }
    private void beginProcedure(Token token){
        breakStack.push(true);
        if(!globalTable.get(currentMethod.getName()).equals(currentMethod)){
            semanticError(13, getPosition(token), currentMethod.getName());
        }
        currentMethod = globalTable.get(currentMethod.getName());
    }
    private void endProcedure(Token token){
        methodBody = false;
        currentMethod = null;
        if(!breakStack.isEmpty())
            breakStack.pop();
        localTable = new SymbolTable();
        System.gc();
    }
    private void findSymbol(Token token){
        currentType = siglaFind(token);
        if(currentType!=null){
            return;
        }
        
        if(globalTable.contains(token.getLexeme())){
            invocations.push(globalTable.get(token.getLexeme()));
            //last.push(invocations.peek());
            if(invocations.peek().getType() !=null && invocations.peek().getType().isArray()){
                accessCount.push(0);
            }
            parameterOrder.push(new ArrayList<>());
            positions.push(token);
        }else if(methodBody && localTable.contains(token.getLexeme())){
            invocations.push(localTable.get(token.getLexeme()));
            //last.push(invocations.peek());
            if(invocations.peek().getType() !=null && invocations.peek().getType().isArray()){
                accessCount.push(0);
            }
            parameterOrder.push(new ArrayList<>());
            positions.push(token);
        }else if (methodBody && currentIdentifier != null && currentIdentifier.containsParameter(token.getLexeme())){
            invocations.push(currentIdentifier.getParameter(token.getLexeme()));
            //last.push(invocations.peek());
            if(invocations.peek().getType().isArray()){
                accessCount.push(0);
            }
            parameterOrder.push(new ArrayList<>());
            positions.push(token);
        }else{
            semanticError(8, token);
        }
    }
    private void putIdentifierOnStack(Token token){
        if(currentType != null){
           parallelStack.push(currentType);
           last = new Identifier();
           last.setCategory(Category.VARIABLE);
        }else if(!invocations.isEmpty() && !isAttribute){
            Identifier identifier = invocations.pop();
            if(invocations.isEmpty())
                last = identifier;
            if(identifier.getCategory()==Category.PROCEDURE){
                //semanticError(12, getPosition(token),identifier.getName());
                parallelStack.push(new Type(-2, "método sin retorno"));
            }else if(identifier.getType().isArray()){
                parallelStack.push(identifier.getType().getBaseType());
            }else{
                genratorIdentifier = identifier;
                parallelStack.push(identifier.getType());
            }
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
    private void addCount(){
        if(!invocations.isEmpty() && invocations.peek().getType().isArray())
            accessCount.editTop(accessCount.peek()+1);
    }
    private void checkArray(Token token){
        if(!invocations.isEmpty() && invocations.peek().getType().isArray()){
            Integer count = accessCount.pop();
            int array = invocations.peek().getType() == null ? 0 : invocations.peek().getType().getDimension().size();
            if(count != array){
                semanticError(20, getPosition(token), invocations.peek().getName(), array+"", count+"");
            }
        }
    }
    private void pushIdSigla(Token token){
        if(!siglaTypes.isEmpty()){
            Type t = siglaFind(token);
            if(t!=null){
                siglaTypes.push(t.getBaseType());
                return;
            }
        }
        Identifier identifier = globalTable.get(token.getLexeme());
        if(identifier!=null){
            if(identifier.getType().isRecord()){
                siglaTypes.push(identifier.getType());
            }else{
                semanticError(3, token, "coniugo",identifier.getType().toString());
            }
        }else{
            semanticError(8, token);
        }
    }
    private Type siglaFind(Token token){
        if(!siglaTypes.isEmpty()){
            for(Type t : siglaTypes.toList()){
                Type type = t.getAttribute(token.getLexeme());
                if(type!=null)
                    return type;
            }
            return null;
            //return siglaTypes.peek().getAttribute(token.getLexeme());
        }
        return null;
    }
    private void popIdSilgla(){
        if(!siglaTypes.isEmpty()){
            siglaTypes.pop();
        }
    }
    private void popReturn(Token token){
        if(canUseReturn){
            returnFlag = true;
            Type expected = currentMethod.getType();
            Type received = parallelStack.pop();
            received = fixType(received, expected);
            if(!Type.isCompatible(received.getCode(), expected.getCode())){
                semanticError(3, getPosition(token),received.toString(),expected.toString());
            }
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
        if(Type.typeName(token.getCode()) != null){
            currentType = typeTable.get(Type.typeName(token.getCode()));
            currentLiteral = new SimpleLiteral(currentType, token.getLexeme());
        }
        else
            currentType = null;
    }
    private void putLiteralOnStack(){
        if(!arrayStack.isEmpty()){
            currentLiteral = arrayStack.pop();
            parallelStack.push(currentLiteral.getType());
        }
        else if(!recordStack.isEmpty()){
            currentLiteral = arrayStack.pop();
            parallelStack.push(currentLiteral.getType());
        }
        else
            parallelStack.push(currentType);
        currentType = null;
    }
    
    private void pushDefault(Token token){
        defaultStack.push(false);
        breakStack.push(true);
    }
    private void checkDefault(Token token){
        boolean def = defaultStack.peek();
        if(def)
            semanticError(16, token);
        else{
            defaultStack.pop();
            defaultStack.push(true);
        }
    }
    private void popDefault(Token token){
        defaultStack.pop();
        breakStack.pop();
    }
    private void checkBreak(Token token){
        if(breakStack.isEmpty()){
            semanticError(17, token);
        }
    }
    private void checkContinue(Token token){
        if(continueStack.isEmpty()){
            semanticError(18, token);
        }
    }
    private void checkRevello(Token token){
        if(revelloStack.isEmpty()){
            semanticError(19, token);
        }
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
    private void popType(Token token){
        Type received = parallelStack.pop();
        Type expected = parallelStack.pop();
        if(expected.getCode()==-1 || received.getCode()==-1)
            return;
        if(expected.getCode()==-1)
            expected=received;
        if(received.getCode()==-1)
            received=expected;
        if(!expected.equals(received)){
            //semanticError(3, getPosition(token),received.toString(),expected.toString());
            if(!Type.isCompatible(received.getCode(), expected.getCode()))
                semanticError(3, getPosition(token),received.toString(),expected.toString());
        }
    }
    private void popArray(Token token){
        Type expected = typeTable.get("numerus");
        Type received = parallelStack.pop();
        if(received.isArray()){
            if(received.getDimension().size() != 1){
                semanticError(3, getPosition(token), received.toString(),"numerus[]");
            }else{
               if(!Type.isCompatible(received.getBaseTypeDepth().getCode(), expected.getCode())){
                   semanticError(3, getPosition(token), received.toString(),"numerus[]");
               }
            }
        }else{
            semanticError(3, getPosition(token), received.toString(),"numerus[]");
        }
    }
    private void checkForId(Token token){
        if(globalTable.contains(token.getLexeme())){
            Type expected = typeTable.get("numerus");
            Type received = globalTable.get(token.getLexeme()).getType().getBaseTypeDepth();
            if(!Type.isCompatible(received.getBaseTypeDepth().getCode(), expected.getCode())){
                semanticError(3, token,received.toString(),expected.toString());
            }
        }else if(methodBody && localTable.contains(token.getLexeme())){
            Type expected = typeTable.get("numerus");
            Type received = localTable.get(token.getLexeme()).getType().getBaseTypeDepth();
            if(!Type.isCompatible(received.getBaseTypeDepth().getCode(), expected.getCode())){
                semanticError(3, token,received.toString(),expected.toString());
            }
        }else if(methodBody && currentMethod != null && currentMethod.containsParameter(token.getLexeme())){
            Type expected = typeTable.get("numerus");
            Type received = currentMethod.getParameter(token.getLexeme()).getType().getBaseTypeDepth();
            if(!Type.isCompatible(received.getBaseTypeDepth().getCode(), expected.getCode())){
                semanticError(3, token,received.toString(),expected.toString());
            }
        }else
            semanticError(8, token);
    }
    private void loopFlagsOn(Token token){
        breakStack.push(true);
        continueStack.push(true);
    }
    private void loopFlagsOff(Token token){
        breakStack.pop();
        continueStack.pop();
    }
    private void tryFlagsOn(Token token){
        revelloStack.push(true);
    }
    private void tryFlagsOff(Token token){
        revelloStack.pop();
    }
    private void pushNumerus(){
        useOperator = true;
        parallelStack.push(typeTable.get("numerus"));
    }
    private void pushImago(){
        useOperator = true;
        parallelStack.push(typeTable.get("imago"));
    }
    private void pushCatena(){
        useOperator = true;
        parallelStack.push(typeTable.get("catena"));
    }
    private void pushFractio(){
        useOperator = true;
        parallelStack.push(typeTable.get("fractio"));
    }
    private void pushLiber(){
        useOperator = true;
        parallelStack.push(typeTable.get("liber"));
    }
    private void pushDualis(){
        useOperator = true;
        parallelStack.push(typeTable.get("dualis"));
    }
    private void pushGregorius(){
        useOperator = true;
        parallelStack.push(typeTable.get("gregorius"));
    }
    private Token getPosition(Token token){
        if(!positions.isEmpty())
            return positions.pop();
        return token;
    }
    private Type fixType(Type type, Type expected){
        if(type.isArray() || type.isRecord())
            return type;
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
