import React from 'react';

import './App.css';
import Model from './model/Model.js';
import { Up, Down, Left, Right } from './model/Model.js';
import { redrawCanvas } from './boundary/Boundary.js';
import { movePiece, selectPiece, solvePuzzle} from './controller/Controller.js';

// load images from src. This fixes problem when being hosted on github.io pages
import fireworks from './fireworks.apng';

import { mobileLayout } from './Mobile.js';
import { desktopLayout } from './Desktop.js';

import { useMediaQuery } from 'react-responsive'

// default puzzle to use
import { puzzleInformation } from './model/Puzzle.js'; 

var actualPuzzle = JSON.parse(JSON.stringify(puzzleInformation));   // parses string into JSON object, used below.

// Used to record when key is pressed, since there will be duplicate events generated,
// and we only want to process the first one.
var isKeyDown = false;

function App() {
  const [model, setModel] = React.useState(new Model(actualPuzzle));
  const [redraw, forceRedraw] = React.useState(0);       // used to conveniently request redraw after model change
  const [checked, setChecked] = React.useState(false);   // is "show labels" checked?
  const [solved, setSolved] = React.useState(false);     // is puzzle solution computed and shown?
  const [solution, setSolution] = React.useState("");
  const [isInputPuzzleVisible, setInputPuzzleVisible] = React.useState(false);
  const [inputPuzzle, setInputPuzzle] = React.useState("");
  
  const [dimensions, setDimensions] = React.useState({
    height: window.innerHeight,
    width: window.innerWidth
  })
  
  const isDesktopOrLaptop = useMediaQuery({ query: '(min-width: 900px)' })
  
  const layout = isDesktopOrLaptop ? desktopLayout : mobileLayout;
    
  const appRef = React.useRef(null);      // need to be able to refer to App to get background color in Boundary
  const canvasRef = React.useRef(null);   // need to be able to refer to Canvas

  /** Ensures initial rendering is performed, and that whenever model changes, it is re-rendered. */
  React.useEffect (() => {
    function handleResize() {
      setDimensions({
        height:window.innerHeight,
        width: window.innerWidth
      })
    }
    window.addEventListener('resize', handleResize);
    
    /** Happens once. */
    redrawCanvas(model, canvasRef.current, appRef.current);
  }, [model, redraw])   // this second argument is CRITICAL, since it declares when to refresh (whenever Model changes)

  const toggleVisibility = () => {
    setInputPuzzleVisible(!isInputPuzzleVisible);
  };

  const updatePuzzle = (e) => {
    setInputPuzzle(e.target.value);
  };

  const handleClick = (e) => {
    selectPiece(model, canvasRef.current, e)
    forceRedraw(redraw+1)          // force a redraw by incrementing this state count
  }
  
  const movePieceHandler = (direction) => {
    movePiece(model, direction);
    if (solved) {
      let idx = solution.indexOf("\n"); // extract first move
      let result = solution.substring(idx+1);
      setSolution(result);
      if (result.length === 0) { setSolved(false); } // remove the text solution....
    }
    forceRedraw(redraw+1)   // force a redraw
  }
  
  const handleKeyUpEvent = (e) => {
    isKeyDown = false;
  }
  
  const handleKeyDownEvent = (e) => { 
     if (isKeyDown) { return; }
     isKeyDown = true;
     
     var direction = null;
     if      ((e.keyCode === 37 || e.keyCode === 65 || e.keyCode === 100) && model.available(Left))  { direction = Left; }
     else if ((e.keyCode === 38 || e.keyCode === 87 || e.keyCode === 104) && model.available(Up))    { direction = Up; }
     else if ((e.keyCode === 39 || e.keyCode === 68 || e.keyCode === 102) && model.available(Right)) { direction = Right; }
     else if ((e.keyCode === 40 || e.keyCode === 83 || e.keyCode === 98)  && model.available(Down))  { direction = Down; }
     if (direction) { movePieceHandler(direction); } 
  }
  
  const resetHandler = () => {
    let m = new Model(actualPuzzle);
    setModel(m);                    // Go back and restart. Changed state forces a redraw
    setChecked(false);
  }

  const solveHandler = () => {
    // if previously not solved, this makes it visible and records the solution.
    if (!solved) {
        let moves = solvePuzzle(model);
    	setSolution(moves);
    }
    
    setSolved(!solved);
  }
  
  const changePuzzle = (e) => {
    setInputPuzzleVisible(!isInputPuzzleVisible);
    actualPuzzle = JSON.parse(inputPuzzle);
    try {
      let m = new Model(actualPuzzle)
      setModel(m);          // load up a new puzzle, which changes state to be redrawn
      setChecked(false);
    } catch (err) {
      console.log("Problem parsing input:" + err);
    }
  }

  const handleCheckChange = () => {
     setChecked(!checked);
     model.setShowLabels(!checked);
     forceRedraw(redraw+1)          // force a redraw by incrementing this state count
  }
 
  // top-level application
  return (
    <main style={layout.Appmain} ref={appRef}>
       {/* Allows key events, with tabIndex */}
      <canvas tabIndex="1"  
        className="App-canvas"
        ref={canvasRef}
        width={layout.canvas.width}
        height={layout.canvas.height}
        onClick={handleClick} onKeyDown={handleKeyDownEvent} onKeyUp={handleKeyUpEvent} />
      
      {/* Using '?' construct is proper React way to make image visible only when victorious. */}  
      { model.isVictorious() ? ( <img src={fireworks} alt="fireworks" /> ) : null }

      <p className="nummoves">#Moves: {model.numberMoves()}</p>
      <label className="showlabels"><input type="checkbox" checked={checked} onChange={handleCheckChange}/>Show Labels</label>

      <textarea style={layout.solution} value={solution} rows={layout.solution.numRows} cols={10} hidden={!solved} readOnly></textarea>
      <textarea style={layout.inputPuzzle} placeholder="Enter JSON here" rows={5} onChange={updatePuzzle} hidden={!isInputPuzzleVisible}></textarea>
      { isInputPuzzleVisible ? ( <button style={layout.inputPuzzleChange} onClick={changePuzzle} hidden={isInputPuzzleVisible}>Change Puzzle</button> ) : null }
      
      {/* Group buttons together */}
      <div style={layout.buttons}>
        <button style={layout.upbutton}    onClick={(e) => movePieceHandler(Up)}    disabled={!model.available(Up)}    >&#8593;</button>
        <button style={layout.leftbutton}  onClick={(e) => movePieceHandler(Left)}  disabled={!model.available(Left)}  >&#8592;</button>
        <button style={layout.rightbutton} onClick={(e) => movePieceHandler(Right)} disabled={!model.available(Right)} >&#8594;</button>
        <button style={layout.downbutton}  onClick={(e) => movePieceHandler(Down)}  disabled={!model.available(Down)}  >&#8595;</button> 
        
        <button style={layout.resetbutton} onClick={(e) => resetHandler()} >Reset</button>
        <button style={layout.solvebutton} onClick={(e) => solveHandler()} >Solve</button>
        <button style={layout.loadbutton}  onClick={toggleVisibility} >Load&nbsp;</button>
        
      </div>
    </main>
  );

}

export default App;
