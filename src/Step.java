import java.util.LinkedList;

public class Step {
    Position currentPosition;
    char[][] map;
    boolean isThisFinalStep;
    LinkedList<Step> steps = new LinkedList<Step>();
    int stepNumber;

    public LinkedList<Step> makeListOfPossibleSteps(char[][] map, Position startingPosition) {
        if (isThisFinalStep == false) {
            lookAround(map, startingPosition, steps);
            return steps;
        } else {
            steps.clear();
            return steps;
        }
    }

    public void checkRight(char[][] map, Position startingPosition, LinkedList<Step> steps) {
        int x = startingPosition.x;
        int y = startingPosition.y;
        if (map[y][x + 1] == ' ') {
            Position updatedPosition = new Position(x + 1, y);
            map[y][x] = '1';
            int num = stepNumber + 1;
            Step step = new Step(updatedPosition, map, false, num);
            steps.push(step);
        }
    }

    public void checkLeft(char[][] map, Position startingPosition, LinkedList<Step> steps) {
        int x = startingPosition.x;
        int y = startingPosition.y;
        if (map[y][x - 1] == ' ') {
            Position updatedPosition = new Position(x - 1, y);
            map[y][x] = '1';
            int num = stepNumber + 1;
            Step step = new Step(updatedPosition, map, false, num);
            steps.push(step);
        }
    }

    public void checkUp(char[][] map, Position startingPosition, LinkedList<Step> steps) {
        int x = startingPosition.x;
        int y = startingPosition.y;
        if (map[y - 1][x] == ' ') {
            Position updatedPosition = new Position(x, y - 1);
            map[y][x] = '1';
            int num = stepNumber + 1;
            Step step = new Step(updatedPosition, map, false, num);
            steps.push(step);
        }
    }

    public void checkDown(char[][] map, Position startingPosition, LinkedList<Step> steps) {
        int x = startingPosition.x;
        int y = startingPosition.y;
        if (map[y + 1][x] == ' ') {
            Position updatedPosition = new Position(x, y + 1);
            map[y][x] = '1';
            int num = stepNumber + 1;
            Step step = new Step(updatedPosition, map, false, num);
            steps.push(step);
        }
    }

    public void lookAround(char[][] map, Position startingPosition, LinkedList<Step> steps) {
        int x = startingPosition.x;
        int y = startingPosition.y;
        if ((x == map[0].length - 1 || x == 0) || (y == map.length - 1 || y == 0)) {
            map[y][x] = '1';
            int num = stepNumber + 1;
            Step step = new Step(startingPosition, map, true, num);
            steps.push(step);
            return;
        } else {
            checkDown(map, startingPosition, steps);
            checkUp(map, startingPosition, steps);
            checkRight(map, startingPosition, steps);
            checkLeft(map, startingPosition, steps);
        }
    }

    public char[][] makeNotReferenceMapCopy(char[][] map){
        char[][] mapCopy = new char[map.length][map[0].length];
        for(int i = 0; i<map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                mapCopy[i][j] = map[i][j];
            }
        }
        return mapCopy;
    }

    public Step(Position currentPosition, char[][] map, boolean isThisFinalStep, int stepNumber) {
        this.map = makeNotReferenceMapCopy(map);
        this.currentPosition = currentPosition;
        this.isThisFinalStep = isThisFinalStep;
        this.stepNumber = stepNumber;
        this.steps = makeListOfPossibleSteps(map, currentPosition);

    }
}
