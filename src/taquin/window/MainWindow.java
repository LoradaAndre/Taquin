package taquin.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import taquin.core.TaquinGrid;
import taquin.window.dialog.*;
import taquin.component.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private static final String NUMBER_GRID = "NUMBER";
	private static final String IMAGE_GRID = "IMAGE";
	private ImageTaquinGrid imageTaquinGrid;
	// private GUITaquinGrid guiTaquinGrid;

	NumberTaquinGrid numberTaquin;

	public MainWindow(int w, int h, String name) {
		this.setTitle(name);
		this.setSize(w, h);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createMenuBar();
		createMainUI();

		//this.pack();
		this.setVisible(true);
	}

	/**
	 * @return void
	 */
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menuFichier = new JMenu("Fichier");
		JMenu menuAffichage = new JMenu("Mode");

		JMenuItem itemNewGame = new JMenuItem("Nouvelle partie");
		menuFichier.add(itemNewGame);
		//Evenement de l'item "nouvele partie"
		itemNewGame.addActionListener((ActionEvent e) -> {
			NewGameDialog dialog = new NewGameDialog(this);
			Integer width = dialog.getSelectedWidth();
			Integer heigth = dialog.getSelectedHeight();
		});

		JCheckBoxMenuItem itemImage = new JCheckBoxMenuItem("Mode image");
		itemImage.setState(true);
		menuAffichage.add(itemImage);
		//Evenement de l'item "mode image"
		itemImage.addItemListener((ItemEvent e) -> {
			if(itemImage.getState())
				showTaquinGrid(IMAGE_GRID);
			else
				showTaquinGrid(NUMBER_GRID);
		});

		JMenuItem itemChiffre = new JMenuItem("Changer l'image");
		menuAffichage.add(itemChiffre);
		//Evenement de l'item "mode chiffres"
		itemChiffre.addActionListener((ActionEvent e) -> {
			showTaquinGrid(IMAGE_GRID);
		});

		menuBar.add(menuFichier);
		menuBar.add(menuAffichage);

		setJMenuBar(menuBar);
	}

	private void createMainUI() {
			setLayout(new CardLayout());
			TaquinGrid taquinGrid = new TaquinGrid(5,5);
			numberTaquin = new NumberTaquinGrid(taquinGrid);
			imageTaquinGrid = new ImageTaquinGrid(taquinGrid);

			//On ajoute l'ensemble des calques, un JPanel et son nom
			this.add(imageTaquinGrid, IMAGE_GRID);
			this.add(numberTaquin, NUMBER_GRID);
	}

	private void showTaquinGrid(String gridType) {
		CardLayout cl = (CardLayout)(getContentPane().getLayout());
		cl.show(getContentPane(), gridType);
	}



}