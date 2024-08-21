package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
