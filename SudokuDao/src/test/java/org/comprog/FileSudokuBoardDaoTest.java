package org.comprog;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.comprog.Dao;
import org.comprog.DaoFactory;
import org.comprog.DaoTypes;
import org.comprog.Difficulty;
import org.comprog.RandomSudokuSolver;
import org.comprog.SudokuBoard;
import org.comprog.SudokuSolver;
import org.junit.After;
import org.junit.Test;

public class FileSudokuBoardDaoTest {

  private static final String fileName = "/tmp/FileSudokuBoardDaoTest.dat";

  @Test
  public void testSerializationCorrectness() {
    SudokuBoard board = new SudokuBoard(Difficulty.NONE);
    SudokuSolver randomSolver = new RandomSudokuSolver();
    randomSolver.solve(board);

    Dao<SudokuBoard> sudokuDao = DaoFactory.create(DaoTypes.FileSuokuBoard, fileName);

    SudokuBoard board2 = null;

    try {
      sudokuDao.write(board);
      board2 = sudokuDao.read();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    assertEquals(board, board2);
  }

  @After
  public void tearDown() throws Exception {
    File file = new File(fileName);
    if (!file.delete())
      throw new RuntimeException("Error deleting " + fileName);
  }

}
