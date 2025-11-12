package com.hascode88.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Resposta paginada")
public class PageResponse<T> {

    @Schema(description = "Lista de itens da página atual")
    private List<T> content;

    @Schema(description = "Total de elementos em todas as páginas", example = "50")
    private long totalElements;

    @Schema(description = "Total de páginas", example = "5")
    private int totalPages;

    @Schema(description = "Página atual (baseado em zero)", example = "0")
    private int currentPage;

    @Schema(description = "Tamanho da página", example = "10")
    private int pageSize;

    @Schema(description = "Indica se existe próxima página", example = "true")
    private boolean hasNext;

    @Schema(description = "Indica se existe página anterior", example = "false")
    private boolean hasPrevious;

    public PageResponse() {
    }

    public PageResponse(List<T> content, long totalElements, int totalPages, int currentPage, int pageSize, boolean hasNext, boolean hasPrevious) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}

