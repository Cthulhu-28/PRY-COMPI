package generator;

public class TagGenerator {
    private static TagGenerator tagGenerator;
    private int count;
    
    private TagGenerator(){
        count  = 0;
    }
    public static synchronized TagGenerator getGenerator(){
        if(tagGenerator==null)
            tagGenerator = new TagGenerator();
        return tagGenerator;
    }
    public String nextTag(){
        return "tag"+String.format("%03d", count++);
    }
}
