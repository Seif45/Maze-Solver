package eg.edu.alexu.csd.datastructure.maze.cs34_cs40;

import java.io.File;

public class mainTest {

	public static void main(String[] args) {
		MazeSolver solve = new MazeSolver();
		File maze = new File("E:\\maze.txt");
		solve.solveDFS(maze);
	}

}
