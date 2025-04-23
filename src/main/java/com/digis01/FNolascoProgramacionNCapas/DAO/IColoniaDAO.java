
package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.ML.Result;


public interface IColoniaDAO {
    Result ColoniaByIdMunicipio(int IdMunicipio);
    Result ColoniaByCP(int CodigoPostal);
}
