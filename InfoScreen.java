import java.awt.event.KeyEvent;
import java.awt.Color;
import asciiPanel.AsciiPanel;

public class InfoScreen implements Screen {
   MenuItemList menuItems = new MenuItemList("Play", "Menu", "Scores");
	@Override
	public void displayOutput(AsciiPanel terminal) {
      String[] information = ArtReader.get("information");
      ConsoleHelper.writeCenterArray(terminal, information, 3, new Color(100, 255, 150));
      menuItems.drawList(terminal, 40);
      
      /* INFORMATION TEXT */
      terminal.writeCenter("This is a game about killing monsters and shit.", 10);
      String[] creator = ArtReader.get("creator_and_contact");
      ConsoleHelper.writeCenterArray(terminal, creator, 12, new Color(150, 150, 150));
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
      /* Code for choosing menu items. */
      switch (key.getKeyCode()){
         case KeyEvent.VK_UP: 
            this.menuItems.select(-1);
            break;
         case KeyEvent.VK_DOWN: 
            this.menuItems.select(1);
            break;
         case KeyEvent.VK_ENTER:
            switch(menuItems.selected){
               case 0:
                  return new NameScreen();
               case 1:
                  return new StartScreen();
               case 2:
                  return new ScoreScreen();
            }
      }
      /* /menu code */
		return this;
	}
}
