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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Autor;
import modelo.Classificacao;
import modelo.Editora;
import modelo.Genero;
import modelo.Livro;
import util.FormataData;

/**
 *
 * @author dappo
 */
@WebServlet(name = "LivroWS", urlPatterns = {"/admin/livro/LivroWS","/public/LivroWS"})
public class LivroWS extends HttpServlet {
   
    private Livro obj;
    private String pagina;
    private String acao;
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Livro> lista = null;
        acao = request.getParameter("acao");
        String id;
        LivroDAO dao;
        switch(String.valueOf(acao)){
            case "add":
                request.setAttribute("genero", this.listaGenero());
                request.setAttribute("classificacao", this.listaClassificacao());
                request.setAttribute("autor", this.listaAutor());
                request.setAttribute("editora", this.listaEditora());
                pagina = "add.jsp";
                break;
            case "del":
                id = request.getParameter("id");
                dao = new LivroDAO();
                pagina = "index.jsp";
                obj = dao.buscarPorChavePrimaria(Long.parseLong(id));
                Boolean deucerto = dao.excluir(obj);
                if(deucerto){   
                    request.setAttribute("lista", this.listaLivros());
                    request.setAttribute("msg", "Excluído com sucesso");
                }
                else{
                    request.setAttribute("msg", "Erro ao excluir");
                }
                break;
            case "edit":
                id = request.getParameter("id");
                dao = new LivroDAO();
                Livro obj = dao.buscarPorChavePrimaria(Long.parseLong(id));
                request.setAttribute("obj", obj);
                pagina = "edita.jsp";
                break;
            case "detalhe":
                dao = new LivroDAO();
                Livro livro = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("id")));
                request.setAttribute("obj", livro);
                request.setAttribute("genero", this.listaGenero());
                request.setAttribute("classificacao", this.listaClassificacao());
                request.setAttribute("autor", this.listaAutor());
                request.setAttribute("editora", this.listaEditora());
                pagina = "detalhe.jsp";
                break;
            case "listLivros":
                request.setAttribute("genero", this.listaGenero());
                request.setAttribute("classificacao", this.listaClassificacao());
                request.setAttribute("autor", this.listaAutor());
                request.setAttribute("editora", this.listaEditora()); 
                
                try {
                    if(request.getParameter("genero")!=null){
                        Genero genero;
                        GeneroDAO gdao = new GeneroDAO();
                        genero = gdao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("genero")));
                        lista = genero.getLivros();
                    }else if(request.getParameter("classificacao")!=null){
                        Classificacao classificacao;
                        ClassificacaoDAO cdao = new ClassificacaoDAO();
                        classificacao = cdao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("classificacao")));
                        lista = classificacao.getLivros();
                    }else if(request.getParameter("autor")!=null){
                        Autor autor;
                        AutorDAO adao = new AutorDAO();
                        autor = adao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("autor")));
                        lista = autor.getLivros();
                    }else if(request.getParameter("editora")!=null){
                        Editora editora;
                        EditoraDAO edao = new EditoraDAO();
                        editora = edao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("editora")));
                        lista = editora.getLivros();
                    }else{
                        lista = this.listaLivros();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LivroWS.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("lista", lista);
                pagina = "index.jsp";
                break;
            default:
                dao = new LivroDAO();
                if (request.getParameter("filtro") != null) {
                    try {
                        lista = dao.listar(request.getParameter("filtro"));
                    } catch (Exception ex) {
                        Logger.getLogger(LivroWS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    lista = this.listaLivros();
                }
                //pra onde deve ser redirecionada a página
                pagina = "index.jsp";
                //passar a listagem para a página
                request.setAttribute("lista", lista);
                break;
                
        }
        RequestDispatcher destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            GeneroDAO gdao = new GeneroDAO();
            ClassificacaoDAO cdao = new ClassificacaoDAO();
            AutorDAO adao = new AutorDAO();
            EditoraDAO edao = new EditoraDAO();
            LivroDAO dao;
            Genero genero;
            Classificacao classificacao;
            Autor autor;
            Editora editora;
            String msg;
            request.setCharacterEncoding("UTF-8");
            String titulo = request.getParameter("txtTitulo");
            Long id_autor = Long.parseLong(request.getParameter("txtAutor"));
            Long id_genero = Long.parseLong(request.getParameter("txtGenero"));
            Long id_classificacao = Long.parseLong(request.getParameter("txtClassificacao"));
            Long id_editora = Long.parseLong(request.getParameter("txtEditora"));
            Date datapublicacao = FormataData.formata(request.getParameter("txtData"));
            String endFoto1 = request.getParameter("txtFoto1");
            String endFoto2 = request.getParameter("txtFoto2");
            String endFoto3 = request.getParameter("txtFoto3");
            String endFoto4 = request.getParameter("txtFoto4");
            String sinopse = request.getParameter("sinopse");
            int paginas = Integer.parseInt(request.getParameter("txtPaginas"));
            double valor = Double.parseDouble(request.getParameter("txtValor"));
            //verificar campos obrigatórios
            if(request.getParameter("txtTitulo") == null){
                msg = "Campos obrigatórios não informados";
            }
            else{
                dao = new LivroDAO();
                obj = new Livro();
                genero = gdao.buscarPorChavePrimaria(id_genero);
                classificacao = cdao.buscarPorChavePrimaria(id_classificacao);
                autor = adao.buscarPorChavePrimaria(id_autor);
                editora = edao.buscarPorChavePrimaria(id_editora);
                
                //preencho o objeto com o que vem do post
                
                Boolean deucerto;
                
                //se veio com a chave primaria então tem q alterar
                if(request.getParameter("txtId")!= null){
                    obj = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("txtId")));
                    obj.setTitulo(titulo);
                    obj.setAutor(autor);
                    obj.setGenero(genero);
                    obj.setClassificacao(classificacao);
                    obj.setEditora(editora);
                    obj.setDatapublicacao(datapublicacao);
                    obj.setPagina(paginas);
                    obj.setValor(valor);
                    obj.setEndFoto1(endFoto1);
                    obj.setEndFoto2(endFoto2);
                    obj.setEndFoto3(endFoto3);
                    obj.setEndFoto4(endFoto4);
                    obj.setSinopse(sinopse);
                    deucerto = dao.alterar(obj);
                    pagina="edita.jsp";
                }
                else{
                    obj.setTitulo(titulo);
                    obj.setAutor(autor);
                    obj.setGenero(genero);
                    obj.setClassificacao(classificacao);
                    obj.setEditora(editora);
                    obj.setDatapublicacao(datapublicacao);
                    obj.setPagina(paginas);
                    obj.setValor(valor);
                    obj.setEndFoto1(endFoto1);
                    obj.setEndFoto2(endFoto2);
                    obj.setEndFoto3(endFoto3);
                    obj.setEndFoto4(endFoto4);
                    obj.setSinopse(sinopse);
                    deucerto = dao.incluir(obj);
                    pagina="add.jsp";   
                }
                if(deucerto){
                    msg = "Operação realizada com sucesso";    
                }
                else{
                    msg = "Erro ao realizar a operação";
                }
            }
            
            request.setAttribute("msg", msg);
            RequestDispatcher destino = request.getRequestDispatcher(pagina);
            destino.forward(request, response);
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
