import {Up, Down, Left, Right } from '../model/Model.js';

// Scaling Constants for Canvas
var BOXSIZE = 100;
const OFFSET = 8;

/** Represents a rectangle. */
export class Rectangle {
  constructor(x, y, width, height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  /** Does the (x,y) point exist within the rectangle. */
  contains(x, y) {
     return x >= this.x && x <= (this.x + this.width) && y >= this.y && y <= (this.y + this.height);
  }
}

/** Map piece into rectangle in puzzle view. */
export function computeRectangle(piece) {
  let c = piece.location();
  return new Rectangle(BOXSIZE*c.column + OFFSET, BOXSIZE*c.row + OFFSET, BOXSIZE*piece.width - 2*OFFSET, BOXSIZE*piece.height - 2*OFFSET);
}

/** Draw puzzle. */
export function drawPuzzle (ctx, puzzle, showLabels) {
  ctx.shadowColor = 'black';
  
  let selected = puzzle.selected;
  
  puzzle.pieces.forEach(piece => {
     let rect = computeRectangle(piece);
     if (piece === selected) {
        ctx.fillStyle = 'yellow';
     } else {
        if (piece.isWinner) {
	      ctx.fillStyle = 'red';
	    } else {
	      ctx.fillStyle = 'lightblue';
	    }
     }
     
     ctx.shadowBlur = 10;
     ctx.fillRect(rect.x, rect.y, rect.width, rect.height);
     if (showLabels) {
        ctx.font = "24px Consolas";
        ctx.fillStyle = 'black';
        ctx.shadowBlur = 0;
        ctx.fillText(piece.label, rect.x + rect.width/2 - 6, rect.y + rect.height/2 + 6);
     }
      
  });

}

/** Redraw entire canvas from model. */
export function redrawCanvas(model, canvasObj, appObj) {
    const ctx = canvasObj.getContext('2d');
 
    // clear the canvas area before rendering the coordinates held in state
    ctx.clearRect( 0,0, canvasObj.width, canvasObj.height);  // assume square region
    
    // fill in unnecessary areas.
    let puzzle = model.puzzle;
    let maxRC = puzzle.numRows;
    if (puzzle.numColumns > maxRC) { maxRC = puzzle.numColumns; }
    BOXSIZE = canvasObj.width / maxRC;
    
    if (puzzle.numRows !== maxRC) {
       ctx.fillStyle = window.getComputedStyle(appObj).backgroundColor;
       ctx.fillRect(0, BOXSIZE*puzzle.numRows, canvasObj.width, BOXSIZE*(maxRC - puzzle.numRows));
    } else if (puzzle.numColumns !== maxRC) {
       ctx.fillStyle = window.getComputedStyle(appObj).backgroundColor;
       ctx.fillRect(BOXSIZE*puzzle.numColumns, 0, BOXSIZE*(maxRC - puzzle.numColumns), canvasObj.height);
    }

    // draw all coordinates held in state
    if (model.puzzle) { 
      drawPuzzle (ctx, model.puzzle, model.showLabels);
      
	  // draw border BUT be sure to leave room for edge.
	  ctx.fillStyle = 'brown';
	  ctx.shadowBlur = 0;
	  
	  if (puzzle.finalMove === Left) {
	    let exit = puzzle.exit;
	    ctx.fillRect(0, 0, OFFSET, (exit[0])*BOXSIZE);
	    ctx.fillRect(0, (exit[1]+1)*BOXSIZE, OFFSET, (puzzle.numRows-exit[0])*BOXSIZE);
	  } else {
        ctx.fillRect(0, 0, OFFSET, puzzle.numRows * BOXSIZE);
	  }
	  
	   if (puzzle.finalMove === Right) {
	    let exit = puzzle.exit;
	    ctx.fillRect(puzzle.numColumns*BOXSIZE - OFFSET, 0, OFFSET, (exit[0])*BOXSIZE);
	    ctx.fillRect(puzzle.numColumns*BOXSIZE - OFFSET, (exit[1]+1)*BOXSIZE, OFFSET, (puzzle.numRows-exit[0])*BOXSIZE);
	  } else {
	    ctx.fillRect(puzzle.numColumns*BOXSIZE - OFFSET, 0, OFFSET, puzzle.numRows * BOXSIZE);
	  }
	  
	  if (puzzle.finalMove === Down) {
	    let exit = puzzle.exit;
	    ctx.fillRect(0, puzzle.numRows*BOXSIZE - OFFSET, (exit[1]-1)*BOXSIZE, OFFSET);
	    ctx.fillRect((exit[1]+1)*BOXSIZE, puzzle.numRows*BOXSIZE - OFFSET, (puzzle.numColumns-exit[1]-1)*BOXSIZE, OFFSET);
	  } else {
        ctx.fillRect(0, puzzle.numRows*BOXSIZE - OFFSET, puzzle.numColumns*BOXSIZE, OFFSET);
	  }
	  
	  if (puzzle.finalMove === Up) {
	    let exit = puzzle.exit;
	    ctx.fillRect(0, 0, (exit[1]-1)*BOXSIZE, OFFSET);
	    ctx.fillRect((exit[1]+1)*BOXSIZE, 0, (exit[1]-exit[0])*BOXSIZE, OFFSET);
	  } else {
        ctx.fillRect(0, 0, puzzle.numColumns * BOXSIZE, OFFSET);
	  }
    }
  };