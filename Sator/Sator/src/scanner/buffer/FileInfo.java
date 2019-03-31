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
    private FileInfo previous;
    public FileInfo(){
        row = 1;
        column = 0;
    }
    public void move(char c){
        if(previous==null)previous = new FileInfo();
        previous.setColumn(getColumn());
        previous.setRow(getRow());
        switch(c){
            case '\n':
                row++;
                column=0;
                break;
            case '\r':
                column = 0;
                break;
            case 0:
                break;
            case '\t':
                column+=4-(column%4);
                break;
            default:
                column++;   
        }
    }
    public void back(char c){
        if(previous==null)previous = new FileInfo();
        switch(c){
            case '\n':
                row--;
                column=previous.getColumn();
                break;
            case '\r':
                column = previous.getColumn();
                break;
            case 0:
                break;
            case '\t':
                column-=5;
                break;
            default:
                column--;   
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
}
