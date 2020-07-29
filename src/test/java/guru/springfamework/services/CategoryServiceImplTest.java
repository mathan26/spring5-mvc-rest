package guru.springfamework.services;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.mapper.CategoryMapper;
import guru.springfamework.repositories.CategoryRepository;
import junit.framework.TestCase;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


public class CategoryServiceImplTest extends TestCase {

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    public void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        categoryService=new CategoryServiceImpl(CategoryMapper.INSTANCE,categoryRepository);
    }

    public void testGetAllCategories() {
        //given
        List<Category> categories= Arrays.asList(new Category(),new Category(),new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        //when

        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        //test
        assertEquals(3,categoryDTOS.size());

    }

    public void testGetCategoryByName() {
        //given
        Category category=new Category();
        category.setId(ID);
        category.setName(NAME);
        when(categoryRepository.findByName(NAME)).thenReturn(category);

        //when

        CategoryDTO categoryDTO=categoryService.getCategoryByName(NAME);

        //test

        assertEquals(ID,categoryDTO.getId());
        assertEquals(NAME,categoryDTO.getName());

    }
}