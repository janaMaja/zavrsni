package jwd.zavrsni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.zavrsni.model.Najam;

@Repository
public interface NajamRepository extends JpaRepository<Najam, Long>{

}
