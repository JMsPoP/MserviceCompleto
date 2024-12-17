package com.jmtsu.ms.resilience.infra;

import com.jmtsu.ms.core.model.ProductModel;

import java.util.List;

public interface ProductClient {
    List<ProductModel> productSearch(String key);

    List<ProductModel> productSearchById(Long id);


}
