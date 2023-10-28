package model.hero;

public enum FacingDirection {
    NORTH {
        @Override
        public FacingDirection toRight() {
            return FacingDirection.EAST;
        }

        @Override
        public FacingDirection toLeft() {
            return FacingDirection.WEST;
        }
    },
    EAST {
        @Override
        public FacingDirection toRight() {
            return FacingDirection.SOUTH;
        }

        @Override
        public FacingDirection toLeft() {
            return FacingDirection.NORTH;
        }
    },
    SOUTH {
        @Override
        public FacingDirection toRight() {
            return FacingDirection.WEST;
        }

        @Override
        public FacingDirection toLeft() {
            return FacingDirection.EAST;
        }
    },
    WEST {
        @Override
        public FacingDirection toRight() {
            return FacingDirection.NORTH;
        }

        @Override
        public FacingDirection toLeft() {
            return FacingDirection.SOUTH;
        }
    };

    public abstract FacingDirection toRight();

    public abstract FacingDirection toLeft();
}
