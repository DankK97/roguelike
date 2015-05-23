import asciiPanel.AsciiPanel;
import java.awt.*;
   
public class StrengthPotion extends Item
{
   public StrengthPotion()
   {
      this.symbol = 'S';
      this.name = "Strength Potion";
      this.tooltip = "Increases your strengh!";
      this.color = new Color(255, 128, 0);
      this.x = 0;
      this.y = 0;
   }
   
   public void use(Player player)
   {
      player.strength += 1;
      player.inventory.remove(this);
   }
}
   
