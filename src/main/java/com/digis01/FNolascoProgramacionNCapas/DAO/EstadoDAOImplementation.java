package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.ML.Estado;
import com.digis01.FNolascoProgramacionNCapas.ML.Pais;
import com.digis01.FNolascoProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDAOImplementation implements IEstadoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result EstadoByIdPais(int IdPais) {
        Result result = new Result();

        try {

            jdbcTemplate.execute("CALL EstadoByIdPais(?,?)", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.setInt(1, IdPais);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);

                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

                result.objects = new ArrayList<>();
                while (resultSet.next()) {
                    Estado estado = new Estado();

                    estado.setIdEstado(resultSet.getInt("IdEstado"));
                    estado.setNombre(resultSet.getString("Nombre"));

                    result.objects.add(estado);
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
    public Result EstadoByIdPaisJPA(int IdPais) {
        Result result = new Result();

        try {
            TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Estado> queryEstado = entityManager.createQuery("FROM Estado WHERE Pais.IdPais = :idpais", com.digis01.FNolascoProgramacionNCapas.JPA.Estado.class);
            queryEstado.setParameter("idpais");
            
            List<com.digis01.FNolascoProgramacionNCapas.JPA.Estado> estados = queryEstado.getResultList();

            for (com.digis01.FNolascoProgramacionNCapas.JPA.Estado estado : estados) {

                Estado estadoo = new Estado();

                estadoo.setIdEstado(estado.getIdEstado());
                estadoo.setNombre(estado.getNombre());

                result.object = estados;

            }
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
