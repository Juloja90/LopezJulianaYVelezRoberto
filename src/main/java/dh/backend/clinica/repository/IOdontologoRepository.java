package dh.backend.clinica.repository;

import dh.backend.clinica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {
    List<Odontologo> findByApellidoAndNombre(String apellido, String nombre);

    @Query("Select o from Odontologo o where LOWER(o.apellido) LIKE LOWER(CONCAT('%',:parteApellido,'%'))")
    List<Odontologo> buscarPorParteApellido(String parteApellido);

    @Query("Select o from Odontologo o where LOWER(o.nombre) LIKE LOWER(CONCAT('%',:parteNombre,'%'))")
    List<Odontologo> buscarPorParteNombre(String parteNombre);
}