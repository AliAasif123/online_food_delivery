package com.code.Service;

import com.code.Entity.MenuItem;
import com.code.Exception.ItemException;

public interface MenuItemService {
	MenuItem getMenuItemById(Long menuItemId)throws ItemException;


	// List<MenuItem> getAllMenuItems();

	MenuItem createMenuItem(MenuItem menuItem) throws ItemException;

	// void updateMenuItem(MenuItem menuItem);

	// void deleteMenuItem(Long menuItemId);
}
