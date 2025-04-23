
package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.ML.Direccion;
import com.digis01.FNolascoProgramacionNCapas.ML.Result;
import com.digis01.FNolascoProgramacionNCapas.ML.Usuario;
import com.digis01.FNolascoProgramacionNCapas.ML.UsuarioDireccion;


public interface IUsuarioDAO {
    Result GetAll();
    Result Add(UsuarioDireccion usuarioDireccion);
    Result UsuarioDireccionesById(int IdUsuario);
    Result UsuarioGetById(int IdUsuario);
    Result UsuarioUpdate(Usuario usuario);
    Result UsuarioADDdireccion(UsuarioDireccion usuarioDireccion);
    Result DireccionUpdate(UsuarioDireccion usuarioDireccion);
}
