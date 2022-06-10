import {computeRectangle} from '../boundary/Boundary.js';
import {Up, Down, Left, Right, NoMove} from '../model/Model.js';

export function selectPiece(model, canvas, event) {
    const canvasRect = canvas.getBoundingClientRect();
    
    let idx = model.puzzle.pieces.findIndex(piece => {
       let rect = computeRectangle(piece);
	   return rect.contains(event.clientX - canvasRect.left, event.clientY - canvasRect.top);
    });

    let selected = null;
    if (idx >= 0) {
      selected = model.puzzle.pieces[idx];
    } 

    // select this piece! Construct new model to represent this situation.
    model.puzzle.select(selected);
    return model.copy();
}

/** Deals with winning condition by removing the special piece! */
export function movePiece(model, direction) {
    let selected = model.puzzle.selected;
    if (!selected) { return model; }

    if (model.puzzle.hasWon() && direction === model.puzzle.finalMove) {
      model.puzzle.pieces = model.puzzle.pieces.filter(p => p !== selected);
      model.puzzle.selected = null;  // GONE!
      model.victorious();
    } else {
      selected.move(direction);
    }
    
    model.updateMoveCount(+1);
    return model.copy();
}

class Node {
  constructor(board, previous, direction) {
    this.board = board;
    this.previous = previous;
    this.direction = direction;
  }
}

class QueueNode {
  constructor(thing) {
    this.thing = thing;
    this.next = null;
  }
}

class Queue {
  constructor() {
    this.head = null;
    this.tail = null;
  }
  
  isEmpty() {
    return this.head == null;
  }
  
  enqueue(thing) {
    if (this.head == null) {
      this.head = new QueueNode(thing);
      this.tail = this.head;
    } else {
      this.tail.next = new QueueNode(thing);
      this.tail = this.tail.next;
    }
  }
  
  dequeue() {
    if (this.head === this.tail) {
      let thing = this.head.thing;
      this.head = this.tail = null;
      return thing;
    } 
    
    let thing = this.head.thing;
    this.head = this.head.next;
    return thing;
  }
}

/** Solve puzzle and return solution as a string. */
export function solvePuzzle(model) {
   var seen = new Map()
   var queue = new Queue();
   let copy = model.puzzle.clone();
   var n = new Node(copy, null, NoMove);
   seen.set(copy.key(), true);
   queue.enqueue(n);
   
   const directions = [Up, Down, Left, Right];
   while (!queue.isEmpty()) {
      n = queue.dequeue();
      
      // for each of the pieces in the copy, try to move in each direction
      for (let idx = 0; idx < n.board.pieces.length; idx++) {
        for (let d of directions) {
          let copy = n.board.clone();
          copy.selected = copy.pieces[idx];
          if (copy.availableMoves().includes(d)) {
            copy.selected.move(d);
            var next = new Node(copy, n, d);
            if (copy.hasWon()) {
               // SOLUTION! Work backwards to produce entire solution
               var moves = "";
               while (next.previous != null) {
                 moves = next.board.selected.label + " " + next.direction.label + "\n" + moves;
                 next = next.previous;
               }
               return moves;
            }
            
            let k = copy.key();
            if (seen.get(k) === true) {
               /* SKIP */
            } else {
               seen.set(k, true);
               queue.enqueue(next);
            }
          }
        }
      }
   }
   
   return "*No Solution*";
}
