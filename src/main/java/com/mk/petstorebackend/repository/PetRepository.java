package com.mk.petstorebackend.repository;

import com.mk.petstorebackend.model.Pet;
import com.mk.petstorebackend.model.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("""
            select p from Pet p
            where (:term is null
                   or lower(p.name) like lower(concat('%', :term, '%'))
                   or lower(p.category) like lower(concat('%', :term, '%')))
              and (:status is null or p.status = :status)
            order by p.name asc
            """)
    List<Pet> search(@Param("term") String term, @Param("status") PetStatus status);

    @Query("select distinct p.category from Pet p order by p.category asc")
    List<String> findDistinctCategories();
}
