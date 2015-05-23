import asciiPanel.AsciiPanel;
import java.awt.*;
   
public class Item
{
   public char symbol;
   public String name, tooltip;
   public Color color;
   public int x, y;
   
   public Item(char symbol, String name, String tooltip, Color color)
   {
      this.symbol = symbol;
      this.name = name;
      this.tooltip = tooltip;
      this.color = color;
      this.x = 0;
      this.y = 0;
   }
   
   public Item()
   {
      this.symbol = '?';
      this.name = "?";
      this.tooltip = "?";
      this.color = AsciiPanel.brightWhite;
      this.x = 0;
      this.y = 0;
   }
   
   public void printText(AsciiPanel terminal, int x, int y)
   {
   }
   
   public void use(Player player)
   {
   }
}
   
