package kr.ac.tukorea.ge.smartphonegamep.runrunrun;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

public class Player extends AnimSprite implements BoxCollidable {
    private static final String TAG = Player.class.getSimpleName();
    private boolean isMove = false;
    private boolean isJump = false;
    private boolean isFall = false;
    protected RectF boundingRect = new RectF();

    private float jumpPower = 15.f;
    private float jumpTime = 0.f;
    private float originalY = 0.f;

    private float attackTime = 0.f;
    private boolean isLeft = false;

    private int score = 0;
    private int attackCount = 0;

    public Player(float x, float y) {
        super(x, y, R.dimen.player_radius, R.mipmap.player_walk, 20, 4);
        originalY = y;
        boundingRect.set(x - Metrics.size(R.dimen.player_coll_radius), y - Metrics.size(R.dimen.player_coll_radius), x + Metrics.size(R.dimen.player_coll_radius), y + Metrics.size(R.dimen.player_coll_radius));
    }

    public void update(float frameTime) {
        if (findNearestPlatform(dstRect.centerX()) == null)
        {
            //float dy = frameTime * 2.f;
            //dstRect.offset(0, 5.f);
            //boundingRect.offset(0, 5.f);
            //return;
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

        //dstRect.offset(0, -jumpY);

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

        if (isMoving) {
            now = System.currentTimeMillis();
            float time = (now - createdOn) / 1000.0f;
            frameIndex = Math.round(time * framesPerSecond) % frameCount;
        }
        else if (isJumping) {
            frameIndex = 3;
        }
        else if (isAttack) {
            now = System.currentTimeMillis();
            float time = (now - createdOn) / 1000.0f;
            frameIndex = Math.round(time * framesPerSecond) % 3;
        }


        srcRect.set(frameIndex * imageWidth, 0,
                (frameIndex + 1) * imageWidth, imageHeight);

        if (isLeft)
            canvas.drawBitmap(reverseBitmap, srcRect, dstRect, null);
        else
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public void setIsMove(boolean isMove) {this.isMove = isMove;}
    public void setIsJump(boolean isJump) {this.isJumping = isJump;}
    public void setIsLeftMove(boolean isLeft) {this.isLeft = isLeft;}
    public void setScore(int score) {this.score = score;}
    public void setAttackCount() {
        this.attackCount += 5;
    }

    @Override
    public RectF getBoudingRect() {
        return boundingRect;
    }
    public RectF getDstRect() {return dstRect;}
    public int getScore() {return score;}
    public boolean isMove() {return isMove;}
    public boolean getIsLeftMove() {return isLeft;}
    public int getAttackCount() {return this.attackCount;}



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
}
