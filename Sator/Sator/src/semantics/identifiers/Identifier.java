package semantics.identifiers;

import java.util.ArrayList;
import java.util.List;
import semantics.literals.Literal;
import semantics.table.TypeTable;

/**
 *
 * @author admin
 */
public class Identifier {
    private String name;
    private Category category;
    private Type type;
    private Literal initialValue;
    private List<Parameter> parameters = new ArrayList<>();
    private int parameterNumber=0;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Literal getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Literal initialValue) {
        this.initialValue = initialValue;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParameterNumber() {
        return parameterNumber;
    }

    public void setParameterNumber(int parameterNumber) {
        this.parameterNumber = parameterNumber;
    }
    public boolean isMethod(){
        return category == Category.FUNCTION || category == Category.PROCEDURE;
    }
    public boolean containsParameter(String name){
        for(Parameter p : parameters)
            if(p.getName().equals(name))
                return true;
        return false;
    }
    public Identifier getParameter(String name){
        for(Parameter p : parameters){
            if(p.getName().equals(name)){
                Identifier identifier = new Identifier();
                identifier.setName(name);
                identifier.setCategory(Category.VARIABLE);
                identifier.setType(p.getType());
                return identifier;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        if(isMethod()){
            String str = getName()+"(";
            int i=0;
            for(;i<parameters.size()-1;i++)
                str += parameters.get(i).getType().toString()+", ";
            if(parameters.size()>0)
                str += parameters.get(i).toString()+")";
            return str;
        }
        return getName();
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Identifier other = (Identifier) obj;
        if(category==Category.PROCEDURE && other.category == Category.PROCEDURE)
            return name.equals(other.name) && category.equals(other.category) && parameters.equals(other.parameters);
        return type != null && other.type != null && name.equals(other.name) && category.equals(other.category) && type.equals(other.type) && parameters.equals(other.parameters);
    }
    
    public static List<Identifier> IO (){
        List<Identifier> list = new ArrayList<>();
        
        Identifier identifier = new Identifier();
        identifier.setName("scribonumerus");
        identifier.setCategory(Category.PROCEDURE);
        identifier.getParameters().add(new Parameter("value", TypeTable.TYPES[0]));
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setName("scriboimago");
        identifier.setCategory(Category.PROCEDURE);
        identifier.getParameters().add(new Parameter("value", TypeTable.TYPES[1]));
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setName("scribofractio");
        identifier.setCategory(Category.PROCEDURE);
        identifier.getParameters().add(new Parameter("value", TypeTable.TYPES[2]));
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setName("scribocatena");
        identifier.setCategory(Category.PROCEDURE);
        identifier.getParameters().add(new Parameter("value", TypeTable.TYPES[3]));
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setName("scriboliber");
        identifier.setCategory(Category.PROCEDURE);
        identifier.getParameters().add(new Parameter("value", TypeTable.TYPES[4]));
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setName("scribodualis");
        identifier.setCategory(Category.PROCEDURE);
        identifier.getParameters().add(new Parameter("value", TypeTable.TYPES[5]));
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setName("scribogregorius");
        identifier.setCategory(Category.PROCEDURE);
        identifier.getParameters().add(new Parameter("value", TypeTable.TYPES[6]));
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setCategory(Category.FUNCTION);
        identifier.setName("legonumerus");
        identifier.setType(TypeTable.TYPES[0]);
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setCategory(Category.FUNCTION);
        identifier.setName("legoimago");
        identifier.setType(TypeTable.TYPES[1]);
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setCategory(Category.FUNCTION);
        identifier.setName("legofractio");
        identifier.setType(TypeTable.TYPES[2]);
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setCategory(Category.FUNCTION);
        identifier.setName("legocatena");
        identifier.setType(TypeTable.TYPES[3]);
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setCategory(Category.FUNCTION);
        identifier.setName("legoliber");
        identifier.setType(TypeTable.TYPES[4]);
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setCategory(Category.FUNCTION);
        identifier.setName("legodualis");
        identifier.setType(TypeTable.TYPES[5]);
        list.add(identifier);
        
        identifier = new Identifier();
        identifier.setCategory(Category.FUNCTION);
        identifier.setName("legogregorius");
        identifier.setType(TypeTable.TYPES[6]);
        list.add(identifier);
        
        return list;
    }
    
}
