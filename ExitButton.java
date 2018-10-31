package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;

public class ExitButton extends Sprite {

    public ExitButton(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
        setHeightProportion(0.25f);
        this.pos.set(- 0.35f, - 0.37f);
    }
//    @Override
//    public boolean touchDown(Vector2 touch, int pointer) {
//        if (isMe(touch)) this.scale = 0.8f;
//        return false;
//    }
//    @Override
//    public boolean touchUp(Vector2 touch, int pointer) {
//        this.scale = 1f;
//        return false;
//    }
}
