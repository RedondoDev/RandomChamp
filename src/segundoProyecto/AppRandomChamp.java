package segundoProyecto;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AppRandomChamp {

	public static void main(String[] args) {

		MiMarco ventana = new MiMarco();

	}

}

class MiMarco extends JFrame {
	
	ImageIcon icono = new ImageIcon("D:/Eclipse workspace/RandomChamp/lolpng/iconito.png");

	public MiMarco() {

		this.setSize(400, 450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("RandomChamp");
		this.setIconImage(icono.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MiPanel fondo = new MiPanel();
		this.add(fondo);

		this.setVisible(true);

	}

}

class MiPanel extends JPanel {

	private BufferedImage fondo;

	public MiPanel() {
		this.setSize(400, 450);
		this.setLayout(null);
		try {
			fondo = ImageIO.read(new File("D:/Eclipse workspace/RandomChamp/lolpng/fondito.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		MiPanelFoto fotoResultado = new MiPanelFoto();
		MiPanelBotones panelBotones = new MiPanelBotones(fotoResultado);

		this.add(panelBotones);

		JLabel labelSeleccion = new JLabel("Elige tu rol");
		labelSeleccion.setFont(new Font("Courier new", Font.BOLD, 18));
		labelSeleccion.setBounds(130, 130, 150, 30);
		this.add(labelSeleccion);

		panelBotones.setLabelSeleccion(labelSeleccion);

		this.add(fotoResultado);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fondo, 0, 0, 382, 409, this);
	}
}

class MiPanelBotones extends JPanel implements ActionListener {

	Random rnd = new Random();
	private Timer timer;

	ImageIcon top = new ImageIcon("D:/Eclipse workspace/RandomChamp/lolpng/top.png");
	ImageIcon jungle = new ImageIcon("D:/Eclipse workspace/RandomChamp/lolpng/jungle.png");
	ImageIcon mid = new ImageIcon("D:/Eclipse workspace/RandomChamp/lolpng/mid.png");
	ImageIcon adc = new ImageIcon("D:/Eclipse workspace/RandomChamp/lolpng/adc.png");
	ImageIcon supp = new ImageIcon("D:/Eclipse workspace/RandomChamp/lolpng/supp.png");

	String seleccion = "";

	JButton botonTop, botonJungle, botonMid, botonAdc, botonSupp;

	private JLabel labelSeleccion;
	private MiPanelFoto panelFoto;

	public static int rand1 = -1;
	public static int rand2 = -1;
	public static int rand3 = -1;
	private int finalRand1, finalRand2, finalRand3;

	public MiPanelBotones(MiPanelFoto panelFoto) {
		this.panelFoto = panelFoto;

		panelFoto.actualizarImagenes(rand1, rand2, rand3);

		this.setBounds(40, 60, 300, 55);
		this.setLayout(new GridLayout(1, 5, 10, 0));
		this.setBackground(new Color(0, 0, 0, 0));

		botonTop = crearBoton(top);
		botonJungle = crearBoton(jungle);
		botonMid = crearBoton(mid);
		botonAdc = crearBoton(adc);
		botonSupp = crearBoton(supp);

		botonTop.setIcon(new ImageIcon(top.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		botonJungle.setIcon(new ImageIcon(jungle.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		botonMid.setIcon(new ImageIcon(mid.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		botonAdc.setIcon(new ImageIcon(adc.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		botonSupp.setIcon(new ImageIcon(supp.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

		botonTop.setCursor(new Cursor(Cursor.HAND_CURSOR));
		botonJungle.setCursor(new Cursor(Cursor.HAND_CURSOR));
		botonMid.setCursor(new Cursor(Cursor.HAND_CURSOR));
		botonAdc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		botonSupp.setCursor(new Cursor(Cursor.HAND_CURSOR));

		botonTop.addActionListener(this);
		botonJungle.addActionListener(this);
		botonMid.addActionListener(this);
		botonAdc.addActionListener(this);
		botonSupp.addActionListener(this);

		this.add(botonTop);
		this.add(botonJungle);
		this.add(botonMid);
		this.add(botonAdc);
		this.add(botonSupp);
	}

	public void setLabelSeleccion(JLabel labelSeleccion) {
		this.labelSeleccion = labelSeleccion;
	}

	private JButton crearBoton(Icon icono) {
		JButton boton = new JButton(icono);
		boton.setBorder(BorderFactory.createLineBorder(new Color(123, 123, 123).brighter(), 2, false));
		boton.setBorderPainted(true);
		boton.setBackground(new Color(255, 255, 255, 255));
		boton.setFocusable(false);

		return boton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonTop) {
			seleccion = "    Top";
			generarRandomFinal(0, 44);
		} else if (e.getSource() == botonJungle) {
			seleccion = "  Jungler";
			generarRandomFinal(77, 109);
		} else if (e.getSource() == botonMid) {
			seleccion = "    Mid";
			generarRandomFinal(44, 77);
		} else if (e.getSource() == botonAdc) {
			seleccion = "    Adc";
			generarRandomFinal(130, 150);
		} else if (e.getSource() == botonSupp) {
			seleccion = "   Supp";
			generarRandomFinal(109, 130);
		}

		labelSeleccion.setText(seleccion);
		efectoTragaperras();
	}

	private void generarRandomFinal(int min, int max) {
		do {
			finalRand1 = rnd.nextInt(max - min) + min;
			finalRand2 = rnd.nextInt(max - min) + min;
			finalRand3 = rnd.nextInt(max - min) + min;
		} while (finalRand1 == finalRand2 || finalRand1 == finalRand3 || finalRand2 == finalRand3);
	}

	private void efectoTragaperras() {
		if (timer != null && timer.isRunning()) {
			timer.stop();
		}

		timer = new Timer(100, new ActionListener() {
			private int count = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (count < 20) {
					botonTop.setEnabled(false);
					botonJungle.setEnabled(false);
					botonMid.setEnabled(false);
					botonAdc.setEnabled(false);
					botonSupp.setEnabled(false);
					rand1 = rnd.nextInt(150);
					rand2 = rnd.nextInt(150);
					rand3 = rnd.nextInt(150);
					panelFoto.actualizarImagenes(rand1, rand2, rand3);
					count++;
				} else {
					timer.stop();
					botonTop.setEnabled(true);
					botonJungle.setEnabled(true);
					botonMid.setEnabled(true);
					botonAdc.setEnabled(true);
					botonSupp.setEnabled(true);
					panelFoto.actualizarImagenes(finalRand1, finalRand2, finalRand3);
				}
			}
		});
		timer.start();
	}
}

class MiPanelFoto extends JPanel {

	Icon champ1, champ2, champ3;
	JLabel foto1, foto2, foto3;

	public MiPanelFoto() {
		this.setBounds(25, 240, 320, 100);
		this.setLayout(new GridLayout(1, 3, 10, 0));
		this.setBackground(new Color(255, 255, 255));

		foto1 = new JLabel();
		foto2 = new JLabel();
		foto3 = new JLabel();

		this.add(foto1);
		this.add(foto2);
		this.add(foto3);

	}

	public void actualizarImagenes(int rand1, int rand2, int rand3) {
		ImageIcon champ1 = new ImageIcon(new ImageIcon("D:/Eclipse workspace/RandomChamp/lolpng/" + rand1 + ".png")
				.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		ImageIcon champ2 = new ImageIcon(new ImageIcon("D:/Eclipse workspace/RandomChamp/lolpng/" + rand2 + ".png")
				.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		ImageIcon champ3 = new ImageIcon(new ImageIcon("D:/Eclipse workspace/RandomChamp/lolpng/" + rand3 + ".png")
				.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		foto1.setIcon(champ1);
		foto2.setIcon(champ2);
		foto3.setIcon(champ3);

		this.revalidate();
		this.repaint();
	}

}
