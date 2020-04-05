package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
    private final NandGate nandOne;
    private final NandGate nandTwo;
    private final NandGate nandTree;


    public OrGate() {
        super("OR", 1);
        nandOne = new NandGate();
        nandTwo = new NandGate();
        nandTree = new NandGate();
        nandTree.connect(0, nandOne);
        nandTree.connect(1, nandTwo);
    }

    @Override
    public boolean read() {
        return nandTree.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }


        if (inputIndex == 0) {
            nandOne.connect(0, emitter);
            //nandOne.connect(1, emitter);
            nandOne.connect(1, emitter);

        } else {

            nandTwo.connect(0, emitter);

            //nandOne.connect(1, emitter);
            nandTwo.connect(1, emitter);
        }

    }
}