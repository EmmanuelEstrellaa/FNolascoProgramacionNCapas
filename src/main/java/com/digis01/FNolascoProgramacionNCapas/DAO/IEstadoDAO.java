
package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.ML.Result;


public interface IEstadoDAO {
    Result EstadoByIdPais(int IdPais);
    Result EstadoByIdPaisJPA(int IdPais);
    
}
