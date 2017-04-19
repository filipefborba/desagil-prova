package br.pro.hashi.ensino.desagil.lucianogic.model;

public class FullGate extends Gate {
	private XorGate xorGateLeft;
	private XorGate xorGateRight;
	private AndGate andGateTop;
	private AndGate andGateBot;
	private OrGate orGate;

	public FullGate() {
		super(3, 2);
		name = "FULL";

		xorGateLeft = new XorGate();
		xorGateRight = new XorGate();
		andGateTop = new AndGate();
		andGateBot = new AndGate();
		orGate = new OrGate();
		
		
		xorGateRight.connect(xorGateLeft, 0);
		andGateTop.connect(xorGateLeft, 0);
		orGate.connect(andGateTop, 0);
		orGate.connect(andGateBot, 1);
		
	}

	@Override
	public boolean doRead(int index) {
		if(index == 0){
			return xorGateRight.read();
		}
		else{
			return orGate.read();
		}
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		switch(index) {
		case 0:
			xorGateLeft.connect(emitter, 0);
			andGateBot.connect(emitter, 0);
			break;
		case 1:
			xorGateLeft.connect(emitter, 1);
			andGateBot.connect(emitter, 1);
			break;
		case 2:
			xorGateRight.connect(emitter, 1);
			andGateTop.connect(emitter, 1);
			break;
		}
	}
}
