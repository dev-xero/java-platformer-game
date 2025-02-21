package levels;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    private Game game;
    private BufferedImage levelSprite;


    public LevelManager(Game game) {
        this.game = game;
        levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
    }

    public void render(Graphics g) {
        g.drawImage(levelSprite, 0, 0, null);
    }

    public void update() {

    }

}
