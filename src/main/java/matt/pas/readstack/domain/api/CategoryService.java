package matt.pas.readstack.domain.api;

import matt.pas.readstack.domain.category.Category;
import matt.pas.readstack.domain.category.CategoryDao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryService {

    CategoryDao categoryDao = new CategoryDao();

    public List<CategoryName> findAllCategoryNames() {
        return categoryDao.findAll()
                .stream()
                .map(CategoryNameMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<CategoryFullInfo> findById(int categoryID) {
       return categoryDao.findById(categoryID)
               .map(CategoryFullInfoMapper::map);
    }

    private static class CategoryNameMapper {
        static CategoryName map(Category category) {
            return new CategoryName(category.getId(), category.getName());
        }
    }

    private static class CategoryFullInfoMapper {
        static CategoryFullInfo map(Category c) {
            return new CategoryFullInfo(
                    c.getId(),
                    c.getName(),
                    c.getDescription()
            );
        }
    }
}

