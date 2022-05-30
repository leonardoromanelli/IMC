package com;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


class GuiPrincipal extends JFrame{

    //variaveis
    private JTextField editaPeso;
    private JTextField editaAltura;
    private JButton botCalc;
    private JLabel lblPeso;
    private JLabel lblAltura;
    private JLabel lblResultado;


    public GuiPrincipal(){
        //chamando o método de configuração
        setConfig();
    }


    private void setConfig() {
        //configuração do JFrame
        this.setTitle("Calculadora de IMC");//título da tela
        this.setSize(500, 400);//tamanho do Jframe
        this.setLayout(new GridLayout(0, 2));//Criação da tabela
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Fechar a aplicação como padrão
        this.setLocationRelativeTo(null);//centralizar
        this.getContentPane().setBackground(new Color(51, 153, 255));//cor de fundo criada


        //instanciando os componentes
        editaPeso=new JTextField();
        editaAltura=new JTextField();
        botCalc=new JButton("Calcular");
        lblPeso=new JLabel("Peso em KG:");
        lblAltura=new JLabel("Altura em CM:");
        lblResultado=new JLabel("Resultado ");


        //adicionar ação ao botão calcular
        botCalc.addActionListener(new EventoCalculaIMC());

        //adicionando os componentes
        this.add(lblPeso);
        this.add(lblAltura);
        this.add(editaPeso);
        this.add(editaAltura);
        this.add(lblResultado);
        this.add(botCalc);
    }


    class EventoCalculaIMC implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                //converter string em double
                double peso=Double.parseDouble(editaPeso.getText());
                double altura=Double.parseDouble(editaAltura.getText());

                //Calculando o IMC - IMC = MASSA / ALTURA*ALTURA (Altura em M)
                double imc=peso/Math.pow(altura,2) * 10000; //Multiplicar por 10.000 para colocar altura em cm


                //Formatar saida com um decimal
                DecimalFormat df=new DecimalFormat("#0.00");

                String resultado="Seu IMC é: "+df.format(imc);

                //Comparar o valor e concatenar com o resultado
                if(imc < 17){
                    resultado+=" - Muito abaixo do peso";
                }
                else if(imc < 18.49){
                    resultado+=" - Abaixo do peso";
                }
                else if(imc < 24.99){
                    resultado+=" - Peso normal";
                }
                else if(imc < 29.99){
                    resultado+=" - Acima do peso";
                }
                else if(imc < 34.99){
                    resultado+=" - Obesidade I";
                }
                else if(imc < 39.99){
                    resultado+=" - Obesidade II (severa)";
                }
                else
                    resultado+=" - Obesidade III (mórbida)";

                //Colocando o texto no campo "lblResultado"
                lblResultado.setText(resultado);

            }catch (ArithmeticException ar) {
                JOptionPane.showMessageDialog(null, "Erro aritmético, causa: "+ar.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro desconhecido, causa: "+ex.getMessage());
            }
        }
    }

}

//classe de execução do sistema
public class AplicCalcIMC {
    public static void main(String[] args) {

        new GuiPrincipal().setVisible(true);
    }
}
