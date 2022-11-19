package de.hsrm.mi.swt02.backend.api.streetgrid.gridelements;

public class ColumnData {
    
    private int posX;
    private int posY;
    private String texture;


    public ColumnData(int posX, int posY, String texture){
        
        this.posX = posX;
        this.posY = posY;
        this.texture = texture;
    }


    public int getPosX() {
        return posX;
    }


    public void setPosX(int posX) {
        this.posX = posX;
    }


    public int getPosY() {
        return posY;
    }


    public void setPosY(int posY) {
        this.posY = posY;
    }


    public String getTexture() {
        return texture;
    }


    public void setTexture(String texture) {
        this.texture = texture;
    }

    

    
}
