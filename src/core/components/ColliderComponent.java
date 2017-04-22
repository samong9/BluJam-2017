package core.components;

import core.Debug;
import core.GameObject;

import java.util.Collection;

/**GameObject
 * Created by zva on 21/04/17.
 */
public class ColliderComponent {

    private GameObject obj;
    private Collidable collidedWith;
    private Rectangle collisionBox;

    public <T extends GameObject & Collidable> ColliderComponent(T obj) {
        this.obj = obj;
        this.collisionBox = new Rectangle(0, 0, 32, 32);
    }

    public <T extends GameObject & Collidable> ColliderComponent(T obj, float width, float height) {
        this.obj = obj;
        this.collisionBox = new Rectangle(width, height);
    }

    public boolean hasCollided() {
        return collidedWith != null;
    }

    public Collidable getCollidedWith() {return collidedWith;}
    public void setCollidedWith(Collidable obj) {
        this.collidedWith = obj;
    }

    public boolean collidesWith(Collidable otherObj) {
        return this.collisionBox.overlaps(otherObj.getCollider().collisionBox);
    }

    public boolean xOverlaps(Collidable otherObj) {
        return this.collisionBox.xOverlaps(otherObj.getCollider().collisionBox);
    }

    public boolean yOverlaps(Collidable otherObj) {
        return this.collisionBox.yOverlaps(otherObj.getCollider().collisionBox);
    }

    private class Rectangle {
        private float dx = 0;
        private float dy = 0;
        private float width;
        private float height;

        private float getAbsX() {
            return obj.getPosition().x + dx;
        }

        private float getAbsY() {
            return obj.getPosition().y + dy;
        }

        private float getAbsX2() {
            return getAbsX()+width;
        }

        private float getAbsY2() {
            return getAbsY()+height;
        }

        private Rectangle(float width, float height) {
            this.width = width;
            this.height = height;
        }

        private Rectangle(float dx, float dy, float width, float height) {
            this.dx = dx;
            this.dy = dy;
            this.width = width;
            this.height = height;
        }

        public boolean overlaps(Rectangle otherRectangle) {
            return this.getAbsX() < otherRectangle.getAbsX2() &&
                    this.getAbsX2() > otherRectangle.getAbsX() &&
                    this.getAbsY() < otherRectangle.getAbsY2() &&
                    this.getAbsY2() > otherRectangle.getAbsY();
        }

        public boolean xOverlaps(Rectangle other) {
            if (this.getAbsX2() > other.getAbsX() &&
                    this.getAbsX2() < other.getAbsX2()) {
                return true;
            } else if (this.getAbsX() > other.getAbsX() &&
                    this.getAbsX() < other.getAbsX2()) {
                return true;
            } else {
                return false;
            }
        }

        public boolean yOverlaps(Rectangle other) {
            if (this.getAbsY2() > other.getAbsY() &&
                    this.getAbsY2() < other.getAbsY2()) {
                return true;
            } else if (this.getAbsY() > other.getAbsY() &&
                    this.getAbsY2() < other.getAbsX2()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
