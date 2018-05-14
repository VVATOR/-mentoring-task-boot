package com.epam.mentoring.springboot.repository;

import com.epam.mentoring.springboot.beans.User;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User,Integer>, UserCustomRepository{

}
