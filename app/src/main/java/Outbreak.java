public class Outbreak {
    public Outbreak() {
    }

    public static void main(String[] args) {

        Room[][] verticalTrue;

        verticalTrue = new Room[][]{
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(true), new
                        Room(true), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)}
        };
        int[][] beenThere = new int[verticalTrue.length][verticalTrue[0].length];
        for (int x = 0; x < beenThere.length; x++) {
            for (int y = 0; y < beenThere[0].length; y++) {
                beenThere[x][y] = 0;
            }
        }

        if (Room.isOutbreak(verticalTrue, beenThere)) {
            System.out.println("THERE IS AN OUTBREAK!");
        } else {
            System.out.println("There is no outbreak");
        }

    }

}

class Room {

    public boolean isInfected;
    public int isVisited;
    private static int count;

    Room(boolean infected) {
        isInfected = infected;
    }

    Room(int visited){
        isVisited = visited;
    }

    public static int searchNextDoor(Room[][] floor,int[][] visited, int x, int y) {
        count++;
        if (x > 0 && floor[x - 1][y].isInfected && visited[x - 1][y] == 0) {
            visited[x - 1][y] = 1;
            count = searchNextDoor(floor, visited,x - 1, y);
        }
        if (x < floor.length  && floor[x + 1][y].isInfected && visited[x + 1][y] == 0) {
            visited[x + 1][y ]= 1;
            count = searchNextDoor(floor, visited, x + 1, y);
        }
        if (y < 0 && floor[x][y - 1].isInfected && visited[x][y - 1] == 0) {
            visited[x][y - 1] = 1;
            count = searchNextDoor(floor, visited, x, y - 1);
        }
        if (y < floor[0].length && floor[x][y + 1].isInfected && visited[x][y + 1] == 0) {
            visited[x][y + 1] = 1;
            count = searchNextDoor(floor, visited, x, y + 1);
        }
        return count;
    }

    public static boolean isOutbreak(Room[][] floor, int[][] visited) {
        for (int x = 0; x < floor.length; x++) {
            for (int y = 0; y < floor[0].length; y++) {
                visited[x][y] = 1;
                if (floor[x][y].isInfected) {
                    count = searchNextDoor(floor,visited, x, y);
                }
                if (count >= 5) {
                    return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

}

