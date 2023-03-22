package com.market.service.impl.product;

import com.market.dto.mapper.ProductCreateToProductEntityMapper;
import com.market.dto.request.ProductCreate;
import com.market.entity.order.OrderItem;
import com.market.entity.productTypes.Product;
import com.market.repository.product.ProductRepository;
import com.market.service.order.OrderItemService;
import com.market.service.product.*;
import com.market.service.user.UserService;
import com.market.util.enums.ProductTypeEnum;
import com.market.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl serviceToTest;
    @Mock
    private ProductRepository mockProductRepository;
    @Mock
    private FoodService mockFoodService;
    @Mock
    private DrinkService mockDrinkService;
    @Mock
    private SanitaryService mockSanitaryService;
    @Mock
    private CosmeticService mockCosmeticService;
    @Mock
    private SpecificationProductFilter mockSpecificationProductFilter;
    @Mock
    private OrderItemService mockOrderItemService;

    @Mock
    private UserService mockUserService;

    @Mock
    private ProductCreateToProductEntityMapper mockProductCreateToProductEntityMapper;

    @Test
    public void saveProductCreateWhenProductCreateTypeIsFood() {
        ProductCreate productCreate = new ProductCreate();
        productCreate.setType(ProductTypeEnum.FOOD);
        serviceToTest.saveProductCreate(productCreate);
        verify(mockFoodService, times(1)).save(productCreate);
    }

    @Test
    public void saveProductCreateWhenProductCreateTypeIsDrinks() {
        ProductCreate productCreate = new ProductCreate();
        productCreate.setType(ProductTypeEnum.DRINKS);
        serviceToTest.saveProductCreate(productCreate);
        verify(mockDrinkService, times(1)).save(productCreate);
    }

    @Test
    public void saveProductCreateWhenProductCreateTypeIsSanitary() {
        ProductCreate productCreate = new ProductCreate();
        productCreate.setType(ProductTypeEnum.SANITARY);
        serviceToTest.saveProductCreate(productCreate);
        verify(mockSanitaryService, times(1)).save(productCreate);
    }

    @Test
    public void saveProductCreateWhenProductCreateTypeIsCosmetics() {
        ProductCreate productCreate = new ProductCreate();
        productCreate.setType(ProductTypeEnum.COSMETICS);
        serviceToTest.saveProductCreate(productCreate);
        verify(mockCosmeticService, times(1)).save(productCreate);
    }

    @Test
    public void saveProductCreateWhenProductCreateTypeIsOthers() {
        ProductCreate productCreate = new ProductCreate();
        productCreate.setType(ProductTypeEnum.OTHERS);
        serviceToTest.saveProductCreate(productCreate);
        verify(mockProductRepository, times(1)).save(mockProductCreateToProductEntityMapper.apply(productCreate));
    }

    @Test
    void deleteProductByIdTestWhenProductNotFound() {
        when(mockProductRepository.findById(0l)).thenReturn(Optional.empty());


        assertThrows(NotFoundException.class, () -> serviceToTest.deleteProductById(0L));
        verify(mockOrderItemService, never()).saveItem(any(OrderItem.class));
        verify(mockProductRepository, never()).deleteById(0L);
    }


    @Test
    void deleteProductByIdTestWhenProductFound() {
        Product product = new Product();
        product.setName("Кока кола - кен");
        product.setAvailableQuantity(10);
        product.setPriceBGN(1.50);
        product.setType(ProductTypeEnum.DRINKS);
        product.setAvailableQuantity(100);
        product.setId(5L);

        // create order items
        OrderItem orderItem1 = new OrderItem(product, 10);
        OrderItem orderItem2 = new OrderItem(product, 5);
        List<OrderItem> items = new ArrayList<>();
        items.add(orderItem1);
        items.add(orderItem2);

        // set items to product
        product.setItems(items);

        when(mockProductRepository.findById(5L)).thenReturn(Optional.of(product));
        serviceToTest.deleteProductById(5L);
        verify(mockOrderItemService, times(2)).saveItem(any(OrderItem.class));
        verify(mockProductRepository, times(1)).deleteById(5L);
    }


    @Test
    public void updateProductTestWhenTypeIsFood() {
        ProductCreate productCreate = new ProductCreate();
        productCreate.setType(ProductTypeEnum.FOOD);

        serviceToTest.updateProduct(productCreate);
        verify(mockFoodService, times(1)).update(productCreate);
        verify(mockDrinkService, never()).update(productCreate);
        verify(mockSanitaryService, never()).update(productCreate);
        verify(mockCosmeticService, never()).update(productCreate);


    }

    @Test
    public void updateProductTestWhenTypeIsDrinks() {
        ProductCreate productCreate = new ProductCreate();
        productCreate.setType(ProductTypeEnum.DRINKS);

        serviceToTest.updateProduct(productCreate);
        verify(mockFoodService, never()).update(productCreate);
        verify(mockDrinkService, times(1)).update(productCreate);
        verify(mockSanitaryService, never()).update(productCreate);
        verify(mockCosmeticService, never()).update(productCreate);

    }

    @Test
    public void updateProductTestWhenTypeIsSanitary() {
        ProductCreate productCreate = new ProductCreate();
        productCreate.setType(ProductTypeEnum.SANITARY);

        serviceToTest.updateProduct(productCreate);
        verify(mockFoodService, never()).update(productCreate);
        verify(mockDrinkService, never()).update(productCreate);
        verify(mockSanitaryService, times(1)).update(productCreate);
        verify(mockCosmeticService, never()).update(productCreate);

    }

    @Test
    public void updateProductTestWhenTypeIsCosmetics() {
        ProductCreate productCreate = new ProductCreate();
        productCreate.setType(ProductTypeEnum.COSMETICS);

        serviceToTest.updateProduct(productCreate);
        verify(mockFoodService, never()).update(productCreate);
        verify(mockDrinkService, never()).update(productCreate);
        verify(mockSanitaryService, never()).update(productCreate);
        verify(mockCosmeticService, times(1)).update(productCreate);

    }

    @Test
    public void updateProductTestWhenTypeIsOthers() {
        ProductCreate productCreate = new ProductCreate();
        productCreate.setType(ProductTypeEnum.OTHERS);
        serviceToTest.updateProduct(productCreate);
        verify(mockFoodService, never()).update(productCreate);
        verify(mockDrinkService, never()).update(productCreate);
        verify(mockSanitaryService, never()).update(productCreate);
        verify(mockCosmeticService, never()).update(productCreate);
        verify(mockProductRepository, times(1)).save(mockProductCreateToProductEntityMapper.apply(productCreate));

    }

    @Test
    public void findProductByIdTestWhenIdNotFound() {
        when(mockProductRepository.findById(0L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> serviceToTest.findProductById(0L));
    }

    @Test
    public void findProductByIdTestWhenIdFound() {
        Product product = new Product();
        product.setId(1L);
        when(mockProductRepository.findById(1L)).thenReturn(Optional.of(product));
        assertEquals(serviceToTest.findProductById(1L), product);
    }


}
