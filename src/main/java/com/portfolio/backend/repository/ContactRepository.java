package com.portfolio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.backend.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}