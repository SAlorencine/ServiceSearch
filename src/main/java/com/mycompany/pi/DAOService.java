
package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DAOService {
    public static void cadastrar(Service s){
        EntityManager manager = JPAUtil.conectar();
        try{
            manager.getTransaction().begin();
            manager.persist(s);
            manager.getTransaction().commit();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconecta();
        }
    }
    public static List<Service> buscarPorId(int filtro) {
    EntityManager manager = JPAUtil.conectar();
    List<Service> lista = new ArrayList<>();
    try {
        TypedQuery<Service> query = manager.createQuery(
            "SELECT p FROM Service p WHERE p.id = :id", Service.class);
        query.setParameter("id", filtro);
        lista = query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        JPAUtil.desconecta();
    }
    return lista;
    }
    public static List<Service> listar(){
        List<Service> lista = new ArrayList();
        EntityManager manager = JPAUtil.conectar();
        try{
            Query sql = manager.createQuery("SELECT p FROM Service p");
            lista = sql.getResultList();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconecta();
        }
        return lista;
    }
}
