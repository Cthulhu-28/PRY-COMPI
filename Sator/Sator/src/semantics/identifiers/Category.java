/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantics.identifiers;

/**
 * Represents the different categories that an identifier
 * might have
 * @see semantics.identifiers.Identifier
 * @author Steven Moya
 */
public enum Category {
    VARIABLE("Variable"),
    CONSTANT("Constante"),
    FUNCTION("Función"),
    PROCEDURE("Procedimiento");
    
    private final String NAME;
    
    private Category(String name){
        this.NAME=name;
    }
    @Override
    public String toString(){
        return NAME;
    }
    
}
