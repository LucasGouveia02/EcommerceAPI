package com.br.senac.EcommerceAPI.Models;

import com.br.senac.EcommerceAPI.DTO.CredencialDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

@Entity
@Table(name = "credencial")
public class CredencialModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Column(nullable = false)
    private String senha;
    private Long idUsuario;
    @Column(nullable = false) //obrigatoriedade para preencher
    private boolean admin;

    public CredencialModel(CredencialDTO dto){
        this.email = dto.getEmail();
        this.senha = dto.getSenha();
        this.idUsuario = dto.getIdUsuario();
        this.admin = dto.isAdmin();
    }


}
