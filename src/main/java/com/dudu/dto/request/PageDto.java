package com.dudu.dto.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;

public class PageDto {
    // @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小为1")
    @ApiModelProperty(value = "页码", example = "1")
    private Integer currentPage = 1;

    // @NotNull(message = "分页大小不能为空")
    @Min(value = 1, message = "分页大小最小为1")
    @ApiModelProperty(value = "分页大小", example = "10")
    private Integer pageSize = 10;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
