import asciiPanel.AsciiPanel;
import java.awt.*;
   
public class HealthPotion extends Item
{
   public HealthPotion()
   {
      this.symbol = 'H';
      this.name = "Health Potion";
      this.tooltip = "Increases your maximum health!";
      this.color = new Color(200, 0, 0);
      this.x = 0;
      this.y = 0;
   }
   
   public void use(Player player)
   {
      player.maxHealth += 1;
      player.inventory.remove(this);
   }
}
   
