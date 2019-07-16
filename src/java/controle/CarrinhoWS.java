/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AutorDAO;
import dao.ClassificacaoDAO;
import dao.EditoraDAO;
import dao.GeneroDAO;
import dao.LivroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Autor;
import modelo.Carrinho;
import modelo.Classificacao;
import modelo.Editora;
import modelo.Genero;
import modelo.ItemVenda;
import modelo.Livro;

/**
 *
 * @author dappo
 */
@WebServlet(name = "CarrinhoWS", urlPatterns = {"/public/CarrinhoWS"})
public class CarrinhoWS extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LivroDAO dao = new LivroDAO();
        Livro livro = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("txtId")));
        Integer qtd = Integer.parseInt(request.getParameter("txtQtd"));
        ItemVenda obj = new ItemVenda();
        obj.setLivro(livro);
        obj.setQtd(qtd);
        obj.setValorItem(qtd*livro.getValor());
        Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
        if(carrinho!=null){
            //existe carrinho
            carrinho.getItensVenda().add(obj);
            carrinho.setValorTotal(carrinho.getValorTotal()+obj.getValorItem());
        }else{
            //não existe
            carrinho = new Carrinho();
            List<ItemVenda> itens = new ArrayList();
            itens.add(obj);
            carrinho.setItensVenda(itens);
            carrinho.setValorTotal(obj.getValorItem());
        }
        request.getSession().setAttribute("carrinho", carrinho);
        GeneroDAO gdao = new GeneroDAO();
        List<Genero> generos = gdao.listar();
        request.setAttribute("genero", generos);
        request.setAttribute("classificacao", this.listaClassificacao());
        request.setAttribute("autor", this.listaAutor());
        request.setAttribute("editora", this.listaEditora()); 
        request.setAttribute("obj", livro);
        request.setAttribute("msg", "Livro Adicionado ao carrinho!");
        String pagina = "detalhe.jsp";
        RequestDispatcher destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        //colocar o carrinho na sessão
        //chamar a pagina que quiser
    }

    private List<Genero> listaGenero() {
        GeneroDAO dao = new GeneroDAO();
        List<Genero> generos = dao.listar();
        dao.fecharConexao();
        return generos;
    }
   
    private List<Livro> listaLivros() {
        LivroDAO dao = new LivroDAO();
        List<Livro> livros = dao.listar();
        dao.fecharConexao();
        return livros;
    }
    private List<Classificacao> listaClassificacao() {
        ClassificacaoDAO dao = new ClassificacaoDAO();
        List<Classificacao> classificacao = dao.listar();
        dao.fecharConexao();
        return classificacao;
    }
    private List<Editora> listaEditora() {
        EditoraDAO dao = new EditoraDAO();
        List<Editora> editoras = dao.listar();
        dao.fecharConexao();
        return editoras;
    }
    private List<Autor> listaAutor() {
        AutorDAO dao = new AutorDAO();
        List<Autor> autores = dao.listar();
        dao.fecharConexao();
        return autores;
    }
    private Livro buscaPorPK(Long id){
        LivroDAO dao = new LivroDAO();
        Livro livro = dao.buscarPorChavePrimaria(id);
         dao.fecharConexao();
         return livro;
       
        
    }
    
}
