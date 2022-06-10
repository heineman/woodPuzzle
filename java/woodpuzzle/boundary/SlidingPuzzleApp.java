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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
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
		setBounds(100, 100, 650, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
		puzzleView.setBackground(Color.GRAY);
		puzzleView.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				new SelectPieceController(SlidingPuzzleApp.this, model).select(me.getPoint());
			}
		});
		
		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResetPuzzleController(SlidingPuzzleApp.this, model).reset();
			}
		});
		
		solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solutionArea.setVisible(true);
				new SolvePuzzleController(SlidingPuzzleApp.this, model).solve();
			}
		});
		
		upButton = new JButton("^");
		upButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(SlidingPuzzleApp.this, model).move(MoveType.Up);
			}
		});
		
		leftButton = new JButton("<");
		leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(SlidingPuzzleApp.this, model).move(MoveType.Left);
			}
		});
		rightButton = new JButton(">");
		rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(SlidingPuzzleApp.this, model).move(MoveType.Right);
			}
		});
		downButton = new JButton("V");
		downButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(SlidingPuzzleApp.this, model).move(MoveType.Down);
			}
		});
		
		JLabel mlabel = new JLabel("Moves:");
		
		moveCount = new JLabel("" + model.getMoveCount());
		
		JScrollPane solutionScrollablePanel = new JScrollPane();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(puzzleView, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(mlabel)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(upButton)
								.addComponent(downButton)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(481)
							.addComponent(moveCount))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(428)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(resetButton)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(solveButton))
								.addComponent(solutionScrollablePanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(443)
							.addComponent(leftButton)
							.addGap(37)
							.addComponent(rightButton)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(puzzleView, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(mlabel)
								.addComponent(moveCount))
							.addGap(153)
							.addComponent(upButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(leftButton)
								.addComponent(rightButton))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(downButton)
							.addGap(18)
							.addComponent(solutionScrollablePanel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(solveButton)
								.addComponent(resetButton))
							.addGap(29)))
					.addContainerGap())
		);
		
		solutionArea = new JTextArea();
		solutionScrollablePanel.setViewportView(solutionArea);
		solutionArea.setRows(8);
		solutionArea.setEditable(false);
		contentPane.setLayout(gl_contentPane);
	}
}
