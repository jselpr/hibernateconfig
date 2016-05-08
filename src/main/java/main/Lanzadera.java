/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import es.munvi.hibernateconfig.Clientes;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



/**
 *
 * @author joselopezruiz
 */
public class Lanzadera {
    
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    public static void main(String[] args) {
        try{
            Configuration configuration = new Configuration().configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex){
            System.err.println("Failed to create SessionFactory object "+ex);
            throw new ExceptionInInitializerError(ex);
        }
        Lanzadera Me = new Lanzadera();
        Me.listClientes();
        /*Me.addCliente("007", "Carlos", "Ciao", "Membrilla");
        Me.addCliente("008", "Enrique", "Clar", "Parla");
        Me.addCliente("009", "Pablo", "Borne", "Pizarra");*/
        //Me.updateCliente("009", "Navalcarnero");
        Me.deleteCliente("009");
        Me.listClientes();
        System.exit(0);
    }
    
    public void addCliente(String dni, String nombre, String apellido, String ciudad){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Clientes clientes = new Clientes(dni,nombre,apellido,ciudad,null);
            session.save(clientes);
            tx.commit();
        } catch (HibernateException ex){
            if (tx != null){
                tx.rollback();
                ex.printStackTrace();
            }
        } finally {
            session.close();
        }
    }
    
    public void updateCliente(String dni, String ciudad){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Clientes cliente = (Clientes)session.get(Clientes.class, dni);
            cliente.setCiudad(ciudad);
            session.update(cliente);
            tx.commit();
        } catch (HibernateException ex){
            if (tx != null){
                tx.rollback();
                ex.printStackTrace();
            }
        } finally {
            session.close();
        }
    }
    
    public void deleteCliente(String dni){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Clientes cliente = (Clientes)session.get(Clientes.class, dni);
            
            session.delete(cliente);
            tx.commit();
        } catch (HibernateException ex){
            if (tx != null){
                tx.rollback();
                ex.printStackTrace();
            }
        } finally {
            session.close();
        }
    }
    
    public void listClientes(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List<Clientes> clientes = session.createQuery("FROM Clientes").list();
            System.out.println("--- Lista de clientes ---");
            for (Clientes cliente : clientes) {
                System.out.println("Dni: "+cliente.getDni());
                System.out.println("Nombre: "+cliente.getNombre());
                System.out.println("Apellido: "+cliente.getApellido());
                System.out.println("Ciudad: "+cliente.getCiudad());
                System.out.println("--------------------");
            }
            tx.commit();
        } catch (HibernateException ex){
            if (tx != null){
                tx.rollback();
                ex.printStackTrace();
            }
        } finally {
            session.close();
        }
    }
    
    
}
