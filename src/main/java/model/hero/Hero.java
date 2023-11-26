package model.hero;

public class Hero {
    private String name;
    private boolean hasGold;
    private int arrows;
    private FacingDirection facingDirection;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasGold() {
        return hasGold;
    }

    public void grabGold() {
        this.hasGold = true;
    }

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    public FacingDirection getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(FacingDirection facingDirection) {
        this.facingDirection = facingDirection;
    }
    public void rotate(Rotation rotation) {
        switch (rotation) {
            case RIGHT -> facingDirection = facingDirection.toRight();
            case LEFT -> facingDirection = facingDirection.toLeft();
            default -> throw new RuntimeException("Cannot rotate player: Invalid rotation");
        }
    }
}


