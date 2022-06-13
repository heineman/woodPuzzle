package woodpuzzle.boundary;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import woodpuzzle.controller.MovePieceController;
import woodpuzzle.controller.ResetPuzzleController;
import woodpuzzle.controller.SelectPieceController;
import woodpuzzle.controller.SolvePuzzleController;
import woodpuzzle.controller.UndoController;
import woodpuzzle.model.Model;
import woodpuzzle.model.MoveType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class SlidingPuzzleApp extends JFrame {

	private static final long serialVersionUID = 3891546431979207541L;
	
	Model model;
	JPanel contentPane;
	JButton solveButton;
	JButton resetButton;
	JButton upButton;
	JButton leftButton;
	JButton rightButton;
	JButton downButton;
	PuzzleView puzzleView;
	JLabel moveCount;
	JMenuItem mntmUndo;
	JTextArea solutionArea;
	
	public PuzzleView getPuzzleView() { return puzzleView; }
	public JButton getUpButton() { return upButton; }
	public JButton getDownButton() { return downButton; }
	public JButton getLeftButton() { return leftButton; }
	public JButton getRightButton() { return rightButton; }
	public JTextArea getSolutionArea() { return solutionArea; }

	public JButton getResetButton() { return resetButton; }
	public JButton getSolveButton() { return solveButton; }
	public JLabel getMovesLabel() { return moveCount; }
	/**
	 * Create the frame.
	 */
	public SlidingPuzzleApp(Model m) {
		this.model = m;
		setTitle("SlidingPuzzleApp");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 808, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setFocusable(true);  // without this, KeyListener won't work!
		
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
					new MovePieceController(SlidingPuzzleApp.this, model).move(MoveType.Up);
					break;
					
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					new MovePieceController(SlidingPuzzleApp.this, model).move(MoveType.Right);
					break;
					
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					new MovePieceController(SlidingPuzzleApp.this, model).move(MoveType.Down);
					break;
					
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					new MovePieceController(SlidingPuzzleApp.this, model).move(MoveType.Left);
					break;
				}
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUndo = new JMenu("Undo");
		menuBar.add(mnUndo);
		
		mntmUndo = new JMenuItem("Undo move...");
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mnUndo.add(mntmUndo);
		
		mntmUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UndoController(SlidingPuzzleApp.this, model).undo();
			}
		});
		
		puzzleView = new PuzzleView(model);
		puzzleView.setBounds(10, 10, 500, 500);
		
		puzzleView.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				new SelectPieceController(SlidingPuzzleApp.this, model).select(me.getPoint());
			}
		});
		
		resetButton = new JButton("Reset");
		resetButton.setBounds(575, 329, 68, 23);
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResetPuzzleController(SlidingPuzzleApp.this, model).reset();
			}
		});
		
		solveButton = new JButton("Solve");
		solveButton.setBounds(641, 329, 69, 23);
		solveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solutionArea.setVisible(true);
				new SolvePuzzleController(SlidingPuzzleApp.this, model).solve();
			}
		});
		
		upButton = new JButton("^");
		upButton.setBounds(620, 230, 45, 25);
		upButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(SlidingPuzzleApp.this, model).move(MoveType.Up);
			}
		});
		
		leftButton = new JButton("<");
		leftButton.setBounds(575, 255, 45, 25);
		leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(SlidingPuzzleApp.this, model).move(MoveType.Left);
			}
		});
		rightButton = new JButton(">");
		rightButton.setBounds(665, 255, 45, 25);
		rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(SlidingPuzzleApp.this, model).move(MoveType.Right);
			}
		});
		downButton = new JButton("V");
		downButton.setBounds(620, 280, 45, 25);
		downButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(SlidingPuzzleApp.this, model).move(MoveType.Down);
			}
		});
		
		JLabel mlabel = new JLabel("Moves:");
		mlabel.setBounds(587, 189, 61, 14);
		
		moveCount = new JLabel("" + model.getMoveCount());
		moveCount.setBounds(666, 189, 26, 14);
		
		JScrollPane solutionScrollablePanel = new JScrollPane();
		solutionScrollablePanel.setBounds(564, 16, 184, 139);
		contentPane.setLayout(null);
		contentPane.add(puzzleView);
		contentPane.add(mlabel);
		contentPane.add(upButton);
		contentPane.add(leftButton);
		contentPane.add(rightButton);
		contentPane.add(downButton);
		contentPane.add(moveCount);
		contentPane.add(resetButton);
		contentPane.add(solveButton);
		contentPane.add(solutionScrollablePanel);
		
		solutionArea = new JTextArea();
		solutionScrollablePanel.setViewportView(solutionArea);
		solutionArea.setRows(8);
		solutionArea.setEditable(false);
		solutionArea.setFocusable(false);  // don't grab keyboard focus
	}
}
