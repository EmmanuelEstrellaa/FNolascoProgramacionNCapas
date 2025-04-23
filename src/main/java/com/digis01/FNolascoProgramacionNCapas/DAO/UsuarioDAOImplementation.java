package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.ML.Colonia;
import com.digis01.FNolascoProgramacionNCapas.ML.Result;
import com.digis01.FNolascoProgramacionNCapas.ML.Roll;
import com.digis01.FNolascoProgramacionNCapas.ML.Usuario;
import com.digis01.FNolascoProgramacionNCapas.ML.Direccion;
import com.digis01.FNolascoProgramacionNCapas.ML.Estado;
import com.digis01.FNolascoProgramacionNCapas.ML.Municipio;
import com.digis01.FNolascoProgramacionNCapas.ML.Pais;
import com.digis01.FNolascoProgramacionNCapas.ML.UsuarioDireccion;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL UsuarioGetAll(?)}",
                    (CallableStatementCallback<Integer>) callableStatement -> {

                        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                        callableStatement.execute();
                        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

                        result.objects = new ArrayList<>();
                        while (resultSet.next()) {

                            int idUsuario = resultSet.getInt("IdUsuario");
                            if (!result.objects.isEmpty() && idUsuario == ((UsuarioDireccion) (result.objects.get(result.objects.size() - 1))).Usuario.getIdUsuario()) {
                                Direccion direccion = new Direccion();
                                direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                                direccion.setCalle(resultSet.getString("Calle"));
                                direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                                direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                                direccion.Colonia = new Colonia();
                                direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                                direccion.Colonia.setNombre(resultSet.getNString("NombreColonia"));
                                direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                                direccion.Colonia.Municipio = new Municipio();
                                direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                                direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));
                                direccion.Colonia.Municipio.Estado = new Estado();
                                direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                                direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));
                                direccion.Colonia.Municipio.Estado.Pais = new Pais();
                                direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                                direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));
                                ((UsuarioDireccion) (result.objects.get(result.objects.size() - 1))).Direcciones.add(direccion);
                            } else {
                                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                                usuarioDireccion.Usuario = new Usuario();

                                usuarioDireccion.Usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                                usuarioDireccion.Usuario.setUserName(resultSet.getString("UserName"));
                                usuarioDireccion.Usuario.setNombre(resultSet.getString("NombreUsuario"));
                                usuarioDireccion.Usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                                usuarioDireccion.Usuario.setEmail(resultSet.getString("Email"));
                                usuarioDireccion.Usuario.setSexo(resultSet.getString("Sexo"));
                                usuarioDireccion.Usuario.setTelefono(resultSet.getString("Telefono"));
                                usuarioDireccion.Usuario.setCelular(resultSet.getString("Celular"));
                                usuarioDireccion.Usuario.setCurp(resultSet.getString("Curp"));
                                usuarioDireccion.Usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                                usuarioDireccion.Usuario.setPassword(resultSet.getString("Password"));
                                usuarioDireccion.Usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                                usuarioDireccion.Usuario.setImagen(resultSet.getString("Imagen"));
                                usuarioDireccion.Usuario.Roll = new Roll();
                                usuarioDireccion.Usuario.Roll.setIdRoll(resultSet.getInt("IdRoll"));
                                usuarioDireccion.Usuario.Roll.setNombre(resultSet.getString("NombreRoll"));
                                usuarioDireccion.Direcciones = new ArrayList<>();
                                Direccion direccion = new Direccion();
                                direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                                direccion.setCalle(resultSet.getString("Calle"));
                                direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                                direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                                direccion.Colonia = new Colonia();
                                direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                                direccion.Colonia.setNombre(resultSet.getNString("NombreColonia"));
                                direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                                direccion.Colonia.Municipio = new Municipio();
                                direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                                direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));
                                direccion.Colonia.Municipio.Estado = new Estado();
                                direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                                direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));
                                direccion.Colonia.Municipio.Estado.Pais = new Pais();
                                direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                                direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));
                                usuarioDireccion.Direcciones.add(direccion);
                                result.objects.add(usuarioDireccion);
                            }
                        }
                        result.correct = true;
                        return 1;
                    });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.objects = null;
        }

        return result;
    }

    @Override
    public Result Add(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL UsuarioDireccionesAdd(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                    (CallableStatementCallback<Integer>) callableStatement -> {

                        callableStatement.setString(1, usuarioDireccion.Usuario.getUserName());
                        callableStatement.setString(2, usuarioDireccion.Usuario.getNombre());
                        callableStatement.setString(3, usuarioDireccion.Usuario.getApellidoPaterno());
                        callableStatement.setString(4, usuarioDireccion.Usuario.getEmail());
                        callableStatement.setString(5, usuarioDireccion.Usuario.getSexo());
                        callableStatement.setString(6, usuarioDireccion.Usuario.getTelefono());
                        callableStatement.setString(7, usuarioDireccion.Usuario.getCelular());
                        callableStatement.setString(8, usuarioDireccion.Usuario.getCurp());
                        callableStatement.setString(9, usuarioDireccion.Usuario.getApellidoMaterno());
                        callableStatement.setString(10, usuarioDireccion.Usuario.getPassword());
                        callableStatement.setDate(11, new java.sql.Date(usuarioDireccion.Usuario.getFechaNacimiento().getTime()));
                        callableStatement.setInt(12, usuarioDireccion.Usuario.Roll.getIdRoll());
                        callableStatement.setString(13, usuarioDireccion.Usuario.getImagen());
                        callableStatement.setString(14, usuarioDireccion.Direccion.getCalle());
                        callableStatement.setString(15, usuarioDireccion.Direccion.getNumeroInterior());
                        callableStatement.setString(16, usuarioDireccion.Direccion.getNumeroExterior());
                        callableStatement.setInt(17, usuarioDireccion.Direccion.Colonia.getIdColonia());

                        int rowAffected = callableStatement.executeUpdate();

                        result.correct = rowAffected > 0 ? true : false; //Operador ternario, Funciona como if, Si : No

                        return 1;

                    });
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result UsuarioDireccionesById(int IdUsuario) {
        Result result = new Result();

        try {

            jdbcTemplate.execute("Call UsuariosDireccionById (?,?,?)", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                callableStatement.setInt(3, IdUsuario);

                callableStatement.execute();

                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();

                ResultSet resultSetUsuario = (ResultSet) callableStatement.getObject(1);

                if (resultSetUsuario.next()) {
                    usuarioDireccion.Usuario = new Usuario();
                    usuarioDireccion.Usuario.setIdUsuario(resultSetUsuario.getInt("IdUsuario"));
                    usuarioDireccion.Usuario.setUserName(resultSetUsuario.getString("UserName"));
                    usuarioDireccion.Usuario.setNombre(resultSetUsuario.getString("Nombre"));
                    usuarioDireccion.Usuario.setApellidoPaterno(resultSetUsuario.getString("ApellidoPaterno"));
                    usuarioDireccion.Usuario.setEmail(resultSetUsuario.getString("Email"));
                    usuarioDireccion.Usuario.setSexo(resultSetUsuario.getString("Sexo"));
                    usuarioDireccion.Usuario.setTelefono(resultSetUsuario.getString("Telefono"));
                    usuarioDireccion.Usuario.setCelular(resultSetUsuario.getString("Celular"));
                    usuarioDireccion.Usuario.setCurp(resultSetUsuario.getString("Curp"));
                    usuarioDireccion.Usuario.setApellidoMaterno(resultSetUsuario.getString("ApellidoMaterno"));
                    usuarioDireccion.Usuario.setPassword(resultSetUsuario.getString("Password")); //LO tienes como Passowrd
                    usuarioDireccion.Usuario.setFechaNacimiento(resultSetUsuario.getDate("FechaNacimiento"));
                    usuarioDireccion.Usuario.setImagen(resultSetUsuario.getString("Imagen"));

                }
                ResultSet resultSetDireccion = (ResultSet) callableStatement.getObject(2);

                usuarioDireccion.Direcciones = new ArrayList();

                while (resultSetDireccion.next()) {

                    Direccion direccion = new Direccion();

                    direccion.setIdDireccion(resultSetDireccion.getInt("IdDireccion"));
                    direccion.setCalle(resultSetDireccion.getString("Calle"));
                    direccion.setNumeroInterior(resultSetDireccion.getString("NumeroInterior"));
                    direccion.setNumeroExterior(resultSetDireccion.getString("NumeroExterior"));
                    direccion.Colonia = new Colonia();
                    direccion.Colonia.setIdColonia(resultSetDireccion.getInt("IdColonia"));
                    direccion.Colonia.setNombre(resultSetDireccion.getString("NombreColonia"));

                    usuarioDireccion.Direcciones.add(direccion);
                }

                result.object = usuarioDireccion;
                return 1;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result UsuarioGetById(int IdUsuario) {
        Result result = new Result();

        try {

            jdbcTemplate.execute("CALL UsuarioGetById(?,?)", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.setInt(1, IdUsuario);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

                if (resultSet.next()) {
                    UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                    usuarioDireccion.Usuario = new Usuario();
                    usuarioDireccion.Usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                    usuarioDireccion.Usuario.setUserName(resultSet.getString("Username"));
                    usuarioDireccion.Usuario.setNombre(resultSet.getString("Nombre"));
                    usuarioDireccion.Usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                    usuarioDireccion.Usuario.setEmail(resultSet.getString("Email"));
                    usuarioDireccion.Usuario.setSexo(resultSet.getString("Sexo"));
                    usuarioDireccion.Usuario.setTelefono(resultSet.getString("Telefono"));
                    usuarioDireccion.Usuario.setCelular(resultSet.getString("Celular"));
                    usuarioDireccion.Usuario.setCurp(resultSet.getString("Curp"));
                    usuarioDireccion.Usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                    usuarioDireccion.Usuario.setPassword(resultSet.getString("Password"));
                    usuarioDireccion.Usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                    usuarioDireccion.Usuario.setImagen(resultSet.getString("Imagen"));                    
                    usuarioDireccion.Usuario.Roll = new Roll();
                    usuarioDireccion.Usuario.Roll.setIdRoll(resultSet.getInt("IdRoll"));
                    usuarioDireccion.Usuario.Roll.setNombre(resultSet.getString("NombreRoll"));

                    result.object = usuarioDireccion;
                }

                result.correct = false;
                return 1;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;

    }

    @Override
    public Result UsuarioUpdate(Usuario usuario) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("Call UsuarioUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?)", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.setInt(1, usuario.getIdUsuario());
                callableStatement.setString(2, usuario.getUserName());
                callableStatement.setString(3, usuario.getNombre());
                callableStatement.setString(4, usuario.getApellidoPaterno());
                callableStatement.setString(5, usuario.getEmail());
                callableStatement.setString(6, usuario.getSexo());
                callableStatement.setString(7, usuario.getTelefono());
                callableStatement.setString(8, usuario.getCelular());
                callableStatement.setString(9, usuario.getCurp());
                callableStatement.setString(10, usuario.getApellidoMaterno());
                callableStatement.setString(11, usuario.getPassword());
                callableStatement.setDate(12, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
                callableStatement.setInt(13, usuario.Roll.getIdRoll());
                callableStatement.setString(14, usuario.getImagen());

                int rowsAffected = callableStatement.executeUpdate();

                if (rowsAffected > 0) {
                    result.correct = true;
                } else {
                    result.correct = false;
                    result.errorMessage = "Error en la actualización";
                }

                return 1;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;

        }

        return result;
    }

    @Override
    public Result UsuarioADDdireccion(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL UsuarioADDdireccion(?,?,?,?,?)}",
                    (CallableStatementCallback<Integer>) callableStatement -> {

                        callableStatement.setString(1, usuarioDireccion.Direccion.getCalle());
                        callableStatement.setString(2, usuarioDireccion.Direccion.getNumeroInterior());
                        callableStatement.setString(3, usuarioDireccion.Direccion.getNumeroExterior());
                        callableStatement.setInt(4, usuarioDireccion.Direccion.Colonia.getIdColonia());
                        callableStatement.setInt(5, usuarioDireccion.Usuario.getIdUsuario());

                        int rowAffected = callableStatement.executeUpdate();

                        result.correct = rowAffected > 0 ? true : false; //Operador ternario, Funciona como if, Si : No

                        return 1;

                    });
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result DireccionUpdate(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL DireccionUpdate(?,?,?,?,?)}",
                    (CallableStatementCallback<Integer>) callableStatement -> {

                        callableStatement.setString(1, usuarioDireccion.Direccion.getCalle());
                        callableStatement.setString(2, usuarioDireccion.Direccion.getNumeroInterior());
                        callableStatement.setString(3, usuarioDireccion.Direccion.getNumeroExterior());
                        callableStatement.setInt(4, usuarioDireccion.Direccion.Colonia.getIdColonia());
                        callableStatement.setInt(5, usuarioDireccion.Usuario.getIdUsuario());

                        int rowAffected = callableStatement.executeUpdate();

                        result.correct = rowAffected > 0 ? true : false; //Operador ternario, Funciona como if, Si : No

                        return 1;

                    });
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;

    }
}
