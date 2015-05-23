import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import java.awt.Color;

public class NameScreen implements Screen {
   public MenuItemList menuItems = new MenuItemList("Play", "Generate New Name");
   private String name = "";
   private Color nameColor = new Color(200, 200, 200);
   String[] title;
   String[] pregeneratedNames;
   String[] pregeneratedTitles;
   boolean customName = false;

   public NameScreen()
   {
      title = ArtReader.get("name");
      
      try { pregeneratedNames = SimpleFiles.readArray("names.txt"); 
            pregeneratedTitles = SimpleFiles.readArray("titles.txt"); }
      catch(Exception e) { }
      
      name = randomName();
   }
   
   
   
   @Override
   public void displayOutput(AsciiPanel terminal) {
      /* Draw name screen title */
      ConsoleHelper.writeCenterArray(terminal, title, 10, new Color(255, 100, 100));
      /* Draw name */
      terminal.writeCenter(name, 27, nameColor);
      /* Draw menu items */
      menuItems.drawList(terminal, 30);
   }

   @Override
   public Screen respondToUserInput(KeyEvent key)
   {
      /* Code for choosing menu items. */
      switch (key.getKeyCode())
      {
         case KeyEvent.VK_UP: 
            this.menuItems.select(-1);
            break;
         case KeyEvent.VK_DOWN: 
            this.menuItems.select(1);
            break;
         case KeyEvent.VK_ENTER:
            switch(menuItems.selected){
               case 0:
                  return new PlayScreen(name);
               case 1:
                  name = randomName();
                  break;
            }
      }
      /* /menu code */
   
      /* BACKSPACE
         deletes one character from your name
         */
      if (key.getKeyCode() == KeyEvent.VK_BACK_SPACE)
      {
         if (name.length() > 0)
            name = name.substring(0, name.length()-1);
         return this;
      }
      /* SPACE OR LETTER
         adds that character to your name
         */
      else
      {
         if (key.getKeyChar() == ' ' || (key.getKeyChar() >= 97 && key.getKeyChar() <= 122))
         {
            if (!customName)
            {
               customName = true;
               name = "";
               nameColor = new Color(255, 200, 0);
            }
            
            name += ("" + key.getKeyChar());
            name = properNounify(name);
            if (name.length() > 32)
               name = name.substring(0, 32);
         }
      }
      
      return this;
   }
   
   public String properNounify(String input)
   {
      StringBuilder output = new StringBuilder();
      
      if (input.length() > 0)
         output.append(("" + input.charAt(0)).toUpperCase());
      for (int i = 1; i < input.length(); i++)
      {
         if (input.charAt(i-1) == ' ')
            output.append(("" + input.charAt(i)).toUpperCase());
         else
            output.append(("" + input.charAt(i)));
      }
      
      return output.toString();
   }  
   
   public String randomName()
   {
      customName = false;
      nameColor = new Color(200, 200, 200);
      return pregeneratedNames[(int)(Math.random() * pregeneratedNames.length)]
             + " " + pregeneratedTitles[(int)(Math.random() * pregeneratedTitles.length)];
   }
}
