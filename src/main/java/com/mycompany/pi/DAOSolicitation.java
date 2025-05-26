
package com.mycompany.pi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOSolicitation {
    public static void cadastrar(Solicitation s){
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
    public static List<Solicitation> buscarPorId(int filtro) {
    EntityManager manager = JPAUtil.conectar();
    List<Solicitation> lista = new ArrayList<>();
    try {
        TypedQuery<Solicitation> query = manager.createQuery(
            "SELECT p FROM Solicitation p WHERE p.id = :id", Solicitation.class);
        query.setParameter("id", filtro);
        lista = query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        JPAUtil.desconecta();
    }
    return lista;
    }
     public static List<Solicitation> listar(){
        List<Solicitation> lista = new ArrayList();
        EntityManager manager = JPAUtil.conectar();
        try{
            Query sql = manager.createQuery("SELECT p FROM Solicitation p");
            lista = sql.getResultList();
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            JPAUtil.desconecta();
        }
        return lista;
    }
     public static void alterarFinalizado(int id, boolean novoValor) {
    EntityManager manager = JPAUtil.conectar();
    try {
        manager.getTransaction().begin();

        Solicitation s = manager.find(Solicitation.class, id);

        if (s != null) {
            s.setFinalizada(novoValor);
            manager.merge(s); 
        } else {
            JOptionPane.showMessageDialog(null, "Solicitação não encontrada");
        }

        manager.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
        manager.getTransaction().rollback();
    } finally {
        JPAUtil.desconecta();
    }
}
    
}
