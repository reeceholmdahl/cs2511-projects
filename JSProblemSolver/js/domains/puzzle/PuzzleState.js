function PuzzleState(tiles) {

    this.tiles = tiles;

    this.toString = function() {
	// You provide
        const width = tiles[0].length;
        let s = "";
        for (const row of tiles) {
            s += horizontalDivider(width);
            s += "\n";
            s += horizontalRow(row);
            s += "\n";
        }
        return s + horizontalDivider(width);
    };

    this.equals = function(other) {
        for (let r = 0; r < this.tiles.length; ++r) {
            for (let c = 0; c < this.tiles[0].length; ++c) {
                if (this.tiles[r][c] !== other.tiles[r][c]) return false;
            }
        }
        return true;
    };

    // Other properties and methods here

    this.makeCanvas = function() {
	return this.makeDefaultCanvas(this);
    };
}

PuzzleState.prototype = STATE_PROTO;

// Helper functions here
function horizontalDivider(width) {
    let s = "";
    for (let i = 0; i < width; ++i) {
        s +="+---";
    }
    return s + "+";
}

function tileString(tile) {
    if ( tile === 0 ) return "   ";
    if ( Math.floor(tile/10) === 0 ) return " " + tile + " ";
    return tile + " ";
}

function horizontalRow(row) {
    let s = "";
    for (const tile of row) {
        s += "|";
        s += tileString(tile);
    }
    return s + "|";
}