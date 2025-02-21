package levels;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;


    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
    }

    public void render(Graphics g) {
        g.drawImage(levelSprite[2], 0, 0, null);
    }

    public void update() {

    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 12; x++) {
                int index = y * 12 + x;
                levelSprite[index] = img.getSubimage(x * 32, y * 32, 32, 32);
            }
        }
    }

}
