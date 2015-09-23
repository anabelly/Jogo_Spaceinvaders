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
 * DATA: 06/03/2015
 * CLASSE: ALIENIGINAS
 * OBJETIVO: CONTROLA OS VILÕES DO JOGO
 *
 */

// EXTENDE A CLASSE AOS ATRIBUTOS E MÉTODOS DA CLASSE DESENHO
public class ClasseAlienigenas extends ClasseDesenho
{

    // DEFINE UMA BOMBA DA SUB-CLASSE BOMBA
    private Bomba bomba;
    // CRIA A STRING COM A URL DA IMAGEM DO ALIEN
    private final String alvo = "alien.png";

    // MÉTODO CONSTRUTOR DA CLASSE
    public ClasseAlienigenas (int x, int y)
    {
        // DEFINE O PLANO (x,y)
        this.x = x;
        this.y = y;

        // DEFINE UMA NOVA BOMBA A PARTIR DO PLANO (x,y) DO ALIEN
        bomba = new Bomba(x, y);

        // CRIA UM NOVO ICONE DE IMAGEM
        ImageIcon alien = new ImageIcon(alvo);
        // SETA A IMAGEM NA EXTENSÃO DA CLASSE DESENHO
        setImagem(alien.getImage());
    }

    // PARA O ALIEN SE MOVIMENTAR NO EIXO (x,y)
    public void atuarX (int direcao)
    { this.x += direcao; }
    public void atuarY (int direcao)
    { this.y += direcao; }

    // OBTÉM A BOMBA DESSE ALIEN
    public Bomba getBomba ()
    { return bomba; }

    // CRIA UMA SUB-CLASSE DE BOMBA COM EXTENSÃO EM DESENHO
    public class Bomba extends ClasseDesenho
    {
        // DEFINE A URL DA IMAGEM DA BOMBA
        private final String bomba = "bomba.png";
        // DEFINE SE A BOMBA ESTÁ DESTRUÍDA OU NÃO
        private boolean destruido;

        // MÉTODO CONSTRUTOR DA CLASSE
        public Bomba (int x, int y)
        {
            // DEFINE O PLANO (x,y)
            this.x = x;
            this.y = y;

            // CRIA UM NOVO ICONE DE IMAGEM
            ImageIcon bomba_ = new ImageIcon(bomba);
            // SETA A IMAGEM NA EXTENSÃO DA CLASSE DESENHO
            setImagem(bomba_.getImage());
        }

        // VERIFICA SE ESTÁ DESTRUIDO OU NÃO
        public boolean isDestruido ()
        { return destruido; }

        // SETA SE ESTÁ DESTRUIDO OU NÃO
        public void setDestruido (boolean destruido)
        { this.destruido = destruido; }
    }

}
