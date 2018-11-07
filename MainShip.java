package com.mygdx.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;


public class MainShip extends Sprite {

    private Vector2 v0 = new Vector2(0.5f, 0);
    private Vector2 v = new Vector2();

    private boolean pressedLeft;
    private boolean pressedRight;

    private BulletPool bulletPool;

    private TextureAtlas atlas;

    private Rect worldBounds;

    Sound shoot;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.atlas = atlas;
        setHeightProportion(0.15f);
        this.bulletPool = bulletPool;
        this.shoot = Gdx.audio.newSound(Gdx.files.internal("D:\\2\\StarGame\\android\\assets\\effekt-strelby-iz-orujiya-gunshot-sound-fx.mp3"));
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.05f);
    }
//  Для определения, в какую часть экрана нажал пользователь
    public boolean touchInLeft(Vector2 touch, int pointer){
      return touch.x >= worldBounds.getLeft() && touch.x <= worldBounds.getRight()/2 && touch.y >= worldBounds.getBottom() && touch.y <= worldBounds.getTop();
    }
    public boolean touchInRight(Vector2 touch, int pointer){
        return touch.x >= worldBounds.getLeft()/2 && touch.x <= worldBounds.getRight() && touch.y >= worldBounds.getBottom() && touch.y <= worldBounds.getTop();
    }
    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if(touchInLeft(touch,pointer) && checkBounds()) {
            moveLeft();
        }
        else if(touchInRight(touch,pointer)&& checkBounds()) {
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        stop();
        return super.touchUp(touch, pointer);
    }


    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
        }
        return false;
    }


    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    public void moveRight() {
            v.set(v0);
    }

    public void moveLeft() {
            v.set(v0).rotate(180);
    }

    public boolean checkBounds(){
        if (pos.x <= worldBounds.getLeft() || pos.x >= worldBounds.getRight()) return true;
        return false;
}

    private void stop() {
        v.setZero();
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        shoot.play(0.2f);
        bullet.set(this, atlas.findRegion("bulletMainShip"), pos, new Vector2(0, 0.5f), 0.01f, worldBounds, 1);
    }
}
