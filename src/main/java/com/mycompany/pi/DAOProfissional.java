
package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DAOProfissional {
    public static void cadastrar(Profissional p){
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
    public static List<Profissional> listar(){
        List<Profissional> lista = new ArrayList();
        EntityManager manager = JPAUtil.conectar();
        try{
            Query sql = manager.createQuery("SELECT p FROM Profissional p");
            lista = sql.getResultList();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconecta();
        }
        return lista;
    }
     public static List<Profissional> buscarPorId(int filtro) {
    EntityManager manager = JPAUtil.conectar();
    List<Profissional> lista = new ArrayList<>();
    try {
        TypedQuery<Profissional> query = manager.createQuery(
            "SELECT p FROM Profissional p WHERE p.idUser = :idUser", Profissional.class);
        query.setParameter("idUser", filtro);
        lista = query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        JPAUtil.desconecta();
    }
    return lista;
}
   public static List<Profissional> buscarPorIdProprio(int filtro) {
    EntityManager manager = JPAUtil.conectar();
    List<Profissional> lista = new ArrayList<>();
    try {
        TypedQuery<Profissional> query = manager.createQuery(
            "SELECT p FROM Profissional p WHERE p.id = :id", Profissional.class);
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
