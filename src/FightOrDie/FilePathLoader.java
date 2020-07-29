package FightOrDie;

import FightOrDie.states.GameState;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FilePathLoader
    {
    private static final Logger LOGGER = Logger.getLogger(FilePathLoader.class.getName());

    Handler handler;
    GameState gameState;
    public String chooseFile(Handler handler)
		{
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("FightOrDie worlds", "wld");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION)
			{
			if ((chooser.getSelectedFile().getName().charAt(chooser.getSelectedFile().getName().length() - 1) != 'd') &&
			(chooser.getSelectedFile().getName().charAt(chooser.getSelectedFile().getName().length() - 2) != 'l') &&
			(chooser.getSelectedFile().getName().charAt(chooser.getSelectedFile().getName().length() - 3) != 'w') &&
			(chooser.getSelectedFile().getName().charAt(chooser.getSelectedFile().getName().length() - 4) != '.'))
				{
				return "demoWorld.wld";
				}
			LOGGER.setLevel(Level.INFO);
			LOGGER.info("You chose to open this file: " + chooser.getSelectedFile().getName());

			return chooser.getSelectedFile().getName();
			}
		return "demoWorld.wld";
		}
    }