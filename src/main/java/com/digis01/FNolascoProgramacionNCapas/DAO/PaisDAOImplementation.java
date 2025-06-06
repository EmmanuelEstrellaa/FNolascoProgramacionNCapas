
package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.ML.Pais;
import com.digis01.FNolascoProgramacionNCapas.ML.Result;
import com.digis01.FNolascoProgramacionNCapas.ML.Roll;
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
public class PaisDAOImplementation implements IPaisDAO {
    
    @Autowired 
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private EntityManager entityManager;
    
    @Override 
    public Result GetAll(){
    Result result = new Result();
        
        try {
            
            result.object = jdbcTemplate.execute("{CALL PaisGetAll(?)}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
                
                result.objects = new ArrayList<>();
                while(resultSet.next()){
                    
                    Pais pais = new Pais();
                    pais.setIdPais(resultSet.getInt("IdPais"));
                    pais.setNombre(resultSet.getString("Nombre"));
                    result.objects.add(pais);
                }
                result.correct = true;
                return 1;
            });
            
        }catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
        
    }

    @Override
    public Result GetAllJPA() {
        Result result = new Result();
        
        try{
            TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Pais> queryPais = entityManager.createQuery("FROM Pais", com.digis01.FNolascoProgramacionNCapas.JPA.Pais.class);
            List<com.digis01.FNolascoProgramacionNCapas.JPA.Pais> paises = queryPais.getResultList();
            
            for (com.digis01.FNolascoProgramacionNCapas.JPA.Pais paise : paises) {
                
                Pais pais = new Pais();
                
                pais.setIdPais(pais.getIdPais());
                pais.setNombre(pais.getNombre());
                
                result.object = paises;
                
            }
            result.correct = true;
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        

        return result;
    }
    
    
    

}
