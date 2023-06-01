package controlador;

import modelo.Categoria;

import java.util.List;

public class CategoriaControlador {
    //Declaración de variables
    Controlador controlador;

    public CategoriaControlador(){
        controlador = new Controlador();
    }

    //----CRUD CLIENTE----
    //Metodo obtener categoria(s) - recibe los valores de los JText
    public List<Categoria> listarCategorias() {
        Categoria categoria = new Categoria();
        //Llama el metodo de listar categorias
        return categoria.listarCategorias(controlador.getConexion());
    }

    //Metodo obtener categoria(s) - recibe los valores de los JText
    public List<Categoria> buscarCategorias(Integer idCategoria, String nombre, String descripcion, Boolean estado){
        //Instacia la categoria con los respectivos valores
        Categoria categoria = new Categoria(idCategoria, nombre, descripcion, estado);
        //Llama el metodo de buscar categorias
        return categoria.buscarCategorias(categoria,controlador.getConexion());
    }
    //Metodo agregar categoria - recibe los valores de los JText
    public String crearCategoria(Integer idCategoria, String nombre, String descripcion, Boolean estado){
        //Instacia la categoria con los respectivos valores
        Categoria categoria = new Categoria(idCategoria, nombre, descripcion, estado);
        //Llama el metodo de crear categoria
        return categoria.agregarCategoria(categoria,controlador.getConexion());
    }
    //Metodo actualizar categoria - recibe los valores de los JText
    public String modificarCategoria(Integer idCategoria, String nombre, String descripcion, Boolean estado){
        //Instacia la categoria con los respectivos valores
        Categoria categoria = new Categoria(idCategoria, nombre, descripcion, estado);
        //Llama el metodo de actualizar categoria
        return categoria.actualizarCategoria(categoria,controlador.getConexion());
    }

}
