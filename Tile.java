import java.awt.Color;
import asciiPanel.AsciiPanel;

public enum Tile {
    FLOOR((char)219, new Color(0, 0, 0), false), //Empty 
    WALL((char)219, AsciiPanel.brightBlack, true), //Full
    WATER((char)176, new Color(150, 150, 255), false), //Lightly dotted
    DEEPWATER((char)177, new Color(0, 0, 255), true), //Heavily dotted
    NONE(' ', new Color(0, 0, 0), true),
    STAIRCASE((char)208, new Color(0, 255, 0), false),
    DOOR((char)219, new Color(255, 255, 255), false);

    public char symbol; //What will be displayed on your screen to represent this tile
    public Color color; //What color the symbol char will be displayed in
    public boolean solid; //The player cannot travel through solid tiles
    
    Tile(char symbol, Color color, boolean solid){
        this.symbol = symbol;
        this.color = color;
        this.solid = solid;
    }
}
