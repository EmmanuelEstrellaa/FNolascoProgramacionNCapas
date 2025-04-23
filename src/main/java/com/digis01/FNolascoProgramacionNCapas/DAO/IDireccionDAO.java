
package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.ML.Result;
import com.digis01.FNolascoProgramacionNCapas.ML.UsuarioDireccion;


public interface IDireccionDAO {
    Result DireccionById(int IdDireccion);
    Result DireccionDelete(UsuarioDireccion usuarioDireccion);
    
}
