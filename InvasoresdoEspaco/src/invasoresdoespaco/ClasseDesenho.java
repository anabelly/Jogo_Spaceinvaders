/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invasoresdoespaco;

// Importação necessária para a classe
import java.awt.Image;

/*
 *
 * AUTOR: Luana de Souza Bianchini
 * DATA: 06/03/2015
 * CLASSE: DESENHO
 * OBJETIVO: RESPONSÁVEL POR MONTAR DESENHOS DO JOGO
 *
 */

public class ClasseDesenho
{

    // DEFINIÇÃO DA VISÍBILIDADE DO OBJETO NA TELA
    private boolean visivel;
    // CRIAÇÃO DE UMA NOVA IMAGEM
    private Image imagem;

    // VALORES (x,y) DO PLANO CARTESIANO
    protected int x;
    protected int y;

    // DEFINIÇÃO DO STATUS DO OBJETO
    protected boolean morrendo;

    // DEFINIÇÃO DA DIREÇÃO DO OBJETO NA TELA
    protected int dx;

    // MÉTODO CONSTRUTOR DA CLASSE
    public ClasseDesenho ()
    {
        // DEFINE COMO VISÍVEL
        visivel = true;
    }

    // TORNA O DESENHO INVISÍVEL
    public void morrer ()
    { visivel = false; }

    // OBTEM SE O OBJETO ESTÁ VISÍVEL OU NÃO
    public boolean isVisivel ()
    { return visivel; }

    // SETA A VISIBILIDADE DO OBJETO
    public void setVisivel (boolean visivel)
    { this.visivel = visivel; }

    // OBTEM A IMAGEM DO OBJETO
    public Image getImagem ()
    { return imagem; }

    // SETA A IMAGEM DO OBJETO
    public void setImagem (Image imagem)
    { this.imagem = imagem; }

    // OBTEM OS VALORES DO PLANO (x,y)
    public int getX ()
    { return x; }
    public int getY ()
    { return y; }

    // SETA OS VALORES DO PLANO (x,y)
    public void setX (int x)
    { this.x = x; }
    public void setY (int y)
    { this.y = y; }

    // OBTEM SE O DESENHO ESTÁ OU NÃO MORRENDO
    public boolean isMorrendo ()
    { return morrendo; }

    // SETA SE O DESENHO ESTÁ OU NÃO MORRENDO
    public void setMorrendo (boolean morrendo)
    { this.morrendo = morrendo; }

}
