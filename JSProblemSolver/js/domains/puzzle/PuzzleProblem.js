var pName = "Puzzle" ;  // You provide problem name

var pIntro = "There is a 3x3 tile board filled with tiles numbered 1 throug 8. There is one empty space. You can "
              + "only slide a tile if it is touching the empty space, switching its location with the empty space. "
              + "To complete the puzzle, the top row must be '1 2 3', the middle row must be '8 EMPTY 4' and the "
              + "bottom row must be '7 6 5'. You must devise a sequence of actions so that you reach this desired "
              + "final state."; ; // Introductory text

var pInit = new PuzzleState([[2, 8, 3], [1, 6, 4], [7, 0, 5]]);  // Initial state

var pGoal = new PuzzleState([[1, 2, 3], [8, 0, 4], [7, 6, 5]]);  // Goal state

var pMvr = new PuzzleMover();   // Mover object

function PuzzleProblem() { }

PuzzleProblem.prototype =
    new Problem(pName, pIntro, pInit, pGoal, pMvr);