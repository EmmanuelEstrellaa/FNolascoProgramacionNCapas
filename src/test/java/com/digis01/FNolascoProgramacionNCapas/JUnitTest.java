package com.digis01.FNolascoProgramacionNCapas;

import com.digis01.FNolascoProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis01.FNolascoProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.digis01.FNolascoProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis01.FNolascoProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis01.FNolascoProgramacionNCapas.DAO.PaisDAOImplementation;
import com.digis01.FNolascoProgramacionNCapas.DAO.RollDAOImplementation;
import com.digis01.FNolascoProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01.FNolascoProgramacionNCapas.ML.Colonia;
import com.digis01.FNolascoProgramacionNCapas.ML.Direccion;
import com.digis01.FNolascoProgramacionNCapas.ML.Result;
import com.digis01.FNolascoProgramacionNCapas.ML.Roll;
import com.digis01.FNolascoProgramacionNCapas.ML.Usuario;
import com.digis01.FNolascoProgramacionNCapas.ML.UsuarioDireccion;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitTest {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;

    @Autowired
    private DireccionDAOImplementation direccionDAOImplementation;

    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;

    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;

    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;

    @Autowired
    private PaisDAOImplementation paisDAOImplementation;

    @Autowired
    private RollDAOImplementation rollDAOImplementation;

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

        result = usuarioDAOImplementation.UsuarioDireccionesById(361);

        Assertions.assertNotNull(result, "El objeto del result viene null");
        Assertions.assertNull(result.ex, "Hay una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un msj de error");
        Assertions.assertTrue(result.correct, "EL result.correct es false");
    }

    @Test
    public void UsuarioDireccionByIdTest() {
        Result result = new Result();
        result = usuarioDAOImplementation.UsuarioGetById(361);

        Assertions.assertNotNull(result, "El objeto de result viene null");
        Assertions.assertNull(result.ex, "Hay una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un msj de error");
        Assertions.assertTrue(result.correct, "El result.correct es false");

    }

    @Test
    public void DireccionByIdTest() {
        Result result = new Result();
        result = direccionDAOImplementation.DireccionById(221);

        Assertions.assertNotNull(result, "El objeto result viene null");
        Assertions.assertNotNull(result.object, "El object viene vacio");
        Assertions.assertNull(result.ex, "Hay una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un msj de error");

        Assertions.assertTrue(result.correct, "El result.correct es false");

    }

    @Test
    public void ColoniaByIdMunicipioTest() {
        Result result = new Result();
        result = coloniaDAOImplementation.ColoniaByIdMunicipio(1);

        Assertions.assertNotNull(result, "El objeto result viene null");
        Assertions.assertNotNull(result.objects, "El objects viene vacio");
        Assertions.assertNull(result.ex, "Hay una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un msj de excepcion");

        Assertions.assertTrue(result.correct, "El result.correct viene false");

    }

    @Test
    public void ColoniaByCPTest() {
        Result result = new Result();
        result = coloniaDAOImplementation.ColoniaByCP(54948);

        Assertions.assertNotNull(result, "El objeto de result viene null");
        Assertions.assertNotNull(result.objects, "El objects viene vacio");
        Assertions.assertNull(result.ex, "Hay una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un msj de errpr");

        Assertions.assertTrue(result.correct, "El result.correct viene null");

    }

    @Test
    public void MunicipioByIdEstadoTest() {
        Result result = new Result();
        result = municipioDAOImplementation.MunicipioByIdEstado(1);

        Assertions.assertNotNull(result, "El objeto de result viene null");
        Assertions.assertNotNull(result.objects, "El objects viene vacio");
        Assertions.assertNull(result.ex, "Hay una exccepcion");
        Assertions.assertNull(result.errorMessage, "Hay un msj de error");

        Assertions.assertTrue(result.correct, "El result.correct viene null");

    }

    @Test
    public void EstadoByIdPaisTest() {
        Result result = new Result();
        result = estadoDAOImplementation.EstadoByIdPais(1);

        Assertions.assertNotNull(result, "El objeto de result viene null");
        Assertions.assertNotNull(result.objects, "El objects viene vacio");
        Assertions.assertNull(result.ex, "Hay una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un msj de error");

        Assertions.assertTrue(result.correct, "El result.correct viene null");

    }

    @Test
    public void PaisGetAllTest() {
        Result result = new Result();
        result = paisDAOImplementation.GetAll();

        Assertions.assertNotNull(result, "El objeto result viene null");
        Assertions.assertNotNull(result.objects, "El objects viene vacio");
        Assertions.assertNull(result.ex, "Hay una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un msj de error");

        Assertions.assertTrue(result.correct, "El result.correct viene false");

    }

    @Test
    public void AddUsuarioTest() {
        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
        usuarioDireccion.Usuario = new Usuario();
        usuarioDireccion.Usuario.setNombre("Ivan");
        usuarioDireccion.Usuario.setApellidoPaterno("Domingo");
        usuarioDireccion.Usuario.setApellidoMaterno("Peralta");
        usuarioDireccion.Usuario.setTelefono("5525896401");
        usuarioDireccion.Usuario.setEmail("ivan@outlook.com");
        usuarioDireccion.Usuario.setFechaNacimiento(new Date(20122002));
        usuarioDireccion.Usuario.Roll = new Roll();
        usuarioDireccion.Usuario.Roll.setIdRoll(2);
        usuarioDireccion.Usuario.setUserName("Ivanson");
        usuarioDireccion.Usuario.setPassword("1234");
        usuarioDireccion.Usuario.setSexo("H");
        usuarioDireccion.Usuario.setCelular("5569874201");
        usuarioDireccion.Direccion = new Direccion();
        usuarioDireccion.Direccion.setCalle("Calle1");
        usuarioDireccion.Direccion.setNumeroExterior("1");
        usuarioDireccion.Direccion.setNumeroInterior("1");
        usuarioDireccion.Direccion.Colonia = new Colonia();
        usuarioDireccion.Direccion.Colonia.setIdColonia(1);

        Result result = usuarioDAOImplementation.AddJPA(usuarioDireccion);

        Assertions.assertNotNull(result, "El objeto del result viene null");
        Assertions.assertNull(result.ex, "Viene una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un mensaje de error");
        Assertions.assertNull(result.object, "Viene algo en object");
        Assertions.assertNull(result.objects, "Viene algo en objects");

        Assertions.assertTrue(result.correct, "El result.correct es false");

    }

    @Test
    public void UpdateUsuarioTest() {
        Usuario Usuario = new Usuario();

        Usuario.setNombre("Mario Ivan");
        Usuario.setApellidoPaterno("Domingo");
        Usuario.setApellidoMaterno("Peralta");
        Usuario.setTelefono("5525896401");
        Usuario.setEmail("ivan@outlook.com");
        Usuario.setFechaNacimiento(new Date(20122002));
        Usuario.Roll = new Roll();
        Usuario.Roll.setIdRoll(2);
        Usuario.setUserName("Ivanson");
        Usuario.setPassword("1234");
        Usuario.setSexo("H");
        Usuario.setCelular("5569874201");

        Result result = usuarioDAOImplementation.UsuarioUpdate(Usuario);

        Assertions.assertNotNull(result, "El objeto del result viene null");
        Assertions.assertNull(result.ex, "Viene una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un mensaje de error");

        Assertions.assertTrue(result.correct, "El result.correct es false");

    }

}
