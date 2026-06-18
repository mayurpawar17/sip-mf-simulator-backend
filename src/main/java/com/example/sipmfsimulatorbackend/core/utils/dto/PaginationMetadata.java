package com.example.sipmfsimulatorbackend.core.utils.dto;

public class PaginationMetadata {
    private int page;
    private int size;
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrevious;

    // Default Constructor
    public PaginationMetadata() {
    }

    // Parameterized Constructor for quick creation
    public PaginationMetadata(int page, int size, boolean isLast, boolean hasNext, boolean hasPrevious) {
        this.page = page;
        this.size = size;
        this.isLast = isLast;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    // Getters and Setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
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
