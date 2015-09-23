/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invasoresdoespaco;

// Importações necessárias para a classe
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 *
 * AUTOR: Luana de Souza Bianchini
 * DATA: 06/03/2015
 * CLASSE: GRADE
 * OBJETIVO: AMBIENTE DO JOGO
 *
 */

// EXTENDE A CLASSE AOS ATRIBUTOS E MÉTODOS DA CLASSE JPANEL
// IMPLEMENTA A PROCESSO DE EXECUÇÃO E A CLASSE COMUNS
public class ClasseGrade extends JPanel implements Runnable, ClasseComuns
{

    // DEFINE A DIMENSÃO DO AMBIENTE
    private Dimension dimensão;

    // CRIA UMA ARRAY COM OS ALIENS DO JOGO
    private ArrayList aliens;
    // CRIA UM JOGADOR PARA O JOGO
    private ClasseJogador jogador;
    // CRIA UM TIRO PARA O JOGADOR
    private ClasseTiro tiro;

    // DEFINE AS POSIÇÕES DE (x,y) PARA O ALIEN
    private int alienX = 150;
    private int alienY = 5;

    // DEFINE A DIREÇÃO DO MOVIMENTO PADRÃO
    private int direção = -1;
    // DEFINE A QUANTIDADE DE MORTES DOS ALIENS
    private int mortes = 0;
    // DEFINE A QUANTIDADE DE VIDAS DO JOGADOR
    private int vidas = 3;

    // VALIDA QUE O JOGO ESTÁ EM EXECUÇÃO
    private boolean estaJogando = true;

    // DEFINE A IMAGEM PARA A EXPLOSÃO
    private final String explosao = "explosão";
    // DEFINE A IMAGEM PARA OS ALIENS
    private final String alienimg = "alien";

    // MENSAGEM DO JOGO
    private String mensagem = "FIM DE JOGO!";

    // THREAD PARA ANIMAR O JOGO
    private Thread animador;

    // MÉTODO CONSTRUTOR DA CLASSE
    public ClasseGrade ()
    {
        // POSSIBILIDADE DE INTERAÇÃO COM O TECLADO
        addKeyListener(new TAdapter());

        // DEFINE O FOCO PARA ESSE JPANEL
        setFocusable(true);
        // DEFINE SUA DIMENSÃO
        dimensão = new Dimension(LARGURA, ALTURA);
        // SETA A COR DO PLANO DE FUNDO
        setBackground(Color.BLACK);

        // INICIA O JOGO
        initJogo();
    }

    // CONTROLA AS NOTIFICAÇÕES DO THREAD
    @Override
    public void addNotify()
    {
        // DEFINE ESTE COMO ELE MESMO
        super.addNotify();
        // INICIA O JOGO
        initJogo();
    }

    // MÉTODO PARA INICIAR O JOGO
    public void initJogo ()
    {
        // CRIA UMA NOVA LISTA DE ALIENS
        aliens = new ArrayList();

        // CARREGA A IMAGEM DO ALIEN
        ImageIcon alien_ = new ImageIcon(alienimg);

        // MAPA DE ALIENS, DISPOSTOS EM 4 LINHAS X 6 COLUNAS
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                // CRIA UM NOVO ALIEN PASSANDO COMO PARÂMETOS SUAS POSIÇÕES NA TELA
                ClasseAlienigenas alien = new ClasseAlienigenas(alienX + 18*j, alienY + 18*i);
                // SETA A IMAGEM DO ALIEN CRIADO
                alien.setImagem(alien_.getImage());
                // ADICIONA O ALIEN NA ARRAY DO JOGO
                aliens.add(alien);
            }
        }

        // CRIA UM NOVO JOGADOR
        jogador = new ClasseJogador();
        // CRIA UM NOVO TIRO
        tiro = new ClasseTiro();

        // SE O THREAD ESTIVER NULL, OU O JOGO NÃO ESTIVER RODANDO
        if (animador == null || !estaJogando)
        {
            // INSTÂNCIA PARA O THREAD ESTA CLASSE
            animador = new Thread(this);
            // INICIA A EXECUÇÃO DO THREAD
            animador.start();
        }
        
    }

    // MÉTODO PARA DESENHAR ALIENS NA TELA
    public void desenharAliens (Graphics g)
    {
        // CRIA UM CURSOR DE POSICIONAMENTO NA ARRAY DOS ALIENS
        Iterator it = aliens.iterator();

        // ENQUANDO EXISTIR UM PRÓXIMO CURSOR, CAPTURA UM ALIEN DA ARRAY,
        // SE ESTE ALIEN ESTIVER VISÍVEL ENTÃO DESENHA ELE NA TELA, COM SUA
        // POSIÇÃO (x,y), SE ELE ESTIVER MORRENDO ENTÃO MATA O ALIEN NA TELA
        while (it.hasNext())
        {
            ClasseAlienigenas alien = (ClasseAlienigenas) it.next();

            if (alien.isVisivel())
            { g.drawImage(alien.getImagem(), alien.getX(), alien.getY(), this); }

            if (alien.isMorrendo()) 
            { alien.morrer(); }
        }
    }

    // MÉTODO PARA DESENHAR JOGADOR NA TELA
    public void desenharJogador (Graphics g)
    {
        // SE O JOGADOR ESTIVER VISÍVEL ENTÃO DESENHA ELE NA TELA, COM SUA
        // POSIÇÃO (x,y), SE ELE ESTIVER MORRENDO ENTÃO MATA O JOGADOR NA TELA
        // E FINALIZA O JOGO

        if (jogador.isVisivel())
        { g.drawImage(jogador.getImagem(), jogador.getX(), jogador.getY(), this); }

        if (jogador.isMorrendo())
        { jogador.morrer(); estaJogando = false; }
    }

    // MÉTODO PARA DESENHAR TIRO NA TELA
    public void desenharTiro (Graphics g)
    {
        // SE O TIRO ESTIVER VISÍVEL ENTÃO DESENHA ELE NA TELA, COM SUA
        // POSIÇÃO (x,y)
        if (tiro.isVisivel())
        { g.drawImage(tiro.getImagem(), tiro.getX(), tiro.getY(), this); }
    }

    // MÉTODO PARA DESENHAR BOMBA NA TELA
    public void desenharBomba (Graphics g)
    {
        // CRIA UM CURSOR DE POSICIONAMENTO NA ARRAY DOS ALIENS
        Iterator it = aliens.iterator();

        // ENQUANDO EXISTIR UM PRÓXIMO CURSOR, CAPTURA UM ALIEN DA ARRAY,
        // E OBTEM A BOMBA DESSE ALIEN, SE A BOMBA NÃO ESTÁ DESTRUIDA
        // ENTÃO DESENHA ELA NA TELA COM SUAS POSIÇÕES (x,y)
        while (it.hasNext())
        {
            ClasseAlienigenas alien = (ClasseAlienigenas) it.next();

            ClasseAlienigenas.Bomba bomba = alien.getBomba();

            if (!bomba.isDestruido()) 
            { g.drawImage(bomba.getImagem(), bomba.getX(), bomba.getY(), this); }
        }
    }

    // MÉTODO PARA DESENHAR A PONTUAÇÃO NA TELA
    public void desenharScore (Graphics g)
    {
        // DEFINE A STRING DE EXIBIÇÃO
        String score = "Pontuação: " + mortes;

        // CRIA UM ESTILO DE FONTE
        Font estilo = new Font("Consolas", Font.BOLD, 12);

        // SETA A COR DA FONTE
        g.setColor(Color.white);
        // SETA O ESTILO DE FONTE
        g.setFont(estilo);
        // DESENHA A STRING COM A POSIÇÃO (x,y)
        g.drawString(score, 10, 20);
    }

    // MÉTODO PARA DESENHAR VIDAS NA TELA
    public void desenharVidas (Graphics g)
    {
        // DEFINE A STRING DE EXIBIÇÃO
        String vida = "VIDAS";
        // CRIA UM ESTILO DE FONTE
        Font estilo = new Font("Consolas", Font.BOLD, 12);
        // CRIA A MÉTRICA DA FONTE
        FontMetrics metrica = this.getFontMetrics(estilo);

        // OBTÉM O TAMANHO DA STRING NA TELA
        int width = metrica.stringWidth(vida);
        // CALCUPA A DISTÂNCIA DA BORDA PARA POSICIONAR A STRING
        int distancia = (15 * vidas) + (5 * vidas) + 25 + width;

        // PARA CADA VIDA DO JOGADOR, DESENHA UMA IMAGEM DA VIDA,
        // ALTERANDO A POSIÇÃO COM BASE NOS CALCULOS PARA DEFINIR
        // A POSIÇÃO DE CADA UMA EM (x,y)
        for (int i = 0; i < vidas; i++)
        {
            g.drawImage(jogador.getImagem(), LARGURA - width, 10, this);
            width += JOGADOR_WIDTH + 5;
        }

        // SETA A COR DA FONTE
        g.setColor(Color.white);
        // SETA O ESTILO DE FONTE
        g.setFont(estilo);
        // DESENHA A STRING COM A POSIÇÃO (x,y)
        g.drawString(vida, LARGURA - distancia, 20);
    }

    // MÉTODO PARA DESENHAR NA TELA COM ATUALIZAÇÃO
    @Override
    public void paint (Graphics g)
    {
        // DEFINE O PAINT COMO ELE MESMO
        super.paint(g);

        // SETA UMA COR
        g.setColor(Color.black);
        // DESENHA UM RETÂNGULO DO TAMANHO DA TELA
        g.fillRect(0, 0, dimensão.width, dimensão.height);
        // SETA UMA COR
        g.setColor(Color.green);

        if (estaJogando)
        {
            // DESENHA UMA LINHA PARA REPRESENTAR A BASE
            g.drawLine(0, BASE, LARGURA, BASE);
            // DESENHA TODOS OBJETOS DO JOGO CHAMANDO OS MÉTODOS CRIADOS
            // ANTERIORMENTE
            desenharAliens(g);
            desenharJogador(g);
            desenharTiro(g);
            desenharBomba(g);
            desenharVidas(g);
            desenharScore(g);
        }

        // ATIVA A SINCRONIA
        Toolkit.getDefaultToolkit().sync();
        // PAUSA
        g.dispose();
    }

    // DEFINE O CICLO DE ANIMAÇÃO E EXECUÇÃO DO JOGO NA TELA
    public void cicloAnimacao()
    {
        // VERIFICA SE JÁ MORRERAM TODOS OS ALIENS, CASO ISSO TENHA ACONTECIDO
        // PARA O JOGO, E DEFINE A MENSAGEM
        if (mortes == NUMERO_DE_ALIENS)
        {
            estaJogando = false;
            mensagem = "VOCÊ VENCEU!";
        }

        // JOGADOR COMEÇA A OPERAR
        jogador.atuar();

        // CONTROLA O TIRO SE ESTE ESTIVER VISÍVEL
        if (tiro.isVisivel())
        {
            // CRIA UM CURSOR DE POSICIONAMENTO NA ARRAY DOS ALIENS
            Iterator it = aliens.iterator();

            // OBTEM AS POSIÇÕES (x,y) DO TIRO
            int tiroX = tiro.getX();
            int tiroY = tiro.getY();

            // ENQUANTO O EXISTIR UM PRÓXIMO CURSOR, ELE CAPTURA UM ALIEN
            // DA ARRAY, RESGATA SEU POSICIONAMENTO EM (x,y) VERIFICA SE
            // O ALIEN ESTÁ VISÍVEL E O TIRO TAMBÉM, SE ESTIVER VERIFICA SE
            // O TIRO ATINGIU O ALIEN CAPTURADO, CASO ISSO ACONTEÇA, DEFINE
            // UMA NOVA IMAGEM PARA O ALIEN, INFORMA QUE O ALIEN ESTÁ MORRENDO,
            // ACRESCENTA UMA MORTE NO JOGO, E TERMINA O TIRO
            while (it.hasNext())
            {
                ClasseAlienigenas alien = (ClasseAlienigenas) it.next();

                int alienX_ = alien.getX();
                int alienY_ = alien.getY();

                if (alien.isVisivel() && tiro.isVisivel())
                {
                    if (tiroX >= alienX_ &&
                        tiroX <= (alienX_ + ALIEN_WIDTH) &&
                        tiroY >= alienY_ &&
                        tiroY <= (alienY_ + ALIEN_HEIGHT))
                    {
                        ImageIcon explosao_ = new ImageIcon(explosao);
                        alien.setImagem(explosao_.getImage());
                        alien.setMorrendo(true);
                        mortes++;
                        tiro.morrer();
                    }
                }
            }

            // PERCORRE O TIRO NO EIXO Y PARA CIMA, CASO O TIRO SAIA DA TELA
            // TERMINA O TIRO, CASO CONTRÁRIO CONTINUA SUBINDO
            int y = tiro.getY();
            y -= 4;
            
            if (y < 0)
            { tiro.morrer(); }
            else
            { tiro.setY(y); }
        }

        // CONTROLE DE ALIENIGINAS
        // CRIA UM CURSOR DE POSICIONAMENTO NA ARRAY DOS ALIENS
        Iterator itAlien = aliens.iterator();

        // ENQUANTO O EXISTIR UM PRÓXIMO CURSOR
        while (itAlien.hasNext())
        {
            // CAPTURA UM ALIEN E OBTEM SEU POSICIONAMENTO EM X
            ClasseAlienigenas alien1 = (ClasseAlienigenas) itAlien.next();
            int x = alien1.getX();

            // SE A DIREÇÃO FOR PARA DIREITA E ULTRAPASSAR A BORDA
            // INVERTE A DIREÇÃO PARA ESQUERDA, CRIA UM NOVO CURSOR,
            // E ENQUANTO EXISTIREM ALIENS DESCE A POSIÇÃO EM Y
            if (x >= LARGURA - BORDA_DIREITA && direção != -1)
{
                direção = -1;

                Iterator itAlienAux = aliens.iterator();

                while (itAlienAux.hasNext())
                {
                    ClasseAlienigenas alien2 = (ClasseAlienigenas) itAlienAux.next();
                    alien2.setY(alien2.getY() + VAI_ABAIXO);
                }

            }

            // SE A DIREÇÃO FOR PARA ESQUERDA E ULTRAPASSAR A BORDA
            // INVERTE A DIREÇÃO PARA DIREITA, CRIA UM NOVO CURSOR,
            // E ENQUANTO EXISTIREM ALIENS DESCE A POSIÇÃO EM Y
            if (x <= BORDA_ESQUERDA && direção != 1)
            {
                direção = 1;

                Iterator itAlienAux = aliens.iterator();

                while (itAlienAux.hasNext())
                {
                    ClasseAlienigenas alien = (ClasseAlienigenas) itAlienAux.next();
                    alien.setY(alien.getY() + VAI_ABAIXO);
                }
            }
        }

        // CRIA UM CURSOR DE POSICIONAMENTO NA ARRAY DOS ALIENS
        Iterator itAliensAux2 = aliens.iterator();

        // ENQUANTO O EXISTIR UM PRÓXIMO CURSOR, CAPTURA UM ALIEN DA ARRAY
        // OBTEM SUA POSIÇÃO EM Y, SE ELE CHEGAR NA BASE, É FIM DE JOGO POR
        // INVASÃO DOS ALIENS. E TAMBÉM ATUA O ALIEN NA POSIÇÃO X COM BASE
        // NA DIREÇÃO
        while (itAliensAux2.hasNext())
        {
            ClasseAlienigenas alien = (ClasseAlienigenas) itAliensAux2.next();
            if (alien.isVisivel())
            {
                int y = alien.getY();

                if (y > BASE - ALIEN_HEIGHT)
                {
                    estaJogando = false;
                    mensagem = "INVASÃO!";
                }

                alien.atuarX(direção);
            }
        }

        // CONTROLE DE BOMBAS
        // CRIA UM CURSOR DE POSICIONAMENTO NA ARRAY DOS ALIENS PARA BOMBAS
        Iterator itBomba = aliens.iterator();
        // CRIA UM RANDOM
        Random random = new Random();

        // ENQUANTO O EXISTIR UM PRÓXIMO CURSOR
        while (itBomba.hasNext())
        {
            // ESTABELECE UM VALOR ALEATÓRIO PARA ATIRAR A BOMBA
            int tiro_ = random.nextInt(15);

            // CAPTURA UM ALIEN DA ARRAY
            ClasseAlienigenas alien = (ClasseAlienigenas) itBomba.next();
            // CAPTURA A BOMBA PARA ESTE ALIEN
            ClasseAlienigenas.Bomba bomba_ = alien.getBomba();

            // SE O TIRO FOR IGUAL A CHANCES DE ACERTAR, E O ALIEN ESTIVER VISÍVEL
            // E A ULTIMA BOMBA ESTIVER DESTRUIDA, ENTÃO DISPARA OUTRA BOMBA, NA
            // POSIÇÃO (x,y) DO ALIEN
            if (tiro_ == CHANCES && alien.isVisivel() && bomba_.isDestruido())
            {
                bomba_.setDestruido(false);
                bomba_.setX(alien.getX());
                bomba_.setY(alien.getY());
            }

            // OBTEM AS POSIÇÕES DA BOMBA
            int bombaX = bomba_.getX();
            int bombaY = bomba_.getY();

            // OBTEM AS POSIÇÕES DO JOGADOR
            int jogadorX = jogador.getX();
            int jogadorY = jogador.getY();

            // SE O JOGADOR ESTIVER VISÍVEL E A BOMBA NÃO ESTIVER DESTRUIDA,
            // ENTÃO VERIFICA SE A BOMBA ATINGIU A POSIÇÃO DO JOGADOR EM ALGUM
            // PONTO (x,y), SE FOR VÁLIDO, VERIFICA SE A QUANTIDADE DE VIDAS É 1
            // SE FOR, DEFINE COMO MORTE PARA O JOGADOR E DESTRUIÇÃO PARA BOMBA,
            // CASO CONTRÁRIO DESTRÓI A BOMBA, E TIRA UMA VIDA DO JOGADOR
            if (jogador.isVisivel() && !bomba_.isDestruido())
            {
                if (bombaX >= (jogadorX) &&
                    bombaX <= (jogadorX + JOGADOR_WIDTH) &&
                    bombaY >= (jogadorY) &&
                    bombaY <= (jogadorY + JOGADOR_HEIGHT))
                {
                    if (vidas == 1)
                    {
                        ImageIcon explosao_ = new ImageIcon(explosao);

                        jogador.setImagem(explosao_.getImage());
                        jogador.setMorrendo(true);
                        
                        bomba_.setDestruido(true);
                    }
                    else
                    {
                        bomba_.setDestruido(true);
                        vidas--;
                    }
                }
            }

            // SE A BOMBA NÃO ESTIVER DESTRUIDA DESCE ELA NO PONTO Y
            // SE ELA ATINGIR SOMENTE A BASE, SETA ELA COMO DESTRUIDA
            if (!bomba_.isDestruido())
            {
                bomba_.setY(bomba_.getY() + 1);

                if (bomba_.getY() >= BASE - BOMBA_HEIGHT)
                { bomba_.setDestruido(true); }
            }
        }
    }

    // MÉTODO DE EXECUÇÃO DA CLASSE
    public void run ()
    {
        // DEFINE OS CONTROLES DE TEMPO
        long antesTempo, tempoDiferente, sleep;

        // OBTEM O TEMPO ATUAL
        antesTempo = System.currentTimeMillis();

        // ENQUANTO O JOGO ESTIVER EM OPERAÇÃO
        while (estaJogando)
        {
            // REDESENHA A TELA
            repaint();

            // OPERA AS ANIMAÇÕES
            cicloAnimacao();

            // DEFINE UM O TEMPO ATUAL EM RELAÇÃO AO ANTERIOR
            tempoDiferente = System.currentTimeMillis() - antesTempo;
            // APLICA UM DELAY
            sleep = DELAY - tempoDiferente;

            // NÃO DEIXA ELE SEM DORMIR
            if (sleep < 0)
            { sleep = 2; }

            try
            {
                // TENTA FAZER O THREAD DORMIR
                Thread.sleep(sleep);
            }
            catch (InterruptedException e)
            {
                System.out.println("Erro de interrupção!");
            }

            // DEFINE O NOVO TEMPO
            antesTempo = System.currentTimeMillis();
        }

        // MOSTRA A MENSAGEM DE FIM DE JOGO
        FimDeJogo();
    }

    // MÉTODO PARA DESENHAR A MENSAGEM NA TELA
    public void FimDeJogo ()
    {
        // OBTEM OS GRÁFICOS DA TELA
        Graphics g = this.getGraphics();

        // SETA UMA COR
        g.setColor(Color.black);
        // CRIA UM RETANGULO
        g.fillRect(0, 0, LARGURA, ALTURA);

        // SETA A COR
        g.setColor(new Color(0, 32, 48));
        // DEFINE UM NOVO RETANGULO
        g.fillRect(50, LARGURA/2 - 30, LARGURA-100, 50);
        // SETA A COR
        g.setColor(Color.white);
        // DEFINE UM NOVO RETANGULO
        g.drawRect(50, LARGURA/2 - 30, LARGURA-100, 50);

        // DEFINE O ESTILO DA FONTE
        Font estilo = new Font("Consolas", Font.BOLD, 14);
        // DEFINE A MÉTRICA DA FONTE
        FontMetrics metrica = this.getFontMetrics(estilo);

        // SETA UMA COR
        g.setColor(Color.white);
        // SETA UM ESTILO DA FONTE
        g.setFont(estilo);
        // DESENHA A MENSAGEM NA TELA
        g.drawString(mensagem, (LARGURA - metrica.stringWidth(mensagem))/2, LARGURA/2);
    }

    // CRIA UMA CLASSE DE CAPTURA DO TECLADOR
    private class TAdapter extends KeyAdapter {

        // DISPARA O MÉTODO DE SOLTA DE TECLA DO JOGADOR
        @Override
        public void keyReleased(KeyEvent e)
        {
            jogador.keyReleased(e);
        }

        // DEFINE O MÉTODO DE TECLA PRESSIONADA DO JOGADOR
        @Override
        public void keyPressed(KeyEvent e)
        {
            // O JOGADOR SE MOVIMENTA
            jogador.keyPressed(e);

            // CAPTURA SUA POSIÇÃO (x,y)
            int x = jogador.getX();
            int y = jogador.getY();

            // SE ESTIVER JOGANDO
            if (estaJogando)
            {

                // OBTEM A TECLA PRESSIONADA
                int key = e.getKeyCode();

                // SE A TECLA FOR Z E O TIRO DO JOGADOR NÃO ESTIVER VISÍVEL
                // DISPARA OUTRO TIRO NA TELA
                if (key == KeyEvent.VK_Z)
                {
                    if (!tiro.isVisivel())
                    { tiro = new ClasseTiro(x, y); }
                }
            }

        }
    }
}
