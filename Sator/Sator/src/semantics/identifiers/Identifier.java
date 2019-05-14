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
    
    public boolean containsParameter(String name){
        for(Parameter p : parameters)
            if(p.getName().equals(name))
                return true;
        return false;
    }
    
    
}
