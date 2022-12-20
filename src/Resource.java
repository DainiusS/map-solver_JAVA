import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Resource {
    char[][] map;
    Position startingPosition;

    public Position getStartingPosition(char[][] arr) {
        int yAxisCoordinate = 0;
        int xAxisCoordinate = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 'X') {
                    yAxisCoordinate = i;
                    xAxisCoordinate = j;
                    break;
                }
            }
        }
       Position start = new Position(xAxisCoordinate, yAxisCoordinate);
       return start;
    }

    public Resource(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        int numberOfRows = 0;
        int numberOfColumns = 0;

        while (scanner.hasNextLine()) {
            numberOfRows = numberOfRows + 1;
            String data = scanner.nextLine();
            numberOfColumns = data.length();
        }
        scanner.close();

        char[][] arrayOfChars = new char[numberOfRows][numberOfColumns];

        Scanner sc = new Scanner(file);
        int row = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            char[] lineOfCharacters = line.toCharArray();
            for (int i = 0; i < lineOfCharacters.length; i++) {
                arrayOfChars[row][i] = lineOfCharacters[i];
            }
            row = row + 1;
        }
        sc.close();
        this.startingPosition = getStartingPosition(arrayOfChars);
        this.map = arrayOfChars;
    }
}
