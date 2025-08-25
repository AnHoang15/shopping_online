package com.anhoang.shopping.online.service.impl;

import com.anhoang.shopping.online.model.Product;
import com.anhoang.shopping.online.respository.ProductResposity;
import com.anhoang.shopping.online.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductResposity productResposity;

    @Override
    public Product saveProduct(Product product){
        return productResposity.save(product);
    }

    @Override
    public List<Product> getAllProduct(){
        return productResposity.findAll();
    }

    @Override
    public Boolean deleteProduct(Integer id){
        Product product = productResposity.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(product)){
            productResposity.delete(product);
            return true;
        }
        return false;

    }

    @Override
    public Product getProductById(Integer id){
        Product product = productResposity.findById(id).orElse(null);
        return product;
    }

    @Override
    public Product updateProduct(Product product, MultipartFile image)  {
        Product dbProduct = getProductById(product.getId());
        String imageName = image.isEmpty() ? dbProduct.getImage() : image.getOriginalFilename();
        dbProduct.setTitle(product.getTitle());
        dbProduct.setDescription(product.getDescription());
        dbProduct.setCategory(product.getCategory());
        dbProduct.setPrice(product.getPrice());
        dbProduct.setStock(product.getStock());
        dbProduct.setImage(imageName);
        dbProduct.setActive(product.isActive());
        dbProduct.setDiscount(product.getDiscount());
        Double discount = product.getPrice()*(product.getDiscount()/100.0);
        Double discountPrice = product.getPrice() - discount;
        dbProduct.setDiscountPrice(discountPrice);
        Product updateProduct = productResposity.save(dbProduct);
        if(!ObjectUtils.isEmpty(updateProduct)){
            if(!image.isEmpty()){
                try {
                    File saveFile = new ClassPathResource("static/img").getFile();

                    Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "product_img" + File.separator
                            + image.getOriginalFilename());

                    System.out.println(path);
                    Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            return product;
        }
        return null;
    }

    @Override
    public List<Product> getAllActiveProducts(String category) {
        List<Product> products = null;
        if(ObjectUtils.isEmpty(category)){
            products = productResposity.findByIsActiveTrue();
        } else {
            products = productResposity.findByCategory(category);
        }
        return products;
    }

}
