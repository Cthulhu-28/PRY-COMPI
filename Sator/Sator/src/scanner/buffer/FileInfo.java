/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner.buffer;

/**
 *
 * @author admin
 */
class FileInfo {
    private int row;
    private int column;
    
    public FileInfo(){
        row = 0;
        column = 0;
    }
    public void move(char c){
        switch(c){
            case '\n':
                row++;
                column=0;
                break;
            case '\r':
                row = 0;
                break;
            case 0:
                break;
            case '\t':
                row+=5;
                break;
            default:
                row++;   
        }
    }
}
