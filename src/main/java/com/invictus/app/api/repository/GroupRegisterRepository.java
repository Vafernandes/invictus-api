package com.invictus.app.api.repository;

import com.invictus.app.api.entity.GroupRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRegisterRepository extends JpaRepository<GroupRegistrationEntity, UUID> {

    @Query(
            value = "select * from group_registration where group_id in (:groupIds)",
            nativeQuery = true)
    List<GroupRegistrationEntity> findAllByGroupId(List<UUID> groupIds);

    List<GroupRegistrationEntity> findByGroupId(UUID id);
}
