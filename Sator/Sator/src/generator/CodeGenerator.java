/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;
import semantics.identifiers.Identifier;
import semantics.identifiers.Type;
import semantics.literals.Literal;
import utils.HashMap;
import utils.Stack;

/**
 *
 * @author admin
 */
public class CodeGenerator {
    private final String name;
    private final FileGenerator fileGenerator;
    private final FileGenerator runtime;
    private final FileGenerator literals;
    private final Stack<String> tagsStack = new Stack<>();
    private final Stack<String> instrucStack = new Stack<>();
    private final Stack<String> continueStack = new Stack<>();
    private final Stack<String> breakStack = new Stack<>();
    private final Stack<Identifier> forStack = new Stack<>();
    private final HashMap<Literal,Identifier> literalsPool = new HashMap<Literal, Identifier>() {
        @Override
        public int hashCode(Literal key) {
            int hash =0;
            String str = key.toString().toLowerCase();
            for(int i=0;i<str.length();i++){
                hash += str.charAt(i);
                hash += hash << 10;
                hash ^= hash >> 6;
            }
            hash += hash << 3;
            hash ^= hash >> 11;
            hash += hash <<15;
            int g = Math.abs(hash) % this.SIZE;
            return Math.abs(hash) % this.SIZE;
        }
    };
    public CodeGenerator(String name) throws FileNotFoundException{
        this.name=name;
        File f = new File("output");
        f.mkdir();
        f = new File("output\\"+name);
        f.mkdir();
        literals = new FileGenerator("output\\"+name+"\\literals.asm");
        name = "output"+"\\"+name+"\\"+name;
        fileGenerator = new FileGenerator(name+".asm");
        runtime = new FileGenerator(name+"_runtime.asm");
        
    }
    public void close(){
        fileGenerator.printf("\tmov ax,4c00h");
        fileGenerator.printf("\tint 21h");
        fileGenerator.printf("end start");
        fileGenerator.close();
        runtime.close();
        literals.close();
    }
    private String literal(int code, String literal){
        switch(code){
            case 0:
                return LiteralWriter.toNumerus(literal);
            case 1:
                return LiteralWriter.toImago(literal);
            case 2:
                return LiteralWriter.toFractio(literal);
            case 3:
                return LiteralWriter.toCatena(literal);
            case 5:
                return LiteralWriter.toDualis(literal);
            case 6:
                return LiteralWriter.toGregorius(literal);
        }
        return "0";
    }
    public void call(Identifier identifier){
        fileGenerator.printf("\tcall %s", identifier.getName());
    }
    public void addVariable(Identifier identifier){
        String size="";
        Type type = identifier.getType().getBaseTypeDepth();
        StringBuilder sb = new StringBuilder();
        String lit;
        if(identifier.getInitialValue()==null){
            if(identifier.getType().isArray())
                type = identifier.getType();
            if(identifier.getType().getCode()==Type.NUMERUS)
                fileGenerator.printf("%s %s %s %d","\t" ,identifier.getName(),"dw",0);
            else
                fileGenerator.printf("%s %s %s %d dup (0)","\t" ,identifier.getName(),"db",type.getSize());
        }else{
            if(identifier.getType().isArray()){
                Type t = identifier.getType().getBaseType().getBaseTypeDepth();
                lit= String.join(",", identifier.getInitialValue().toList().stream().map(l->literal(t.getCode(), l.toString())).collect(Collectors.toList()));            
                type=t;
                fileGenerator.printf("%s %s %s %s", "\t",identifier.getName(),type.getDataSize(),lit);
            }else{
                lit = identifier.getInitialValue().toString();
                fileGenerator.printf("%s %s %s %s", "\t",identifier.getName(),type.getDataSize(),literal(type.getCode(), lit));
            }
            
        }
    }
    public void createDataSegment(){
        fileGenerator.printf(".model small","");
        fileGenerator.printf(".stack 0ffffh","");
        fileGenerator.printf(".data");
    }
    public void createCodeSegment(){
        fileGenerator.printf("\tinclude %s","literals.asm");
        fileGenerator.printf(".code");
        fileGenerator.printf(".386");
        fileGenerator.printf("\tinclude %s","runtime.asm"); 
        fileGenerator.printf("start:");
        fileGenerator.printf("\tmov ax,@data");
        fileGenerator.printf("\tmov ds,ax");
    }
    
    public void putLiteral(Literal literal){
        Identifier identifier;
        if(literalsPool.contains(literal)){
            identifier = literalsPool.get(literal);
            //addLiteral(identifier);
            
        }else{
            identifier = new Identifier();
            identifier.setName(LiteralGenerator.getGenerator().nextLiteral());
            identifier.setInitialValue(literal);
            identifier.setType(literal.getType());
            literalsPool.put(literal, identifier);
            addLiteral(identifier);
        }
        writePush(identifier);
    }
    public void writePush(Identifier identifier){
        Type type = identifier.getType().getBaseTypeDepth();
        switch(type.getCode()){
            case Type.NUMERUS:
                fileGenerator.printf("\tpush %s", identifier.getName());
                break;
            case Type.DUALIS:
                fileGenerator.printf("\txor ax,ax");
                fileGenerator.printf("\tmov al,%s", identifier.getName());
                fileGenerator.printf("\tpush ax");
                break;
            case Type.FRACTIO:
                fileGenerator.printf("\tpush %s[2]", identifier.getName());
                fileGenerator.printf("\tpush %s[0]", identifier.getName());
                break;
            case Type.GREGORIUS:
                fileGenerator.printf("\txor dx,dx");
                fileGenerator.printf("\tmov dl,byte ptr %s[0]", identifier.getName());
                fileGenerator.printf("\tmov ah,byte ptr %s[1]", identifier.getName());
                fileGenerator.printf("\tmov al,byte ptr %s[2]", identifier.getName());
                fileGenerator.printf("\tpush ax");
                fileGenerator.printf("\tpush dx");
                break;
            case Type.IMAGO:
                fileGenerator.printf("\txor ax,ax");
                fileGenerator.printf("\tmov al,%s[0]", identifier.getName());
                fileGenerator.printf("\tpush ax");
                break;
            case Type.LIBER:
                fileGenerator.printf("\tlea ax,%s", identifier.getName());
                fileGenerator.printf("\t push ax");
            default:
                String tag = TagGenerator.getGenerator().nextTag();                
                fileGenerator.printf("\tmov bx, %d",type.getSize());
                fileGenerator.printf("%s:",tag);
                fileGenerator.printf("\tdec bx");
                fileGenerator.printf("\txor ax,ax");
                fileGenerator.printf("\tmov al,%s[bx]",identifier.getName());
                fileGenerator.printf("\tpush ax");
                fileGenerator.printf("\tcmp bx,0");
                fileGenerator.printf("ja %s",tag);
                break;
        }
    }
    public void incrementum(){
        fileGenerator.printf("\tcall increm");
    }
    public void decrementum(){
        fileGenerator.printf("\tcall decrem");
    }
    public void non(){
        fileGenerator.printf("\tcall non");
    }
    public void upper(){
        fileGenerator.printf("\tcall uperc");
    }
    public void lower(){
        fileGenerator.printf("\tcall lowc");
    }
    public void isAlpha(){
        fileGenerator.printf("\tcall letter");
    }
    public void isDigit(){
        fileGenerator.printf("\tcall numero");
    }
    public void length(){
        fileGenerator.printf("\tcall len");
    }
    public void floor(){
        fileGenerator.printf("\tcall pent");
    }
    public void round(){
        fileGenerator.printf("\tcall round");
    }
    public void annus(){
        fileGenerator.printf("\tcall getYear");
    }
    public void mensis(){
        fileGenerator.printf("\tcall getMonth");
    }
    public void dies(){
        fileGenerator.printf("\tcall getDay");
    }
    public void nunc(){
        fileGenerator.printf("\tcall nunc");
    }
    public void day(){
        fileGenerator.printf("\tcall weekDay");
    }
    public void week(){
        fileGenerator.printf("\tcall dayInMonth");
    }
    public void isLeap(){
        fileGenerator.printf("\tcall isLeap");
    }
    public void concat(){
        fileGenerator.printf("\tcall concat");
    }
    public void index(){
        fileGenerator.printf("\tcall index");
    }
    public void mul(){
        fileGenerator.printf("\tcall multi");
    }
    public void div(){
        fileGenerator.printf("\tcall divide");
    }
    public void modulus(){
        fileGenerator.printf("\tcall modulo");
    }
    public void fracMul(){
        fileGenerator.printf("\tcall mult");
    }
    public void fracDiv(){
        fileGenerator.printf("\tcall divit");
    }
    public void plus(){
        fileGenerator.printf("\tcall suma");
    }
    public void subtract(){
        fileGenerator.printf("\tcall resta");
    }
    public void fracPlus(){
        fileGenerator.printf("\tcall addit");
    }
    public void fracSubtract(){
        fileGenerator.printf("\tcall subs");
    }
    public void dateAdd(){
        fileGenerator.printf("\tcall addDays");
    }
    public void dateMinus(){
        fileGenerator.printf("\tcall subDays");
    }
    public void less(){
        fileGenerator.printf("\tcall less");
    }
    public void greater(){
        fileGenerator.printf("\tcall more");
    }
    public void greaterEq(){
        fileGenerator.printf("\tcall moreeq");
    }
    public void lessEq(){
        fileGenerator.printf("\tcall lesseq");
    }
    public void equal(){
        fileGenerator.printf("\tcall equal");
    }
    public void different(){
        fileGenerator.printf("\tcall difer");
    }
    public void and(){
        fileGenerator.printf("\tcall et");
    }
    public void xor(){
        fileGenerator.printf("\tcall aut");
    }
    public void or(){
        fileGenerator.printf("\tcall aut");
    }
   
    private void addLiteral(Identifier identifier){
       String size="";
       Type type = identifier.getType().getBaseTypeDepth();
       StringBuilder sb = new StringBuilder();
       String lit;
       if(identifier.getInitialValue()==null){
           if(identifier.getType().isArray())
               type = identifier.getType();
           fileGenerator.printf("\t%s %s %d dup (0)" ,identifier.getName(),"db",type.getSize());
       }else{
           if(identifier.getType().isArray()){
               Type t = identifier.getType().getBaseType().getBaseTypeDepth();
               lit= String.join(",", identifier.getInitialValue().toList().stream().map(l->literal(t.getCode(), l.toString())).collect(Collectors.toList()));            
               type=t;
               literals.printf("%s %s %s %s", "\t",identifier.getName(),type.getDataSize(),lit);
           }else{
               lit = identifier.getInitialValue().toString();
               literals.printf("%s %s %s %s", "\t",identifier.getName(),type.getDataSize(),literal(type.getCode(), lit));
           }

       }
    }
    public void assignValue(Identifier identifier){
        Type type = identifier.getType().getBaseTypeDepth();
        switch(type.getCode()){
            case Type.NUMERUS:
                fileGenerator.printf("\tpop %s", identifier.getName());
                break;
            case Type.DUALIS:
                fileGenerator.printf("\tpop ax");
                fileGenerator.printf("\txor ah,ah");
                fileGenerator.printf("\tmov %s,al",identifier.getName());
                break;
            case Type.FRACTIO:
                fileGenerator.printf("\tpop ax");
                fileGenerator.printf("\tmov word ptr %s[0],ax", identifier.getName());
                fileGenerator.printf("\tpop ax");
                fileGenerator.printf("\tmov word ptr %s[2],ax", identifier.getName());
                break;
            case Type.IMAGO:
                fileGenerator.printf("\tpop ax");
                fileGenerator.printf("\tmov %s,al", identifier.getName());
                break;
            case Type.GREGORIUS:
                fileGenerator.printf("\tpop ax");
                fileGenerator.printf("\tpop dx");
                fileGenerator.printf("\tmov byte ptr %s[0],dl", identifier.getName());
                fileGenerator.printf("\tmov byte ptr %s[1],ah", identifier.getName());
                fileGenerator.printf("\tmov byte ptr %s[2],al", identifier.getName());
                
                break;
            case Type.LIBER:
               // fileGenerator.printf("\tlea ax,%s", identifier.getName());
               // fileGenerator.printf("\tpush ax");
            default:
                String tag = TagGenerator.getGenerator().nextTag();                
                fileGenerator.printf("\txor bx,bx");
                fileGenerator.printf("%s:",tag);
                fileGenerator.printf("\tpop ax");
                fileGenerator.printf("\tmov %s[bx],al",identifier.getName());
                fileGenerator.printf("\tinc bx");
                fileGenerator.printf("\tcmp bx,32");
                fileGenerator.printf("jbe %s",tag);
                break;
        }
    }
    public void beginIfCertus(){
       fileGenerator.printf("\tpop ax");
        fileGenerator.printf("\tcmp ax,0");
        String tag = TagGenerator.getGenerator().nextTag();
        String tag2 = TagGenerator.getGenerator().nextTag();
        fileGenerator.printf("\tjne %s", tag);
        tagsStack.push(tag2);
        fileGenerator.printf("\tjmp %s", tag2);
        fileGenerator.printf("%s:", tag);
    }
    public void beginIfMentiri(){
        fileGenerator.printf("\tpop ax");
        fileGenerator.printf("\tcmp ax,0");
        String tag = TagGenerator.getGenerator().nextTag();
        String tag2 = TagGenerator.getGenerator().nextTag();
        fileGenerator.printf("\tje %s", tag);
        tagsStack.push(tag2);
        fileGenerator.printf("\tjmp %s", tag2);
        fileGenerator.printf("%s:", tag);
        
    }
    public void thenBody(){
        String tag = TagGenerator.getGenerator().nextTag();
        String tag2 = tagsStack.pop();
        fileGenerator.printf("\tjmp %s", tag); 
        fileGenerator.printf("%s:", tag2);
        tagsStack.push(tag);
    }
    public void elseBody(){
        String tag = tagsStack.pop();
        fileGenerator.printf("%s:", tag);
    }
    
    public void whileOne(){
        String tag = TagGenerator.getGenerator().nextTag();
        fileGenerator.printf("%s:",tag);
        instrucStack.push(tag);
        continueStack.push(tag);
    }
    public void whileTwo(){
        fileGenerator.printf("\tpop ax");
        fileGenerator.printf("\tcmp ax,0");
        String tag = TagGenerator.getGenerator().nextTag();
        fileGenerator.printf("\tjne %s", tag);
        
        String tagSalir = TagGenerator.getGenerator().nextTag();
        fileGenerator.printf("\tjmp %s", tagSalir);
        fileGenerator.printf("%s:", tag);
        
        instrucStack.push(tagSalir);
        breakStack.push(tagSalir);
    }
    public void whileThree(){
        String salir = instrucStack.pop();
        String ciclo = instrucStack.pop();
        fileGenerator.printf("\tjmp %s", ciclo);
        fileGenerator.printf("%s:", salir);
        breakStack.pop();
        continueStack.pop();
    }
    public void putForId(Identifier identifier){
        forStack.push(identifier);
    }
    public void assignFor(){
        Identifier identifier = forStack.peek();
        fileGenerator.printf("\tpop %s",identifier.getName());
    }
    
    
    
    
}
