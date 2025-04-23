
package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.ML.Result;
import com.digis01.FNolascoProgramacionNCapas.ML.Roll;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class RollDAOImplementation implements IRollDAO{
    
    @Autowired 
    private JdbcTemplate jdbcTemplate;
    
    @Override 
    public Result GetAll(){
    Result result = new Result();
        
        try {
            
            result.object = jdbcTemplate.execute("{CALL RollGetAll(?)}", (CallableStatementCallback<List<Roll>>) callableStatement -> {
                callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
                
                List<Roll> rolls = new ArrayList<>();
                while(resultSet.next()){
                    Roll roll = new Roll();
                    roll.setIdRoll(resultSet.getInt("IdRoll"));
                    roll.setNombre(resultSet.getString("Nombre"));
                    rolls.add(roll);
                }
                result.correct = true;
                return rolls;
            });
            
        }catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
        
    }
    
}
