package server.service;

import java.util.List;

import shared.dto.CategoryDto;


public interface ManageCategory {
	
	public void addCategory(CategoryDto categoryDto);
	
	public void deleteCategory(int categoryId);
	
	public List<CategoryDto> retrieveAllCategories();

}
