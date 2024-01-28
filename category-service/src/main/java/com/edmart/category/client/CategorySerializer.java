package com.edmart.category.client;

import com.edmart.category.entity.Category;
import com.edmart.category.entity.SubCategory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CategorySerializer extends JsonSerializer<Category> {

    @Override
    public void serialize(Category category, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("categoryId", category.getCategoryId());
        jsonGenerator.writeStringField("categoryName", category.getCategoryName());
        jsonGenerator.writeStringField("categoryDescription", category.getCategoryDescription());
        jsonGenerator.writeStringField("categoryDesignation", category.getCategoryDesignation());

        // Handle subCategories manually
        jsonGenerator.writeArrayFieldStart("subCategories");
        for (SubCategory subCategory : category.getSubCategories()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("subCategoryId", subCategory.getSubCategoryId());
            jsonGenerator.writeStringField("name", subCategory.getName());
            jsonGenerator.writeStringField("description", subCategory.getDescription());
            // Add more fields as needed
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        // Add other fields if needed

        jsonGenerator.writeEndObject();
    }
}
