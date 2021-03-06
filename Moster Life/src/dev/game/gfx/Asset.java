package dev.game.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Asset {

    public static BufferedImage inventoryScreen;
    public static Font font36;
    public static BufferedImage coin, potion, defenceUp, speedUp, healthUp, attackUp;

    public static BufferedImage star1, star2, star3, metroid1, metroid2, wall;
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] enemy1, enemy2, enemy3, enemy4, enemyship;
    public static BufferedImage[] playbutton, helpbutton, exitbutton, resetbutton;
    private static final int width = 200, height = 200;

    public static void intit() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/sheet.png"));
        SpriteSheet button = new SpriteSheet(ImageLoader.loadImage("/texture/button.png"));

        inventoryScreen = ImageLoader.loadImage("/texture/inventoryScreen.png");
        font36 = FontLoader.loadFont("res/fonts/slkscr.ttf", 36);

        coin = sheet.crop(0, height, width, height);
        potion = sheet.crop(width, height, width, height);
        defenceUp = sheet.crop(width * 2, height, width, height);
        speedUp = sheet.crop(width * 3, height, width, height);
        healthUp = sheet.crop(width * 4, height, width, height);
        attackUp = sheet.crop(width * 5, height, width, height);

        star1 = sheet.crop(0, 0, width, height);
        star2 = sheet.crop(width, 0, width, height);
        star3 = sheet.crop(width * 2, 0, width, height);
        metroid1 = sheet.crop(width * 3, 0, width, height);
        metroid2 = sheet.crop(width * 4, 0, width, height);
        wall = sheet.crop(width * 5, 0, width, height);
        /////////////
        playbutton = new BufferedImage[2];
        playbutton[0] = button.crop(0, 0, width * 2, height);
        playbutton[1] = button.crop(0, height, width * 2, height);

        helpbutton = new BufferedImage[2];
        helpbutton[0] = button.crop(width * 2, 0, width * 2, height);
        helpbutton[1] = button.crop(width * 2, height, width * 2, height);

        exitbutton = new BufferedImage[2];
        exitbutton[0] = button.crop(width * 4, 0, width * 2, height);
        exitbutton[1] = button.crop(width * 4, height, width * 2, height);

        resetbutton = new BufferedImage[2];
        resetbutton[0] = button.crop(0, height * 2, width * 2, height);
        resetbutton[1] = button.crop(0, height * 3, width * 2, height);
        ////////////
        player_down = new BufferedImage[2];
        player_down[0] = sheet.crop(0, height * 3, width, height);
        player_down[1] = sheet.crop(width, height * 3, width, height);

        player_right = new BufferedImage[2];
        player_right[0] = sheet.crop(width * 2, height * 3, width, height);
        player_right[1] = sheet.crop(width * 3, height * 3, width, height);

        player_left = new BufferedImage[2];
        player_left[0] = sheet.crop(width * 4, height * 3, width, height);
        player_left[1] = sheet.crop(width * 5, height * 3, width, height);

        player_up = new BufferedImage[2];
        player_up[0] = sheet.crop(0, height * 4, width, height);
        player_up[1] = sheet.crop(width, height * 4, width, height);

        enemy1 = new BufferedImage[2];
        enemy1[0] = sheet.crop(width * 2, height * 4, width, height);
        enemy1[1] = sheet.crop(width * 3, height * 4, width, height);

        enemy2 = new BufferedImage[2];
        enemy2[0] = sheet.crop(width * 4, height * 4, width, height);
        enemy2[1] = sheet.crop(width * 5, height * 4, width, height);

        enemy3 = new BufferedImage[2];
        enemy3[0] = sheet.crop(0, height * 5, width, height);
        enemy3[1] = sheet.crop(width, height * 5, width, height);

        enemy4 = new BufferedImage[2];
        enemy4[0] = sheet.crop(width * 2, height * 5, width, height);
        enemy4[1] = sheet.crop(width * 3, height * 5, width, height);

        enemyship = new BufferedImage[2];
        enemyship[0] = sheet.crop(width * 4, height * 5, width, height);
        enemyship[1] = sheet.crop(width * 5, height * 5, width, height);
    }
}
