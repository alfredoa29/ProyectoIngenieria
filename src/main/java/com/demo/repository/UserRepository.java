package com.demo.repository;

import com.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    //estos metodos tienen algo interesante, ya que java utiliza la palabra findBy + un atributo da la  itendidad
    //asi que el mismo framework instancia estos metodos
    public Optional<User> findByUsername(String username);
    //public Iterable<User> findByRoles();

    public void deleteUserByUsername(String username);

    public String findByRoles(String roleDesc);
    //esto es un buen ejemplo
   // public Iterable<User> findAllByStatus( String status);

}
