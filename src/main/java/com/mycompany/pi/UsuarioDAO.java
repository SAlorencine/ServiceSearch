/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    public static Usuarios validarUsuario(Usuarios u){
        EntityManager manager = JPAUtil.conectar();
        
        try {
            Query consulta = manager.createQuery(
                "SELECT u FROM Usuarios u WHERE u.login = :login AND u.senha = :senha"
            );
            consulta.setParameter("login", u.getLogin());
            consulta.setParameter("senha", u.getSenha());

            List<Usuarios> listaUser = consulta.getResultList();
            System.out.println("Quantidade de usuários encontrados: " + listaUser.size());


            if (!listaUser.isEmpty()) {
                return listaUser.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JPAUtil.desconecta();
        }

        return null;
    }
    public static void cadastrar(Usuarios p){
        EntityManager manager = JPAUtil.conectar();
        try{
            manager.getTransaction().begin();
            manager.persist(p);
            manager.getTransaction().commit();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconecta();
        }
    }
    public static List<Usuarios> buscarPorId(int filtro) {
    EntityManager manager = JPAUtil.conectar();
    List<Usuarios> lista = new ArrayList<>();
    try {
        TypedQuery<Usuarios> query = manager.createQuery(
            "SELECT p FROM Usuarios p WHERE p.id = :id", Usuarios.class);
        query.setParameter("id", filtro);
        lista = query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        JPAUtil.desconecta();
    }
    return lista;
}
    
    
}
