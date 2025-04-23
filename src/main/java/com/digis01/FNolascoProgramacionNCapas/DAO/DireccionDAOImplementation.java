package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.ML.Colonia;
import com.digis01.FNolascoProgramacionNCapas.ML.Direccion;
import com.digis01.FNolascoProgramacionNCapas.ML.Estado;
import com.digis01.FNolascoProgramacionNCapas.ML.Municipio;
import com.digis01.FNolascoProgramacionNCapas.ML.Pais;
import com.digis01.FNolascoProgramacionNCapas.ML.Result;
import com.digis01.FNolascoProgramacionNCapas.ML.UsuarioDireccion;
import java.sql.ResultSet;
import java.sql.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DireccionDAOImplementation implements IDireccionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result DireccionById(int IdDireccion) {
        Result result = new Result();

        try {

            jdbcTemplate.execute("{CALL DireccionGetById(?,?)}",
                    (CallableStatementCallback<Integer>) callableStatement -> {

                        callableStatement.setInt(1, IdDireccion);
                        callableStatement.registerOutParameter(2, Types.REF_CURSOR);

                        callableStatement.execute();

                        ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

                        if (resultSet.next()) {

                            Direccion direccion = new Direccion();
                            direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                            direccion.setCalle(resultSet.getString("Calle"));
                            direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                            direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                            direccion.Colonia = new Colonia();
                            direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                            direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
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

                            result.object = direccion;
                        }

                        return 1;
                    });
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result DireccionDelete(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL DireccionDelete(?)}",
                    (CallableStatementCallback<Integer>) callableStatement -> {

                        callableStatement.setInt(1, usuarioDireccion.Direccion.getIdDireccion());

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
