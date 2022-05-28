const SLIDE_1 = "Slide 1";
const SLIDE_2 = "Slide 2";
const SLIDE_3 = "Slide 3";
const SLIDE_4 = "Slide 4";
const SLIDE_5 = "Slide 5";
const SLIDE_6 = "Slide 6";
const SLIDE_7 = "Slide 7";
const SLIDE_8 = "Slide 8";

var puzzleMover = new Mover();

puzzleMover.addMove(SLIDE_1, (s) => slideTile(1, s) ); // You provide move functions
puzzleMover.addMove(SLIDE_2, (s) => slideTile(2, s) );
puzzleMover.addMove(SLIDE_3, (s) => slideTile(3, s) );
puzzleMover.addMove(SLIDE_4, (s) => slideTile(4, s) );
puzzleMover.addMove(SLIDE_5, (s) => slideTile(5, s) );
puzzleMover.addMove(SLIDE_6, (s) => slideTile(6, s) );
puzzleMover.addMove(SLIDE_7, (s) => slideTile(7, s) );
puzzleMover.addMove(SLIDE_8, (s) => slideTile(8, s) );

function PuzzleMover() { }

PuzzleMover.prototype = puzzleMover;

function slideTile(tile, state) {
    const pos0 = findTilePos(0, state.tiles);
    const posTile = findTilePos(tile, state.tiles);
    if (Math.abs(pos0.r - posTile.r) + Math.abs(pos0.c - posTile.c) === 1) {
        const tiles = [...state.tiles];
        tiles[pos0.r][pos0.c] = tile;
        tiles[posTile.r][posTile.c] = 0;
        return new PuzzleState(tiles);
    }
    return null;
}

function findTilePos(tile, tiles) {
    for (let r = 0; r < tiles.length; ++r) {
        for (let c = 0; c < tiles[0].length; ++c) {
            if (tiles[r][c] === tile) {
                return { r, c };
            }
        }
    }
    return null;
}