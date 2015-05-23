import java.awt.event.KeyEvent;
import java.awt.*;

import asciiPanel.AsciiPanel;

public class WinScreen implements Screen {
   public MenuItemList menuItems = new MenuItemList("Continue");
   
	@Override
	public void displayOutput(AsciiPanel terminal) {
      String[] winMessage = ArtReader.get("win");
      ConsoleHelper.writeCenterArray(terminal, winMessage, 4, new Color(255, 0, 0));
		menuItems.drawList(terminal, 40);
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
                  return new ScoreScreen();
            }
      }
      /* /menu code */
      return this;
	}
}
