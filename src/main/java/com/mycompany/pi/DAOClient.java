
package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DAOClient {
    public static void cadastrar(Client c){
        EntityManager manager = JPAUtil.conectar();
        try{
            manager.getTransaction().begin();
            manager.persist(c);
            manager.getTransaction().commit();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconecta();
        }
    }
    public static List<Client> buscarPorId(int filtro) {
                EntityManager manager = JPAUtil.conectar();
                List<Client> lista = new ArrayList<>();
            try {
                TypedQuery<Client> query = manager.createQuery(
                "SELECT c FROM Client c WHERE c.idUser = :idUser", Client.class);
                query.setParameter("idUser", filtro);
                lista = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JPAUtil.desconecta();
            }           
    return lista;
}
    public static List<Client> buscarPorIdProprio(int filtro) {
                EntityManager manager = JPAUtil.conectar();
                List<Client> lista = new ArrayList<>();
            try {
                TypedQuery<Client> query = manager.createQuery(
                "SELECT c FROM Client c WHERE c.id = :id", Client.class);
                query.setParameter("id", filtro);
                lista = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JPAUtil.desconecta();
            }           
    return lista;
}
    public static List<Client> listar(){
        List<Client> lista = new ArrayList();
        EntityManager manager = JPAUtil.conectar();
        try{
            Query sql = manager.createQuery("SELECT p FROM Client p");
            lista = sql.getResultList();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconecta();
        }
        return lista;
    }
}

