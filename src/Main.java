import java.io.FileNotFoundException;
// The idea was for code to take starting position, look around and find adjacent empty cells.
// Then create and update its list of possible moves with those cells.
// Every Step object should look around and create list of possible Steps for it's position when it is being created.
// When the last position before out of bounds is reached - the last Step should be created.
// Last Step should have a property isThisFinalStep set to true and number of steps it took to get there.
// So, every successful path should have an extra Step object at the end with isThisFinalStep set to true.

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Resource resource = new Resource("map2.txt");
        MapSolver mapSolver = new MapSolver(resource);
        mapSolver.makeListOfPossibleSteps(mapSolver.forest, mapSolver.currentPosition);
        mapSolver.createCollectionOfStepsForWaysOut(mapSolver.steps);
        mapSolver.findFastestWayOut(mapSolver.collectionOfStepsForWaysOut);
        System.out.println(mapSolver.findFastestWayOut(mapSolver.collectionOfStepsForWaysOut));
        System.out.println(mapSolver.collectionOfStepsForWaysOut.size());
    }
}