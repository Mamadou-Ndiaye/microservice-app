package sn.ucad.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sn.ucad.productservice.model.Product;

public interface ProductRepository  extends MongoRepository<Product, String> {
}
