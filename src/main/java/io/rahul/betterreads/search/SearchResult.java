package io.rahul.betterreads.search;

import java.util.List;

public class SearchResult {

    public int numFound;

    public List<SearchResultBookContainer> docs;

    public int getNumFound() {
        return numFound;
    }

    public void setNumFound(int numFound) {
        this.numFound = numFound;
    }

    public List<SearchResultBookContainer> getDocs() {
        return docs;
    }

    public void setDocs(List<SearchResultBookContainer> docs) {
        this.docs = docs;
    }

}
