package com.digis01.FNolascoProgramacionNCapas;

import com.digis01.FNolascoProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01.FNolascoProgramacionNCapas.ML.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitTest {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;

    @Test
    public void GetAllJUnit() {
        Result result = new Result();
        result = usuarioDAOImplementation.GetAllJPA();

        Assertions.assertNotNull(result, "El objeto del result viene null");
        Assertions.assertNotNull(result.objects, "El result.objects viene null");
        Assertions.assertNull(result.ex, "Hay una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un msj de error");
        Assertions.assertTrue(result.correct, "El result.correct es false");

    }

    @Test
    public void GetByIdUsuarioTest() {
        Result result = new Result();

        result = usuarioDAOImplementation.UsuaDirByIdJPA(361);

//        Assertions.assertNotNull(result, "El objeto del result viene null");
//        Assertions.assertNull(result.ex, "Hay una excepcion");
//        Assertions.assertNull(result.errorMessage, "Hay un msj de error");
        Assertions.assertTrue(result.correct, "EL result.correct es false");
    }

    @Test
    public void UsuarioDireccionByIdTest() {
        Result result = new Result();
        result = usuarioDAOImplementation.UsuaDirByIdJPA(361);
        
        Assertions.assertNotNull(result, "El objeto de result viene null");
//        Assertions.assertNull(result.ex, "Hay una excepcion");
//        Assertions.assertNull(result.errorMessage, "Hay un msj de error");
        Assertions.assertTrue(result.correct, "El result.correct es false");
        

    }

}
