package com.example.repository.profile;

import com.example.entity.ProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer> {
    Optional<ProfileEntity> findByEmailAndPasswordAndVisible(String login, String password, boolean b);

    Page<ProfileEntity> findAll(Pageable pageable);

    Optional<ProfileEntity> findByEmail(String email);
}
