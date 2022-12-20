import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;

public class MapSolver {
    char[][] forest;
    Position currentPosition;
    LinkedList<Step> steps = new LinkedList<Step>();
    LinkedList<Integer> collectionOfStepsForWaysOut = new LinkedList<>();
    int fastestWayOut;

    public void createCollectionOfStepsForWaysOut(LinkedList<Step> steps){
      for (Step i : steps) {
            if (i.isThisFinalStep == true) {
                collectionOfStepsForWaysOut.push(i.stepNumber - 1);
            } else if (i.isThisFinalStep == false){
                createCollectionOfStepsForWaysOut(i.steps);
            }
        }
    }

    public int findFastestWayOut(LinkedList<Integer> collectionOfStepsForWaysOut) {
        int fastestWay = 0;
        try {
            fastestWay = Collections.min(collectionOfStepsForWaysOut);
        } catch (Exception e) {
    //if there is no way out, there will be no collectionOfStepsForWaysOut, this should handle the error
            return 0;
        }
        if ((forest.length < 5 || forest.length > 11000) || (forest[0].length < 5 || forest[0].length > 11000)) {
            return 0;
        } else if (collectionOfStepsForWaysOut.size() > 1000) {
            return 0;
        } else {
            return fastestWay;
        }
    }

    public LinkedList<Step> makeListOfPossibleSteps(char[][] map, Position startingPosition) {
        lookAround(map, startingPosition, steps);
        return steps;
    }

    public void checkRight(char[][] map, Position startingPosition, LinkedList<Step> steps) {
        int x = startingPosition.x;
        int y = startingPosition.y;
        if (map[y][x + 1] == ' ') {
            Position updatedPosition = new Position(x + 1, y);
            map[y][x] = '1';
            Step step = new Step(updatedPosition, map, false, 1);
            steps.push(step);
        }
    }

    public void checkLeft(char[][] map, Position startingPosition, LinkedList<Step> steps) {
        int x = startingPosition.x;
        int y = startingPosition.y;
        if (map[y][x - 1] == ' ') {
            Position updatedPosition = new Position(x - 1, y);
            map[y][x] = '1';
            Step step = new Step(updatedPosition, map, false, 1);
            steps.push(step);
        }
    }

    public void checkUp(char[][] map, Position startingPosition, LinkedList<Step> steps) {
        int x = startingPosition.x;
        int y = startingPosition.y;
        if (map[y - 1][x] == ' ') {
            Position updatedPosition = new Position(x, y - 1);
            map[y][x] = '1';
            Step step = new Step(updatedPosition, map, false, 1);
            steps.push(step);
        }
    }

    public void checkDown(char[][] map, Position startingPosition, LinkedList<Step> steps) {
        int x = startingPosition.x;
        int y = startingPosition.y;
        if (map[y + 1][x] == ' ') {
            Position updatedPosition = new Position(x, y + 1);
            map[y][x] = '1';
            Step step = new Step(updatedPosition,  map, false, 1);
            steps.push(step);
        }
    }

    public void lookAround(char[][] map, Position startingPosition, LinkedList<Step> steps) {
        int x = startingPosition.x;
        int y = startingPosition.y;
        if ((x == map[0].length - 1 || x == 0) || (y == map.length - 1 || y == 0)) {
            map[y][x] = '1';
            Step step = new Step(startingPosition, map, true, 1);
            steps.push(step);
            return;
        } else {
            checkDown(map, startingPosition, steps);
            checkUp(map, startingPosition, steps);
            checkRight(map, startingPosition, steps);
            checkLeft(map, startingPosition, steps);
        }
    }

    public char[][] makeNotReferenceMapCopy ( char[][] forestMap){
        char[][] forestCopy = new char[forestMap.length][forestMap[0].length];
        for (int i = 0; i < forestMap.length; i++) {
            for (int j = 0; j < forestMap[i].length; j++) {
                forestCopy[i][j] = forestMap[i][j];
            }
        }
        return forestCopy;
    }

    public MapSolver(Resource resource) throws FileNotFoundException {
            this.forest = makeNotReferenceMapCopy(resource.map);
            this.currentPosition = resource.startingPosition;
    }
}