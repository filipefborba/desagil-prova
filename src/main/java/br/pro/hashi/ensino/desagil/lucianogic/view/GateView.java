package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;


public class GateView extends FixedPanel implements ItemListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private Image image;

	private JCheckBox[] inBoxes;
	private JCheckBox[] outBoxes;

	private Switch[] switches_in;
	private Switch[] switches_out;
	private Gate gate;


	public GateView(Gate gate) {
		super(205, 180);

		this.gate = gate;

		image = loadImage(gate.toString());

		int inSize = gate.getInSize();
		int outSize = gate.getOutSize();

		inBoxes = new JCheckBox[inSize];

		switches_in = new Switch[inSize];

		for(int i = 0; i < inSize; i++) {
			inBoxes[i] = new JCheckBox();

			inBoxes[i].addItemListener(this);

			switches_in[i] = new Switch();

			gate.connect(switches_in[i], i);
		}

		outBoxes = new JCheckBox[outSize];

		switches_out = new Switch[outSize];

		if(inSize == 1) {
			add(inBoxes[0], 0, 60, 20, 20);			
		}
		else {
			for(int i = 0; i < inSize; i++) {
				add(inBoxes[i], 0, (i + 1) * 40, 20, 20);			
			}			
		}
		
		for(int i = 0; i < outSize; i++) {
			outBoxes[i] = new JCheckBox();

			outBoxes[i].addItemListener(this);

			switches_out[i] = new Switch();

			gate.connect(switches_out[i], i);
		}
		
		if(outSize == 1) {
			add(outBoxes[0], 184, 60, 20, 20);	
		}
		else {
			for(int i = 0; i < outSize; i++) {
				add(outBoxes[i], 184, (i + 1) * 40, 20, 20);			
			}			
		}

		//outBoxes[i].setSelected(gate.read());
	}


	@Override
	public void itemStateChanged(ItemEvent event) {
		int i;
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == event.getSource()) {
				break;
			}
		}

		switches_in[i].setOn(inBoxes[i].isSelected());

		outBoxes[i].setSelected(gate.read());
	}


	// Necessario para carregar os arquivos de imagem.
	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}


	@Override
	public void paintComponent(Graphics g) {
		// Evita bugs visuais em alguns sistemas operacionais.
		super.paintComponent(g);

		g.drawImage(image, 10, 20, 184, 140, null);

		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
    }
}
