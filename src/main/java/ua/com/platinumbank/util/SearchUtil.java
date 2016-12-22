package ua.com.platinumbank.util;

import static ua.com.platinumbank.util.JSONUtil.addressToJSONString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import ua.com.platinumbank.model.Address;

// TODO add javadoc
public class SearchUtil {

    /**
     * Parses {@link SearchResponse} and returns {@link String} with all
     * concatenated corresponding {@link Address} objects.
     *
     * @param searchResponse
     *            {@link SearchResponse} from ElasticSearch
     * @return {@link String} representing this search response with each search
     *         hit id and source fields
     */
    public static String searchResponseToString(SearchResponse searchResponse) {

        StringBuilder resultString = new StringBuilder();

        Map resultSourceMap;

        SearchHit[] hits = searchResponse.getHits()
                                         .getHits();

        for (int i = 0; i < hits.length; i++) {

            resultSourceMap = hits[i].sourceAsMap();

            Address address = new Address();

            String region = (String) resultSourceMap.get("region");
            String district = (String) resultSourceMap.get("district");
            String city = (String) resultSourceMap.get("city");
            String postIndex = (String) resultSourceMap.get("post_index");
            String street = (String) resultSourceMap.get("street");
            String house = (String) resultSourceMap.get("house");

            address.setRegion(region);
            address.setDistrict(district);
            address.setCity(city);
            address.setPostIndex(postIndex);
            address.setStreet(street);
            address.setHouse(house);

            resultString.append(addressToJSONString(address));
            resultString.append(System.lineSeparator());
        }

        return resultString.toString();
    }

    /**
     * Parses {@link SearchResponse} and returns {@link List} filled with
     * corresponding {@link Address} objects.
     *
     * @param searchResponse
     *            {@link SearchResponse} from ElasticSearch
     * @return {@link List} with {@link Address} objects
     */
    public static List<Address> searchResponseToList(SearchResponse searchResponse) {

        List<Address> resultList;

        Map resultSourceMap;

        SearchHit[] hits = searchResponse.getHits()
                                         .getHits();

        resultList = new ArrayList(hits.length);

        for (int i = 0; i < hits.length; i++) {

            resultSourceMap = hits[i].sourceAsMap();

            Address address = new Address();

            String region = (String) resultSourceMap.get("region");
            String district = (String) resultSourceMap.get("district");
            String city = (String) resultSourceMap.get("city");
            String postIndex = (String) resultSourceMap.get("post_index");
            String street = (String) resultSourceMap.get("street");
            String house = (String) resultSourceMap.get("house");

            address.setRegion(region);
            address.setDistrict(district);
            address.setCity(city);
            address.setPostIndex(postIndex);
            address.setStreet(street);
            address.setHouse(house);

            resultList.add(i, address);
        }

        return resultList;
    }

}
