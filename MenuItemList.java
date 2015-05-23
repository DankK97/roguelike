import asciiPanel.AsciiPanel;
import java.awt.*;

public class MenuItemList
{
   public MenuItem[] items;
   public int selected;
      
   public MenuItemList(String ... itemStrings)
   {
      items = new MenuItem[itemStrings.length];
      for (int n = 0; n < items.length; n++)
      {
         items[n] = new MenuItem(itemStrings[n]);
         this.selected = 0; //the ^top one
      }
   }

   // int bottom is where the list begins to be drawn
   // no damn clue why i called it bottom
   public void drawList(AsciiPanel terminal, int bottom)
   {
      for(int n = items.length-1; n >= 0; n--)
      {
         Color c;
         if (n != selected)
           c = items[n].color;
         else
           // COLOR OF SELECTED MENU ITEMS
           c = new Color(255, 0, 0);
         
         terminal.writeCenter(items[n].text, bottom+n, c);
      }
   }
   
   public void select(int direction) //the direction you'd like to select -1 = back 1, 1 = forward 1
   {
      this.selected += direction;
      if(this.selected >= this.items.length)
         this.selected = 0;
      else if(this.selected <= -1)
         this.selected = this.items.length -1;
   }
}
