package sn.ucad.productservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sn.ucad.productservice.dto.ProductDTO;
import sn.ucad.productservice.model.Product;
import sn.ucad.productservice.repository.ProductRepository;
import sn.ucad.productservice.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private  final ProductRepository productRepository;

    private final ModelMapper modelMapper;
    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.info("ProductServiceImpl:save  new product execution started ");
        return  modelMapper.map(productRepository.save(modelMapper.map(productDTO, Product.class)), ProductDTO.class);
    }

    @Override
    public Product create(Product product) {
        log.info("ProductServiceImpl:save  new product execution started ");
        return productRepository.save(product);
    }


    @Override
    public ProductDTO update(ProductDTO productDTO) {
      return null;
    }

    @Override
    public List<ProductDTO> findAll() {
        log.info("****** ProductServiceImpl:findAll execution started ******* ");
        return productRepository.findAll().stream().map(ProductDTO::mapToDto).toList();
    }

    @Override
    public List<Product> fetchAll() {
        log.info("****** ProductServiceImpl:findAll execution started ******* ");
        return productRepository.findAll();
    }


    @Override
    public Product findById(String id) {
        log.info("ProductServiceImpl:findById execution started ");
        if( id == null)
        {
            log.error("Product not found");
            throw  new RuntimeException(" ID  product is null ");
        }
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not existe in DB"));
        return  product ;
    }

    @Override
    public ProductDTO findByName(String name) {
        return null;
    }

    @Override
    public void delete(String id) {
        log.info("ProductServiceImpl:deleteById execution started ");
        if( id == null)
        {
            log.error("Category not found");
            throw  new RuntimeException("  ID  product is null ");
        }
        productRepository.deleteById(id);
        log.info("ProductServiceImpl:deleteById execution ended, the product deleting  ID = {} ", id);

    }


}
