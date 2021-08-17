package com.teste.varejo.service;

import org.springframework.stereotype.Service;

import com.teste.varejo.model.Contact;
import com.teste.varejo.model.Contacts;

@Service
public interface ContactsService {
	void salvar(Contacts contacts);
	Contact buscar(Integer id);
	void deletar(Integer id);
	Contact atualizar(Integer id, Contact contact);
}
