package com.example.momentodosapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momentodosapp.data.adapter.ProductAdapter;
import com.example.momentodosapp.data.dao.ProductDao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProductActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productDao = new ProductDao();

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar Adapter
        productAdapter = new ProductAdapter(this);
        recyclerView.setAdapter(productAdapter);

        // Cargar los productos existentes
        loadProducts();

        // Configurar botones CRUD
        Button createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para crear un nuevo producto
                Product newProduct = new Product("id1", "username1", "password1");
                createProduct(newProduct);
            }
        });

        // Configurar otros botones para Leer, Actualizar y Eliminar
        // Similar a createButton pero llamando a los métodos correspondientes
    }

    private void loadProducts() {
        CompletableFuture<List<Product>> productsFuture = productDao.readProducts();
        productsFuture.thenAccept(products -> {
            // Actualizar el adaptador con la lista de productos
            productAdapter.setProducts(products);
        }).exceptionally(throwable -> {
            Toast.makeText(ProductActivity.this, "Error al cargar productos", Toast.LENGTH_SHORT).show();
            return null;
        });
    }

    private void createProduct(Product product) {
        CompletableFuture<Void> createFuture = productDao.createProduct(product);
        createFuture.thenRun(() -> {
            Toast.makeText(ProductActivity.this, "Producto creado", Toast.LENGTH_SHORT).show();
            loadProducts(); // Recargar los productos después de crear uno nuevo
        }).exceptionally(throwable -> {
            Toast.makeText(ProductActivity.this, "Error al crear producto", Toast.LENGTH_SHORT).show();
            return null;
        });
    }
}
