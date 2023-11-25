package com.aditya.BlogPost.model;

import java.util.List;

public class PostFilterAndSearch {
    private List<String> filter;
    private String sortBy;
    private String search;

    public List<String> getFilter() {
        return filter;
    }

    public void setFilter(List<String> filter) {
        this.filter = filter;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "PostFilterAndSearch{" +
                "filter=" + filter +
                ", sortBy='" + sortBy + '\'' +
                ", search='" + search + '\'' +
                '}';
    }
}
