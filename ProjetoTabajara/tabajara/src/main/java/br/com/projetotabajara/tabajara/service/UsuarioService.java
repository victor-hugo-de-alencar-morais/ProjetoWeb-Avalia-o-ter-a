package br.com.projetotabajara.tabajara.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.projetotabajara.tabajara.entity.Usuario;
import br.com.projetotabajara.tabajara.repository.UsuarioRepository;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

      // Método para salvar um usuario
    public Usuario save(Usuario usuario) {
        usuario.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
        return usuarioRepository.save(usuario); 
    }

    public List<Usuario> findAll(){
      return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id){
      return usuarioRepository.findById(id).orElse(null);
    }

    //metodo para gerar um token de recuperação de senha
    public String gerarTokenRecuperacao(String email){
      Usuario usuario = usuarioRepository.findByEmailUsuario(email).orElse(null);
      if(usuario == null){
        return null;
      }
      String token = UUID.randomUUID().toString();
      //Defina token expirição 30 min
      usuario.setResetToken(token);
      usuario.setTokenExpiracao(LocalDateTime.now().plusMinutes( 30));
      usuarioRepository.save(usuario);
      return token; 
    }
    
    // metodo para redifinir a senha 

  public boolean redefinirSenha(String token, String novaSenha) {
    // Busca com Optional, para evitar NullPointerException
    Usuario usuario = usuarioRepository.findByResetToken(token).orElse(null);
    
    if (usuario == null || usuario.getTokenExpiracao().isBefore(LocalDateTime.now())) {
        return false;
    }
    
    // Criptografa e redefine a senha
    usuario.setSenhaUsuario(passwordEncoder.encode(novaSenha));
    
    // Invalida o token de recuperação
    usuario.setResetToken(null);
    usuario.setTokenExpiracao(null);
    
    usuarioRepository.save(usuario);
    return true;
}
}
