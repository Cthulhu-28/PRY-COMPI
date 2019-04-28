/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author admin
 */
public class Program {
    public static void main(String[] args){
        try {
            String path = "C:\\Users\\admin\\Documents\\TEC\\2019\\I Semestre\\Compiladores e Interpretes\\Proyecto\\Etapa 2\\Pruebas\\test015.str";
            Parser parser = Parser.initParser(path);
            parser.parse();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
