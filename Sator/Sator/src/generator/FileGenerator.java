/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author admin
 */
public class FileGenerator extends PrintWriter{
    private boolean first = true;
    private final String path;
    public FileGenerator(String string) throws FileNotFoundException {
        super(string);
        path=string;
    }

    @Override
    public PrintWriter printf(String string, Object... os) {
        if(first){
            first = false;
            return super.printf(string, os);
        }
        else
            return super.printf("\n"+string, os);
    }

    public String getPath() {
        return path;
    }
    
    
}
