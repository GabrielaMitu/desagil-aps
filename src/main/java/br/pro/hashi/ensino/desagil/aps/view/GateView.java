package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//import br.pro.hashi.ensino.desagil.crossover.model.Calculator;


// Duas modificações em relação à versão da entrega anterior:
// (a) esta classe agora é subclasse de FixedPanel em vez
// de JPanel; e (b) esta classe agora implementa MouseListener,
// indicando que ela reage a eventos de interação com o mouse.
public class GateView extends FixedPanel implements ActionListener, MouseListener {
    private final Gate gate;

    // private final JTextField weightField;
    //private final JTextField radiusField;
    private final JCheckBox[] entradas;
    private final JCheckBox outputBox;
    private final Switch[] switches;
    // private final Image image;
    // Novos atributos necessários para esta versão da interface.
    private Color color;

    public GateView(Gate gate) {
        super();

        // Como subclasse de FixedPanel, esta classe agora
        // exige que uma largura e uma altura sejam fixadas.
        this.gate = gate;

        int inputSize = gate.getInputSize();
        entradas = new JCheckBox[inputSize];
        switches = new Switch[inputSize];

        for (int i = 0; i < inputSize; i++) {
            entradas[i] = new JCheckBox();
            switches[i] = new Switch();
            gate.connect(i, switches[i]);
        }

        outputBox = new JCheckBox();

        JLabel entrada = new JLabel("Entrada");
        JLabel saida = new JLabel("Saída");

        int x = 15;
        int y = 30;
        int w = 50;
        int h = 50;
        int incremento = 75;
        for (JCheckBox inputBox : entradas) {
            add(inputBox, x, y, w, h);
            y += incremento;

        }

        int x_out = 15;
        int y_out = 200;
        int w_out = 50;
        int h_out = 50;
        add(outputBox, x_out, y_out, w_out, h_out);


        for (JCheckBox inputbox : entradas) {
            inputbox.addActionListener(this);
        }
        outputBox.setEnabled(false);
        update();


        int xe = 5;
        int ye = 5;
        int we = 120;
        int he = 40;
        add(entrada, xe, ye, we, he);

        int xs = 5;
        int ys = 175;
        int ws = 120;
        int hs = 40;
        add(saida, xs, ys, ws, hs);


        // Toda componente Swing tem uma lista de observadores
        // que reagem quando algum evento de mouse acontece.
        // Usamos o método addMouseListener para adicionar a
        // própria componente, ou seja "this", nessa lista.
        // Só que addMouseListener espera receber um objeto
        // do tipo MouseListener como parâmetro. É por isso que
        // adicionamos o "implements MouseListener" lá em cima.
        addMouseListener(this);

    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (entradas[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        boolean result = gate.read();
        System.out.println(result);

        outputBox.setSelected(result);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();

        // Se o clique foi dentro do quadrado colorido...
        if (x >= 210 && x < 235 && y >= 311 && y < 336) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, color);

            // ...e chamamos repaint para atualizar a tela.
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de soltar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.

    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // sair do painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        //g.drawImage(image, 10, 80, 221, 221, this);

        // Desenha um quadrado cheio.
        g.setColor(color);
        g.fillRect(210, 311, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}
