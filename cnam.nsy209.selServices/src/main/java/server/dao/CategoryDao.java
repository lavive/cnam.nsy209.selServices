package server.dao;

import java.util.List;

import server.dao.entity.CategoryEntity;
import server.dao.interfaces.InterfaceDao;


public interface CategoryDao extends InterfaceDao<CategoryEntity> {
	
	public List<CategoryEntity> getCategories();
	
	public CategoryEntity getCategory(int categoryId);

}
