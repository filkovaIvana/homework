package org.example.repository;

import org.example.entity.AreaOfExpertise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<AreaOfExpertise, Long> {

    public AreaOfExpertise findByCode(String code);
    public AreaOfExpertise findByArea(String area);

}
