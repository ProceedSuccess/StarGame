package com.mygdx.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Ship;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;
import com.mygdx.game.pool.EnemyPool;

public class Enemy extends Ship {

    private Vector2 v0 = new Vector2();
    private EnemyPool enemyPool;
//  Метка для большого корабля
    public boolean isEnemyBig;
    public void setIsEnemyBig(){this.isEnemyBig = true;}


    public Enemy(BulletPool bulletPool, EnemyPool enemyPool, Rect worldBounds, Sound shootSound) {
        super(shootSound);
        this.bulletPool = bulletPool;
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        this.v.set(v0);
        this.isEnemyBig = false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        //скопировал из метода update Mainship для реализации стрельбы, со звуком не успел разобраться
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            shoot();
            reloadTimer = 0.2f;
        }
        // если корабль только выезжает из границ мира и он большой, скорость на данном отрезке возрастает
        if(pos.y >= 0.40f && isEnemyBig == true) pos.mulAdd(v, delta*8f);
        else pos.mulAdd(v,delta);
        // если выехал за границы мира, ставим флаг, и потом в методах GameScreen: render( ) -> deleteAllDestroyed() ->
        // вызывается метод freeAllDestroyedActiveObjects() для объектов BulletPool и EnemyPool, который переводит эти объекты в неактивные
        if(this.pos.y < -0.5f) isDestroyed = true;
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int bulletDamage,
            float reloadInterval,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0f, bulletVY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.hp = hp;
        setHeightProportion(height);
        v.set(v0);
    }
}