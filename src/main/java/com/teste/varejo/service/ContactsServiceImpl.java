package com.teste.varejo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.varejo.model.Contact;
import com.teste.varejo.model.Contacts;
import com.teste.varejo.repository.ContactsRepository;

@Service
public class ContactsServiceImpl implements ContactsService {
	
	@Autowired
	private ContactsRepository repository;

	@Override
	public void salvar(Contacts contacts) {
		List<Contact> listContacts = contacts.getContacts();
		for (Contact contact : listContacts) {
			contact.setName(contact.getName().toUpperCase());
			contact.setCellphone(mascararCelular(contact.getCellphone()));
			repository.saveAndFlush(contact);
		}
	}

	@Override
	public Contact buscar(Integer id) {
		Optional<Contact> contact = repository.findById(id);
		if (contact.isPresent()) {
			return contact.get();
		}
		return null;
	}

	@Override
	public void deletar(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public Contact atualizar(Integer id, Contact contact) {
		Optional<Contact> optContact = repository.findById(id);
		if (optContact.isPresent()) {
			Contact c = optContact.get();
			c.setName(contact.getName().toUpperCase());
			c.setCellphone(mascararCelular(contact.getCellphone()));
			return repository.saveAndFlush(c);
		}
		return null;
	}
	
	private String mascararCelular(String celular) {
		if (celular.length() < 13) {
			return celular;
		}
		String mascarado = String.format("+%s (%s) %s-%s", 
				celular.substring(0, 2), 
				celular.substring(2, 4), 
				celular.substring(4, 9), 
				celular.substring(9, 13));
		return mascarado;
	}
}
