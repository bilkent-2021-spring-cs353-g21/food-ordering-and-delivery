import React, { useCallback, useState } from "react";
import SiteHeader from "~/components/SiteHeader";
import MyBasket from "~/components/MyBasket";
import { search } from "~/Service/service";

import "../styles.css";

// import Swiper core and required modules
import SearchFilters from "./SearchFilter";
import SearchResults from "./SearchResults";

export default function Search() {
    const [searchResults, setSearchResults] = useState();

    const onFiltersChange = useCallback((data) => {
        search(data)
            .then((response) => {
                setSearchResults(response.data.data);
            })
            .catch((error) => {
                alert(error);
            });
    }, []);

    return (
        <>
            <SiteHeader />
            <SearchFilters onFiltersChange={onFiltersChange} keyword="a" />
            <SearchResults results={searchResults} />
            <MyBasket />
        </>
    );
}
