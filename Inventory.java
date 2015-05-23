import asciiPanel.AsciiPanel;
import java.util.*;
import java.awt.Color;
   
public class Inventory
{
   /* List of items in the inventory, size cannot exceed max */
   private List<Item> items;
   private int max;
   public int selected;
   
   public Inventory(int max)
   {
      this.items = new ArrayList<Item>();
      this.max = max;
      this.selected = 0;
   }
   
   public Item item(int index)
   {
      if (items.size() > index)
         return items.get(index);
      return null;
   }
   
   public void select(int slot)
   {
      if (slot >= 0 && slot < max)
         selected = slot;
   }
   
   public List<Item> getItems()
   {
      return new ArrayList<Item>(items);
   }
   
   public boolean add(Item item)
   {
      if (this.items.size() < this.max)
      {
         items.add(item);
         return true;
      }
      return false;
   }
   
   public void remove(Item item)
   {
      items.remove(item);
   }
   
   public void drawInventory(AsciiPanel terminal)
   {
      /* Draw boxes */
      for (int i = 0; i < 10; i++)
      {
         ConsoleHelper.rect(terminal, i*4,46, 3 ,3);
      }
      /* Highlight selected box */
      ConsoleHelper.rect(terminal, selected*4,46, 3, 3, new Color(255, 202, 1));
      /* Draw items */
      int n = 0;
      for (Item item : items)
      {
         terminal.write(item.symbol, 1 + 4*n, 47, item.color);
         n++;
      }
   }
}
