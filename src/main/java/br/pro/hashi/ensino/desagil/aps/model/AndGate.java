package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate nandOne;
    private final NandGate nandTwo;


    public AndGate() {
        super("AND", 1);
        nandOne = new NandGate();
        nandTwo = new NandGate();
        nandTwo.connect(0, nandOne);
        nandTwo.connect(1, nandOne);
    }

    @Override
    public boolean read() {
        return nandTwo.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nandOne.connect(inputIndex, emitter);

    }
}

