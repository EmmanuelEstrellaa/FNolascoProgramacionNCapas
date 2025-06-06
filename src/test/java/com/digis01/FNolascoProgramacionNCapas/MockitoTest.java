package com.digis01.FNolascoProgramacionNCapas;

import com.digis01.FNolascoProgramacionNCapas.DAO.ColoniaDAOImplementation;
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
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MockitoTest {

    @Mock
    EntityManager entityManager;

    @InjectMocks
    UsuarioDAOImplementation usuarioDAOImplementation;

    @InjectMocks
    RollDAOImplementation rollDAOImplementation;

    @InjectMocks
    PaisDAOImplementation paisDAOImplementation;

    @Test
    public void ADDUsuarioMockito() {
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

        Assertions.assertTrue(result.correct, "El result.correct es false");

        Mockito.verify(entityManager, Mockito.times(1)).persist(Mockito.any(com.digis01.FNolascoProgramacionNCapas.JPA.Usuario.class));
        Mockito.verify(entityManager, Mockito.times(1)).persist(Mockito.any(com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class));

    }

    @Test
    public void ADDdireccionMockito() {
        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
        usuarioDireccion.Direccion = new Direccion();

        usuarioDireccion.Direccion.setCalle("Santa Maria Rayon");
        usuarioDireccion.Direccion.setNumeroExterior("12");
        usuarioDireccion.Direccion.setNumeroInterior("10");

        usuarioDireccion.Direccion.Colonia = new Colonia();
        usuarioDireccion.Direccion.Colonia.setIdColonia(1);

        usuarioDireccion.Usuario = new Usuario();
        usuarioDireccion.Usuario.setIdUsuario(321);

        Result result = usuarioDAOImplementation.AddDireccionJPA(usuarioDireccion);

        Assertions.assertNotNull(result, "El objeto de result viene null");
        Assertions.assertNull(result.ex, "Hay un excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un msj de error");

        Assertions.assertTrue(result.correct, "El result.correct es false");

        Mockito.verify(entityManager, Mockito.times(1)).persist(Mockito.any(com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class));
    }

    @Test
    public void UpdateUsarioMockito() {
        Usuario Usuario = new Usuario();

        Usuario.setIdUsuario(221);
        Usuario.setNombre("Ivan");
        Usuario.setApellidoPaterno("Domingo");
        Usuario.setApellidoMaterno("Peralta");
        Usuario.setTelefono("5525896401");
        Usuario.setEmail("ivan@outlook.com");
        Usuario.setFechaNacimiento(new Date(20122002));
        Usuario.setUserName("Ivanson");
        Usuario.setPassword("1234");
        Usuario.setSexo("H");
        Usuario.setCelular("5569874201");
        Usuario.Roll = new Roll();
        Usuario.Roll.setIdRoll(2);

        com.digis01.FNolascoProgramacionNCapas.JPA.Usuario usuarioJPA = new com.digis01.FNolascoProgramacionNCapas.JPA.Usuario();

        Mockito.when(entityManager.find(
                Mockito.eq(com.digis01.FNolascoProgramacionNCapas.JPA.Usuario.class),
                Mockito.eq(Usuario.getIdUsuario())
        )).thenReturn(usuarioJPA);

        Result result = usuarioDAOImplementation.UsuarioUpdateJPA(Usuario);

        Assertions.assertNotNull(result, "El objeto del result viene null");
        Assertions.assertNull(result.ex, "Viene una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un mensaje de error");

        Assertions.assertTrue(result.correct, "El result.correct es false");

        Mockito.verify(entityManager, Mockito.times(1)).merge(Mockito.any(com.digis01.FNolascoProgramacionNCapas.JPA.Usuario.class));

    }

    @Test
    public void UpdateDireccionMockito() {
        UsuarioDireccion direccion = new UsuarioDireccion();
        direccion.Direccion = new Direccion();

        direccion.Direccion.setCalle("Santa Maria Rayon");
        direccion.Direccion.setNumeroExterior("10");
        direccion.Direccion.setNumeroInterior("21");

        direccion.Direccion.Colonia = new Colonia();
        direccion.Direccion.Colonia.setIdColonia(1);

        direccion.Usuario = new Usuario();
        direccion.Usuario.setIdUsuario(321);

        Result result = usuarioDAOImplementation.DieccionUpdateJPA(direccion);

        Assertions.assertNotNull(result, "El objeto de result viene null");
        Assertions.assertNull(result.ex, "Hay una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un msj de eror");

        Assertions.assertTrue(result.correct, "El result.correct es false");

        Mockito.verify(entityManager, Mockito.times(1)).merge(Mockito.any(com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class));

    }

    @Test
    public void DeleteUsuarioDireccion() {
        Result result = new Result();

        com.digis01.FNolascoProgramacionNCapas.JPA.Usuario usuario = new com.digis01.FNolascoProgramacionNCapas.JPA.Usuario();
        usuario.setIdUsuario(100);

        TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Direccion> queryDirecciones = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :IdUsuario", com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class)).thenReturn(queryDirecciones);
        queryDirecciones.setParameter("IdUsuario", usuario.getIdUsuario());

        List<com.digis01.FNolascoProgramacionNCapas.JPA.Direccion> direcciones = queryDirecciones.getResultList();

//        Mockito.when(entityManager.find(Mockito.eq(com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class),
//                Mockito.eq(usuario.getIdUsuario())
//        )).thenReturn();
    }

    @Test
    public void DeleteDireccionMockito() {
        Result result = new Result();

        com.digis01.FNolascoProgramacionNCapas.JPA.Direccion direccion = new com.digis01.FNolascoProgramacionNCapas.JPA.Direccion();

        direccion.setIdDireccion(121);

        Mockito.when(entityManager.find(Mockito.eq(com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class),
                Mockito.eq(direccion.getIdDireccion())
        )).thenReturn(direccion);

        result = usuarioDAOImplementation.DireccionDeleteJPA(121);

        Assertions.assertNotNull(result, "El objeto de result es false");
        Assertions.assertNull(result.ex, "Hay una excepcion");
        Assertions.assertNull(result.errorMessage, "Hahy un msj de error");

        Assertions.assertTrue(result.correct, "El result.correct viene false");

        Mockito.verify(entityManager, Mockito.atLeast(1)).find(Mockito.eq(com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class), Mockito.any(int.class));
        Mockito.verify(entityManager, Mockito.times(1)).remove(Mockito.eq(direccion));
    }

    @Test
    public void GetAllMockito() {

        com.digis01.FNolascoProgramacionNCapas.JPA.Usuario usuarioJPA = new com.digis01.FNolascoProgramacionNCapas.JPA.Usuario();
        usuarioJPA.setIdUsuario(1);
        usuarioJPA.setNombre("Emmanuel");
        usuarioJPA.setApellidoPaterno("Nolasco");

        usuarioJPA.Roll = new com.digis01.FNolascoProgramacionNCapas.JPA.Roll();
        usuarioJPA.Roll.setIdRoll(1);

        com.digis01.FNolascoProgramacionNCapas.JPA.Direccion direccionJPA = new com.digis01.FNolascoProgramacionNCapas.JPA.Direccion();
        direccionJPA.setIdDireccion(1);
        direccionJPA.setCalle("Santa Maria Rayon");

        direccionJPA.Colonia = new com.digis01.FNolascoProgramacionNCapas.JPA.Colonia();
        direccionJPA.Colonia.setIdColonia(1);

        TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Usuario> queryUsuario = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("FROM Usuario", com.digis01.FNolascoProgramacionNCapas.JPA.Usuario.class)).thenReturn(queryUsuario);

        TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Direccion> queryDireccion = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class)).thenReturn(queryDireccion);

        List<com.digis01.FNolascoProgramacionNCapas.JPA.Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuarioJPA);
        Mockito.when(queryUsuario.getResultList()).thenReturn(usuarios);

        List<com.digis01.FNolascoProgramacionNCapas.JPA.Direccion> direcciones = new ArrayList<>();
        direcciones.add(direccionJPA);
        Mockito.when(queryDireccion.getResultList()).thenReturn(direcciones);

        Result result = usuarioDAOImplementation.GetAllJPA();

        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.ex, "La excepción viene nula");
        Assertions.assertNull(result.errorMessage, "Viene nulo");

        Mockito.verify(entityManager,
                Mockito.times(1)).createQuery("FROM Usuario", com.digis01.FNolascoProgramacionNCapas.JPA.Usuario.class);
        Mockito.verify(entityManager,
                Mockito.times(1)).createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class);
        Mockito.verify(queryUsuario,
                Mockito.times(1)).getResultList();
        Mockito.verify(queryDireccion,
                Mockito.times(1)).getResultList();
    }

    @Test
    public void UserGetbyId() {

    }

    @Test
    public void GetAllRoll() {
        com.digis01.FNolascoProgramacionNCapas.JPA.Roll roll = new com.digis01.FNolascoProgramacionNCapas.JPA.Roll();
        roll.setIdRoll(1);
        roll.setNombre("Jefe");

        TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Roll> queryRoll = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("FROM Roll", com.digis01.FNolascoProgramacionNCapas.JPA.Roll.class)).thenReturn(queryRoll);

        List<com.digis01.FNolascoProgramacionNCapas.JPA.Roll> rolles = new ArrayList<>();
        rolles.add(roll);
        Mockito.when(queryRoll.getResultList()).thenReturn(rolles);

//        Mockito.when(queryRoll.getResultList()).thenReturn(rolles);
        Result result = rollDAOImplementation.GetAllJPA();

        Assertions.assertNotNull(result, "el objeto de result viene null");
        Assertions.assertNull(result.ex, "Viene una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un error de msj");

        Assertions.assertTrue(result.correct, "El result.correct es false");

        Mockito.verify(entityManager,
                Mockito.times(1)).createQuery("FROM Roll", com.digis01.FNolascoProgramacionNCapas.JPA.Roll.class);

        Mockito.verify(queryRoll, Mockito.times(1)).getResultList();
    }

    @Test
    public void GetAllPais() {
        com.digis01.FNolascoProgramacionNCapas.JPA.Pais pais = new com.digis01.FNolascoProgramacionNCapas.JPA.Pais();
        pais.setIdPais(1);
        pais.setNombre("Mexico");

        TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Pais> queryPais = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("FROM Pais", com.digis01.FNolascoProgramacionNCapas.JPA.Pais.class)).thenReturn(queryPais);

        List<com.digis01.FNolascoProgramacionNCapas.JPA.Pais> paises = new ArrayList<>();
        paises.add(pais);
        Mockito.when(queryPais.getResultList()).thenReturn(paises);

        Result result = paisDAOImplementation.GetAllJPA();

        Assertions.assertNotNull(result, "El objeto de result viene null");
        Assertions.assertNull(result.ex, "Hay una excepcion");
        Assertions.assertNull(result.errorMessage, "Hay un msj de error");

        Assertions.assertTrue(result.correct, "El result.correct viene null");
        
        Mockito.verify(entityManager,
                Mockito.times(1)).createQuery("FROM Pais", com.digis01.FNolascoProgramacionNCapas.JPA.Pais.class);
        
        Mockito.verify(queryPais, Mockito.times(1)).getResultList();

    }

}
