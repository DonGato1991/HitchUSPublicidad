/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.model.view;

import ec.edu.espe.models.Usuario;
import ec.edu.espe.services.UsuarioJpaController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author homer
 */
public class Main {
    public static void main(String[] args) {
        UsuarioJpaController usu =new UsuarioJpaController();
        try {
            usu.create(new Usuario(3,"correoElectronico", "password", "nombres"));
        } catch (Exception ex) {
            System.err.println("ERROR");
        }
        
    }
   
}
