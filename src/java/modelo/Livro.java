/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author dappo
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Livro.findAll", query = "SELECT l FROM Livro l"),
    @NamedQuery(name = "Livro.findFilter", query = "SELECT l FROM Livro l WHERE l.titulo like :filtro")
})
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String titulo;
    
    @ManyToOne
    private Autor autor;
    
    @ManyToOne
    private Genero genero;
    
    @ManyToOne
    private Classificacao classificacao;
    
    @ManyToOne
    private Editora editora;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datapublicacao;
    
    private int pagina;
    
    private double valor;
    
    private String sinopse;
    
    private String endFoto1;
    private String endFoto2;
    private String endFoto3;
    private String endFoto4;

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getEndFoto1() {
        return endFoto1;
    }

    public void setEndFoto1(String endFoto1) {
        this.endFoto1 = endFoto1;
    }

    public String getEndFoto2() {
        return endFoto2;
    }

    public void setEndFoto2(String endFoto2) {
        this.endFoto2 = endFoto2;
    }

    public String getEndFoto3() {
        return endFoto3;
    }

    public void setEndFoto3(String endFoto3) {
        this.endFoto3 = endFoto3;
    }

    public String getEndFoto4() {
        return endFoto4;
    }

    public void setEndFoto4(String endFoto4) {
        this.endFoto4 = endFoto4;
    }

    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Date getDatapublicacao() {
        return datapublicacao;
    }

    public void setDatapublicacao(Date datapublicacao) {
        this.datapublicacao = datapublicacao;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

   
}
