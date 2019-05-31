package generator;

public class LiteralGenerator {
    private static LiteralGenerator tagGenerator;
    private int count;
    
    private LiteralGenerator(){
        count  = 0;
    }
    public static synchronized LiteralGenerator getGenerator(){
        if(tagGenerator==null)
            tagGenerator = new LiteralGenerator();
        return tagGenerator;
    }
    public String nextLiteral(){
        return "_lit"+String.format("%03d", count++);
    }
}
