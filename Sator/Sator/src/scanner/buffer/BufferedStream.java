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
public class BufferedStream {
    private FileInputStream stream;
    private final int BUFFER_SIZE=1048576;
    private byte[] buffer;
    private int readCharacters=BUFFER_SIZE;
    private int idx = BUFFER_SIZE;
    private Queue<Byte> queue;
    
    public BufferedStream(String file) throws FileNotFoundException{
        File f = new  File(file);
        if(!f.exists())
            throw new FileNotFoundException("El archivo \""+file+"\" no se pudo encontrar");
        stream = new FileInputStream(f);
        buffer = new byte[BUFFER_SIZE];
        queue = new LinkedList<>();
    }
    public char readNextChar() throws IOException{
        try {
            if(!queue.isEmpty())
                return (char)queue.remove().byteValue();
            if(idx==readCharacters){
                idx=0;
                readCharacters = stream.read(buffer);
                if(readCharacters==-1)
                    return 0;
                System.out.println("Reading new chunk");   
            }
        } catch (IOException e) {
            throw new IOException("Hubo un error al leer el archivo");
        }
        return (char) buffer[idx++];
    }
    public void returnChar(char c){
        queue.add((byte)c);
    }
    public void returnChar(){
        idx = idx <=0 ? 0 : idx - 1;
    }
    public void close() throws IOException{
        try {
            stream.close();
        } catch (IOException ex) {
            throw new IOException("No se pudo cerrar el buffer");
        }
    }
}
