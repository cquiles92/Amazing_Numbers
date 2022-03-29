
class Move {
    public static void moveRobot(Robot robot, int toX, int toY) {
        Direction xDirection = toX > robot.getX() ? Direction.RIGHT : Direction.LEFT;
        Direction yDirection = toY > robot.getY() ? Direction.UP : Direction.DOWN;

        int xDelta = Math.abs(toX - robot.getX());
        int yDelta = Math.abs(toY - robot.getY());

        if (xDelta > 0) {
            while (robot.getDirection() != xDirection) {
                robot.turnLeft();
            }
            moveForwardMultiple(robot, xDelta);
        }
        if (yDelta > 0) {
            while (robot.getDirection() != yDirection) {
                robot.turnRight();
            }
            moveForwardMultiple(robot, yDelta);
        }

    }

    private static void moveForwardMultiple(Robot robot, int delta) {
        for (int i = 0; i < delta; i++) {
            robot.stepForward();
        }
    }
}

//Don't change code below

enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                throw new IllegalStateException();
        }
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void stepForward() {
        x += direction.dx();
        y += direction.dy();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}