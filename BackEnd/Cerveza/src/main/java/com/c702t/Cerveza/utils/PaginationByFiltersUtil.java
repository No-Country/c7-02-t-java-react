package com.c702t.Cerveza.utils;

import com.c702t.Cerveza.models.entity.BusinessEntity;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

@Setter
public class PaginationByFiltersUtil {


    private Integer page;
    private static final Integer PAGE_NUMBER = 0;
    private Integer size;
    private static final Integer PAGE_SIZE = 3;
    private String path;
    private Page<?> pageObject;

    public PaginationByFiltersUtil(Specification<?> specification, JpaSpecificationExecutor repository, Optional<Integer> page, Optional<Integer> size, String path) {
        Pageable getPageWithSizeElements = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
        this.path = path;
        if (page.isPresent() && size.isPresent()) {
            getPageWithSizeElements = PageRequest.of(page.get() - 1, size.get());
            this.page = page.get();
            this.size = size.get();
        } else if (page.isPresent()) {
            getPageWithSizeElements = PageRequest.of(page.get() - 1, PAGE_SIZE);
            this.page = page.get();
            this.size = PAGE_SIZE;
        }
        else if (size.isPresent()) {
            getPageWithSizeElements = PageRequest.of(PAGE_NUMBER, size.get());
            this.size = size.get();
            this.page = PAGE_NUMBER+1;
        }
        else {
            this.size = PAGE_SIZE;
            this.page = PAGE_NUMBER+1;
        }
        this.pageObject = repository. findAll(specification,getPageWithSizeElements);




    }

    public Page<?> getPage() {

        return pageObject;
    }

    public String getPrevious() {
        if(pageObject.hasPrevious() && pageObject.hasContent()){
            return String.format(path, page-1, size);
        }
        return null;
    }

    public String getNext() {
        if(pageObject.hasNext()){
            return String.format(path, page+1, size);
        }
        return null;
    }


}
