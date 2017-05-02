package dev.game.state;

import dev.game.Handler;
import dev.game.UI.ClickListener;
import dev.game.UI.UIImageButton;
import dev.game.UI.UIManager;
import dev.game.gfx.Asset;
import dev.game.gfx.ImageLoader;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EndState extends State {

    private UIManager uimanager;
    private BufferedImage Background;

    public EndState(Handler handler) {
        super(handler);
        uimanager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uimanager);
        Background = ImageLoader.loadImage("/texture/ending.png");

        uimanager.addObject(new UIImageButton(350, 200, 200, 100, Asset.resetbutton, new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().reset();
            }
        }));

        uimanager.addObject(new UIImageButton(350, 300, 200, 100, Asset.exitbutton, new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
    }

    @Override
    public void tick() {
        uimanager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Background, 0, 0, null);
        uimanager.render(g);
    }

}
