/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invasoresdoespaco;

// Importação necessária para a classe
import javax.swing.ImageIcon;

/*
 *
 * AUTOR: Luana de Souza Bianchini
 * DATA: 09/06/2013
 * CLASSE: TIRO
 * OBJETIVO: REPRESENTA O TIRO DA NAVE
 *
 */

// EXTENDE A CLASSE AOS ATRIBUTOS E MÉTODOS DA CLASSE DESENHO
public class ClasseTiro extends ClasseDesenho
{

    // CRIA A STRING COM A URL DA IMAGEM DO TIRO
    private String tiro = "tiro.png";

    // DEFINE O TAMANHO, E O ESPAÇAMENTO
    private final int H_SPACE = 6;
    private final int V_SPACE = 1;

    // MÉTODO CONSTRUTOR VAZIO DA CLASSE, INSTÂNCIA UM TIRO SEM DEFINIR
    // SUA POSIÇÃO NO AMBIENTE
    public ClasseTiro () {}

    // MÉTODO CONSTRUTOR QUE RECEBE A POSIÇÃO DO TIRO
    public ClasseTiro (int x, int y)
    {
        // CRIA UM NOVO ICONE DE IMAGEM
        ImageIcon tiro_ = new ImageIcon(tiro);
        // SETA A IMAGEM NA EXTENSÃO DA CLASSE DESENHO
        setImagem(tiro_.getImage());

        // DEFINE O PLANO (x,y) COM BASE NO TAMANHO E ESPAÇAMENTO DO TIRO
        setX(x + H_SPACE);
        setY(y - V_SPACE);
    }

}
