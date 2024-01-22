package com.code.Service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.code.Entity.MenuItem;
import com.code.Entity.Restaurant;
import com.code.Exception.ItemException;
import com.code.Exception.ResourceNotFoundException;
import com.code.Exception.RestrauntException;
import com.code.Service.MenuItemService;
import com.code.repositories.MenuItemRepository;
import com.code.repositories.RestaurantRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
public class MenuService implements MenuItemService {
	private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

	private final RestaurantRepository restaurantRepository;
	private final MenuItemRepository menuItemRepository;

	@SuppressWarnings("null")
	@Override
	@Transactional
	public MenuItem createMenuItem(MenuItem menuItem) {
		Restaurant existingRestaurant = new Restaurant();
		Restaurant existingRestaurant_id = null;
		try {
			logger.info("Creating menu item for restaurant ID: {}", menuItem.getRestaurant().getId());

			existingRestaurant_id = restaurantRepository.findById(menuItem.getRestaurant().getId()).orElseThrow(
					() -> new RestrauntException("Restaurant not found with ID: " + menuItem.getRestaurant().getId()));

			if (existingRestaurant_id != null) {
				existingRestaurant.setName(menuItem.getRestaurant().getName());
				existingRestaurant.setLocation(menuItem.getRestaurant().getLocation());
				existingRestaurant.setRating(menuItem.getRestaurant().getRating());
			}
			Restaurant savedRestaurant = restaurantRepository.save(existingRestaurant);

			menuItem.setRestaurant(savedRestaurant);

			MenuItem savemenuItem = menuItemRepository.save(menuItem);
			logger.info("Menu item created successfully with ID: {}", savemenuItem.getId());

			return savemenuItem;
		} catch (Exception e) {
			logger.error("Error creating menu item: {}", e.getMessage());
			throw new ItemException("Error creating menu item");
		}
	}

	@Override
	public MenuItem getMenuItemById(Long menuItemId) {
		try {
			logger.info("Fetching menu item details for ID: {}", menuItemId);

			MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(() -> {
				logger.error("Menu item not found for ID: {}", menuItemId);
				return new ResourceNotFoundException("MenuItem", "menuItemId", menuItemId);
			});

			logger.info("Menu item details fetched successfully for ID: {}", menuItemId);

			return menuItem;
		} catch (Exception e) {
			logger.error("Error while fetching menu item details: {}", e.getMessage());
			throw e;
		}
	}

}
