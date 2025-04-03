package com.dudu.dto.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class PageDto {
    @NotEmpty(message = "页码不能为空")
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private String currentPage;

    @NotEmpty(message = "分页大小不能为空")
    @ApiModelProperty(value = "分页大小", required = true, example = "10")
    private String pageSize;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
