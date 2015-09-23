/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invasoresdoespaco;

// Importações necessárias para a classe
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/*
 *
 * AUTOR: Luana de Souza Bianchini
 * DATA: 06/03/2015
 * CLASSE: JOGADOR
 * OBJETIVO: CONSTRÓI O JOGADOR
 *
 */

// ESTENDE A CLASSE AOS ATRIBUTOS E MÉTODOS DA CLASSE DESENHO
// E POSSUI A IMPLEMENTAÇÃO DA CLASSE COMUNS
public class ClasseJogador extends ClasseDesenho implements ClasseComuns
{

    // DEFINE AS COORDENADAS (x,y) DE INICIO
    private final int INICIAL_X = 270;
    private final int INICIAL_Y = 280;

    // CRIA A STRING COM A URL DA IMAGEM DO JOGADOR
    private final String jogador = "jogador";

    // DEFINE A LARGURA
    private int width;

    public ClasseJogador ()
    {
        // CRIA UM NOVO ICONE DE IMAGEM
        ImageIcon jogador_ = new ImageIcon(jogador);
        // OBTEM A LARGURA DESSE ICONE
        width = jogador_.getImage().getWidth(null);

        // SETA A IMAGEM NA EXTENSÃO DA CLASSE DESENHO
        setImagem(jogador_.getImage());

        // SETA O PLANO (x,y) NA EXTENSÃO DA CLASSE DESENHO
        setX(INICIAL_X);
        setY(INICIAL_Y);
    }

    // DESLOCA A NAVE DO JOGADOR NA TELA
    public void atuar ()
    {
        // INCREMENTA NO X A DIREÇÃO RECEBIDA
        x += dx;

        // SE O X FOR MENOR QUE DOIS MANTEM ELE COMO 2
        if (x <= 2)
        { x = 2; }

        // SE O X FOR MAIOR QUE O LIMITE DA TELA, MANTEM ELE NO LIMITE DA TELA
        if (x >= LARGURA - 2*width)
        { x = LARGURA - 2*width; }
    }

    // CAPTURA O EVENTO CLICADO NO TECLADO
    public void keyPressed (KeyEvent e)
    {
        // RECEBE O CÓDIGO DA TECLA DIGITADO
        int key = e.getKeyCode();

        // SE A TECLA FOR PARA ESQUERDA DECREMENTA A DIREÇÃO
        if (key == KeyEvent.VK_LEFT)
        { dx = -2; }

        // SE A TECLA FOR PRA DIREITA INCREMENTA A DIREÇÃO
        if (key == KeyEvent.VK_RIGHT)
        { dx = 2; }
    }

    // CAPTURA O FIM DO CLIQUE NO TECLADO
    // QUANDO O USUÁRIO PARA DE CLICAR
    public void keyReleased (KeyEvent e)
    {
        // RECEBE O CÓDIGO DA TECLA DIGITADO
        int key = e.getKeyCode();

        // SE A TECLA FOR PARA ESQUERDA NÃO DESLOCA
        if (key == KeyEvent.VK_LEFT)
        { dx = 0; }

        // SE A TECLA FOR PARA DIREITA NÃO DESLOCA
        if (key == KeyEvent.VK_RIGHT)
        { dx = 0; }
    }
}