package dh.backend.clinica;


import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.service.impl.OdontologoService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class OdontologoServiceTest {
//    static final Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
//    OdontologoService odontologoService = new OdontologoService(new DaoH2Odontologo());
//
//    @BeforeAll
//    static void crearTabla(){
//        Connection connection = null;
//        try{
//            Class.forName("org.h2.Driver");
//
//            connection.setAutoCommit(false);
//            logger.info("Se ha creado la tabla");
//            connection.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error(e.getMessage());
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                logger.error(e.getMessage());
//            }
//
//        }
//    }
//
//    @Test
//    @DisplayName("Probar que se devuelven todos los odontologos")
//    void caso1() {
//        List<Odontologo> odontologos;
//        odontologos = odontologoService.buscarTodos();
//        assertNotNull(odontologos);
//    }

}