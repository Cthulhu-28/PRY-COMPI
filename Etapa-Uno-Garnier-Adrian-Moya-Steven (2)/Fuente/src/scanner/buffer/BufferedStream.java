/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Read
 * @author Steven Moya
 */
public final class BufferedStream {
    private boolean finished=false;
    private FileInputStream stream;
    private final int BUFFER_SIZE=1024*1024;
    private byte[] buffer;
    private int readCharacters=BUFFER_SIZE;
    private int idx = BUFFER_SIZE;
    private Queue<Byte> queue;
    private final FileInfo info = new FileInfo();
    
    public BufferedStream(String file) throws FileNotFoundException, IOException{
        File f = new  File(file);
        
        if(!getExtension(file).toLowerCase().equals("str"))
            throw new IOException("GN-1: Se esperaba un archivo con extensión .str, extension recibida: ."+getExtension(file));
        
        if(!f.exists())
            throw new FileNotFoundException("GN-0: El archivo \""+file+"\" no existe");
        
        if(!f.canRead())
            throw new IOException("GN-2: El archivo \""+file+"\" no puede leerse");
        
        stream = new FileInputStream(f);
        buffer = new byte[BUFFER_SIZE];
        queue = new LinkedList<>();
    }
    public char readNextChar() throws IOException{
        if(finished)return 0;
        try {
            if(!queue.isEmpty()){
                char c = (char)queue.remove().byteValue();
                info.move(c);
                return c;
            }
            if(idx==readCharacters){
                idx=0;
                readCharacters = stream.read(buffer);
                if(readCharacters==-1){
                    finished=true;
                    return 0;
                }
                System.out.println("Reading new chunk");   
            }
        } catch (IOException e) {
            throw new IOException("GN-3: Ha ocurrido un error al leer la siguiente porción del archivo");
        }
        char c = (char) buffer[idx++];
        info.move(c);
        return c;
    }
    public void returnChar(char c){
        info.back(c);
        queue.add((byte)c);
    }
    public void returnChar(){
        idx = idx <=0 ? 0 : idx - 1;
    }
    public void close() throws IOException{

        try {
            stream.close();
        } catch (IOException ex) {
            throw new IOException("GN-4: Ha ocurrido un error al cerrar buffer");
        }
    }
    public int getRow(){
        return info.getRow();
    }
    public int getColumn(){
        return info.getColumn();
    }
    
    public String getExtension(String path){
        if(path.contains(".")){
            path = path.substring(path.lastIndexOf(".") + 1);
        }
        return path;
    }
    public int getLines(){
        return info.lines;
    }
}
