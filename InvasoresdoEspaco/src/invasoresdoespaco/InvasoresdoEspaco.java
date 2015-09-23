/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invasoresdoespaco;

// Importação necessária para a classe
import javax.swing.JFrame;

/*
 *
 * AUTOR: Luana de Souza Bianchini
 * DATA: 06/03/2015
 * CLASSE: INVASORESDOESPACO
 * OBJETIVO: TELA DO JOGO
 *
 */

public class InvasoresdoEspaco extends JFrame implements ClasseComuns
{

    // MÉTODO CONTRUTOR DA CLASSE
    public InvasoresdoEspaco ()
    {
        // CRIA UMA NOVA GRADE NA TELA
        add(new ClasseGrade());

        // DEFINE O TITULO
        setTitle("Invasores do Espaço");
        // INFORMA O MÉTODO DE SAÍDA
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // SETA O TAMANHO
        setSize(LARGURA, ALTURA);
        // SETA A LOCALIZAÇÃO
        setLocationRelativeTo(null);
        // SETA A VISIBILIDADE
        setVisible(true);
        // SETA SE É REDIMENSIONAVEL
        setResizable(false);
    }

    // MÉTODO PRINCIPAL
    public static void main(String[] args)
    {
        // INSTÂNCIA UM NOVO JOGO
        new InvasoresdoEspaco();
    }

}

