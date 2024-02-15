/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class Importador {

    private ArrayList<Transacoes> transacoes;
    File arquivo;
    Movimentos movimento = new Movimentos();

    public Importador(ArrayList<Transacoes> transacoes, File arquivo) {
        this.transacoes = transacoes;
        this.arquivo = arquivo;
    }

    public Importador() {
    }

    public ArrayList<Transacoes> getTransacoes() {
        return transacoes;
    }

    /**
     * Esse método le os dados do diretorio e atualiza o array com esses dados
     *
     * @param arquivo do tipo file que deve ser lido pelo metodo
     * @throws FileNotFoundException Exeção para diretório não encontrado
     *
     */
    public void carregarDados(File arquivo) throws FileNotFoundException {
        try (Scanner sc = new Scanner(arquivo, "UTF-8")) {

            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] dados = linha.split(";");
                Transacoes novaTransacao;

                if (!dados[4].equals("")) {
                    novaTransacao = new Receitas();
                } else {
                    novaTransacao = new Despesas();
                }

                if (novaTransacao instanceof Receitas) {
                    novaTransacao.setNome(dados[0]);
                    novaTransacao.setValor(Double.parseDouble(dados[1]));
                    novaTransacao.setData(dados[2]);
                    novaTransacao.setDescricao(dados[3]);
                    ((Receitas) novaTransacao).setTipoReceita(TpReceita.valueOf(dados[4]));
                } else {
                    novaTransacao.setNome(dados[0]);
                    novaTransacao.setValor(Double.parseDouble(dados[1]));
                    novaTransacao.setData(dados[2]);
                    novaTransacao.setDescricao(dados[3]);
                    ((Despesas) novaTransacao).setTipoDespesa(TpDespesa.valueOf(dados[5]));
                }

                movimento.incluirMovimentos(novaTransacao);
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Erro ao achar diretorio.(Método carregarDados)" + e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar dados do arquivo. (Método carregarDados)", e);
        }

    }

    /**
     * Salva as transações do objeto de movimento em um arquivo.
     *
     * Este método cria ou sobrescreve um arquivo no caminho especificado com
     * informações sobre as transações.
     *
     * @param caminhoArquivo O arquivo no qual as transações serão salvas.
     * @param movimento O objeto Movimentos que contém as transações a serem
     * salvas.
     * @throws IOException Se ocorrer um erro durante a operação de salvamento.
     */
    public void salvarTransacoes(File caminhoArquivo, Movimentos movimento) throws IOException {
        String dados = "Nome;Valor;Data;Descrição;TipoReceita;TipoDespesa\n";

        try {
            for (Transacoes t : movimento.getContas()) {
                if (t instanceof Receitas) {
                    dados += t.getNome() + ";" + t.getValor() + ";" + t.getData() + ";" + t.getDescricao() + ";" + ((Receitas) t).getTipoReceita() + ";\n";
                } else {
                    dados += t.getNome() + ";" + t.getValor() + ";" + t.getData() + ";" + t.getDescricao() + ";;" + ((Despesas) t).getTipoDespesa() + "\n";
                }

            }

            PrintWriter out = new PrintWriter(caminhoArquivo, "UTF-8");
            out.write(dados);
            out.close();
        } catch (IOException e) {
            throw new IOException("Erro ao salvar Transações");

        }
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public Movimentos getMovimento() {
        return movimento;
    }


    public void setMovimento(Movimentos movimento) {
        this.movimento = movimento;
    }

    public void setTransacoes(ArrayList<Transacoes> transacoes) {
        this.transacoes = transacoes;
    }

}
