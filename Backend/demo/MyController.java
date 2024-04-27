package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MyController {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	TaskRepo taskRepo;
	
	@RequestMapping("add{id}")
	public Task add(@PathVariable int id,@RequestBody String details) {
		try {
			Task task=new Task();
			task.setDetails(details);
			Task taskdb=taskRepo.save(task);
			
			User user=userRepo.findById(id).get();
			user.getTasks().add(taskdb);
			userRepo.save(user);
			
			return taskdb;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("readAllTasks{id}")
	public List<Task> readAllTask(@PathVariable int id) {
		try {
			//first way(Expanded)
//			Optional<User> obj=userRepo.findById(id);
//			User user=obj.get();
			
			//second way
			User user=userRepo.findById(id).get();
			return user.getTasks();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("login{username}")
	public int login(@PathVariable String username,@RequestBody String password) {
		try {
			int count=userRepo.countByUsername(username);
			if(count==0) {
				return -2;// wrong username
			}
			if(count>1) {
				return -3;//multiple accounts with same username
			}
			
			User user=userRepo.findByUsername(username);
			if(user.getPassword().equals(password))
				return user.getId();
			else
				return -4;
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //Exception on server
		}
	}
	
	@RequestMapping("delete{uid}and{tid}")
	public int delete(@PathVariable int uid , @PathVariable int tid) {
		
		try {
				User user=userRepo.findById(uid).get();
				Task dltask=null;
				for(Task task:user.getTasks()) {
					if(task.getId()==tid) {
						dltask=task;
						break;
					}
				}
				if(dltask!=null) {
					user.getTasks().remove(dltask);
					userRepo.save(user);
					return 1;
				}
				else
					return -1;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
	}
}
