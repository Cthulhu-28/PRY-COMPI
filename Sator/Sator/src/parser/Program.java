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
            if(args.length<1){
                throw new Exception("Un parámetro esperados");
            }
            String path = args[0];
            Parser parser = Parser.initParser(path);
            parser.parse();
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
