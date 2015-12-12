package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DAOGenerico<T> {

    public static EntityManager em = Persistence.createEntityManagerFactory("UP").createEntityManager();

    private Class classe;

    public DAOGenerico(Class classe) {
        this.classe = classe;
    }

    public void inserir(T e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();

    }

    public void atualizar(T e) {
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
    }

    public void remover(T e) {
        if (e != null) {
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        }
    }

    public T obter(int id) {
        em.clear();
        return (T) em.find(classe, id);
    }

    public T obter(String id) {
        em.clear();
        return (T) em.find(classe, id);
    }

    public List<T> list() {
        return em.createQuery("SELECT e FROM " + classe.getSimpleName() + " e").getResultList();
    }

}
