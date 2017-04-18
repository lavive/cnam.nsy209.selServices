package server.service.bean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import server.dao.CategoryDao;
import server.dao.entity.CategoryEntity;
import server.service.ManageCategory;
import server.service.transform.DtoToEntity;
import server.service.transform.EntityToDto;
import shared.dto.CategoryDto;


public class ManageCategoryBean implements ManageCategory {
	
	@Inject
	private CategoryDao categoryDao;

	@Override
	public void addCategory(CategoryDto categoryDto) {
		
		categoryDao.create(DtoToEntity.categoryDtoToEntity(categoryDto));

	}

	@Override
	public void deleteCategory(int categoryId) {
		
		categoryDao.delete(categoryDao.getCategory(categoryId));

	}

	@Override
	public List<CategoryDto> retrieveAllCategories() {
		List<CategoryDto> categories = new ArrayList<CategoryDto>();
		for(CategoryEntity category:categoryDao.getCategories()){
			categories.add(EntityToDto.categoryEntityToDto(category));
		}
		
		return categories;
	}

}
