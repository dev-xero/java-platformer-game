package levels;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;


    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
    }

    public void render(Graphics g) {
        for (int y = 0; y < Game.TILES_IN_HEIGHT; y++) {
            for (int x = 0; x < Game.TILES_IN_WIDTH; x++) {
                int index = levelOne.getSpriteIndex(x, y);
                g.drawImage(
                        levelSprite[index],
                        Game.TILES_SIZE * x,
                        Game.TILES_SIZE * y,
                        Game.TILES_SIZE,
                        Game.TILES_SIZE,
                        null
                );
            }
        }
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
