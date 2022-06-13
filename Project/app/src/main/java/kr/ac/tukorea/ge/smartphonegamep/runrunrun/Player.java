package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;

public class Player extends AnimSprite implements BoxCollidable {
    private static final String TAG = Player.class.getSimpleName();
    private final Paint shieldPaint;
    private boolean isMove = false;
    private boolean isJump = false;
    private boolean isFall = false;
    private float fallTime = 0.f;
    private boolean isShield = false;
    protected RectF boundingRect = new RectF();


    private float jumpPower = 15.f;
    private float jumpTime = 0.f;
    private float originalY = 0.f;

    private float attackTime = 0.f;
    private boolean isLeft = false;

    private int score = 0;
    private int attackCount = 0;
    private float allTime = 0.f;

    public Player(float x, float y) {
        super(x, y, R.dimen.player_radius, R.mipmap.player_walk, 20, 4);
        originalY = y;
        reverseBitmap = BitmapPool.get(R.mipmap.player_walk_left);
        boundingRect.set(x - Metrics.size(R.dimen.player_coll_radius), y - Metrics.size(R.dimen.player_coll_radius), x + Metrics.size(R.dimen.player_coll_radius), y + Metrics.size(R.dimen.player_coll_radius));

        shieldPaint = new Paint();
        shieldPaint.setColor(Color.RED);
        shieldPaint.setStyle(Paint.Style.STROKE);
        shieldPaint.setStrokeWidth(5.f);
    }

    public void update(float frameTime) {
        allTime += frameTime;
        if (isFall) {
            fallTime += frameTime;
            if (fallTime > 1.f) {
                GameView.view.rankingScene(this.score, false);
                return;
            }
        }

        if (findNearestPlatform(dstRect.centerX()) == null && allTime > 2.f && !isJumping)
        {
            isFall = true;
            float dy = frameTime * 2.f;
            dstRect.offset(0, 5.f);
            boundingRect.offset(0, 5.f);


        }

        float dx = 0.f, dy = 0.f;
        float foot = boundingRect.top;
        if (isMove == true) {
            isMoving = true;
            if (isLeft)
                dx = -frameTime * Metrics.size(R.dimen.player_speed);
            else
                dx = frameTime * Metrics.size(R.dimen.player_speed);

        }
        else
            isMoving = false;

        if (isJumping == true) {
            dy = jumping(frameTime);
            if (isJumping) {
                if (isLeft) dx -= 7.f;
                else if (!isLeft) dx += 7.f;

                dstRect.offset(dx, -dy);
                boundingRect.offset(dx, -dy);
            }
        }
        else {
            dstRect.offset(dx, 0.f);
            boundingRect.offset(dx, 0.f);
        }

        if (isAttack) {
            attackTime += frameTime;
            if (attackTime > 0.3f) {
                attackTime = 0.f;
                isAttack = false;
                isMoving = false;
            }
        }



    }

    private GroundBlock findNearestPlatform(float foot) {
        GroundBlock nearest = null;
        MainScene game = MainScene.getInstance();
        ArrayList<GameObject> platforms = game.objectsAt(MainScene.Layer.groundBlock.ordinal());

        float top = Metrics.height;
        for (GameObject obj: platforms) {
            GroundBlock platform = (GroundBlock) obj;
            RectF rect = platform.getDstRect();
            if (rect.left <= foot && foot <= rect.right) {
                nearest = platform;
                return nearest;
            }
        }
        return null;
    }

    private float jumping(float frameTime) {
        float jumpY = originalY;

        jumpY = jumpPower * jumpTime - 9.8f * jumpTime * jumpTime * 0.5f;
        jumpTime += 10.f * frameTime;

        if (dstRect.bottom > y + radius) {
            dstRect.bottom = y + radius;
            dstRect.top = y - radius;
            boundingRect.bottom = y + radius;
            boundingRect.top = y - radius;
            isJumping = false;
            jumpTime = 0.f;
        }
        return jumpY;
    }

    @Override
    public void draw(Canvas canvas) {
        long now;
        int frameIndex = 0;

        if (isMove) {
            now = System.currentTimeMillis();
            float time = (now - createdOn) / 1000.0f;
            frameIndex = Math.round(time * framesPerSecond) % frameCount;
        }
        if (isJumping) {
            frameIndex = 3;

        }
        if (isAttack) {
            now = System.currentTimeMillis();
            float time = (now - createdOn) / 1000.0f;
            frameIndex = Math.round(time * framesPerSecond) % 3;
        }


        srcRect.set(frameIndex * imageWidth, 0,
                (frameIndex + 1) * imageWidth, imageHeight);

        if (isLeft) {
            canvas.drawBitmap(reverseBitmap, srcRect, dstRect, null);
        }
        else
        {
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
        }

        if (isShield) {
            canvas.drawCircle(dstRect.centerX(), dstRect.centerY(), Metrics.size(R.dimen.player_radius), shieldPaint);
        }
    }

    public void setIsMove(boolean isMove) {this.isMove = isMove;}
    public void setIsJump(boolean isJump) {this.isJumping = isJump;}
    public void setIsLeftMove(boolean isLeft) {this.isLeft = isLeft;}
    public void setScore(int score) {this.score = score;}
    public void setAttackCount() {
        this.attackCount += 5;
    }
    public void setIsShield(boolean isShield) {this.isShield = isShield;}

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }
    public RectF getDstRect() {return dstRect;}
    public int getScore() {return score;}
    public boolean isMove() {return isMove;}
    public boolean isJump() {return isJumping;}
    public boolean getIsLeftMove() {return isLeft;}
    public int getAttackCount() {return this.attackCount;}
    public boolean isShield() {return isShield;}


    public void getCoin(int score) {
        this.score += score;
        setPlayerScoreUI();
    }

    private void setPlayerScoreUI()
    {
        ArrayList<GameObject> playerScoreUIs = MainScene.getInstance().objectsAt(MainScene.Layer.player_score.ordinal());
        GameObject tenScore = playerScoreUIs.get(0);
        GameObject oneScore = playerScoreUIs.get(1);

        ((PlayerScore)tenScore).setScore(score / 10);
        ((PlayerScore)oneScore).setScore(score % 10);
    }


    public void attack() {
        if (attackCount > 0) {

            // Create Bullet
            Bullet bullet = new Bullet(dstRect.centerX(), dstRect.centerY());
            bullet.setObject(Bullet.OBJ.PLAYER_BULLET);
            if (isLeft)
                bullet.setDirection(-1.f);
            else
                bullet.setDirection(1.f);
            MainScene.getInstance().add(MainScene.Layer.bullets.ordinal(), bullet);

            // Apply UI
            ArrayList<GameObject> playerBullets = MainScene.getInstance().objectsAt(MainScene.Layer.player_bulletUI.ordinal());
            GameObject playerBullet = playerBullets.get(attackCount-1);
            MainScene.getInstance().remove(playerBullet);

            // Change Player State
            isAttack = true;
            attackCount -= 1;
        }
        if (attackCount == 0) {
            MainScene.getInstance().attackButton.onAttack(false);
        }
    }

    public void hitByEnemy() {
        attackCount = 0;
        MainScene.getInstance().attackButton.onAttack(false);

        ArrayList<GameObject> playerBullets = MainScene.getInstance().objectsAt(MainScene.Layer.player_bulletUI.ordinal());
        for (GameObject playerBullet : playerBullets) {
            MainScene.getInstance().remove(playerBullet);
        }
    }
}
