package semantics.identifiers;

import java.util.ArrayList;
import java.util.List;
import semantics.literals.Literal;

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
        return type != null && other.type != null && name.equals(other.name) && category.equals(other.category) && type.equals(other.type) && parameters.equals(other.parameters);
    }
    
    
}
