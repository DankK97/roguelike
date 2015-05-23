import asciiPanel.AsciiPanel;
import java.awt.*;
   
public class Weapon extends Item
{
   public int damage;
   public int strength;
   
   public Weapon(char symbol, String name, String tooltip, Color color, int damage, int strength)
   {
      this.symbol = symbol;
      this.name = name;
      this.tooltip = tooltip;
      this.color = color;
      this.x = 0;
      this.y = 0;
      this.damage = damage;
      this.strength = strength;
   }
   
   public Weapon()
   {
      this.symbol = '?';
      this.name = "?";
      this.tooltip = "?";
      this.color = AsciiPanel.brightWhite;
      this.x = 0;
      this.y = 0;
      this.damage = 1;
      this.strength = 1;
   }
   
   public void use(Player player)
   {
      if (this.strength > player.strength)
      {
         System.out.println("This weapons strength requirement (" + this.strength + ") > your strength (" + player.strength + ")!");
         return;
      }
      else
      {
         Item previouslyEquipped = player.equipSword; 
         player.equipSword = this; 
         player.inventory.remove(this); 
         if (previouslyEquipped != null) 
            player.inventory.add(previouslyEquipped); 
      }
   }
}
   
