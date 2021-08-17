package com.teste.varejo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teste.varejo.model.Contact;
import com.teste.varejo.model.Contacts;
import com.teste.varejo.service.ContactsService;

@Controller
@RequestMapping("/contacts")
public class ContactsController {
	
	@Autowired
	private ContactsService service;

	@PostMapping
	public ResponseEntity incluir(@RequestBody Contacts contacts) {
		service.salvar(contacts);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity buscar(@PathVariable(name = "id") Integer id) {
		Contact contact = service.buscar(id);
		if (contact == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(contact);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deletar(@PathVariable(name = "id") Integer id) {
		service.deletar(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity atualizar(@PathVariable(name = "id") Integer id, @RequestBody Contact contact) {
		Contact c = service.atualizar(id, contact);
		if (c == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(c);
	}
}
