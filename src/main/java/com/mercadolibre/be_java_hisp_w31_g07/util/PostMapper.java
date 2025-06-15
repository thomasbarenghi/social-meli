package com.mercadolibre.be_java_hisp_w31_g07.util;

import com.mercadolibre.be_java_hisp_w31_g07.dto.request.PostDto;
import com.mercadolibre.be_java_hisp_w31_g07.dto.request.ProductDto;
import com.mercadolibre.be_java_hisp_w31_g07.dto.response.PostResponseDto;
import com.mercadolibre.be_java_hisp_w31_g07.model.Post;
import com.mercadolibre.be_java_hisp_w31_g07.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final GenericObjectMapper mapper;

    public Post fromPostDtoToPost(PostDto dto) {
        Post post = mapper.map(dto, Post.class);
        post.setGeneratedId(IdUtils.generateId());
        return post;
    }

    public PostResponseDto fromPostToPostResponseDto(Post post) {
        return mapper.map(post, PostResponseDto.class);
    }

    public List<PostResponseDto> fromPostListToPostResponseDtoList(List<Post> posts) {
        return mapper.mapList(posts, PostResponseDto.class);
    }
    
    public Product fromProductDtoToProduct(ProductDto dto) {
        Product product = mapper.map(dto, Product.class);
        product.setId(IdUtils.generateId());
        return product;
    }
}
