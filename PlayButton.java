package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;

public class PlayButton extends Sprite {



    public PlayButton(TextureAtlas atlas) {
        super(atlas.findRegion("btPlay"));
        setHeightProportion(0.3f);
        this.pos.set(0.35f, -0.35f);
    }
}
