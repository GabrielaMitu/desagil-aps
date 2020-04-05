package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    private final NandGate nandOne;
    private final NandGate nandTwo;
    private final NandGate nandTree;
    private final NandGate nandFour;


    public XorGate() {
        super("OR", 1);
        nandOne = new NandGate();
        nandTwo = new NandGate();
        nandTree = new NandGate();
        nandFour = new NandGate();
        nandFour.connect(1, nandTree);
        nandFour.connect(0, nandTwo);


    }

    @Override
    public boolean read() {
        return nandFour.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }


        if (inputIndex == 0) {
            nandOne.connect(0, emitter);
            nandTwo.connect(0, emitter);
            nandTree.connect(0, nandOne);


        } else {
            nandOne.connect(1, emitter);
            nandTwo.connect(1, nandOne);
            nandTree.connect(1, emitter);
        }
    }

}
