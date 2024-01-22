package com.code.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.Entity.MenuItem;
import com.code.Service.impl.MenuService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/controller")
public class MenuController {

	private final MenuService menuService;

	@PostMapping("/save")
	public ResponseEntity<MenuItem> saveMenuItem(@RequestBody MenuItem menuItem) {
		MenuItem createMenuItem = menuService.createMenuItem(menuItem);
		return new ResponseEntity<>(createMenuItem, HttpStatus.CREATED);
	}

	@GetMapping("/gettingData/{id}")
	public ResponseEntity<MenuItem> gettingDataById(@PathVariable Long id) {
		MenuItem menuItemById = menuService.getMenuItemById(id);
		if (menuItemById != null) {
			return ResponseEntity.ok(menuItemById);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
