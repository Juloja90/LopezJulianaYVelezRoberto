package dh.backend.clinica.dao;

import dh.backend.clinica.model.Turno;

import java.util.List;

public interface IDao <T>{
    T guardar(T t);
    T buscarPorId(Integer id);
    List<T> listaTodos();
    T modificar(T t);
    void eliminar(Integer id);
}
