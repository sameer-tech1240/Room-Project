package com.room.api.mask.repo;

import com.room.api.mask.entity.UserAccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserMaskRepository extends JpaRepository<UserAccountDetailsEntity, String> {
}
