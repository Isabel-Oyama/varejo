package com.teste.varejo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.varejo.model.Contact;

public interface ContactsRepository extends JpaRepository<Contact, Integer> {

}
