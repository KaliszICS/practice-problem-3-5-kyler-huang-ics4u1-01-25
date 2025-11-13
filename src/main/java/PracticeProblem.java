public class PracticeProblem {

    // Count number of SHORTEST paths from 'S' (bottom-left) to 'F' (top-right)
    public static int noOfPaths(String[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;
        int startRow = rows - 1;
        int startCol = 0;

        boolean[][] visited = new boolean[rows][cols];
        return dfs(maze, startRow, startCol, visited);
    }

    private static int dfs(String[][] maze, int r, int c, boolean[][] visited) {
        int rows = maze.length;
        int cols = maze[0].length;

        // Out of bounds
        if (r < 0 || r >= rows || c < 0 || c >= cols) return 0;

        // Wall
        if (maze[r][c].equals("#")) return 0;

        // Found finish
        if (maze[r][c].equals("F")) return 1;

        // Only move UP or RIGHT (shortest path directions)
        int paths = 0;
        paths += dfs(maze, r - 1, c, visited);  // up
        paths += dfs(maze, r, c + 1, visited);  // right

        return paths;
    }

    // Find minimum moves from 'S' to 'F' using BFS
    public static int searchMazeMoves(String[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;
        int startRow = rows - 1, startCol = 0;

        boolean[][] visited = new boolean[rows][cols];
        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        queue.add(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], dist = cur[2];

            if (maze[r][c].equals("F"))
                return dist;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                    !maze[nr][nc].equals("#") && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc, dist + 1});
                }
            }
        }

        return -1;
    }

    // Manual test
    public static void main(String[] args) {
        String[][] maze = {
            {"", "", "", "F"},
            {"", "", "", ""},
            {"", "", "", ""},
            {"S", "", "", ""},
        };

        System.out.println("No. of paths: " + noOfPaths(maze));     // ✅ 20
        System.out.println("Min moves: " + searchMazeMoves(maze));  // ✅ 6
    }
}

