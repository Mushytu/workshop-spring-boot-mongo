package com.mushytu.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mushytu.workshopmongo.domain.User;
import com.mushytu.workshopmongo.dto.UserDTO;
import com.mushytu.workshopmongo.repositories.UserRepository;
import com.mushytu.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id){
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User toUpdObj = findById(obj.getId());
		updateData(toUpdObj, obj);
		return repo.save(toUpdObj);
	}
	
	public void updateData(User toUpdObj, User obj) {
		toUpdObj.setName(obj.getName());
		toUpdObj.setEmail(obj.getEmail());
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
