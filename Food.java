import asciiPanel.AsciiPanel;
import java.awt.*;
   
public class Food extends Item
{
   public int health;
   
   public Food(char symbol, String name, String tooltip, Color color, int health)
   {
      this.symbol = symbol;
      this.name = name;
      this.tooltip = tooltip;
      this.color = color;
      this.x = 0;
      this.y = 0;
      this.health = health;
   }
   
   public Food()
   {
      this.symbol = '?';
      this.name = "?";
      this.tooltip = "?";
      this.color = AsciiPanel.brightWhite;
      this.x = 0;
      this.y = 0;
      this.health = 1;
   }
   
   public void use(Player player)
   {
      player.health += health;
      player.inventory.remove(this);
   }
}
   
