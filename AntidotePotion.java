import asciiPanel.AsciiPanel;
import java.awt.*;
   
public class AntidotePotion extends Item
{
   private int health;
   public AntidotePotion(int health)
   {
      this.symbol = 'A';
      this.name = "Antidote";
      this.tooltip = "Heals your wounds!";
      this.color = new Color(255, 150, 150);
      this.x = 0;
      this.y = 0;
      this.health = health;
   }
   
   public void use(Player player)
   {
   System.out.println("Using potion");
      player.addHealth(health);
      player.inventory.remove(this);
   }
}
   
