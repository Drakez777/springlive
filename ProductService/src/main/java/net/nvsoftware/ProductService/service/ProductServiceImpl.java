package net.nvsoftware.ProductService.service;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import net.nvsoftware.ProductService.entity.ProductEntity;
import net.nvsoftware.ProductService.model.ProductRequest;
import net.nvsoftware.ProductService.model.ProductResponse;
import net.nvsoftware.ProductService.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(@RequestBody ProductRequest productRequest) {
        log.info("Start: ProductService addProduct");
        ProductEntity productEntity = ProductEntity.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();

        productRepository.save(productEntity);
        log.info("End: ProductService addProduct");
        return productEntity.getId();
    }

    @Override
    public ProductResponse getProductById(long id) {
        log.info("Start: ProductService getProductById");
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductService getById not found with id: " + id));
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(productEntity, productResponse);
        log.info("End: ProductService getProductById"+ productResponse);
        return productResponse;
    }
}
